package view.Animations.Explosion;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RadioActiveExplosion extends Transition {
    private Rectangle radioActive;
    public String address = "/Images/Explosion/RadioActiveBomb/";
    public int radius;

    public RadioActiveExplosion(Rectangle radioActive, int radius) {
        this.radioActive = radioActive;
        this.radius = radius;
        radioActive.setRotate(0);

        this.setCycleDuration(Duration.seconds(2));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        int temp = (int) Math.ceil(v * 6);
        radioActive.setWidth(radioActive.getWidth() + v);
        radioActive.setHeight(radioActive.getHeight() + v);
        radioActive.setX(radioActive.getX() - v/2);
        radioActive.setY(radioActive.getY() - v);

        ImagePattern imagePattern = new ImagePattern(
                new Image(getClass().getResource(address + temp + ".png").toExternalForm())
        );
        radioActive.setFill(imagePattern);


    }
}
