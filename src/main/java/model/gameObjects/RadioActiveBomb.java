package model.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import view.Animations.RadioActiveAnimation;

public class RadioActiveBomb extends Rectangle {
    public final double WIDHT = 10;
    public final double HEIGHT = 6;
    public final Game game;
    private RadioActiveAnimation radioActiveAnimation;

    public RadioActiveAnimation getRadioActiveAnimation() {
        return radioActiveAnimation;
    }

    public void setRadioActiveAnimation(RadioActiveAnimation radioActiveAnimation) {
        this.radioActiveAnimation = radioActiveAnimation;
    }

    public RadioActiveBomb(Airplane airplane, Game game){
        super(8 , 10);
        this.game = game;
        setX(airplane.getX());
        setY(airplane.getY());
        try {
            setFill(new ImagePattern(
                    new Image(getClass().getResource("/Images/GameObjects/RadioActive/radioActive.png").toExternalForm())
            ));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
