package ElevatorSystem.Models.Constructions;

import ElevatorSystem.Controlers.Simulation;
import ElevatorSystem.Models.FloorsQueue;
import ElevatorSystem.Models.Physics.Direction;
import ElevatorSystem.Views.ElevatorView;

import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Thread.currentThread;

public class Elevator implements  Runnable {
    private int capacity;
    private int currentPeopleNo;
    private FloorsQueue floorsToServeSystem;
    private FloorsQueue floorsToServePeople;
    private Direction direction;
    private BuildingFloor currentBuildingFloor;
    private BuildingFloor tmpFloor;
    private int steps;
    private int elevatorNo;
    private ElevatorView view;
    private double elevatorSpeed;
    private ArrayList<BuildingFloor> buildingFloors;
    private Simulation simulation;


    public Elevator(int capacity, BuildingFloor startingBuildingFloor, int elevatorNo, double elevatorSpeed, ArrayList<BuildingFloor> buildingFloors){
        setCurrentFloor(startingBuildingFloor);
        this.setElevatorNo(elevatorNo);
        this.setCapacity(capacity);
        this.setCurrentPeopleNo(0);
        this.setElevatorSpeed(elevatorSpeed);
        this.setFloorsToServeSystem(new FloorsQueue(this));
        setDirection(Direction.NONE);
        this.setBuildingFloors(buildingFloors);
    }

    public int[] getStatus(){
        return new int[]{getElevatorNo(), this.getCurrentFloor().getFloorNo(), this.getFloorsToServeSystem().poll().getFloorNo()};
    }

    public int getStepsToFloor(BuildingFloor buildingFloor){
        BuildingFloor maxBuildingFloor = this.getCurrentFloor();
        BuildingFloor minBuildingFloor = this.getCurrentFloor();

        if(this.getDirection()==Direction.UP){
            Iterator<BuildingFloor> iterator = this.getFloorsToServeSystem().iterator();
            while(iterator.hasNext()){
                BuildingFloor goalBuildingFloor = iterator.next();
                if(this.getCurrentFloor().compareTo(buildingFloor)<0){// current floor < new floor
                    return buildingFloor.getFloorNo() - this.getCurrentFloor().getFloorNo();
                }else {// current floor > new floor
                    if(goalBuildingFloor.compareTo(maxBuildingFloor)>0)
                        maxBuildingFloor = goalBuildingFloor;
                    if (buildingFloor.compareTo(goalBuildingFloor) > 0) {
                        return maxBuildingFloor.getFloorNo() - buildingFloor.getFloorNo();
                    }
                }
            }
        }else if (this.getDirection()==Direction.DOWN){
            Iterator<BuildingFloor> iterator = this.getFloorsToServeSystem().iterator();
            while(iterator.hasNext()){
                BuildingFloor goalBuildingFloor = iterator.next();
                if(this.getCurrentFloor().compareTo(buildingFloor)>0){// current floor > new floor
                    return this.getCurrentFloor().getFloorNo() - buildingFloor.getFloorNo();
                }else {// current floor < new floor
                    if(goalBuildingFloor.compareTo(minBuildingFloor)<0)
                        minBuildingFloor = goalBuildingFloor;
                    if (buildingFloor.compareTo(goalBuildingFloor) < 0) {
                        return buildingFloor.getFloorNo() - minBuildingFloor.getFloorNo();
                    }
                }
            }
        }
        return Math.abs(this.getCurrentFloor().getFloorNo() - buildingFloor.getFloorNo());
    }

    public void run(){
        this.setSteps(getStepsToFloor(getTmpFloor()));
        System.out.println("Elevator no:"+ this.getElevatorNo() + "->" + getSteps());
    }

    public void serveNewFloor(BuildingFloor floor){
        System.out.println("!!! Elevator no:" + getElevatorNo() + " received request to serve floor no:"+floor.getFloorNo()+" |thread id:"+currentThread().getId());
        getFloorsToServeSystem().add(floor);
        if(getDirection()==Direction.NONE){
            setDirection((getFloorsToServeSystem().peek().getFloorNo()>getCurrentFloor().getFloorNo())?Direction.UP:Direction.DOWN);
        }
    }

    public void popPeopleFromElevator(int floorNo){
        //System.out.println("Elevator no:"+getElevatorNo()+" on floor:"+floorNo+"-> "+getView().getY());
        setCurrentFloor(getBuildingFloors().stream()
                                        .filter(floor -> floorNo==floor.getFloorNo())
                                        .findFirst()
                                        .get());

        if(getFloorsToServeSystem().peek()!=null && getFloorsToServeSystem().peek().getFloorNo()==floorNo){
            getFloorsToServeSystem().poll();
            if(getFloorsToServeSystem().peek()!=null)
                setDirection((getFloorsToServeSystem().peek().getFloorNo()>getCurrentFloor().getFloorNo())?Direction.UP:Direction.DOWN);
            else
                setDirection(Direction.NONE);
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentPeopleNo() {
        return currentPeopleNo;
    }

    public void setCurrentPeopleNo(int currentPeopleNo) {
        this.currentPeopleNo = currentPeopleNo;
    }



    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public BuildingFloor getCurrentFloor() {
        return getCurrentBuildingFloor();
    }

    public void setCurrentFloor(BuildingFloor currentBuildingFloor) {
        this.setCurrentBuildingFloor(currentBuildingFloor);
    }

    public BuildingFloor getTmpFloor() {
        return tmpFloor;
    }

    public void setTmpFloor(BuildingFloor tmpFloor) {
        this.tmpFloor = tmpFloor;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public ElevatorView getView() {
        return view;
    }

    public void setView(ElevatorView view) {
        this.view = view;
    }

    public int getElevatorNo() {
        return elevatorNo;
    }

    public void setElevatorNo(int elevatorNo) {
        this.elevatorNo = elevatorNo;
    }

    public double getElevatorSpeed() {
        return elevatorSpeed;
    }

    public void setElevatorSpeed(double elevatorSpeed) {
        this.elevatorSpeed = elevatorSpeed;
    }

    public FloorsQueue getFloorsToServeSystem() {
        return floorsToServeSystem;
    }

    public void setFloorsToServeSystem(FloorsQueue floorsToServeSystem) {
        this.floorsToServeSystem = floorsToServeSystem;
    }

    public FloorsQueue getFloorsToServePeople() {
        return floorsToServePeople;
    }

    public void setFloorsToServePeople(FloorsQueue floorsToServePeople) {
        this.floorsToServePeople = floorsToServePeople;
    }

    public BuildingFloor getCurrentBuildingFloor() {
        return currentBuildingFloor;
    }

    public void setCurrentBuildingFloor(BuildingFloor currentBuildingFloor) {
        this.currentBuildingFloor = currentBuildingFloor;
    }

    public ArrayList<BuildingFloor> getBuildingFloors() {
        return buildingFloors;
    }

    public void setBuildingFloors(ArrayList<BuildingFloor> buildingFloors) {
        this.buildingFloors = buildingFloors;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
