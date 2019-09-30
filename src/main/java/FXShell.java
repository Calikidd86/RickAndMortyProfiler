import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Random Quote Generator GUI
 *
 * @author Dante L. Anthony
 * @version 1.0.3
 */
public class FXShell extends Application {

    private Button button;
    private Text text;
    private List<String> quoteList;
    private Random random = new Random();

    @Override
    public void start(Stage stage) throws Exception {
        quoteList = new ArrayList<String>();
        quoteList.add("The best preparation for tomorrow is doing your best today.\n" +
                "H. Jackson Brown, Jr.\n");
        quoteList.add(" The supreme art of war is to subdue the enemy without fighting.\n" +
                "Sun Tzu ");
        quoteList.add(" Independence is happiness.\n" +
                "Susan B. Anthony ");

        button = new Button("Next Quote!");
        button.setLayoutY(160);
        button.setLayoutX(80);
        button.setOnAction(this::buttonAction);

        text = new Text();
        text.setLayoutY(20);
        text.setTextAlignment(TextAlignment.CENTER);

        Group root = new Group();
        root.getChildren().addAll(button, text);

        Pane pane = new FlowPane();
        pane.getChildren().addAll(root);
        Scene firstScene = new Scene(pane, 250, 200, Color.CYAN);
        text.wrappingWidthProperty().bind(firstScene.widthProperty().subtract(20));

        stage.setTitle("Quote Grabber");
        stage.setScene(firstScene);
        stage.show();
    }

    public void buttonAction(Event e){
        int quoteIndex = random.nextInt(quoteList.size());
        text.setText(quoteList.get(quoteIndex));
        text.setFill(Color.color(random.nextDouble(),
                random.nextDouble(),
                random.nextDouble(),
                random.nextDouble()));
        String fontName = Font.font(random.nextDouble()).toString();
        text.setFont(Font.font(fontName, 20));
    }

    public static void main(String[] args) {
        launch();
    }
}
