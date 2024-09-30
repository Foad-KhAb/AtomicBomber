package model.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import view.Animations.BombAnimation;
import view.Animations.TankBulletAnimation;

public class TankBullet extends Rectangle {
    public final Game game;
    private TankBulletAnimation tankBulletAnimation;

    public void setTankBulletAnimation(TankBulletAnimation tankBulletAnimation) {
        this.tankBulletAnimation = tankBulletAnimation;
    }

    public TankBulletAnimation getTankBulletAnimation(){
        return tankBulletAnimation;
    }

    public TankBullet(Game game, Tank tank ,Airplane airplane){
        super(8, 10);
        this.game = game;
        setX(tank.getX());
        setY(tank.getY());
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
