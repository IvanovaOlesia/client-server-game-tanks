package edu.school21.clientTanks.JSONModel;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameData {
    private double healthEnemy;
    private double healthPlayer;
    private  List<Double> positionPlayersX ;
    private  List<Bullet> bulletListPlayer;
    private  List<Bullet> bulletListEnemy;
    private boolean deleteBullet;


    public GameData() {
        this.positionPlayersX = new ArrayList<>(Arrays.asList(472.00,472.00));
        bulletListPlayer = new ArrayList<>();
        bulletListEnemy = new ArrayList<>();
        deleteBullet = false;

    }

    public double getHealthEnemy() {
        return healthEnemy;
    }

    public double getHealthPlayer() {
        return healthPlayer;
    }

    public boolean isDeleteBullet() {
        return deleteBullet;
    }

    public void addPosition(double position, int index){
        positionPlayersX.set(index, position);
    }

    public List<Double> getPositionPlayersX() {
        return positionPlayersX;
    }
    public void addBulletPlayer(Bullet bullet){
        bulletListPlayer.add(bullet);
    }

    public List<Bullet> getBulletListPlayer() {
        return bulletListPlayer;
    }

    public List<Bullet> getBulletListEnemy() {
        return bulletListEnemy;
    }

    public void setBulletListPlayer(List<Bullet> bulletListPlayer) {
        this.bulletListPlayer = bulletListPlayer;
    }

    public void setBulletListEnemy(List<Bullet> bulletListEnemy) {
        this.bulletListEnemy = bulletListEnemy;
    }
}
