package view;

import controller.ApplicationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.User;

public class ProfileMenu extends Application {
    private User loggedInUser;

    public User getLoggedInUser() {
        return loggedInUser;
    }
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public ProfileMenu(User user){
        this.loggedInUser = user;
    }
    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        stage.centerOnScreen();
        ApplicationController.setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(LoginMenu.class.getResource("/FXML/ProfileMenu.fxml"));
        root.getChildren().add(pane);
        Scene scene = new Scene(root, 500, 500);
        Image icon = new Image(ProfileMenu.class.getResource("/Images/Icon/AtomicBomberIcon.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
}
