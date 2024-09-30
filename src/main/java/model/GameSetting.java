package model;

public class GameSetting {
    public GameSetting(int difficulty, boolean isMute, boolean isColorFullScreen) {
        this.difficulty = difficulty;
        this.isMute = isMute;
        this.isColorFullScreen = isColorFullScreen;
    }

    private int difficulty;
    private boolean isMute;
    private boolean isColorFullScreen;

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isMute() {
        return isMute;
    }

    public void setMute(boolean mute) {
        isMute = mute;
    }

    public boolean isColorFullScreen() {
        return isColorFullScreen;
    }

    public void setColorFullScreen(boolean colorFullScreen) {
        isColorFullScreen = colorFullScreen;
    }
}
