package view;

import controller.ApplicationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Stack;

public class AvatarMenu extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        stage.centerOnScreen();
        ApplicationController.setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(LoginMenu.class.getResource("/FXML/AvatarMenu.fxml"));
        root.getChildren().add(pane);
        Scene scene = new Scene(root, 500 , 500);
        Image icon = new Image(ProfileMenu.class.getResource("/Images/Icon/AtomicBomberIcon.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
}