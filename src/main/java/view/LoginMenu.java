package view;

import controller.ApplicationController;
import controller.LoginMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.scene.layout.StackPane;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class LoginMenu extends Application {

    public LoginMenuController controller;
    private ImageView draggedImageView;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        Image image = new Image(LoginMenu.class.getResource("/Images/xx.png").toExternalForm());
//        ImageView imageView = new ImageView(image);
        StackPane root = new StackPane();
//        root.getChildren().add(imageView);
        stage.setTitle("Atomic Bomber");
        stage.setResizable(false);

        stage.centerOnScreen();
        ApplicationController.setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(LoginMenu.class.getResource("/FXML/LoginMenu.fxml"));
        controller = fxmlLoader.getController();
        root.getChildren().addAll(pane);
//        imageView.setFitHeight(500);
//        imageView.setPreserveRatio(true);
        Scene scene = new Scene(root, 600, 400);
        Image icon = new Image(ProfileMenu.class.getResource("/Images/Icon/AtomicBomberIcon.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
    private void setBackGround(Pane pane){
        Image backgroundImage = new Image(LoginMenu.class.getResource("/Images/89a55917-7c5a-4f41-916b-1be28fc27a92.JPG").toExternalForm());
    }
    private BackgroundImage createBackgroundImage () {
        Image image = new Image(LoginMenu.class.getResource("/Images/89a55917-7c5a-4f41-916b-1be28fc27a92.JPG").toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }

}