package ElevatorSystem.Models.Constructions;

import ElevatorSystem.Models.Observer.Observable;
import ElevatorSystem.Models.Observer.Observer;
import ElevatorSystem.Models.Observer.PiceOfInformation;
import ElevatorSystem.Models.Physics.Direction;

import java.util.ArrayList;
import java.util.EnumMap;

public class BuildingFloor implements Comparable, Observable {
    private int peopleWaiting;
    private EnumMap<Direction,Integer> requests;
    private int floorNo;
    ArrayList<Observer> observers;
    public BuildingFloor(int floorNo, Observer observer){
        setPeopleWaiting(0);
        this.setRequests(new EnumMap<>(Direction.class));
        this.getRequests().put(Direction.UP,0);
        this.getRequests().put(Direction.DOWN,0);
        this.setFloorNo(floorNo);
        this.observers = new ArrayList<>();
        addObserver(observer);
        System.out.println("Built floor no " + floorNo + ".");
    }
    public BuildingFloor(int floorNo){
        setPeopleWaiting(0);
        this.setRequests(new EnumMap<>(Direction.class));
        this.getRequests().put(Direction.UP,0);
        this.getRequests().put(Direction.DOWN,0);
        this.setFloorNo(floorNo);
        this.observers = new ArrayList<>();
        System.out.println("Built floor no " + floorNo + ".");
    }

    @Override
    public int compareTo(Object o) {
        return this.getFloorNo() - ((BuildingFloor) o).getFloorNo();
    }

    public int getPeopleWaiting() {
        return peopleWaiting;
    }

    public void setPeopleWaiting(int peopleWaiting) {
        this.peopleWaiting = peopleWaiting;
    }

    public EnumMap<Direction, Integer> getRequests() {
        return requests;
    }

    public void setRequests(EnumMap<Direction, Integer> requests) {
        this.requests = requests;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    @Override
    public void inform(PiceOfInformation piceOfInformation) {
        for(Observer observer:observers)
            observer.update(piceOfInformation);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public String toString() {
        return "Floor no."+getFloorNo();
    }
}
