package model.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import view.Animations.TankAnimations;
import view.Animations.TankShootingAnimation;

import java.util.Random;

public class Tank extends Rectangle {
    private final double WIDTH = 50;
    private final double HEIGHT = 50;
    public final Game game;
    private boolean isHit = false;
    private TankAnimations tankAnimations;

    public TankAnimations getTankAnimations() {
        return tankAnimations;
    }

    public void setTankAnimations(TankAnimations tankAnimations) {
        this.tankAnimations = tankAnimations;
    }


    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public Tank(Game game, Random random) {
        super(0 , game.getHEIGHT() - 17 , 32 , 17);
//        super(150 , game.getHEIGHT() - 16 , 32 , 16);
        this.game = game;
        try {
            setFill(new ImagePattern(
                    new Image(Tree.class.getResource("/Images/GameObjects/Tank/tank1.png").toExternalForm())));
        }
        catch (Exception e){
            System.out.println("salam");
            e.printStackTrace();
        }
    }
}
