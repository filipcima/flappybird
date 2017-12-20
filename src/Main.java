package game.scenes;

import game.essentials.StageSize;
import game.manage.SceneManager;
import game.essentials.MusicThread;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        VBox root = FXMLLoader.load(getClass().getResource("/game/fxml/menu.fxml"));
        primaryStage.setTitle("Flappy Bird");
        primaryStage.setScene(new Scene(root, StageSize.width, StageSize.height));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    @FXML
    private void playButtonClicked() throws Exception {
        SceneManager.getInstance().getGameScene().start(primaryStage);
        new MusicThread("resources/music/theme.mp3").run();
    }

    @FXML
    private void highScoresButtonClicked() throws Exception {
        SceneManager.getInstance().getHighScores().start(primaryStage);
    }

    @FXML
    private void quitButtonClicked() throws Exception {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
