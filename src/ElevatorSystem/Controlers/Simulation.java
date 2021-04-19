package ElevatorSystem.Controlers;

import ElevatorSystem.Models.Constructions.Elevator;
import ElevatorSystem.Models.Constructions.ElevatorSystem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class Simulation {
    private ElevatorSystem elevatorSystem;
    private ExecutorService executorService;

    public Simulation(ElevatorSystem elevatorSystem){
        this.setElevatorSystem(elevatorSystem);
    }

    public void startSimulation(){
        System.out.println("Getting elevators going | main thread id:"+currentThread().getId());
        setExecutorService(Executors.newFixedThreadPool(getElevatorSystem().getElevators().size()));
        for(Elevator elevator: getElevatorSystem().getElevators()){
            getExecutorService().execute(elevator.getView());
        }
        System.out.println("All elevators are ready to serve!");
    }

    public  void stopSimulation(){

        getExecutorService().shutdown();
        try {
            if (!getExecutorService().awaitTermination(800, TimeUnit.MILLISECONDS)) {
                getExecutorService().shutdownNow();
            }
        } catch (InterruptedException e) {
            getExecutorService().shutdownNow();
        }
    }


    public ElevatorSystem getElevatorSystem() {
        return elevatorSystem;
    }

    public void setElevatorSystem(ElevatorSystem elevatorSystem) {
        this.elevatorSystem = elevatorSystem;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }
}
