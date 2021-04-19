package ElevatorSystem.Controlers;

import ElevatorSystem.Models.Constructions.Elevator;
import ElevatorSystem.Models.Constructions.ElevatorSystem;
import ElevatorSystem.Views.ElevatorView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class Simulation {
    ElevatorSystem elevatorSystem;
    ExecutorService executorService;

    public Simulation(ElevatorSystem elevatorSystem){
        this.elevatorSystem=elevatorSystem;
    }

    public void startSimulation(){
        System.out.println("Getting elevators going | main thread id:"+currentThread().getId());
        executorService = Executors.newFixedThreadPool(elevatorSystem.getElevators().size());
        for(Elevator elevator:elevatorSystem.getElevators()){
            executorService.execute(elevator.getView());
        }
        System.out.println("All elevators are ready to serve!");
    }

    public  void stopSimulation(){

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }


}
