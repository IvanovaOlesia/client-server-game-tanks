package edu.school21.serverTanks.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameData {
    private  List<Double> positionPlayersX ;


    public GameData() {
        this.positionPlayersX = new ArrayList<>(Arrays.asList(472.00,472.00));

    }
    public void addPosition(double position, int index){
        positionPlayersX.set(index, position);
    }

    public List<Double> getPositionPlayersX() {
        return positionPlayersX;
    }




}
