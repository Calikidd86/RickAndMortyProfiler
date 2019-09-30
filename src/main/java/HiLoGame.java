import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

public class HiLoGame extends Application {

    private static final String INSTRUCTIONS = "Guess the hidden number between 1 and 100!";
    private static final String TOO_HIGH = "Your guess was too high!";
    private static final String TOO_LOW = "Your guess was too low";

    Button guessSubmissionButton;
    Random numberGenerator = new Random();
    TextField guessEntryField;

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();

        Label instructionLabel = new Label(INSTRUCTIONS);
        instructionLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 20));
        instructionLabel.setWrapText(true);


        root.getChildren().add(instructionLabel);

        Pane pane = new BorderPane(instructionLabel);
        BorderPane.setAlignment(instructionLabel, Pos.TOP_CENTER);


        Scene sceneOne = new Scene(pane, 300, 300, Color.ALICEBLUE);

        stage.setTitle("Number Guesser");
        stage.setScene(sceneOne);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void guess(Event e){

    }
}
