package view.Animations.Explosion;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import view.Animations.ClusterAnimation;

public class ClusterExplosion extends Transition {
    private Rectangle cluster;
    private String address = "/Images/Explosion/RadioActiveBomb/";
    private double radius;

    public ClusterExplosion(Rectangle cluster, double radius) {
        this.cluster = cluster;
        this.radius = radius;
        cluster.setRotate(0);
        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(1));
    }

    @Override
    protected void interpolate(double v) {
        int temp = (int) Math.ceil(v * 6);


        cluster.setWidth(cluster.getWidth() + v);
        cluster.setHeight(cluster.getHeight() + v);
        cluster.setX(cluster.getX() - v/2);
        cluster.setY(cluster.getY() - v);
        ImagePattern imagePattern = new ImagePattern(
                new Image(getClass().getResource(address + temp + ".png").toExternalForm())
        );
//        Image gifImage = new Image(getClass().getResource(address).toExternalForm());
//        ImageView imageView = new ImageView(gifImage);
//        imageView.setFitWidth(500);
//        imageView.setPreserveRatio(true);
        cluster.setFill(imagePattern);

    }
}
