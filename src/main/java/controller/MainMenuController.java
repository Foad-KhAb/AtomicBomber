package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.GameInfo;
import model.GameSetting;
import model.User;
import view.*;

public class MainMenuController {
    public Label username;
    public AnchorPane settingPane;
    public Button easy;
    public Button moderate;
    public Button hard;
    public Circle imageView;

    @FXML
    public void initialize() {
        changeLabel();
        if (User.getLoggedInUser().getAvatarImage() == null)
            System.out.println("sa");
        imageView.setFill(new ImagePattern(User.getLoggedInUser().getAvatarImage()));
    }

    public void changeLabel() {
        username.setText(User.getLoggedInUser().getName());
    }

    public void chop(MouseEvent mouseEvent) {
        System.out.println("saka");
    }

    public void goToProfileMenu(ActionEvent actionEvent) {
        try {
            new ProfileMenu(User.getLoggedInUser()).start(ApplicationController.getStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showScoreTable(ActionEvent actionEvent) {
    }

    public void showGameSetting(ActionEvent actionEvent) throws Exception {
        settingPane.setVisible(true);

    }

    public void startNewGame(ActionEvent actionEvent) {
        GameInfo gameInfo = new GameInfo();
        try {
            GameController.gameStarter();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void resumeGame(ActionEvent actionEvent) {
        try {
            GameController.gameStarter();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setGameSetting(ActionEvent actionEvent) {
        Button button = ((Button) actionEvent.getSource());

        User user = User.getLoggedInUser();
        GameSetting gameSetting = user.getGameSetting();
        if (button == easy)
            gameSetting.setDifficulty(1);
        else if (button == moderate)
            gameSetting.setDifficulty(2);
        else if (button == hard)
            gameSetting.setDifficulty(3);
        user.setGameSetting(gameSetting);
        User.setLoggedInUser(user);
    }

    public void settingSet(ActionEvent actionEvent) {
        settingPane.setVisible(false);
    }

    public void muteOrUnmute(ActionEvent actionEvent) {
        User user = User.getLoggedInUser();
        GameSetting gameSetting = user.getGameSetting();
        gameSetting.setMute(!gameSetting.isMute());
        user.setGameSetting(gameSetting);
        User.setLoggedInUser(user);
    }

    public void setBlackAndWhite(ActionEvent actionEvent) {
        User user = User.getLoggedInUser();
        GameSetting gameSetting = user.getGameSetting();
        gameSetting.setColorFullScreen(!gameSetting.isColorFullScreen());
        user.setGameSetting(gameSetting);
        User.setLoggedInUser(user);
    }

    public void exit(ActionEvent actionEvent) throws Exception {
        new LoginMenu().start(ApplicationController.getStage());
    }
}
