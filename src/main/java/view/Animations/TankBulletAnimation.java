package view.Animations;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.GameInfo;
import model.User;
import model.gameObjects.Airplane;
import model.gameObjects.Bullet;
import model.gameObjects.Tank;
import model.gameObjects.TankBullet;

public class TankBulletAnimation extends Transition {
    public double radian = 0;
    private double ySpeed;
    private double xSpeed;
    private double speed = 1;

    private final Game game;
    private final Pane pane;
    private final Tank tank;
    private final TankBullet tankBullet;
    private double accelaration = 0.05;
    private final Airplane airplane;

    public TankBulletAnimation(Game game, Pane pane, TankBullet tankBullet, Airplane airplane, Tank tank) {
        this.game = game;
        this.pane = pane;
        this.tankBullet = tankBullet;
        this.airplane = airplane;
        this.tank = tank;
        double xD = airplane.getX() - tank.getX();
        double yD = airplane.getY() - tank.getY();
        radian = Math.toDegrees(Math.atan(xD / yD));
        this.xSpeed = -5 * Math.sin(Math.toRadians(radian));
        this.ySpeed = -5 * Math.cos(Math.toRadians(radian));

        game.addAnimation(this);
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.minutes(1));
    }

    @Override
    protected void interpolate(double v) {
        tankBullet.setX(tankBullet.getX() + xSpeed * User.getLoggedInUser().getGameSetting().getDifficulty());
        tankBullet.setY(tankBullet.getY() + ySpeed * User.getLoggedInUser().getGameSetting().getDifficulty());

        tankBullet.setRotate(Math.toRadians(radian));

        if (tankBullet.getBoundsInParent().intersects(airplane.getBoundsInParent())) {
            collision(tankBullet, airplane);
        }

        if (tankBullet.getY() < 0 || tankBullet.getX() > pane.getWidth() || tankBullet.getX() < 0) {
            pane.getChildren().remove(tankBullet);
        }

    }

    private void collision(TankBullet tankBullet, Airplane airplane) {


//        FadeTransition fadeTransition = new FadeTransition();
//        fadeTransition.setNode(airplane);
//        fadeTransition.setDuration(Duration.millis(1000));
//        fadeTransition.setFromValue(1);
//        fadeTransition.setToValue(0.01);
//        fadeTransition.play();
        User user = User.getLoggedInUser();
        GameInfo gameInfo = user.getGameInfo();
        gameInfo.HP--;
        user.setGameInfo(gameInfo);
        User.setLoggedInUser(user);

        pane.getChildren().remove(tankBullet);
        airplane.setY(20);
        airplane.setY(20);
        this.stop();
    }
}
