import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
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
    private static final String TOO_LOW = "Your guess was too low!";
    private static final String SUCCESS = "Lucky guess! You found the hidden number! \n Press Enter or click 'Submit'" +
            " to play again";
    private static final String NUMBER_ERROR = "Please enter a numeric value between 1 and 100";

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
        hiddenNumber = numberGenerator.nextInt(100) + 1;

        // Configure the pane being used to hold our nodes.
        VBox pane = new VBox();
        pane.setPadding(new Insets(0, 10, 0, 10));
        pane.setSpacing(10);

        // Add Game Description label to columns 1 - 4, row 1
        Label instructionLabel = new Label(INSTRUCTIONS);
        instructionLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 20));
        instructionLabel.setWrapText(true);
        pane.getChildren().add(instructionLabel);

        // Add the result Text node to column 3, row 5
        guessResultText.setFont(Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        guessResultText.setText("");
        pane.getChildren().add(guessResultText);

        // Guess Entry Field
        guessEntryField.setAlignment(Pos.CENTER);
        pane.getChildren().add(guessEntryField);
        guessEntryField.setOnAction(this::processTextField);

        // Submission button
        guessSubmissionButton.setOnKeyPressed(this::guess);
        pane.getChildren().add(guessSubmissionButton);


        // Add the pane to the scene
        Scene sceneOne = new Scene(pane, 250, 200, Color.ALICEBLUE);
        guessResultText.setWrappingWidth(sceneOne.getWidth() / 2);

        // Configure the state and add the scene.
        stage.setResizable(false);
        stage.setTitle("Number Guesser");
        stage.setScene(sceneOne);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void guess(Event e) {
        guessResultText.setFill(Color.OLIVEDRAB);
        try {
            int guessValue = Integer.parseInt(guessEntryField.getText());
            guessEntryField.setText("");
            if(guessValue > 100){
                throw new NumberFormatException();
            }

            if (guessValue < hiddenNumber) {
                guessResultText.setText(TOO_LOW + "\n\nPrevious Guess: " + guessValue);
            } else if (guessValue > hiddenNumber) {
                guessResultText.setText(TOO_HIGH + "\n\nPrevious Guess: " + guessValue);
            } else {
                guessResultText.setText(SUCCESS);
                hiddenNumber = numberGenerator.nextInt(100) + 1;
            }
        } catch(NumberFormatException formatException) {
            guessResultText.setText(NUMBER_ERROR);
            guessEntryField.setText("");
        }
    }

    private void processTextField(ActionEvent event){
        guess(event);
    }
//    public void guessEnter(KeyEvent e) {
//        if (e.getCode() == KeyCode.ENTER) {
//            guess(new Event(Event.ANY));
//        }
//    }
}
