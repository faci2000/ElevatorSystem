package ElevatorSystem.Controlers;

import ElevatorSystem.Models.Constructions.ElevatedBuilding;
import ElevatorSystem.Models.Physics.Direction;
import ElevatorSystem.Views.ControlPanelView;

public class ControlPanelController {
    private ControlPanelView view;
    private ElevatedBuilding building;


    public ControlPanelController(ElevatedBuilding building,double width,double height){
        this.setBuilding(building);
        setView(new ControlPanelView(this,width,height));
    }



    public ElevatedBuilding getBuilding() {
        return building;
    }

    public void setBuilding(ElevatedBuilding building) {
        this.building = building;
    }

    public ControlPanelView getView() {
        return view;
    }

    public void setView(ControlPanelView view) {
        this.view = view;
    }
}
