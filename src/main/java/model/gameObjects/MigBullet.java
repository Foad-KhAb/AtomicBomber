package model.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import view.Animations.MigAnimation;
import view.Animations.MigBulletAnimation;

import java.util.Random;

public class MigBullet extends Rectangle {
    public final Game game;
    private MigBulletAnimation migBulletAnimation;

    public MigBulletAnimation getMigBulletAnimation() {
        return migBulletAnimation;
    }

    public void setMigBulletAnimation(MigBulletAnimation migBulletAnimation) {
        this.migBulletAnimation = migBulletAnimation;
    }
    public MigBullet(Game game, Mig mig, Airplane airplane){
        super(8 , 10);
        this.game = game;
        setX(mig.getX());
        setY(mig.getY());
        try {
            setFill(new ImagePattern(
                    new Image(getClass().getResource("/Images/GameObjects/Sam/img.png").toExternalForm())
            ));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
