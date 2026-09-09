package psu.edu.se411;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Lab 04");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}