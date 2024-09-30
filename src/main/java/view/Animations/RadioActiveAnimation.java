package view.Animations;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.GameInfo;
import model.User;
import model.gameObjects.*;
import model.Game;
import view.Animations.Explosion.Blast;
import view.Animations.Explosion.RadioActiveExplosion;

public class RadioActiveAnimation extends Transition {
    private double radian = 0;
    private double vSpeed;
    private double hSpeed;
    private double speed = 1;
    private final Game game;
    private final Pane pane;
    private final RadioActiveBomb radioActiveBomb;
    private double accelaration = 0.05;
    private final Airplane airplane;

    public RadioActiveAnimation(Game game, Pane pane, RadioActiveBomb radioActiveBomb, Airplane airplane) {
        radian = airplane.getPlaneAnimation().getRadian();
        if (radian > 180) radian -= 180;
        else radian *= -1;
        this.game = game;
        this.pane = pane;
        this.radioActiveBomb = radioActiveBomb;
        this.airplane = airplane;
        this.vSpeed = speed * Math.cos(Math.toRadians(radian));
        this.hSpeed = speed * Math.sin(Math.toRadians(radian));
        game.addAnimation(this);
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.minutes(10));
    }

    @Override
    protected void interpolate(double v) {
        vSpeed -= accelaration;
        double x = radioActiveBomb.getX() + hSpeed;
        double y = radioActiveBomb.getY() - vSpeed;
        radioActiveBomb.setX(x);
        radioActiveBomb.setY(y);
        double theta = Math.atan(hSpeed / vSpeed);
        radioActiveBomb.setRotate(Math.toDegrees(theta));
        //making the explosion
        if (radioActiveBomb.getY() >= game.getHEIGHT() - 10) {
            this.pause();

            radioActiveBomb.setWidth(radioActiveBomb.getWidth() + v * 700);
            radioActiveBomb.setHeight(radioActiveBomb.getHeight() + v * 200);
            radioActiveBomb.setX(radioActiveBomb.getX() - v * 100);
            radioActiveBomb.setY(radioActiveBomb.getY() - v * 200);

            RadioActiveExplosion radioActiveExplosion = new RadioActiveExplosion(radioActiveBomb, (int) (radian));
            radioActiveExplosion.play();

            radioActiveExplosion.setOnFinished(actionEvent -> {
                pane.getChildren().remove(radioActiveBomb);
            });
            destroyNearObjects(pane, 80, radioActiveBomb.getX());
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
        if (node.getClass().equals(RadioActiveBomb.class)
                || node.getClass().equals(Airplane.class)) return;

        if (!node.getClass().equals(Tree.class)) {
            User user = User.getLoggedInUser();
            GameInfo gameInfo = user.getGameInfo();
            gameInfo.setKills(gameInfo.getKills() + 1);
            user.setGameInfo(gameInfo);
            User.setLoggedInUser(user);
        }


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
