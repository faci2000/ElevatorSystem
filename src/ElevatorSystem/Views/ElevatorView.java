package ElevatorSystem.Views;

import ElevatorSystem.Models.Constructions.Elevator;
import ElevatorSystem.Models.Physics.Direction;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class ElevatorView extends Pane implements  Runnable {
    private Elevator elevator;
    private double floorHeight;
    private double y;
    private Rectangle elevatorRepresentation;
    private Timeline elevatorLife;


    public ElevatorView(Elevator elevator,double floorHeight,double width,double height){
        super();
        this.setElevator(elevator);
        this.setFloorHeight(floorHeight);
        this.setWidth(width);
        this.setHeight(height);
        this.setY(((height/floorHeight)-elevator.getCurrentFloor().getFloorNo()-1)*floorHeight);
        System.out.println(elevator.getElevatorNo()+ ": x:"+elevator.getElevatorNo()*width+" y:"+ getY());
        setElevatorRepresentation(new Rectangle(0,
                ((height/floorHeight)-elevator.getCurrentFloor().getFloorNo()-1)*floorHeight,
                width,floorHeight));
        getElevatorRepresentation().setFill(Color.BLACK);
        getElevatorRepresentation().setStrokeType(StrokeType.INSIDE);
        getElevatorRepresentation().setStroke(Color.DEEPPINK);
        this.getChildren().add(getElevatorRepresentation());
    }

    @Override
    public void run() {
        System.out.println("Starting elevator no:"+ elevator.getElevatorNo()+" in thread no:"+getId());
//        setElevatorLife(new Timeline());
//        getElevatorLife().getKeyFrames().add(
//                new KeyFrame(Duration.seconds(0.1),e->{
//                    move();
//                })
//        );
//        getElevatorLife().setCycleCount(Timeline.INDEFINITE);
//        getElevatorLife().play();
        while (true){
            move();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                System.out.println("Elevator no:"+ elevator.getElevatorNo()+" stopped!");
                break;
            }
        }
    }

    private  void move(){
        //System.out.println("Moving elevator no:" +elevator.getElevatorNo() +" => "+elevator.getFloorsToServeSystem() + " => "+ elevator.getDirection());
        if(elevator.getDirection()== Direction.UP){
            this.setY(this.getY() - getElevator().getElevatorSpeed()*0.05*floorHeight);
            getElevatorRepresentation().setY(this.getY());
            //System.out.println("Speed:"+getElevator().getElevatorSpeed()+" Changed:"+getElevator().getElevatorSpeed()*0.05*floorHeight+" y:"+getElevatorRepresentation().getY());
        }else if(elevator.getDirection()==Direction.DOWN){
            this.setY(this.getY() + getElevator().getElevatorSpeed()*0.05*floorHeight);
            getElevatorRepresentation().setY(this.getY());
        }
        if((this.getHeight()- this.getY())% getFloorHeight() <=(720/(this.getHeight()/ getFloorHeight())*0.06)){
            this.getElevator().popPeopleFromElevator((int)(((this.getHeight()- this.getY())-(this.getHeight()- this.getY())% getFloorHeight())/ getFloorHeight())-1);
        }
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public double getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(double floorHeight) {
        this.floorHeight = floorHeight;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Rectangle getElevatorRepresentation() {
        return elevatorRepresentation;
    }

    public void setElevatorRepresentation(Rectangle elevatorRepresentation) {
        this.elevatorRepresentation = elevatorRepresentation;
    }

    public Timeline getElevatorLife() {
        return elevatorLife;
    }

    public void setElevatorLife(Timeline elevatorLife) {
        this.elevatorLife = elevatorLife;
    }
}
