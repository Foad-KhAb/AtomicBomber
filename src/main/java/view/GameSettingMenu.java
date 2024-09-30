package view;

import controller.ApplicationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.GameSetting;

public class GameSettingMenu extends Application {

    public Button easy;
    public Button moderate;
    public Button hard;

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        stage.centerOnScreen();
        ApplicationController.setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(GameSettingMenu.class.getResource("/FXML/GameSettingMenu.fxml"));
        root.getChildren().add(pane);
        Scene scene = new Scene(root, 500, 500);
        Image icon = new Image(ProfileMenu.class.getResource("/Images/Icon/AtomicBomberIcon.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public void handleEasyClicked(MouseEvent mouseEvent) {

    }

    public void handleModerateClicked(MouseEvent mouseEvent) {
    }

    public void handleHardClicked(MouseEvent mouseEvent) {
    }
    public void selectOption(Button selectedButton){
        selectedButton.setStyle("");
    }
}
