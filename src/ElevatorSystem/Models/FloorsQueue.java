package ElevatorSystem.Models;

import ElevatorSystem.Models.Constructions.BuildingFloor;
import ElevatorSystem.Models.Constructions.Elevator;
import ElevatorSystem.Models.Physics.Direction;

import java.util.*;

public class FloorsQueue implements Queue<BuildingFloor> {
    LinkedList<BuildingFloor> floorArrayList;
    Elevator elevator;

    public FloorsQueue(Elevator elevator){
        floorArrayList = new LinkedList<>();
        this.elevator=elevator;
    }

    @Override
    public int size() {
        return floorArrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return floorArrayList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return floorArrayList.contains(o);
    }

    @Override
    public Iterator<BuildingFloor> iterator() {
        return floorArrayList.iterator();
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
        if(elevator.getDirection()== Direction.UP){
            Iterator<BuildingFloor> iterator = this.iterator();
            while(iterator.hasNext()){
                BuildingFloor goalFloor = iterator.next();
                if(elevator.getCurrentFloor().compareTo(floor)<0){// current floor < new floor
                    if(floor.compareTo(goalFloor)<0){
                        floorArrayList.add(floorArrayList.indexOf(goalFloor),goalFloor);
                        return true;
                    }else if (elevator.getCurrentFloor().compareTo(goalFloor)>0){// goalFloor < current floor
                        floorArrayList.add(floorArrayList.indexOf(goalFloor),goalFloor);
                        return true;
                    }else if (floor.compareTo(goalFloor)==0){
                        return true;
                    }
                }else {// current floor > new floor
                    if (floor.compareTo(goalFloor) > 0) {
                        floorArrayList.add(floorArrayList.indexOf(goalFloor), goalFloor);
                        return true;
                    }else if(floor.compareTo(goalFloor) == 0){
                        return true;
                    }
                }
            }
        }else if (elevator.getDirection()==Direction.DOWN){
            Iterator<BuildingFloor> iterator = this.iterator();
            while(iterator.hasNext()){
                BuildingFloor goalFloor = iterator.next();
                if(elevator.getCurrentFloor().compareTo(floor)>0){// current floor > new floor
                    if(floor.compareTo(goalFloor)>0){
                        floorArrayList.add(floorArrayList.indexOf(goalFloor),goalFloor);
                        return true;
                    }else if (elevator.getCurrentFloor().compareTo(goalFloor)<0){// goalFloor < current floor
                        floorArrayList.add(floorArrayList.indexOf(goalFloor),goalFloor);
                        return true;
                    }else if(floor.compareTo(goalFloor) == 0){
                        return true;
                    }
                }else {// current floor < new floor
                    if (floor.compareTo(goalFloor) < 0) {
                        floorArrayList.add(floorArrayList.indexOf(goalFloor), goalFloor);
                        return true;
                    }else if(floor.compareTo(goalFloor) == 0){
                        return true;
                    }
                }
            }
        }
        return floorArrayList.add(floor);

    }

    @Override
    public boolean remove(Object o) {
        return floorArrayList.remove(o);
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

        return floorArrayList.remove(0);
    }

    @Override
    public BuildingFloor element() {
        return null;
    }

    @Override
    public BuildingFloor peek() {
        if(floorArrayList.size()>0)
            return floorArrayList.get(0);
        else
            return null;
    }

    @Override
    public String toString() {
        if(floorArrayList.size()>0)
            return String.valueOf(floorArrayList.get(0).getFloorNo());
        else
            return "empty";
    }
}
