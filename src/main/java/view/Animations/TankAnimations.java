package view.Animations;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Game;
import model.User;
import model.gameObjects.Airplane;
import model.gameObjects.Bullet;
import model.gameObjects.Tank;
import view.Animations.Explosion.Blast;
import view.Animations.Explosion.BulletExplosion;

public class TankAnimations extends Transition {
    private final Game game;
    private final Pane pane;
    private final Tank tank;
    private final double acceleration = 0.05;
    private double hSpeed = 0.5;
    private final int duration = 100;
    private Airplane airplane;
    private boolean doFire = false;

    public TankAnimations(Game game, Pane pane, Tank tank, Airplane airplane) {
        this.game = game;
        this.pane = pane;
        this.tank = tank;
        this.airplane = airplane;
        game.addAnimation(this);
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
    }

    @Override
    protected void interpolate(double v) {
        double x = tank.getX() + hSpeed * User.getLoggedInUser().getGameSetting().getDifficulty();
        tank.setX(x);
        if (tank.getX()>game.getWIDTH()){
            pane.getChildren().remove(tank);
        }
    }
}
