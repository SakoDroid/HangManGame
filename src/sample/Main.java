package sample;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException{
        primaryStage.setTitle("Hang Man");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("sample.fxml"))));
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
