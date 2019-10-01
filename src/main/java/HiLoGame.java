import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class HiLoGame extends Application {

    private static final String INSTRUCTIONS = "Guess the hidden number between 1 and 100!";
    private static final String TOO_HIGH = "Your guess was too high!";
    private static final String TOO_LOW = "Your guess was too low";
    private static final String SUCCESS = "Lucky guess! You found the hidden number! \n Press Enter or click 'Submit'" +
            " to play again";

    private Button guessSubmissionButton;
    private Text guessResultText;
    private Random numberGenerator = new Random();
    private TextField guessEntryField;
    private int hiddenNumber;

    @Override
    public void start(Stage stage) throws Exception {

        // Instantiate instance variables
        guessEntryField = new TextField();
        guessSubmissionButton = new Button("Submit");
        guessSubmissionButton.setOnAction(this::guess);
        guessResultText = new Text();
        hiddenNumber = numberGenerator.nextInt();

        // Configure the pane being used to hold our nodes.
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(0, 10, 0, 10));
        pane.setGridLinesVisible(true);
        pane.setOnKeyPressed(this::guessEnter);

        // Add Game Description label to columns 1 - 4, row 1
        Label instructionLabel = new Label(INSTRUCTIONS);
        instructionLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 20));
        instructionLabel.setWrapText(true);
        pane.add(instructionLabel, 0, 0, 3, 1);

        // Add the result Text node to column 3, row 5
        guessResultText.setFont(Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        guessResultText.setText("Testing");
        pane.add(guessResultText, 0, 5, 1, 1);

        // Add the guessEntry node to column 1 -3, row 7
        guessEntryField.setText("Place your guess Here");
        guessEntryField.setAlignment(Pos.CENTER);
        pane.add(guessEntryField, 0, 7, 2, 1);

        guessSubmissionButton.setOnKeyPressed(this::guessEnter);
        pane.add(guessSubmissionButton, 2, 7, 1, 1);


        // Add the pane to the scene
        Scene sceneOne = new Scene(pane, 300, 300, Color.ALICEBLUE);
        guessResultText.setWrappingWidth(sceneOne.getWidth()/2);



        // Configure the state and add the scene.
        stage.setTitle("Number Guesser");
        stage.setScene(sceneOne);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void guess(Event e) {
        guessResultText.setText("It worked.");
        guessResultText.setFill(Color.color(numberGenerator.nextDouble(), numberGenerator.nextDouble(),
                numberGenerator.nextDouble(), numberGenerator.nextDouble()));
        try {
            int guessValue = Integer.parseInt(guessEntryField.getText());
            if(guessValue < hiddenNumber){
                guessResultText.setText(TOO_LOW);
            } else if (guessValue > hiddenNumber) {
                guessResultText.setText(TOO_HIGH);
            } else {
                guessResultText.setText(SUCCESS);
            }
        } catch (NumberFormatException formatException) {
            guessResultText.setText("Please enter a numeric value between 1 and 100");
        }
    }

    public void guessEnter(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            guess(new Event(Event.ANY));
        }
    }
}
