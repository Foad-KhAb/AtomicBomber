package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Game;
import model.GameInfo;
import model.User;
import model.gameObjects.Airplane;
import model.gameObjects.Cluster;
import model.gameObjects.RadioActiveBomb;
import view.Animations.ClusterAnimation;
import view.Animations.RadioActiveAnimation;
import view.GameLauncher;
import view.MainMenu;

public class GameController {
    public Button pause;
    public Label kills;
//    public Label wave;
    public Label accuracy;
    public AnchorPane pauseMenu;

    public static void updateLabel(Pane pane) {
        System.out.println("hi mf");
        ((Label)(pane.getChildren().get(1))).setText("kills = " + User.getLoggedInUser().getGameInfo().getKills());
        ((Label)(pane.getChildren().get(2))).setText("accuracy = " + (double)(User.getLoggedInUser().getGameInfo().getKills()) /
                User.getLoggedInUser().getGameInfo().getShootedBullets());
        ((Label)(pane.getChildren().get(5))).setText("wave = " + User.getLoggedInUser().getGameInfo().getWaveNumber());
        ((Label)(pane.getChildren().get(6))).setText("radioActive = " + User.getLoggedInUser().getGameInfo().getRadioActiveBombs());
        ((Label)(pane.getChildren().get(7))).setText("cluster = " + User.getLoggedInUser().getGameInfo().getClusterBombs());

    }
    public static void gameStarter() throws Exception {
        new GameLauncher(User.getLoggedInUser(), User.getLoggedInUser().getGameInfo().getWaveNumber()).start(ApplicationController.getStage());
    }



    public static void createRadioActive(Game game, Pane pane, Airplane airplane) {

        User user = User.getLoggedInUser();

        if (user.getGameInfo().getRadioActiveBombs() == 0) return;


        GameInfo gameInfo = user.getGameInfo();
        gameInfo.setShootedBullets(gameInfo.getShootedBullets() + 1);
        gameInfo.setRadioActiveBombs(gameInfo.getRadioActiveBombs() - 1);
        user.setGameInfo(gameInfo);
        User.setLoggedInUser(user);

        RadioActiveBomb radioActiveBomb = new RadioActiveBomb(airplane, game);
        radioActiveBomb.setRadioActiveAnimation(new RadioActiveAnimation(game, pane, radioActiveBomb, airplane));
        game.addAnimation(airplane.getPlaneAnimation());
        radioActiveBomb.getRadioActiveAnimation().play();
        pane.getChildren().add(radioActiveBomb);



    }

    public static void createCluster(Game game, Pane pane, Airplane airplane) {

        User user = User.getLoggedInUser();

        if (user.getGameInfo().getClusterBombs() == 0) return;

        GameInfo gameInfo = user.getGameInfo();
        gameInfo.setShootedBullets(gameInfo.getShootedBullets() + 1);
        gameInfo.setClusterBombs(gameInfo.getClusterBombs() - 1);
        user.setGameInfo(gameInfo);
        User.setLoggedInUser(user);



        Cluster cluster = new Cluster(airplane, game, airplane.getX(), airplane.getY(), airplane.getPlaneAnimation().getRadian() - 10);
        cluster.setClusterAnimation(new ClusterAnimation(game, airplane.getPlaneAnimation().getRadian() - 10, pane, cluster, airplane, airplane.getX(), airplane.getY()));
        game.addAnimation(airplane.getPlaneAnimation());
        cluster.getClusterAnimation().play();
        pane.getChildren().add(cluster);
        Cluster cluster1 = new Cluster(airplane, game, airplane.getX(), airplane.getY(), airplane.getPlaneAnimation().getRadian());
        cluster1.setClusterAnimation(new ClusterAnimation(game, airplane.getPlaneAnimation().getRadian() , pane, cluster1, airplane, airplane.getX(), airplane.getY()));
        game.addAnimation(airplane.getPlaneAnimation());
        cluster1.getClusterAnimation().play();
        pane.getChildren().add(cluster1);
        Cluster cluster2 = new Cluster(airplane, game, airplane.getX(), airplane.getY(), airplane.getPlaneAnimation().getRadian() + 10);
        cluster2.setClusterAnimation(new ClusterAnimation(game, airplane.getPlaneAnimation().getRadian() + 10, pane, cluster2, airplane, airplane.getX(), airplane.getY()));
        game.addAnimation(airplane.getPlaneAnimation());
        cluster2.getClusterAnimation().play();
        pane.getChildren().add(cluster2);
    }

