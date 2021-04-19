package ElevatorSystem.Views;

import ElevatorSystem.Models.Constructions.Building;
import ElevatorSystem.Models.Constructions.ElevatedBuilding;
import ElevatorSystem.Models.Constructions.Elevator;
import javafx.scene.layout.HBox;

public class BuildingView extends HBox {
    private double floorHeight;

    public BuildingView(Building building){
        super();
        System.out.println("Creating building visualization.");
        this.setWidth(800);
        this.setHeight(720);
        this.setFloorHeight((double)720/building.getFloorsNo());
        if(building instanceof ElevatedBuilding){
            for(Elevator elevator:((ElevatedBuilding) building).getElevatorSystem().getElevators()){
                ElevatorView ev = new ElevatorView(elevator, this.getFloorHeight(),(double)800/((ElevatedBuilding) building).getElevatorSystem().getElevators().size(),720);
                this.getChildren().add(ev);
                elevator.setView(ev);
            }
        }
        System.out.println("Visualization created.");

    }

    public double getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(double floorHeight) {
        this.floorHeight = floorHeight;
    }
}
