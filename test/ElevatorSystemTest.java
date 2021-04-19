import ElevatorSystem.Models.Constructions.Building;
import ElevatorSystem.Models.Constructions.Elevator;
import ElevatorSystem.Models.Constructions.ElevatorSystem;
import ElevatorSystem.Models.Physics.Direction;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ElevatorSystemTest {
    Building testBuilding = new Building(10);
    ElevatorSystem testElevatorSystem = new ElevatorSystem(5,0.5,testBuilding.getBuildingFloors());

    @Test
    public void closestElevatorTest() throws InterruptedException {
        assertEquals(5,testElevatorSystem.findElevatorToServe(testBuilding.getBuildingFloors().get(5), Direction.UP ));
        assertEquals(0,testElevatorSystem.findElevatorToServe(testBuilding.getBuildingFloors().get(0), Direction.UP ));
        assertEquals(9,testElevatorSystem.findElevatorToServe(testBuilding.getBuildingFloors().get(9), Direction.UP ));
    }
}
