package game.sprites;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class Panel extends Pane implements Sprite {

    private Image topPanelImage, bottomPanelImage;
    private ImageView topPanel, bottomPanel;
    public Boolean scoreAdded = false;
    private double width, height, verticalSpace;

    public ImageView getBottomPanel() {
        return bottomPanel;
    }

    public ImageView getTopPanel() {
        return bottomPanel;
    }

    public Panel(AnchorPane root) {
        //setPrefSize(StageSize.width, StageSize.height);
        topPanelImage = new Image("file:resources/images/pipe-south.png");
        bottomPanelImage = new Image("file:resources/images/pipe-north.png");
        width = topPanelImage.getWidth();
        height = topPanelImage.getHeight();
        bottomPanel = new ImageView(topPanelImage);
        topPanel = new ImageView(bottomPanelImage);
        verticalSpace = 130;

        topPanel.setY(height + verticalSpace);

        this.getChildren().addAll(bottomPanel, topPanel);
    }

    public void move() {
        setLayoutX(getLayoutX() -2);
    }

    public Rectangle2D getBoundary(String orientation) {
        if(Objects.equals(orientation, "TOP")) {
            return new Rectangle2D(this.getLayoutX(), this.getLayoutY() + 5, width, height - 5);
        } else if (Objects.equals(orientation, "BOTTOM"))
            return new Rectangle2D(this.getLayoutX(), this.getLayoutY() + height + verticalSpace + 5, width, height - 5);
        return null;

    }

    @Override
    public Rectangle2D getBoundary() {
        return null;
    }

    @Override
    public boolean intersects(Sprite s) {
        return s.getBoundary().intersects(this.getBoundary("TOP")) || s.getBoundary().intersects(this.getBoundary("BOTTOM"));
    }

    public double getImageWidth() {
        return width;
    }

    public double getImageHeight() {
        return height;
    }
}
