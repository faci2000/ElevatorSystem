package ElevatorSystem.Models.Constructions;


import ElevatorSystem.Views.BuildingView;

public class ElevatedBuilding extends Building {
    private ElevatorSystem elevatorSystem;

    public ElevatedBuilding(String filePath){
        super(filePath);
        System.out.println("Building with "+ getFloorsNo() +" floors created.");

        System.out.println("Started building elevating system in building.");
        System.out.println("ElevatorsSpeed: "+parseInitialValue(filePath,"elevatorsSpeed"));
        setElevatorSystem(new ElevatorSystem((int)parseInitialValue(filePath,"elevatorsNo"),
                                           0.5,
                                            getBuildingFloors()));
//        for(BuildingFloor floor:getBuildingFloors())
//            floor.addObserver(elevatorSystem);
        setView(new BuildingView(this));
    }

    public ElevatorSystem getElevatorSystem() {
        return elevatorSystem;
    }

    public void setElevatorSystem(ElevatorSystem elevatorSystem) {
        this.elevatorSystem = elevatorSystem;
    }
}
