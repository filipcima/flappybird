package game.scenes;

import game.animations.BirdAnimation;
import game.manage.SceneManager;
import game.essentials.MusicThread;
import game.essentials.StageSize;
import game.manage.ScoreManager;
import game.sprites.Bird;
import game.sprites.Panel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game extends Application {
    private static Game gameInstance = new Game();
    public static Game getInstance() {
        return gameInstance;
    }
    private Scene scene;
    private Text pressAnything, score;
    private Bird bird = Bird.getInstance();
    private boolean gameStarted, gamePaused;
    private List<Panel> panels;
    private List<KeyCode> keysPressed;
    private AnchorPane root;
    private Timeline gameLoop;
    private Random rnd;
    private MusicThread boomSound, scoreSound;
    private static final int numberOfPanels = 8;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("../fxml/game.fxml"));
        scene = new Scene(root, StageSize.width, StageSize.height);
        primaryStage.setScene(scene);

        panels = new ArrayList<>();
        keysPressed = new ArrayList<>();
        rnd = new Random();
        boomSound = new MusicThread("resources/music/crush.mp3");
        scoreSound = new MusicThread("resources/music/score.mp3");

        prepareScene();

        scene.setOnKeyPressed(e -> {
            if (!keysPressed.contains(e.getCode())) {
                keysPressed.add(e.getCode());
            }
        });

        scene.setOnKeyReleased(e -> {
            KeyCode keyPressed = e.getCode();
            keysPressed.remove(keyPressed);
        });

        gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        KeyFrame kf = new KeyFrame(Duration.seconds(0.017), (ActionEvent e) -> { // 60 FPS - 1 frame

            if(keysPressed.contains(KeyCode.SPACE) && !gameStarted) {
                gameStarted = true;
                pressAnything.setText("");
                new BirdAnimation(bird).play();
            }

            if(keysPressed.contains(KeyCode.ESCAPE)) {
                gameLoop.stop();
            }

            if (gameStarted) {
                if (keysPressed.contains(KeyCode.SPACE)) {
                    bird.jump();
                } else {
                    bird.fall();
                }

                for (Panel p : panels) {

                    if (p.intersects(bird) || bird.isOutOfBounds()) {
                        gameLoop.stop();
                        boomSound.run();
                        try {
                            SceneManager.getInstance().getGameEndedScene().start(game.scenes.Main.getPrimaryStage());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    if (p.getLayoutX() < -140){
                        p.scoreAdded = false;
                        p.setLayoutX(-140 + 200 * numberOfPanels);
                        p.setLayoutY(rnd.nextInt(150) - 200);
                    }

                    if (p.getLayoutX() < bird.getImageView().getX() && !p.scoreAdded) {

                        incrementScore(p);
                    }

                    p.move();

                }
            }
        });

        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();
        primaryStage.show();
    }

    private void generatePanels() {
        panels.clear();

        for (int i = 0; i < numberOfPanels; i++) {
            Panel panel = new Panel(root);
            panel.setLayoutX(200 + i * 200);
            panel.setLayoutY(-panel.getImageHeight() + rnd.nextInt(150) + 200);
            panels.add(panel);
        }
    }

    private void prepareScene() {
        generatePanels();

        gameStarted = false;

        pressAnything = new Text("Press SPACE to begin...");
        pressAnything.setX(100);
        pressAnything.setY(100);

        score = new Text("Score: 0");
        score.setX(10);
        score.setY(10);

        bird.getImageView().setX(65);
        bird.getImageView().setY(200);

        root.getChildren().addAll(panels);
        root.getChildren().addAll(bird, pressAnything, score);
    }


    private void incrementScore(Panel p) {
        scoreSound.run();
        ScoreManager scoreManager = ScoreManager.getInstance();
        scoreManager.incrementScore();
        score.setText("Score: " + scoreManager.getScore());
        p.scoreAdded = true;
    }

}