    public void changeKillLabel(int kill) {
        kills.setText("kills = " + kill);
    }

    public void goToMainMenu(ActionEvent actionEvent) throws Exception {
        new MainMenu().start(ApplicationController.getStage());
    }

    public void newGame(ActionEvent actionEvent) throws Exception {
        User user = User.getLoggedInUser();
        user.setGameInfo(new GameInfo());
        User.setLoggedInUser(user);
        GameController.gameStarter();
    }

    public void pause(ActionEvent actionEvent) {
        Game game = User.getLoggedInUser().getCurrentGame();
        for (int i = 0; i < game.getAnimation().size(); i++) {
            game.getAnimation().get(i).pause();
        }
        for (int i = 0; i < game.getAllTimeLines().size(); i++) {
            game.allTimeLines.get(i).play();
            System.out.println(game.getAllTimeLines().size());
        }
        pauseMenu.setVisible(true);
    }

    public void saveAndLeave(ActionEvent actionEvent) throws Exception {
        GameLauncher.mediaPlayer.stop();
        new MainMenu().start(ApplicationController.getStage());
    }

    public void leaveAndDontSave(ActionEvent actionEvent) throws Exception {
        GameLauncher.mediaPlayer.stop();
        User user = User.getLoggedInUser();
        user.setGameInfo(new GameInfo());
        new MainMenu().start(ApplicationController.getStage());
    }

    public void pauseMusic(ActionEvent actionEvent) {
        User user = User.getLoggedInUser();
//        user.getCurrentGame().getMediaPlayer().pause();
        GameLauncher.mediaPlayer.pause();
        GameLauncher.changeFocus(GameLauncher.pane);
        Game game = User.getLoggedInUser().getCurrentGame();
        for (int i = 0; i < game.getAnimation().size(); i++) {
            game.getAnimation().get(i).play();
        }
        pauseMenu.setVisible(false);

    }

    public void firstSong(ActionEvent actionEvent) {
        System.out.println("firstsong");
        User user = User.getLoggedInUser();
        GameLauncher.mediaPlayer.stop();


        String fileName = getClass().getResource("/Media/inspiring-cinematic-ambient-116199.mp3").toExternalForm();
        Media media = new Media(fileName);
        GameLauncher.mediaPlayer = new MediaPlayer(media);
        GameLauncher.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        GameLauncher.mediaPlayer.play();
    }

    public void SecondSong(ActionEvent actionEvent) {
        System.out.println("second song");
//        pauseMusic(actionEvent);

        GameLauncher.mediaPlayer.stop();
        User user = User.getLoggedInUser();

        String fileName = getClass().getResource("/Media/abstract-future-bass-162604.mp3").toExternalForm();
        Media media = new Media(fileName);
        GameLauncher.mediaPlayer = new MediaPlayer(media);
        GameLauncher.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        GameLauncher.mediaPlayer.play();

    }

    public void thirdSong(ActionEvent actionEvent) {
        User user = User.getLoggedInUser();

        GameLauncher.mediaPlayer.stop();
        String fileName = getClass().getResource("/Media/price-of-freedom-33106.mp3").toExternalForm();
        Media media = new Media(fileName);
        GameLauncher.mediaPlayer = new MediaPlayer(media);
        GameLauncher.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        GameLauncher.mediaPlayer.play();
    }

    public void resumeGame(ActionEvent actionEvent) {
        Game game = User.getLoggedInUser().getCurrentGame();
        for (int i = 0; i < game.getAnimation().size(); i++) {
            game.getAnimation().get(i).play();
        }
        for (int i = 0; i < game.getAllTimeLines().size(); i++) {
            game.allTimeLines.get(i).play();
            System.out.println(game.getAllTimeLines().size());
        }
        pauseMenu.setVisible(false);
    }
}
