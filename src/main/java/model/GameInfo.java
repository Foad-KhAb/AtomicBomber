package model;

public class GameInfo {
    private int kills = 0;
    private int shootedBullets = 0;
    private int waveNumber = 1;
    private int radioActiveBombs = 0;
    private int clusterBombs = 0;
    public int HP = 3;

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getShootedBullets() {
        return shootedBullets;
    }

    public void setShootedBullets(int shootedBullets) {
        this.shootedBullets = shootedBullets;
    }

    public int getWaveNumber() {
        return waveNumber;
    }

    public void setWaveNumber(int waveNumber) {
        this.waveNumber = waveNumber;
    }

    public int getRadioActiveBombs() {
        return radioActiveBombs;
    }

    public void setRadioActiveBombs(int radioActiveBombs) {
        this.radioActiveBombs = radioActiveBombs;
    }

    public int getClusterBombs() {
        return clusterBombs;
    }

    public void setClusterBombs(int clusterBombs) {
        this.clusterBombs = clusterBombs;
    }
}
