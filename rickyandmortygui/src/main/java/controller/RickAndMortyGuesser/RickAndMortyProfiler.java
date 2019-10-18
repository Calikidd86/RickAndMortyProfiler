package controller.RickAndMortyGuesser;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RickAndMortyProfiler extends Application {

    private static final Logger logger = Logger.getLogger(RickAndMortyProfiler.class.getSimpleName());

    private static final String API_BASE = "https://rickandmortyapi.com/api/character/";
    private static final String IMAGE_BASE_URL = API_BASE + "avatar/";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Random random = new Random();
    private ImageView imageView;
    private Image randomImage;
    private Text characterNameText;
    private Text characterSpeciesText;
    private Label nameLabel;
    private Label speciesLabel;

    private Group root;
    private VBox primaryPane;
    private GridPane characterDetailsPane;

    @Override
    public void start(Stage stage) throws Exception {

        // create root and base pane
        configurePanes();

        // Configure imageView
        configureImageNode();

        // Configure Labels and Text
        configureTextNodes();

        // Configure characterDetailsPane
        configureCharacterDetailsPane();


        // add CharacterDetailsPane to primaryPane
        primaryPane.getChildren().add(characterDetailsPane);

        // add nodes to root
        root.getChildren().add(primaryPane);

        // set up scene
        Scene primaryScene = new Scene(root);
        primaryScene.setOnKeyPressed(this::enterKeyPressedAction);
        primaryScene.setFill(Color.ANTIQUEWHITE);

        // grab initial image and character information
        grabRandomData(new ActionEvent());

        // Set up stage
        stage.setTitle("Who's that Ricky and Morty Character?");
        stage.setScene(primaryScene);
        stage.setWidth(220);
        stage.setHeight(300);
        stage.setMaxWidth(340);
        stage.setMaxHeight(400);
        stage.setResizable(false);
        stage.show();
    }

    private void configurePanes() {
        // create root and base pane
        root = new Group();
        primaryPane = new VBox();
        characterDetailsPane = new GridPane();
        logger.log(Level.INFO, "Configuring panes.");
    }
    private void configureImageNode() {
        imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setLayoutX(20);
        imageView.setY(50);
        imageView.setPreserveRatio(true);
        primaryPane.getChildren().add(imageView);
        logger.log(Level.INFO, "Configuring Text Nodes.");
    }
    private void configureTextNodes() {
        nameLabel = new Label("Name: ");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));
        speciesLabel = new Label("Species: ");
        speciesLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));

        characterNameText = new Text();
        characterNameText.setFont(Font.font("Comic Sans", FontWeight.NORMAL, FontPosture.ITALIC, 14));
        characterSpeciesText = new Text();
        characterSpeciesText.setFont(Font.font("Comic Sans", FontWeight.NORMAL, FontPosture.ITALIC, 14));
        characterSpeciesText.setTextAlignment(TextAlignment.CENTER);
        characterNameText.setTextAlignment(TextAlignment.CENTER);
        logger.log(Level.INFO, "Configuring Text Nodes.");
    }
    private void configureCharacterDetailsPane() {
        characterDetailsPane.add(nameLabel, 0, 0);
        characterDetailsPane.add(characterNameText, 1, 0);
        characterDetailsPane.add(speciesLabel, 0, 1);
        characterDetailsPane.add(characterSpeciesText, 1, 1);
        characterDetailsPane.setHgap(10);
        characterDetailsPane.setVgap(5);
        logger.log(Level.INFO, "configuring character details pane.");
    }

    private void grabRandomData(Event e) {
        int randomSearchNumber = random.nextInt(400) + 1;

        String imageUrl = IMAGE_BASE_URL + randomSearchNumber + ".jpeg";
        String characterApiUrl = API_BASE + randomSearchNumber;

        try {
            Connection.Response resultImageResponse = Jsoup.connect(imageUrl)
                    .ignoreContentType(true)
                    .execute();

            String characterDetailsResponse = Jsoup.connect(characterApiUrl)
                    .ignoreContentType(true)
                    .execute()
                    .body();

            Map<String, String> characterDetailsMap = MAPPER.readValue(characterDetailsResponse, Map.class);

            String name = characterDetailsMap.get("name");
            String species = characterDetailsMap.get("species");

            characterNameText.setText(name);
            characterSpeciesText.setText(species);

            System.out.println(characterDetailsMap.get("name"));

            randomImage = new Image(resultImageResponse.bodyStream());
            imageView.setImage(randomImage);
            logger.log(Level.INFO, "Performing data grab.");
        } catch (IOException exception) {
            logger.throwing("RickAndMorty Application", "getRandomData", exception);
            exception.printStackTrace();
        }

    }

    private void enterKeyPressedAction(KeyEvent e) {
        logger.log(Level.INFO, "Enter key pressed.");
        if (e.getCode() == KeyCode.ENTER) {
            grabRandomData(e);
        }
    }
}
