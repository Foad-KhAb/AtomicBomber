package view.Animations;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.Game;
import model.GameInfo;
import model.User;
import model.gameObjects.*;
import view.Animations.Explosion.Blast;
import view.Animations.Explosion.ClusterExplosion;

public class ClusterAnimation extends Transition {
    private double x;
    private double y;
    private double radian = 0;
    private double vSpeed;
    private double hSpeed;
    private double speed = 1;

    private final Game game;
    private final Pane pane;
    private final Cluster cluster;
    private final Airplane airplane;
    private double accelaration = 0.05;

    public ClusterAnimation(Game game, double radian, Pane pane, Cluster cluster, Airplane airplane, double xParameter, double yParameter) {
//        radian = airplane.getPlaneAnimation().getRadian();
//        if (radian > 180) radian -= 180;
//        else radian *= -1;
        this.game = game;
        this.pane = pane;
        this.y = airplane.getY();
        this.x = airplane.getX();
        this.airplane = airplane;
        this.cluster = cluster;
        this.vSpeed = speed * Math.cos(Math.toRadians(radian));
        this.hSpeed = speed * Math.sin(Math.toRadians(radian));
        game.addAnimation(this);

        this.setCycleCount(-1);
        this.setCycleDuration(Duration.minutes(10));
    }


    @Override
    protected void interpolate(double v) {
        cluster.setY(y);
        cluster.setX(x);

        vSpeed -= accelaration;
        this.x = this.x + hSpeed;
        this.y = this.y - vSpeed;

        double theta = Math.atan(hSpeed / vSpeed);
        cluster.setRotate(Math.toDegrees(theta));
        if (cluster.getY() >= game.getHEIGHT() - 10) {
            this.pause();

            ClusterExplosion clusterExplosion = new ClusterExplosion(cluster, radian);
            clusterExplosion.play();
            clusterExplosion.setOnFinished(actionEvent -> {
                pane.getChildren().remove(cluster);
            });

            destroyNearObjects(pane, 20, cluster.getX());
        }
    }

    private void destroyNearObjects(Pane pane, double radian, double xParameter) {
        int paneSize = pane.getChildren().size();
        for (int i = 0; i < paneSize; i++) {
            if (checkBeingNear(pane.getChildren().get(i), radian, xParameter)) {
                destroyObject(pane.getChildren().get(i));
            }
        }
    }

    private void destroyObject(Node node) {
        Rectangle rectangle;
        try {
            rectangle = (Rectangle) node;
        } catch (Exception e) {
            return;
        }
        //check if the node is not the class of bullet
        if (node.getClass().equals(Cluster.class)
                || node.getClass().equals(Airplane.class)) return;
        if (!node.getClass().equals(Tree.class)) {
            User user = User.getLoggedInUser();
            GameInfo gameInfo = user.getGameInfo();
            gameInfo.setKills(gameInfo.getKills() + 1);
            user.setGameInfo(gameInfo);
            User.setLoggedInUser(user);
        }
//        GameController.kills.setText("kills = " + gameInfo.getKills());


        if (node.getClass().equals(Bunker.class)) {
            User user = User.getLoggedInUser();
            GameInfo gameInfo = user.getGameInfo();
            gameInfo.setClusterBombs(gameInfo.getClusterBombs() + 1);

//            System.out.println(gameInfo.getKills());
            user.setGameInfo(gameInfo);
            User.setLoggedInUser(user);
        }

        if (node.getClass().equals(Building.class)) {
            User user = User.getLoggedInUser();
            GameInfo gameInfo = user.getGameInfo();
            gameInfo.setRadioActiveBombs(gameInfo.getRadioActiveBombs() + 1);

//            System.out.println(gameInfo.getKills());
            user.setGameInfo(gameInfo);
            User.setLoggedInUser(user);
        }
        Blast blast = new Blast((Rectangle) node, (int) radian);
        blast.play();


        blast.setOnFinished(actionEvent -> {
            pane.getChildren().remove(node);
        });
    }

    private boolean checkBeingNear(Node node, double radian, double xParameter) {
        Rectangle rectangle;
        try {
            rectangle = (Rectangle) node;
        } catch (Exception e) {
            return false;
        }
        return Math.abs(rectangle.getX() - xParameter) <= radian;
    }

}
