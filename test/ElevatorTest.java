import ElevatorSystem.Controlers.Simulation;
import ElevatorSystem.Models.Constructions.Building;
import ElevatorSystem.Models.Constructions.Elevator;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ElevatorTest{
    Building testBuilding = new Building(10);
    Elevator testElevator = new Elevator(6,testBuilding.getBuildingFloors().get(0),0,0.5,testBuilding.getBuildingFloors());

    @Test
    public void elevatorGetStepsTest(){
        assertEquals(5,testElevator.getStepsToFloor(testBuilding.getBuildingFloors().get(5)));
        assertEquals(0,testElevator.getStepsToFloor(testBuilding.getBuildingFloors().get(0)));
        assertEquals(9,testElevator.getStepsToFloor(testBuilding.getBuildingFloors().get(9)));
        testElevator.serveNewFloor(testBuilding.getBuildingFloors().get(5));
        assertEquals(3,testElevator.getStepsToFloor(testBuilding.getBuildingFloors().get(3)));
        testElevator.serveNewFloor(testBuilding.getBuildingFloors().get(9));
        testElevator.popPeopleFromElevator(4);
        assertEquals(10,testElevator.getStepsToFloor(testBuilding.getBuildingFloors().get(3)));
    }

    @Test
    public void servingFloorsTest(){
        testElevator.serveNewFloor(testBuilding.getBuildingFloors().get(5));
        assertFalse(testElevator.getFloorsToServeSystem().isEmpty());
        testElevator.popPeopleFromElevator(5);
        assertTrue(testElevator.getFloorsToServeSystem().isEmpty());
    }
}