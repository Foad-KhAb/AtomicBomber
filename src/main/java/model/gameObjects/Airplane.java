package model.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import view.Animations.AirplaneAnimation;

public class Airplane extends Rectangle {
    public final double WIDTH = 100;
    public final double HEIGHT = 100;

    public final Game game;
    private AirplaneAnimation airplaneAnimation;

    public AirplaneAnimation getPlaneAnimation() {
        return airplaneAnimation;
    }

    public void setPlaneAnimation(AirplaneAnimation airplaneAnimation) {
        this.airplaneAnimation = airplaneAnimation;
    }

    public Airplane(Game game){
        super(27 , 9 , 27 , 9);
        this.game = game;
        try {
            setFill(new ImagePattern(new Image(getClass().getResource("/Images/GameObjects/Plane/plane.png").toExternalForm())));
        } catch (Exception e){
            System.out.println("salam");
            e.printStackTrace();
        }
    }

    public void getHurt() {
        this.setX(50);
        this.setY(50);
    }

}
