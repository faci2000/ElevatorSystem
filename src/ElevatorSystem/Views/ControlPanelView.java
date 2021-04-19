package ElevatorSystem.Views;

import ElevatorSystem.Controlers.ControlPanelController;
import ElevatorSystem.Models.Constructions.BuildingFloor;
import ElevatorSystem.Models.Observer.PiceOfInformation;
import ElevatorSystem.Models.Physics.Direction;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ControlPanelView extends VBox {
    private ControlPanelController controller;

    public ControlPanelView(ControlPanelController controller,double width,double height){
        super();
        this.setController(controller);
        this.setHeight(height);
        this.setWidth(width);
        for(int i=0;i<controller.getBuilding().getFloorsNo()*2;i++){
            if(i%2==0){
                this.getChildren().add(new Button("^"));
                System.out.println("Down button for floor no " + (controller.getBuilding().getFloorsNo()-i/2));
            }
            else{
                this.getChildren().add(new Button("v"));
                System.out.println("Up button for floor no " + (controller.getBuilding().getFloorsNo()-i/2));
            }
            double floor = (double) (controller.getBuilding().getFloorsNo()*2-i-1)/2;
            ((Button)this.getChildren().get(i)).setPrefHeight(this.getHeight()/controller.getBuilding().getFloorsNo()*2);
            ((Button)this.getChildren().get(i)).setPrefWidth(width);
            ((Button)this.getChildren().get(i)).setOnAction(value ->{
                controller.getBuilding().getBuildingFloors().get((int)Math.floor(floor))
                        .inform(new PiceOfInformation(controller.getBuilding().getBuildingFloors().get((int)Math.floor(floor)),
                                (floor==Math.ceil(floor))? Direction.DOWN:Direction.UP));

            });
        }

    }


    public ControlPanelController getController() {
        return controller;
    }

    public void setController(ControlPanelController controller) {
        this.controller = controller;
    }
}
