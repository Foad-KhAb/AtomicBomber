package model.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import view.Animations.ClusterAnimation;

public class Cluster extends Rectangle {
    private double x;
    private double y;
    private double theta;
    private final Game game;
    private ClusterAnimation clusterAnimation;

    public ClusterAnimation getClusterAnimation() {
        return clusterAnimation;
    }

    public void setClusterAnimation(ClusterAnimation clusterAnimation) {
        this.clusterAnimation = clusterAnimation;
    }
    public Cluster(Airplane airplane, Game game , double x , double y , double theta){
        super(11, 5);
        this.game = game;
        this.theta = theta;
        this.x = x;
        this.y =y;
        setX(x);
        setY(y);
        try {
            setFill(new ImagePattern(
                    new Image(getClass().getResource("/Images/GameObjects/Cluster/clusterbomb.png").toExternalForm())
            ));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}