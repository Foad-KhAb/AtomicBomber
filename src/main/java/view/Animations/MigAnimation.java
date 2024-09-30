package view.Animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.User;
import model.gameObjects.Airplane;
import model.gameObjects.Mig;

public class MigAnimation extends Transition {
    private final Game game;
    private final Pane pane;
    private final Mig mig;
    private final double vSpeed = 0.5;
    private final int duration = 100;
    private final Airplane airplane;

    public MigAnimation(Game game, Pane pane, Mig mig, Airplane airplane) {
        this.game = game;
        this.pane = pane;
        this.mig = mig;
        this.airplane = airplane;
        game.addAnimation(this);
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
    }

    @Override
    protected void interpolate(double v) {
        double x = mig.getX() + vSpeed / User.getLoggedInUser().getGameSetting().getDifficulty();
        mig.setX(x);
        if (mig.getX()<0){
            pane.getChildren().remove(mig);
        }
    }
}
