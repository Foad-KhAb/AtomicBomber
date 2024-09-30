package model.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import view.Animations.BombAnimation;

public class Bullet extends Rectangle {
    public final double WIDTH = 10;
    public final double HEIGHT = 6;
    public final Game game;
    private BombAnimation bombAnimation;

    public BombAnimation getBombAnimation() {
        return bombAnimation;
    }

    public void setBombAnimation(BombAnimation bombAnimation) {
        this.bombAnimation = bombAnimation;
    }

    public Bullet(Airplane airplane , Game game){
        super(8, 10);
        this.game = game;
        setX(airplane.getX());
        setY(airplane.getY());
        try {
            setFill(new ImagePattern(
                    new Image(getClass().getResource("/Images/GameObjects/Bullet/img.png").toExternalForm())
            ));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
