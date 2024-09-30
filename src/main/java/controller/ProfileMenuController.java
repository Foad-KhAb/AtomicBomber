package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.User;
import view.AvatarMenu;
import view.LoginMenu;

import java.util.ArrayList;

public class ProfileMenuController {
    public TextField newPasswordOrUsername;

    public void changeUsername(ActionEvent actionEvent) {
        //check if the new username is the same as the previous username
        User loggedinUser = User.getLoggedInUser();
        if (loggedinUser.getName().equals(newPasswordOrUsername.toString())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information dialog");
            alert.setHeaderText(null);
            alert.setContentText("please enter new username");
            alert.showAndWait();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information dialog");
        alert.setHeaderText(null);
        alert.setContentText("username changed successfully");
        alert.showAndWait();
        loggedinUser.setName(newPasswordOrUsername.getText());
        User.setLoggedInUser(loggedinUser);
        newPasswordOrUsername.setText("");
        return;
    }

    public void changePassword(ActionEvent actionEvent) {
        User loggedinUser = User.getLoggedInUser();
        if (loggedinUser.getPassword().equals(newPasswordOrUsername.toString())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information dialog");
            alert.setHeaderText(null);
            alert.setContentText("please enter new password");
            alert.showAndWait();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information dialog");
        alert.setHeaderText(null);
        alert.setContentText("password changed successfully");
        alert.showAndWait();

        loggedinUser.setPassword(newPasswordOrUsername.getText());
//        User.setLoggedInUser(

        newPasswordOrUsername.setText("");

        return;
    }

    public void deleteAccount(ActionEvent actionEvent) throws Exception {
        User loggedinUser = User.getLoggedInUser();
        //get all users and remove logged-in user from all users and set it back
        ArrayList<User> allUsers = User.getAllUsers();
        allUsers.remove(loggedinUser);
        User.setAllUsers(allUsers);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information dialog");
        alert.setHeaderText(null);
        alert.setContentText("account deleted successfully");
        alert.showAndWait();
        new LoginMenu().start(ApplicationController.getStage());
    }
    public void logout(ActionEvent actionEvent) throws Exception {
        User.setLoggedInUser(null);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information dialog");
        alert.setHeaderText(null);
        alert.setContentText("logged-out successfully");
        alert.showAndWait();
        new LoginMenu().start(ApplicationController.getStage());
    }

    public void goToAvatarMenu(ActionEvent actionEvent) {
        try {
            new AvatarMenu().start(ApplicationController.getStage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
