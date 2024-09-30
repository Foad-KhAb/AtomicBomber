package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import model.User;
import view.AvatarMenu;
import view.LoginMenu;

import java.io.File;
import java.util.Random;

public class AvatarMenuController {


    public Circle avatarImage;
    public Circle image5;
    public Circle image4;
    public Circle image6;
    public Circle image1;
    public Circle image2;
    public Circle image3;
    public ImagePattern imagePattern1 = new ImagePattern(new Image(getClass().getResource("/Images/Avatars/chandler.png").toExternalForm()));
    public ImagePattern imagePattern2 = new ImagePattern(new Image(getClass().getResource("/Images/Avatars/joey.png").toExternalForm()));
    public ImagePattern imagePattern3 = new ImagePattern(new Image(getClass().getResource("/Images/Avatars/Monica.png").toExternalForm()));
    public ImagePattern imagePattern4 = new ImagePattern(new Image(getClass().getResource("/Images/Avatars/phebe.png").toExternalForm()));
    public ImagePattern imagePattern5 = new ImagePattern(new Image(getClass().getResource("/Images/Avatars/rachel.png").toExternalForm()));
    public ImagePattern imagePattern6 = new ImagePattern(new Image(getClass().getResource("/Images/Avatars/ross.png").toExternalForm()));

    @FXML
    public void initialize() {
        if (User.getLoggedInUser().getAvatarImage() == null)
            System.out.println("sa");
        avatarImage.setFill(new ImagePattern( User.getLoggedInUser().getAvatarImage()));
    }

    public void randomAvatar(ActionEvent actionEvent) {
        Image newImage = getRandomDefaultImage();
        User user = User.getLoggedInUser();
        user.setAvatarImage(newImage);
        initialize();
    }

    public Image getRandomDefaultImage() {
        String[] defaultAvatarPath = {"chandler.PNG", "joey.PNG", "monica.PNG", "phebe.PNG"
                , "ross.png", "rachel.png"};
        Random random = new Random();
        int index = random.nextInt(defaultAvatarPath.length);
        String randomAvatar = defaultAvatarPath[index];
        try {
            return new Image(AvatarMenu.class.getResource("/Images/Avatars/" + randomAvatar).toExternalForm());
        }
        catch (Exception e){
            System.out.println("HI");
        }
        return User.getLoggedInUser().getAvatarImage();
    }

    public void chooseBetweenOptions(ActionEvent actionEvent) {
        image1.setVisible(true);
        image2.setVisible(true);
        image3.setVisible(true);
        image4.setVisible(true);
        image5.setVisible(true);
        image6.setVisible(true);

        image1.setFill(imagePattern1);
        image2.setFill(imagePattern2);
        image3.setFill(imagePattern3);
        image4.setFill(imagePattern4);
        image5.setFill(imagePattern5);
        image6.setFill(imagePattern6);

        image2.setOnMouseClicked(event -> callSelectAvatars(imagePattern1));
        image1.setOnMouseClicked(event -> callSelectAvatars(imagePattern2));
        image3.setOnMouseClicked(event -> callSelectAvatars(imagePattern3));
        image4.setOnMouseClicked(event -> callSelectAvatars(imagePattern4));
        image5.setOnMouseClicked(event -> callSelectAvatars(imagePattern5));
        image6.setOnMouseClicked(event -> callSelectAvatars(imagePattern6));



    }

    private void callSelectAvatars(ImagePattern imagePattern) {


        User.getLoggedInUser().setAvatarImage(imagePattern.getImage());

        avatarImage.setFill(imagePattern);

        image1.setVisible(false);
        image2.setVisible(false);
        image3.setVisible(false);
        image4.setVisible(false);
        image5.setVisible(false);
        image6.setVisible(false);

    }

    public void chooseFileFromSystem(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("choose image");

        File selectedFile = fileChooser.showOpenDialog(ApplicationController.getStage());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            avatarImage.setFill(new ImagePattern(image));
        }
    }


    public void dragDropped(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            File file = db.getFiles().get(0);
            avatarImage.setFill(new ImagePattern(new Image(file.toURI().toString())));
        }
        dragEvent.setDropCompleted(success);
        dragEvent.consume();
    }

    public void dragOver(DragEvent event) {
        if (event.getGestureSource() != avatarImage &&
                event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }
}
