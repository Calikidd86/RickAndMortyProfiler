import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Base extends Application {

    private Button button;

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();

        button = new Button("Hi");
        root.getChildren().addAll(button);
        Scene scene = new Scene(root, 300, 300, Color.OLIVEDRAB);

        stage.setTitle("Trial");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}