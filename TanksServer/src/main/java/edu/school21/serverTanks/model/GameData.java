package edu.school21.serverTanks.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameData {
    private  List<Double> positionPlayersX ;
    private  List<Bullet> bulletListPlayer;
    private  List<Bullet> bulletListEnemy;
    private boolean deleteBullet;


    public GameData() {
        this.positionPlayersX = new ArrayList<>(Arrays.asList(270.00, 270.00));
        bulletListPlayer = new ArrayList<>();
        bulletListEnemy = new ArrayList<>();
        deleteBullet = false;

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
