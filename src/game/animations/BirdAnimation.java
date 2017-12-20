package game.animations;

import game.sprites.Bird;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class BirdAnimation extends Transition {
    private Bird bird;
    private List<Image> birdImages;

    public BirdAnimation(Bird b) {
        bird = b;
        birdImages = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            birdImages.add(new Image("file:resources/images/bird-anim/bird-"+ i +".png"));
        }
        setCycleDuration(Duration.millis(400));
        setCycleCount(Transition.INDEFINITE);
        setInterpolator(Interpolator.EASE_IN);

    }

    @Override
    protected void interpolate(double frac) {
        if (frac < 0.25) {
            bird.setImage(birdImages.get(0));
        } else if (frac < 0.5) {
            bird.setImage(birdImages.get(1));
        } else if (frac < 0.75) {
            bird.setImage(birdImages.get(2));
        } else if (frac < 1) {
            bird.setImage(birdImages.get(3));
        }
    }
}
