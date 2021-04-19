package ElevatorSystem.Models.Constructions;

import ElevatorSystem.Models.Observer.Observer;
import ElevatorSystem.Models.Observer.PiceOfInformation;
import ElevatorSystem.Models.Physics.Direction;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ElevatorSystem implements Observer {
    private ArrayList<Elevator> elevators;
    private ArrayList<BuildingFloor> buildingFloors;
    double elevatorsSpeed;

    public  ElevatorSystem(int elevators,double elevatorsSpeed, ArrayList<BuildingFloor> buildingFloors){
        this.setElevators(new ArrayList<>(elevators));
        this.setBuildingFloors(buildingFloors);
        this.elevatorsSpeed=elevatorsSpeed;
        for(BuildingFloor floor:getBuildingFloors())
            floor.addObserver(this);
        for(int i=0;i<elevators;i++)
            this.getElevators().add(new Elevator(6,getBuildingFloors().get(0),i,elevatorsSpeed,getBuildingFloors()));

        System.out.println(elevators);
    }


    private void findElevatorToServe(BuildingFloor buildingFloor, Direction direction) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(getElevators().size());
        for(Elevator elevator: getElevators()){
            elevator.setTmpFloor(buildingFloor);
            executorService.execute(elevator);
        }

        executorService.shutdown();
        executorService.awaitTermination(10_000L, TimeUnit.MILLISECONDS );
        Elevator selectedElevator= getElevators().get(0);
        for(Elevator elevator: getElevators()){
            if(elevator.getSteps()==selectedElevator.getSteps() && elevator.getDirection()==direction)
                selectedElevator=elevator;
            else if(elevator.getSteps()<selectedElevator.getSteps())
                selectedElevator=elevator;
        }

        selectedElevator.serveNewFloor(buildingFloor);
    }

    public ArrayList<Elevator> getElevators() {
        return elevators;
    }

    public void setElevators(ArrayList<Elevator> elevators) {
        this.elevators = elevators;
    }

    @Override
    public void update(PiceOfInformation piceOfInformation) {
        try {
            this.findElevatorToServe(piceOfInformation.getFloor(),piceOfInformation.getDirection());
        } catch (InterruptedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Elevator system error!");
            alert.setHeaderText("Your request won't be served properly.");
            alert.show();
            e.printStackTrace();
        }
    }

    public ArrayList<BuildingFloor> getBuildingFloors() {
        return buildingFloors;
    }

    public void setBuildingFloors(ArrayList<BuildingFloor> buildingFloors) {
        this.buildingFloors = buildingFloors;
    }
}
