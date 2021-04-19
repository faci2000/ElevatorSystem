package ElevatorSystem.Models;

import ElevatorSystem.Models.Constructions.BuildingFloor;
import ElevatorSystem.Models.Constructions.Elevator;
import ElevatorSystem.Models.Physics.Direction;

import java.util.*;

public class FloorsQueue implements Queue<BuildingFloor> {
    private LinkedList<BuildingFloor> floorArrayList;
    private Elevator elevator;

    public FloorsQueue(Elevator elevator){
        setFloorArrayList(new LinkedList<>());
        this.setElevator(elevator);
    }

    @Override
    public int size() {
        return getFloorArrayList().size();
    }

    @Override
    public boolean isEmpty() {
        return getFloorArrayList().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return getFloorArrayList().contains(o);
    }

    @Override
    public Iterator<BuildingFloor> iterator() {
        return getFloorArrayList().iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(BuildingFloor floor) {
        if(getElevator().getDirection()== Direction.UP){
            Iterator<BuildingFloor> iterator = this.iterator();
            while(iterator.hasNext()){
                BuildingFloor goalFloor = iterator.next();
                if(getElevator().getCurrentFloor().compareTo(floor)<0){// current floor < new floor
                    if(floor.compareTo(goalFloor)<0){
                        getFloorArrayList().add(getFloorArrayList().indexOf(goalFloor),goalFloor);
                        return true;
                    }else if (getElevator().getCurrentFloor().compareTo(goalFloor)>0){// goalFloor < current floor
                        getFloorArrayList().add(getFloorArrayList().indexOf(goalFloor),goalFloor);
                        return true;
                    }else if (floor.compareTo(goalFloor)==0){
                        return true;
                    }
                }else {// current floor > new floor
                    if (floor.compareTo(goalFloor) > 0) {
                        getFloorArrayList().add(getFloorArrayList().indexOf(goalFloor), goalFloor);
                        return true;
                    }else if(floor.compareTo(goalFloor) == 0){
                        return true;
                    }
                }
            }
        }else if (getElevator().getDirection()==Direction.DOWN){
            Iterator<BuildingFloor> iterator = this.iterator();
            while(iterator.hasNext()){
                BuildingFloor goalFloor = iterator.next();
                if(getElevator().getCurrentFloor().compareTo(floor)>0){// current floor > new floor
                    if(floor.compareTo(goalFloor)>0){
                        getFloorArrayList().add(getFloorArrayList().indexOf(goalFloor),goalFloor);
                        return true;
                    }else if (getElevator().getCurrentFloor().compareTo(goalFloor)<0){// goalFloor < current floor
                        getFloorArrayList().add(getFloorArrayList().indexOf(goalFloor),goalFloor);
                        return true;
                    }else if(floor.compareTo(goalFloor) == 0){
                        return true;
                    }
                }else {// current floor < new floor
                    if (floor.compareTo(goalFloor) < 0) {
                        getFloorArrayList().add(getFloorArrayList().indexOf(goalFloor), goalFloor);
                        return true;
                    }else if(floor.compareTo(goalFloor) == 0){
                        return true;
                    }
                }
            }
        }
        return getFloorArrayList().add(floor);

    }

    @Override
    public boolean remove(Object o) {
        return getFloorArrayList().remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends BuildingFloor> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(BuildingFloor floor) {
        return false;
    }

    @Override
    public BuildingFloor remove() {
        return null;
    }

    @Override
    public BuildingFloor poll() {

        return getFloorArrayList().remove(0);
    }

    @Override
    public BuildingFloor element() {
        return null;
    }

    @Override
    public BuildingFloor peek() {
        if(getFloorArrayList().size()>0)
            return getFloorArrayList().get(0);
        else
            return null;
    }

    @Override
    public String toString() {
        if(getFloorArrayList().size()>0)
            return String.valueOf(getFloorArrayList().get(0).getFloorNo());
        else
            return "empty";
    }

    public LinkedList<BuildingFloor> getFloorArrayList() {
        return floorArrayList;
    }

    public void setFloorArrayList(LinkedList<BuildingFloor> floorArrayList) {
        this.floorArrayList = floorArrayList;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }
}
