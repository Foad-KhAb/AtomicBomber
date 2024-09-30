package view.Animations;

import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.GameInfo;
import model.User;
import model.gameObjects.Airplane;

public class AirplaneAnimation extends Transition {
    private double radian = 0;
    private final double speed = 1;
    private final Game game;
    private final Pane pane;
    private final Airplane airplane;
    private int hp = 3;

    public AirplaneAnimation(Game game, Pane pane, Airplane airplane) {
        this.game = game;
        this.pane = pane;
        this.airplane = airplane;
        game.addAnimation(this);
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.minutes(10));
    }

    public double getRadian() {
        return radian;
    }

    public void setRadian(double radian) {
        this.radian = radian;
    }


    @Override
    protected void interpolate(double v) {
        double x = airplane.getX() + speed * Math.cos(((radian % 360) / 180) * Math.PI);
        airplane.setX(x);
        double y = airplane.getY() + speed * Math.sin(((radian % 360) / 180) * Math.PI);
        airplane.setY(y);
        airplane.setRotate(radian);
        checkPlace();
    }

    private void checkPlace() {
        if (airplane.getX() >= game.getWIDTH())
            airplane.setX(0);
        else if (airplane.getX() <= 0) {
            airplane.setX(game.getWIDTH());
        } else if (airplane.getY() > game.getHEIGHT()) {
            airplane.setX(game.getWIDTH() / 2);
            airplane.setY(game.getHEIGHT() / 2);
            User user = User.getLoggedInUser();
            GameInfo gameInfo = user.getGameInfo();
            gameInfo.HP--;
            if (gameInfo.HP == 0){
                try {
                    pane.getChildren().get(3).setVisible(true);
                    ((Label)((AnchorPane)pane.getChildren().get(3)).getChildren().get(0)).setText("Game Lost");
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                timeline.stop();
//                theEnd = true;
                return;
            }
            user.setGameInfo(gameInfo);
            User.setLoggedInUser(user);
        }
        if (hp == 0)
            gameOver();
    }

    private void gameOver() {
    }


}
