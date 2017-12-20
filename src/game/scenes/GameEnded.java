package game.scenes;

import game.essentials.StageSize;
import game.manage.SceneManager;
import game.manage.ScoreManager;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameEnded extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = FXMLLoader.load(getClass().getResource("../fxml/gameover.fxml"));
        Scene scene = new Scene(root, StageSize.width, StageSize.height);

        int finalScore = ScoreManager.getInstance().getScore();
        Text finalScoreText = new Text(0, 0, "SCORE: " + finalScore);
        ScoreManager.getInstance().writeToFile();
        ScoreManager.getInstance().resetScore();
        root.getChildren().add(finalScoreText);
        primaryStage.setScene(scene);
    }

    @FXML
    private void newGameButtonClicked() throws Exception {
        SceneManager.getInstance().getGameScene().start(game.scenes.Main.getPrimaryStage());
    }

    @FXML
    private void highScoresButtonClicked() throws Exception {
        SceneManager.getInstance().getHighScores().start(game.scenes.Main.getPrimaryStage());
    }

    @FXML
    private void mainMenuButtonClicked() throws Exception {
        SceneManager.getInstance().getMainMenu().start(game.scenes.Main.getPrimaryStage());
    }


}
