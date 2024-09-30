package model.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;

import java.util.Random;

public class Building extends Rectangle {
    public final double WIDTH = 20;
    public final double HEIGHT = 30;
    public final Game game;
    public Building(Game game){
        super(48 , 40);
        this.game = game;
        game.invaders.getChildren().add(this);
        Random random = new Random();
        double place =  random.nextDouble(0 ,game.getWIDTH() - 48);
        setX(place);
        setY(game.getHEIGHT() - 40);
        try {
            setFill(new ImagePattern(
                    new Image(Building.class.getResource("/Images/GameObjects/Building/building.png").toExternalForm())));
        }
        catch (Exception e){
            System.out.println("ridi");
        }
    }

}
