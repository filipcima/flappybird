package game.sprites;

import game.essentials.StageSize;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bird extends Pane implements Sprite {

    private static Bird birdInstance = new Bird();
    private Image image;
    private ImageView imageView;

    public ImageView getImageView() { return imageView; }

    public void setImage(Image i) { this.imageView.setImage(i); }

    public static Bird getInstance() { return birdInstance; }

    private Bird() {
        image = new Image("file:resources/images/bird.png");
        imageView = new ImageView(image);
        this.getChildren().add(imageView);
    }

    public void fall() {
        imageView.setY(imageView.getY() + 3);
    }

    public void jump() {
        imageView.setY(imageView.getY() - 3);
    }

    public boolean isOutOfBounds() {
        return imageView.getY() < 0 || imageView.getY() > StageSize.height;
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(imageView.getX() + 5, imageView.getY() + 5, image.getWidth() - 10, image.getWidth() - 10);
    }

    @Override
    public boolean intersects(Sprite s) {
        return s.getBoundary().intersects(this.getBoundary());
    }
}
