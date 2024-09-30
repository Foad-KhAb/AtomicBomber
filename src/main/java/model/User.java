package model;

import javafx.scene.image.Image;
import view.LoginMenu;

import java.util.ArrayList;

public class User {
    //constuctor
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.gameInfo = new GameInfo();
        try {
            this.avatarImage = new Image(getClass().getResource("/Images/Avatars/chandler.PNG").toExternalForm());
        } catch (Exception e) {
            System.out.println("sala");
        }
        this.gameSetting = new GameSetting(1, false, true);
        allUsers.add(this);
    }
    private GameSetting gameSetting;
    private ArrayList<Game> allGames = new ArrayList<>();
    private Image avatarImage = null;

    public Image getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(Image avatarImage) {
        this.avatarImage = avatarImage;
    }

    private String name;
    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        User.loggedInUser = loggedInUser;
    }

    private String password;
    private GameInfo gameInfo;
    public Game currentGame;

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    private static ArrayList<User> allUsers = new ArrayList<>();

    public ArrayList<Game> getAllGames() {
        return allGames;
    }

    public void setAllGames(ArrayList<Game> allGames) {
        this.allGames = allGames;
    }

    public GameSetting getGameSetting() {
        return gameSetting;
    }

    public void setGameSetting(GameSetting gameSetting) {
        this.gameSetting = gameSetting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        User.allUsers = allUsers;
    }
    //methods
    public static User getUserByUsername(String username){
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getName().equals(username))
                return allUsers.get(i);
        }
        return null;
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }
}
