package view.Animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.GameInfo;
import model.User;
import model.gameObjects.*;

public class MigBulletAnimation extends Transition {
    public double radian = 0;
    private double ySpeed;
    private double xSpeed;
    private double speed = 1;

    private final Game game;
    private final Pane pane;
    private final Mig mig;
    private final MigBullet migBullet;
    private double accelaration = 0.05;
    private final Airplane airplane;

    public MigBulletAnimation(Game game, Pane pane, MigBullet migBullet, Airplane airplane, Mig mig){
        this.game = game;
        this.pane = pane;
        this.migBullet = migBullet;
        this.airplane = airplane;
        this.mig = mig;
        double xD = airplane.getX() - mig.getX();
        double yD = airplane.getY() - mig.getY();
        radian = Math.toDegrees(Math.atan(xD / yD));
        this.xSpeed = -5 * Math.sin(Math.toRadians(radian));
        this.ySpeed = -5 * Math.cos(Math.toRadians(radian));

        game.addAnimation(this);
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.minutes(1));


    }

    @Override
    protected void interpolate(double v) {
        migBullet.setX(migBullet.getX() + xSpeed * User.getLoggedInUser().getGameSetting().getDifficulty());
        migBullet.setY(migBullet.getY() + ySpeed * User.getLoggedInUser().getGameSetting().getDifficulty());
        migBullet.setRotate(Math.toRadians(radian));

        if (migBullet.getBoundsInParent().intersects(airplane.getBoundsInParent())){
            collision(migBullet, airplane);
        }

        if (migBullet.getY() < 0 || migBullet.getY() > game.getHEIGHT() ||
                migBullet.getX() < 0 || migBullet.getX() > game.getWIDTH()){
            pane.getChildren().remove(migBullet);
        }
    }
    private void collision(MigBullet migBullet, Airplane airplane) {


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

        pane.getChildren().remove(migBullet);
        airplane.setY(20);
        airplane.setY(20);
        this.stop();
    }
}
