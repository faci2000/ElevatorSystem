package ElevatorSystem.Models.Observer;

public interface Observable {
    void inform(PiceOfInformation piceOfInformation);
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
}
