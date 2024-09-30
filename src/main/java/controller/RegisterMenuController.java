package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.User;
import view.LoginMenu;
import view.RegisterMenu;

public class RegisterMenuController {


    public TextField username;
    public TextField password;

    public void goToRegisterMenu(ActionEvent actionEvent) {
        try {
            new LoginMenu().start(ApplicationController.getStage());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void register(ActionEvent actionEvent) {

        if (User.getUserByUsername(username.getText()) != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("error");
            alert.setContentText("Username is already used");
            alert.showAndWait();
            return;
        }
        // check if username field is null
        if (username.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("error");
            alert.setContentText("Username is null");
            alert.showAndWait();
            return;
        }
        User user = new User(username.getText(), password.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText("Successful");
        alert.setContentText("User Created Successful");
        alert.showAndWait();
        username.setText("");
        password.setText("");
    }
}
