package model.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import view.Animations.TruckAnimation;

import java.util.Random;

public class Truck extends Rectangle {
    private final double WIDTH = 50;
    private final double HEIGHT = 50;
    public final Game game;
    private final boolean isHit = false;
    private TruckAnimation truckAnimation;

    public TruckAnimation getTruckAnimation() {
        return truckAnimation;
    }

    public void setTruckAnimation(TruckAnimation truckAnimation) {
        this.truckAnimation = truckAnimation;
    }

    public Truck(Game game, Random random){
//        super(50, game.getHEIGHT() - 16 , 32 , 16);
        super(random.nextInt(-150,0), game.getHEIGHT() - 16 , 32 , 16);
        this.game = game;

        try {
            setFill(new ImagePattern(
                    new Image(Tree.class.getResource("/Images/GameObjects/Truck/truck.png").toExternalForm())
            ));
        }
        catch (Exception e){
            System.out.println("saa");
            e.printStackTrace();
        }
    }
}
