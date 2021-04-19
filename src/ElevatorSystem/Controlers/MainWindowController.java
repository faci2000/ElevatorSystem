package ElevatorSystem.Controlers;

import ElevatorSystem.Models.Constructions.ElevatedBuilding;

public class MainWindowController {
    private ElevatedBuilding building;
    private ControlPanelController controlPanel;
    private Simulation simulation;

    public MainWindowController(){
        System.out.println("Main window controller started... \nReading json data.");
        setBuilding(new ElevatedBuilding("parameters.json"));
        setControlPanel(new ControlPanelController(this.getBuilding(),280,720));
        setSimulation(new Simulation(building.getElevatorSystem()));
        getSimulation().startSimulation();
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

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
