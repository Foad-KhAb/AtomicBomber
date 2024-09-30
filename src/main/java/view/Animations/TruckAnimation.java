package view.Animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.User;
import model.gameObjects.Truck;

public class TruckAnimation extends Transition {
    private final Game game;
    private final Pane pane;
    private final Truck truck;
    private final double acceleration = 0.05;
    private double hspeed = 0.5;
    private final int duration = 100;
    public TruckAnimation(Game game, Pane pane, Truck truck){
        this.game = game;
        this.pane = pane;
        this.truck = truck;
        game.addAnimation(this);
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
    }

    @Override
    protected void interpolate(double v){
        double x = truck.getX() + hspeed * User.getLoggedInUser().getGameSetting().getDifficulty();
        truck.setX(x);
        if (truck.getX()> game.getWIDTH()){
            pane.getChildren().remove(truck);
        }
    }

}
