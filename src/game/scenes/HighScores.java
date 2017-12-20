package game.scenes;

import game.essentials.StageSize;
import game.manage.SceneManager;
import game.manage.ScoreManager;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class HighScores extends Application {

    @FXML
    Label firstPlace;

    @FXML
    Label secondPlace;

    @FXML
    Label thirdPlace;

    @FXML
    private void initialize() {
        ScoreManager sm = ScoreManager.getInstance();
        if (!sm.getScores().isEmpty()) {
            firstPlace.setText("" + sm.getScores().get(0));
            secondPlace.setText("" + sm.getScores().get(1));
            thirdPlace.setText("" + sm.getScores().get(2));
        } else if (sm.getScores().size() == 2) {
            firstPlace.setText("" + sm.getScores().get(0));
            secondPlace.setText("" + sm.getScores().get(1));
        } else if (sm.getScores().size() == 1) {
            firstPlace.setText("" + sm.getScores().get(0));
        }

    }

    @FXML
    private void mainMenuButtonClicked() throws Exception {
        SceneManager.getInstance().getMainMenu().start(game.scenes.Main.getPrimaryStage());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = FXMLLoader.load(getClass().getResource("../fxml/highscores.fxml"));
        Scene scene = new Scene(root, StageSize.width, StageSize.height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
