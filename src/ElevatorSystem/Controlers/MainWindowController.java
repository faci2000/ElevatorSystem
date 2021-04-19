package ElevatorSystem.Controlers;

import ElevatorSystem.Models.Constructions.ElevatedBuilding;

public class MainWindowController {
    private ElevatedBuilding building;
    private ControlPanelController controlPanel;
    public Simulation simulation;

    public MainWindowController(){
        System.out.println("Main window controller started... \nReading json data.");
        setBuilding(new ElevatedBuilding("parameters.json"));
        setControlPanel(new ControlPanelController(this.getBuilding(),280,720));
        simulation = new Simulation(building.getElevatorSystem());
        simulation.startSimulation();
    }

    public ElevatedBuilding getBuilding() {
        return building;
    }

    public void setBuilding(ElevatedBuilding building) {
        this.building = building;
    }

    public ControlPanelController getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(ControlPanelController controlPanel) {
        this.controlPanel = controlPanel;
    }
}
