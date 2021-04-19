package ElevatorSystem.Models.Constructions;

import ElevatorSystem.Views.BuildingView;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Building {
    private ArrayList<BuildingFloor> buildingFloors;
    private int floorsNo;
    private BuildingView view;

    public  Building(int floorsNo){
        System.out.println("Started constructing building.");
        this.setFloorsNo(floorsNo);
        setBuildingFloors(new ArrayList<>(floorsNo));
        for(int i=0;i<floorsNo;i++)
            getBuildingFloors().add(new BuildingFloor(i));
        System.out.println("Building with "+ getFloorsNo() +" floors created.");
    }

    public  Building(String filePath){
        System.out.println("Started constructing building.");
        this.setFloorsNo((int) parseInitialValue(filePath,"floorsNo"));
        setBuildingFloors(new ArrayList<>(floorsNo));
        for(int i=0;i<floorsNo;i++)
            getBuildingFloors().add(new BuildingFloor(i));
        System.out.println("Building with "+ getFloorsNo() +" floors created.");

    }

    protected double parseInitialValue(String filePath,String value){
        double returnValue;
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader(filePath)){
            JSONObject initialValues = (JSONObject)parser.parse(reader);
            System.out.println(initialValues);
            try{
                returnValue = ((Double) initialValues.get(value)).intValue();
                System.out.println(returnValue);

            }catch (Exception e){
                e.printStackTrace();
                returnValue=1;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Given file with initial data does not exists.");
            returnValue=1;
            e.printStackTrace();
        } catch (IOException e) {
            returnValue=1;
            e.printStackTrace();
        } catch (ParseException e) {
            returnValue=1;
            System.out.println("Given file with initial data is not a valid json file.");
            e.printStackTrace();
        }
        return  returnValue;
    }

    public ArrayList<BuildingFloor> getBuildingFloors() {
        return buildingFloors;
    }

    public void setBuildingFloors(ArrayList<BuildingFloor> buildingFloors) {
        this.buildingFloors = buildingFloors;
    }

    public int getFloorsNo() {
        return floorsNo;
    }

    public void setFloorsNo(int floorsNo) {
        this.floorsNo = floorsNo;
    }

    public BuildingView getView() {
        return view;
    }

    public void setView(BuildingView view) {
        this.view = view;
    }
}
