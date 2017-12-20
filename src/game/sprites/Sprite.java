package game.sprites;

import javafx.geometry.Rectangle2D;

public interface Sprite {
    Rectangle2D getBoundary();
    boolean intersects(Sprite s);
}
