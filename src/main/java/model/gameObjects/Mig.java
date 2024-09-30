package model.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import view.Animations.MigAnimation;

import java.util.Random;

public class Mig extends Rectangle {
    private final Game game;
    private MigAnimation migAnimation;

    public MigAnimation getMigAnimation() {
        return migAnimation;
    }

    public void setMigAnimation(MigAnimation migAnimation) {
        this.migAnimation = migAnimation;
    }
    public Mig(Game game, Random random){
        super(0, random.nextDouble(20 , game.getHEIGHT()) - 50, 39 , 13);
        this.game = game;
        try {
            setFill(new ImagePattern(
                    new Image(Mig.class.getResource("/Images/GameObjects/Mig/mig1.png").toExternalForm())
            ));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
