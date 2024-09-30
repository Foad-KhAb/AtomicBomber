package view.Animations.Explosion;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BulletExplosion extends Transition {
    private Rectangle bullet;

    private String address = "/Images/Explosion/BulletExplosion/RegularBomb/";

    private int radius;

    public BulletExplosion(Rectangle bullet, int radius) {
        this.bullet = bullet;
        this.radius = radius;

        bullet.setRotate(0);

        this.setCycleDuration(Duration.seconds(2));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        int temp = (int) Math.ceil(v * 6);
        bullet.setWidth(bullet.getWidth() + v);
        bullet.setHeight(bullet.getHeight() + v);
        bullet.setX(bullet.getX() - v/2);
        bullet.setY(bullet.getY() - v);

        ImagePattern imagePattern = new ImagePattern(
                new Image(getClass().getResource(address + temp + ".png").toExternalForm())
        );
        bullet.setFill(imagePattern);
    }
}
