package view.Animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.gameObjects.Airplane;
import model.gameObjects.Bullet;
import model.gameObjects.Tank;

import java.util.ArrayList;

public class TankShootingAnimation extends Transition {
    private final Game game;
    private final Pane pane;
    private final Tank tank;
    private final double speed = 1;
    private final int duration;
    private double hSpeed;
    private double vSpeed;
    private Bullet bullet;
    private Airplane airplane;
    public TankShootingAnimation(Game game, Pane pane, Tank tank, Airplane airplane, int duration) {
        this.game = game;
        this.pane = pane;
        this.tank = tank;
        this.airplane = airplane;
        this.duration = duration;
        game.addAnimation(this);
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
    }

    @Override
    protected void interpolate(double v) {
//        System.out.println(Math.pow(airplane.getX() - tank.getX(), 2)+ Math.pow(airplane.getY() - airplane.getX(),2));
//        if (Math.pow(airplane.getX() - tank.getX(), 2)+ Math.pow(airplane.getY() - airplane.getX() , 2) <10000 ){
//            bullet = new Bullet(airplane, game);
//            bullet.setBombAnimation(new BombAnimation(game,pane,bullet,airplane));
//            bullet.getBombAnimation().play();
//            game.addAnimation(bullet.getBombAnimation());
//
//        }

    }
}
