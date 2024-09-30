package model;

import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.media.MediaPlayer;
import view.GameLauncher;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    public Group invaders = new Group();
    private final double WIDTH = 569;
    public ArrayList<Transition> animation = new ArrayList<>();

    public ArrayList<Transition> getAnimation() {
        return animation;
    }

    public void setAnimation(ArrayList<Transition> animation) {
        this.animation = animation;
    }

    public Game(User user) {
        this.player = user;
//        if (user != null)
//            user.setGameInfo(new GameInfo());
    }

    public double getWIDTH() {
        return WIDTH;
    }

    private final double HEIGHT = 320;

    public double getHEIGHT() {
        return HEIGHT;
    }
    public ArrayList<Timeline> allTimeLines = new ArrayList<>();

    public ArrayList<Timeline> getAllTimeLines() {
        return allTimeLines;
    }

    public void setAllTimeLines(ArrayList<Timeline> allTimeLines) {
        this.allTimeLines = allTimeLines;
    }

    private User user;
    private double score;
    private int killed;
    private int difficulty;
    private int totalShot;
    private int hitShot;
    private User player;
    private MediaPlayer mediaPlayer;

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void addAnimation(Transition transition) {
        ArrayList<Transition> animations = this.getAnimation();
        animations.add(transition);
        this.setAnimation(animations);
    }
}
