package ElevatorSystem.Models.Observer;

import ElevatorSystem.Models.Constructions.BuildingFloor;
import ElevatorSystem.Models.Physics.Direction;

public class PiceOfInformation {
    private BuildingFloor floor;
    private Direction direction;

    public PiceOfInformation(BuildingFloor floor,Direction direction){

        this.setFloor(floor);
        this.setDirection(direction);
    }

    public BuildingFloor getFloor() {
        return floor;
    }

    public void setFloor(BuildingFloor floor) {
        this.floor = floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
