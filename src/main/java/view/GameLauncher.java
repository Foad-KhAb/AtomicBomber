package view;

import controller.ApplicationController;
import controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import model.gameObjects.*;
import view.Animations.*;

import java.util.ArrayList;
import java.util.Random;

import static controller.GameController.createCluster;
import static controller.GameController.createRadioActive;
//import static view.GameLauncher.GameController.*;

public class GameLauncher extends Application {
    public Airplane airplane;
    private final Game game;
    public boolean theEnd;
    public Button pause;
    //    public Label accuracy;
//    public Label kills;
//    public Label wave;
    private Timeline createTanks, createTrucks;
    public Timeline timeline, updatingInfo, showWave;
    public static Pane pane;
    public PauseTransition delay;
    public GameController gameController;
    public static MediaPlayer mediaPlayer;
    private int round;

    @Override
    public void start(Stage stage) throws Exception {
        pane = new Pane();
        FXMLLoader fxmlLoader = new FXMLLoader(GameLauncher.class.getResource("/FXML/Game.fxml"));
        pane = FXMLLoader.load(GameLauncher.class.getResource("/FXML/Game.fxml"));

        System.out.println("kills = " + User.getLoggedInUser().getGameInfo().getKills() +
                "\nwave = " + User.getLoggedInUser().getGameInfo().getWaveNumber() +
                "\nshot bullet = " + User.getLoggedInUser().getGameInfo().getShootedBullets());


        //Load the media file
        String fileName = getClass().getResource("/Media/abstract-future-bass-162604.mp3").toExternalForm();
        Media media = new Media(fileName);
        game.setMediaPlayer(mediaPlayer);
        //create mediaplayer
        mediaPlayer = new MediaPlayer(media);
        // Set to repeat indefinitely
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Start playing the media
        mediaPlayer.play();
        User user = User.getLoggedInUser();
        user.setCurrentGame(game);
        User.setLoggedInUser(user);
        gameController = fxmlLoader.getController();
        stage.centerOnScreen();
        setBackGroundImage(pane);
        setSize(pane);
        createPlane(pane);
        //create a building
        createBuilding(pane);
        //create a bunker
        createBunker(pane);
        //create trees
        createTrees(pane);
        if (round > 1) {
            System.out.println("round " + round);
            //creating some tanks
            createTanks = new Timeline(new KeyFrame(Duration.seconds(15),
                    actionEvent -> createTank(pane)));
            createTanks.setCycleCount(3);
            createTanks.play();
            game.allTimeLines.add(createTanks);
        }
        if (round == 3) {
            Random random = new Random();
            Timeline createMigs = new Timeline(new KeyFrame(Duration.seconds(15),
                    actionEvent -> createMig()));
            createMigs.setCycleCount(3);
            createMigs.play();
            game.allTimeLines.add(createMigs);
        }
        //creating some trucks
//        ArrayList<Timeline> allTimeLines = game.getAllTimeLines();
//        game.setAllTimeLines(allTimeLines);

        createTrucks = new Timeline(new KeyFrame(Duration.seconds(15),
                actionEvent -> createTruck(pane)));
        createTrucks.setCycleCount(2);
        createTrucks.play();
        game.allTimeLines.add(createTrucks);
        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-background-color: lightblue; -fx-padding: 10px;");
        messageLabel.setVisible(false);
        showMessage("salam", messageLabel);
//        pane.getChildren().add(messageLabel);

        //creating pasue option


        stage.setResizable(true);
        Image icon = new Image(ProfileMenu.class.getResource("/Images/Icon/AtomicBomberIcon.png").toExternalForm());
        stage.getIcons().add(icon);
        Scene scene = new Scene(pane);
        stage.setScene(scene);


        changeFocus(pane);
        stage.show();
        delay = new PauseTransition(Duration.seconds(60));
//        delay.setOnFinished(event -> stage.close());
        delay.play();

//        allTimeLines = game.getAllTimeLines();
//        allTimeLines.add(timeline);
//        game.setAllTimeLines(allTimeLines);
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), actionEvent -> {
                    System.out.println("kills = " + User.getLoggedInUser().getGameInfo().getKills() +
                            "\nwave = " + User.getLoggedInUser().getGameInfo().getWaveNumber() +
                            "\nshot bullet = " + User.getLoggedInUser().getGameInfo().getShootedBullets());
                    if (delay.getCurrentTime().toSeconds() > 45 && getPaneSize(pane) == 9) {
                        if (round == 3) {
                            try {
                                pane.getChildren().get(3).setVisible(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            timeline.stop();
                            theEnd = true;
                            return;
                        }

                        User user1 = User.getLoggedInUser();
                        GameInfo gameInfo = user1.getGameInfo();
                        System.out.println("kills = " + User.getLoggedInUser().getGameInfo().getKills() +
                                "\nwave = " + User.getLoggedInUser().getGameInfo().getWaveNumber() +
                                "\nshot bullet = " + User.getLoggedInUser().getGameInfo().getShootedBullets());
                        gameInfo.setWaveNumber(gameInfo.getWaveNumber() + 1);
                        user1.setGameInfo(gameInfo);
                        User.setLoggedInUser(user1);
                        try {
                            new GameLauncher(User.getLoggedInUser(), round + 1).start(ApplicationController.getStage());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

//                        stage.close();
                        timeline.stop();
                        theEnd = true;
                        return;
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        game.allTimeLines.add(timeline);

        updatingInfo = new Timeline(
                new KeyFrame(Duration.seconds(0.1), actionEvent -> {
                    GameController.updateLabel(pane);
                })
        );
        updatingInfo.setCycleCount(Timeline.INDEFINITE);
        updatingInfo.play();
        game.allTimeLines.add(updatingInfo);


    }

    private void createMig() {
        Mig mig = new Mig(game, new Random());
        mig.setMigAnimation(new MigAnimation(game, pane, mig, airplane));
        game.addAnimation(mig.getMigAnimation());
        mig.getMigAnimation().play();


        Timeline timeline1 = new Timeline(
                new KeyFrame(Duration.seconds(1), actionEvent -> {
                    double xA = airplane.getX();
                    double yA = airplane.getY();
                    double yT = mig.getY();
                    double xT = mig.getX();
                    double distanceX = xA - xT;
                    double distanceY = yA - yT;
                    double totalDistnace = Math.pow(distanceY, 2) + Math.pow(distanceX, 2);
                    totalDistnace = Math.sqrt(totalDistnace);
                    System.out.println(totalDistnace);
                    if (totalDistnace < 150 * User.getLoggedInUser().getGameSetting().getDifficulty()) {
                        MigBullet migBullet = new MigBullet(game, mig, airplane);
                        migBullet.setMigBulletAnimation(new MigBulletAnimation(game, pane, migBullet, airplane, mig));
                        game.addAnimation(migBullet.getMigBulletAnimation());
                        migBullet.getMigBulletAnimation().play();
                        pane.getChildren().add(migBullet);
                    }
                })
        );
        timeline1.setCycleCount(-1);
        timeline1.play();
        game.allTimeLines.add(timeline1);
        pane.getChildren().add(mig);
    }

    public static void changeFocus(Pane pane) {
        for (int i = 0; i < pane.getChildren().size(); i++) {
            if (pane.getChildren().get(i).getClass().equals(Airplane.class))
                pane.getChildren().get(i).requestFocus();
        }
//        pane.getChildren().get(5).requestFocus();
    }

    private void showMessage(String message, Label messageLabel) {
        messageLabel.setText(message);
        messageLabel.setVisible(true);

        // Hide the message after 2 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> messageLabel.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
        game.allTimeLines.add(timeline);
    }

    private void setSize(Pane pane) {
        pane.setMaxWidth(569);
        pane.setMaxHeight(320);
        pane.setMinWidth(569);
        pane.setMinHeight(320);
    }

    private void createPlane(Pane pane) {
        airplane = new Airplane(game);
        airplane.setPlaneAnimation(new AirplaneAnimation(game, pane, airplane));
        game.addAnimation(airplane.getPlaneAnimation());
        airplane.getPlaneAnimation().play();
        airplane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String kryName = keyEvent.getCode().getName();
                switch (kryName) {
                    case "A":
                        leftDir(airplane.getPlaneAnimation());
                        break;
                    case "S":
                        downDir(airplane.getPlaneAnimation());
                        break;
                    case "D":
                        rightDir(airplane.getPlaneAnimation());
                        break;
                    case "W":
                        upDir(airplane.getPlaneAnimation());
                        break;
                    case "Space":
                        createBomb(game, pane, airplane);
                        break;
                    case "R":
                        createRadioActive(game, pane, airplane);
                        break;
                    case "C":
                        createCluster(game, pane, airplane);
                        break;
                    case "T":
                        createTank(pane);
                        break;
                    case "P":
                        goToNextWave(pane);
                        break;
                    case "H":
                        addHP(pane);
                        break;
                    case "Control":
                        addCluster(pane);
                        break;
                    case "G":
                        addRadioActive(pane);
                        break;
                }
            }
        });
        pane.getChildren().add(airplane);
    }

    private void addRadioActive(Pane pane) {
        User user = User.getLoggedInUser();
        GameInfo gameInfo = user.getGameInfo();
        gameInfo.setRadioActiveBombs(gameInfo.getRadioActiveBombs() + 1);
        user.setGameInfo(gameInfo);
        User.setLoggedInUser(user);
    }

    private void addCluster(Pane pane) {
        User user = User.getLoggedInUser();
        GameInfo gameInfo = user.getGameInfo();
        gameInfo.setClusterBombs(gameInfo.getClusterBombs() + 1);
        user.setGameInfo(gameInfo);
        User.setLoggedInUser(user);
    }

    private void addHP(Pane pane) {
        User user = User.getLoggedInUser();
        GameInfo gameInfo = user.getGameInfo();
        gameInfo.HP++;
        user.setGameInfo(gameInfo);
        User.setLoggedInUser(user);
    }

    private void goToNextWave(Pane pane) {
        if (round == 3) {
            try {
                pane.getChildren().get(3).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            timeline.stop();
            theEnd = true;
            return;
        }

        User user1 = User.getLoggedInUser();
        GameInfo gameInfo = user1.getGameInfo();
        System.out.println("kills = " + User.getLoggedInUser().getGameInfo().getKills() +
                "\nwave = " + User.getLoggedInUser().getGameInfo().getWaveNumber() +
                "\nshot bullet = " + User.getLoggedInUser().getGameInfo().getShootedBullets());
        gameInfo.setWaveNumber(gameInfo.getWaveNumber() + 1);
        user1.setGameInfo(gameInfo);
        User.setLoggedInUser(user1);
        try {
            new GameLauncher(User.getLoggedInUser(), round + 1).start(ApplicationController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//                        stage.close();
        timeline.stop();
        theEnd = true;
        return;
    }

    private int getPaneSize(Pane pane) {
        int num = 0;
        for (int i = 0; i < pane.getChildren().size(); i++) {
            if (!pane.getChildren().get(i).getClass().equals(Tree.class))
                num++;
        }
        System.out.println(num);
        return num;
    }

    private void upDir(AirplaneAnimation airplaneAnimation) {
        double radian = airplaneAnimation.getRadian();
        radian = radian % 360;
        radian += 3;
        airplaneAnimation.setRadian(radian);
    }

    private void rightDir(AirplaneAnimation airplaneAnimation) {
        double radian = airplaneAnimation.getRadian();
        radian = radian % 360;
        if (radian < 0) radian += 360;
        if (radian > 0 && radian < 180) {
            radian -= 3;
        } else if (radian > 180 && radian < 360)
            radian += 3;
        airplaneAnimation.setRadian(radian % 360);
    }

    private void downDir(AirplaneAnimation airplaneAnimation) {
        double radian = airplaneAnimation.getRadian();
        radian = radian % 360;
        if (radian < 0) radian += 360;
        radian -= 3;
        airplaneAnimation.setRadian(radian % 360);
    }

    private void leftDir(AirplaneAnimation airplaneAnimation) {
        double radian = airplaneAnimation.getRadian();
        radian = radian % 360;
        if (radian < 0) radian += 360;
        if (radian >= 0 && radian < 180) {
            radian += 3;
        } else if (radian > 180 && radian < 360)
            radian -= 3;
        airplaneAnimation.setRadian(radian);
    }

    private void createBunker(Pane pane) {
        pane.getChildren().add(new Bunker(game));
    }

    private void createBuilding(Pane pane) {
        pane.getChildren().add(new Building(game));
    }

    private void createTruck(Pane pane) {
        Truck truck = new Truck(game, new Random());
        truck.setTruckAnimation(new TruckAnimation(game, pane, truck));
        game.addAnimation(truck.getTruckAnimation());
        truck.getTruckAnimation().play();
//        adding truck object to invaders
        pane.getChildren().add(truck);
    }

    private void createTank(Pane pane) {
        Tank tank = new Tank(game, new Random());
        tank.setTankAnimations(new TankAnimations(game, pane, tank, airplane));
        game.addAnimation(tank.getTankAnimations());
        tank.getTankAnimations().play();
//        tank.setTankShootingAnimation(new TankShootingAnimation(game, pane, tank, airplane, 2));
//        game.addAnimation(tank.getTankShootingAnimation());
//        tank.getTankShootingAnimation().play();


        Timeline timeline1 = new Timeline(
                new KeyFrame(Duration.seconds(0.1), actionEvent -> {
                    double xA = airplane.getX();
                    double yA = airplane.getY();
                    double yT = tank.getY();
                    double xT = tank.getX();
                    double distanceX = xA - xT;
                    double distanceY = yA - yT;
                    double totalDistnace = Math.pow(distanceY, 2) + Math.pow(distanceX, 2);
                    totalDistnace = Math.sqrt(totalDistnace);
                    System.out.println(totalDistnace);
                    if (totalDistnace < 150 * User.getLoggedInUser().getGameSetting().getDifficulty()) {
                        TankBullet tankBullet = new TankBullet(game, tank, airplane);
                        tankBullet.setTankBulletAnimation(new TankBulletAnimation(game, pane, tankBullet, airplane, tank));
                        game.addAnimation(tankBullet.getTankBulletAnimation());
                        tankBullet.getTankBulletAnimation().play();
                        pane.getChildren().add(tankBullet);
                    }
                })
        );
        timeline1.setCycleCount(-1);
        timeline1.play();
        game.allTimeLines.add(timeline1);
        pane.getChildren().add(tank);
    }

    private void createTrees(Pane pane) {
        pane.getChildren().add(createTree());
        pane.getChildren().add(createTree());
        pane.getChildren().add(createTree());
        pane.getChildren().add(createTree());
        pane.getChildren().add(createTree());
        pane.getChildren().add(createTree());
    }

    public GameLauncher(User user, int round) {
        System.out.println(round);
        game = new Game(user);
        user.setCurrentGame(game);
        User.setLoggedInUser(user);
        this.round = round;
    }

    private Tree createTree() {
        Tree tree = new Tree(game);
        return tree;
    }

    private void setBackGroundImage(Pane pane) {
        try {
            // Load background image
            Image image = new Image(getClass().getResource("/Images/GameObjects/sky.png").toExternalForm());

            // Create BackgroundImage
            BackgroundImage backgroundImage = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT
            );

            // Set background to the Pane
            pane.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
            System.out.println("Failed to set background image: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for detailed error information
        }
    }

    public static void createBomb(Game game, Pane pane, Airplane airplane) {

        User user = User.getLoggedInUser();
        GameInfo gameInfo = user.getGameInfo();
        gameInfo.setShootedBullets(gameInfo.getShootedBullets() + 1);
        user.setGameInfo(gameInfo);
        User.setLoggedInUser(user);


        Bullet bullet = new Bullet(airplane, game);
        bullet.setBombAnimation(new BombAnimation(game, pane, bullet, airplane));
        game.addAnimation(airplane.getPlaneAnimation());
        bullet.getBombAnimation().play();
        pane.getChildren().add(bullet);
    }


    public int getRandomInteger(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(lowerBound, upperBound);
    }

}
