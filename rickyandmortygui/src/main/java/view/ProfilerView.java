package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.apache.logging.log4j.util.PropertiesUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Profile viewer class implements ViewInterface. Primary View for Rick and Morty Profiler Application.
 */
public class ProfilerView implements ViewInterface {

    private static final Logger logger = Logger.getLogger(ProfilerView.class.getSimpleName());

    private ImageView imageView;
    private Image randomImage;
    private Text characterNameText;
    private Text characterSpeciesText;
    private Label nameLabel;
    private Label speciesLabel;
    private Scene primaryScene;

    private Group root;
    private VBox primaryPane;
    private GridPane characterDetailsPane;

    public ProfilerView() {
        setup();
    }

    @Override
    public void setStage(Stage stage) {
        stage.setScene(this.primaryScene);
    }

    @Override
    public Scene getScene() {
        return this.primaryScene;
    }

    @Override
    public ImageView getImageView() {
        return this.imageView;
    }

    @Override
    public Text getCharacterNameText() {
        return this.characterNameText;
    }

    @Override
    public Text getCharacterSpeciesText() {
        return this.characterSpeciesText;
    }

    @Override
    public Group getRoot() {
        return this.root;
    }

    @Override
    public VBox getPrimaryPane() {
        return this.primaryPane;
    }

    @Override
    public Image getImage() {
        return this.randomImage;
    }

    @Override
    public void setImage(Image image) {
        this.randomImage = image;
    }

    @Override
    public GridPane getCharacterDetailsPane() {
        return this.characterDetailsPane;
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
    private void configureScene() {
        // set up scene
        primaryScene = new Scene(root);
        primaryScene.setFill(Color.ANTIQUEWHITE);
    }

    private void setup(){
        configurePanes();
        configureScene();
        configureImageNode();
        configureTextNodes();
        configureCharacterDetailsPane();
        // Add primary pane to root. Otherwise the thing shows up blank!
        root.getChildren().add(primaryPane);
        // add CharacterDetailsPane to primaryPane
        primaryPane.getChildren().add(characterDetailsPane);
    }

    public static void main(String[] args) {
        String formatedDate = new SimpleDateFormat().format(new Date());
        System.out.println(formatedDate);
    }
}
