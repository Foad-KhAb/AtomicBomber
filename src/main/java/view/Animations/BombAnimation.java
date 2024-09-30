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
import view.Animations.Explosion.BulletExplosion;

public class BombAnimation extends Transition {
    public double radian = 0;
    private double ySpeed;
    private double xSpeed;
    private double speed = 1;

    private final Game game;
    private final Pane pane;
    private final Bullet bullet;
    private double accelaration = 0.05;
    private final Airplane airplane;


    public BombAnimation(Game game, Pane pane, Bullet bullet, Airplane airplane) {
//        setRadian();
        radian = airplane.getPlaneAnimation().getRadian();
        if (radian < 180) radian = radian - 360;
//        else radian *= -1;
        this.game = game;
        this.pane = pane;
        this.airplane = airplane;
        this.bullet = bullet;
        if (radian < 270 && radian > 90) bullet.setScaleX(-1);
        this.xSpeed= speed * Math.cos(Math.toRadians(radian));
        this.ySpeed= speed * Math.sin(Math.toRadians(radian));

        game.addAnimation(this);
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.minutes(10));
    }


    @Override
    protected void interpolate(double v) {
        ySpeed -= accelaration;
        double x = bullet.getX() + xSpeed;
        double y = bullet.getY() - ySpeed;
        bullet.setX(x);
        bullet.setY(y);
        double theta = Math.atan(xSpeed / ySpeed);
//        radian = theta;
        bullet.setRotate(Math.toDegrees(theta) - 180);
        // making the explosion
        if (bullet.getY() >= game.getHEIGHT() - 10) {

            this.pause();

            bullet.setWidth(bullet.getWidth() + v * 700);
            bullet.setHeight(bullet.getHeight() + v * 200);
            bullet.setX(bullet.getX() - v * 100);
            bullet.setY(bullet.getY() - v * 200);

            BulletExplosion bulletExplosion = new BulletExplosion(bullet, (int) radian);
            bulletExplosion.play();

            bulletExplosion.setOnFinished(actionEvent -> {
                pane.getChildren().remove(bullet);
            });
            destroyNearObjects(pane, 20, bullet.getX());
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
        if (node.getClass().equals(Bullet.class)
                || node.getClass().equals(Airplane.class)) return;
        Blast blast = new Blast((Rectangle) node, (int) radian);
        blast.play();
        if (!node.getClass().equals(Tree.class)) {
            User user = User.getLoggedInUser();
            GameInfo gameInfo = user.getGameInfo();
            gameInfo.setKills(gameInfo.getKills() + 1);

            System.out.println(gameInfo.getKills());
            user.setGameInfo(gameInfo);
            User.setLoggedInUser(user);
        }

        if (node.getClass().equals(Bunker.class)){
            User user = User.getLoggedInUser();
            GameInfo gameInfo = user.getGameInfo();
            gameInfo.setClusterBombs(gameInfo.getClusterBombs() + 1);

//            System.out.println(gameInfo.getKills());
            user.setGameInfo(gameInfo);
            User.setLoggedInUser(user);
        }

        if (node.getClass().equals(Building.class)){
            User user = User.getLoggedInUser();
            GameInfo gameInfo = user.getGameInfo();
            gameInfo.setRadioActiveBombs(gameInfo.getRadioActiveBombs() + 1);

//            System.out.println(gameInfo.getKills());
            user.setGameInfo(gameInfo);
            User.setLoggedInUser(user);
        }

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
