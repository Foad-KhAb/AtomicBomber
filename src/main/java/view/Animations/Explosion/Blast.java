package view.Animations.Explosion;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Blast extends Transition {
    private Rectangle object;
    private String address = "/Images/Explosion/Blast/bigblast";
    private int radius;

    public Blast(Rectangle rectangle, int radius) {
        this.object = rectangle;
        this.radius = radius;

        object.setRotate(0);

        this.setCycleDuration(Duration.seconds(1.5));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        int temp = (int) Math.ceil(v * 4);
        object.setWidth(object.getWidth() + v);
        object.setHeight(object.getHeight() + v);
        object.setX(object.getX() - v / 2);
        object.setY(object.getY() - v);


        ImagePattern imagePattern = new ImagePattern(
                new Image(getClass().getResource(address + temp + ".png").toExternalForm())
        );
        object.setFill(imagePattern);
    }

}
