package game.manage;

import game.scenes.Game;
import game.scenes.GameEnded;
import game.scenes.HighScores;
import game.scenes.Main;

public class SceneManager {
    private static SceneManager sceneManager = new SceneManager();
    public static SceneManager getInstance() { return sceneManager; };

    private Game gameScene;
    private GameEnded gameEndedScene;
    private Main mainMenuScene;
    private HighScores highScoresScene;

    private SceneManager() {
        this.gameScene = new Game();
        this.gameEndedScene = new GameEnded();
        this.mainMenuScene = new Main();
        this.highScoresScene = new HighScores();
    }

    public Game getGameScene() { return gameScene; }
    public GameEnded getGameEndedScene() { return gameEndedScene; }
    public Main getMainMenu() { return mainMenuScene; }
    public HighScores getHighScores() { return highScoresScene; }

}
