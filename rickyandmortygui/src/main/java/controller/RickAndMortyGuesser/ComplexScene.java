package controller.RickAndMortyGuesser;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ComplexScene extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene primaryScene = new Scene(root);

        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
}
