package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Result;
import model.User;
import view.MainMenu;
import view.RegisterMenu;

public class LoginMenuController {
    public TextField username;
    public TextField password;

    public void signUp(MouseEvent mouseEvent) {
        // check username validation
        // check if username is used
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

    public Result login(ActionEvent actionEvent) {
        //check if username is already exists
        if (User.getUserByUsername(username.getText()) == null)
            return new Result(false, "Username Doesn't Exist");

        User userToLogin = User.getUserByUsername(username.getText());
        //check if password mathcer the username
        if (!userToLogin.getPassword().equals(password.getText())) {
            return new Result(false, "Password Doesn't Match");
        }
        User.setLoggedInUser(userToLogin);
        try {
            new MainMenu().start(ApplicationController.getStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(true, "");
    }

    private boolean checkUsername(String username) {
        return User.getUserByUsername(username) != null;
    }


    public void goToRegisterMenu(ActionEvent actionEvent) {
        try {
            new RegisterMenu().start(ApplicationController.getStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enterGame(ActionEvent actionEvent) {
        try {
            User user = new User("guest" , "guest");
            User.setLoggedInUser(user);
            GameController.gameStarter();
        }
        catch(Exception e){
            System.out.println("SAL");
            e.printStackTrace();
        }
    }
}
