package model.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;

import java.util.Random;

public class Tree extends Rectangle {
    public final double WIDTH = 20;
    public final double HEIGHT = 30;
    public final Game game;

    public Tree(Game game) {
        super(20, 30);
        this.game = game;
        Random random = new Random();
        int index = random.nextInt((int)game.getWIDTH());
        setX(index);
        setY(290);
        String[] tree = {"tree2.png", "tree3.png", "tree.png"};
        Random random2 = new Random();
        index = random2.nextInt(tree.length);
        String randomTree = tree[index];
        try {
            setFill(new ImagePattern(new Image(Tree.class.getResource("/Images/GameObjects/"+randomTree).toExternalForm())));
        }
        catch(Exception e){
            System.out.println("sala,");
        }
    }
}
