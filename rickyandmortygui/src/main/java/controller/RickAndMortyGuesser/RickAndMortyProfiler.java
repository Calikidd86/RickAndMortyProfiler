package controller.RickAndMortyGuesser;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.ModelInterface;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import view.ProfilerView;
import view.ViewInterface;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Rick and Morty Profiler application Controller. 
 * @author Dante Anthony
 */
public class RickAndMortyProfiler extends Application implements ControllerInterface {

    private static final Logger logger = Logger.getLogger(RickAndMortyProfiler.class.getSimpleName());

    private static final String API_BASE = "https://rickandmortyapi.com/api/character/";
    private static final String IMAGE_BASE_URL = API_BASE + "avatar/";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private ViewInterface view;
    private ModelInterface model;

    private Random random = new Random();

    @Override
    public void start(Stage stage) throws Exception {

        this.view = new ProfilerView();
        view.getScene().setOnKeyPressed(this::enterKeyPressedAction);
        view.setStage(stage);

        grabRandomData(new ActionEvent());

        // Set up stage
        stage.setTitle("Who's that Rick and Morty Character?");
        stage.setScene(this.view.getScene());
        stage.setWidth(220);
        stage.setHeight(300);
        stage.setMaxWidth(340);
        stage.setMaxHeight(400);
        stage.setResizable(false);
        stage.show();
    }

    private void grabRandomData(Event e) {
        logger.log(Level.INFO, "Performing data grab.");
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

            view.getCharacterNameText().setText(name);
            view.getCharacterSpeciesText().setText(species);

            System.out.println(characterDetailsMap.get("name"));
            System.out.println(view.getCharacterNameText().toString());

            view.setImage(new Image(resultImageResponse.bodyStream()));
            view.getImageView().setImage(view.getImage());
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

    @Override
    public void setViewInterface(ViewInterface view) {
        this.view = view;
    }

    @Override
    public void setModelInterface(ModelInterface model) {
        this.model = model;
    }
}
