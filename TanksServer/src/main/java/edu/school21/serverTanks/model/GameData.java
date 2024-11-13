package edu.school21.serverTanks.model;

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
    private boolean PlayerWin;
    private boolean EnemyWin;


    public GameData() {
        this.positionPlayersX = new ArrayList<>(Arrays.asList(270.00, 270.00));
        bulletListPlayer = new ArrayList<>();
        bulletListEnemy = new ArrayList<>();
        deleteBullet = false;
        healthEnemy = 1.0;
        healthPlayer = 1.0;

    }

    public void setPlayerWin(boolean playerWin) {
        PlayerWin = playerWin;
    }

    public void setEnemyWin(boolean enemyWin) {
        EnemyWin = enemyWin;
    }

    public double getHealthEnemy() {
        return healthEnemy;
    }

    public void setHealthEnemy(double healthEnemy) {
        this.healthEnemy = healthEnemy;
    }

    public double getHealthPlayer() {
        return healthPlayer;
    }

    public void setHealthPlayer(double healthPlayer) {
        this.healthPlayer = healthPlayer;
    }

    public boolean isDeleteBullet() {
        return deleteBullet;
    }

    public void setDeleteBullet(boolean deleteBullet) {
        this.deleteBullet = deleteBullet;
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
