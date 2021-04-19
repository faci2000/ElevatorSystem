package ElevatorSystem.Views;

import ElevatorSystem.Controlers.MainWindowController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainWindow extends Application {
    private MainWindowController mainWindowController;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        setMainWindowController(new MainWindowController());
        HBox hbox = new HBox();

        hbox.getChildren().add(getMainWindowController().getControlPanel().getView());
        hbox.getChildren().add(getMainWindowController().getBuilding().getView());

        Scene scene = new Scene(hbox,1080,720);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("Elevator system by faci2000");
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stopping simulation");
                getMainWindowController().getSimulation().stopSimulation();
            }
        });
    }

    @Override
    public void stop() throws Exception {
        //super.stop();

    }

    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}
