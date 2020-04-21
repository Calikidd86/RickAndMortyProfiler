package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;
import java.util.logging.Logger;

public interface ViewInterface {
    void setStage(Stage stage);

    Scene getScene();

    ImageView getImageView();

    Text getCharacterNameText();

    Text getCharacterSpeciesText();

    Group getRoot();

    VBox getPrimaryPane();

    Image getImage();
    void setImage(Image image);

    GridPane getCharacterDetailsPane();

}
