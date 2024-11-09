package edu.school21.clientTanks.view;


import edu.school21.clientTanks.JSONModel.Bullet;
import edu.school21.clientTanks.JSONModel.GameData;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class View {
    @FXML
    private ImageView player;
    @FXML
    private ImageView enemy;
    @FXML
    private AnchorPane field;

    private  String resourcePathToBulletDown = getClass().getResource("/bulletDown.png").toExternalForm();
    private  String getResourcePathToBulletUp = getClass().getResource("/bulletUp.png").toExternalForm();
    private List<ImageView> bulletViewsUp;
    private List<ImageView> bulletViewsDown;



    public View() {
        bulletViewsUp = new ArrayList<>();
        bulletViewsDown = new ArrayList<>();
    }



    public void renderNewData(GameData playerData) throws InterruptedException {
        moveTank(playerData);
//        moveBullet(playerData);

    }

   public void moveBullet(GameData playerData){
        if (playerData.isDeleteBullet()){
           for(var image : bulletViewsUp){
               field.getChildren().remove(image);
           }
           bulletViewsUp.clear();
            for(var image : bulletViewsDown){
                field.getChildren().remove(image);
            }
            bulletViewsDown.clear();
        }

        for ( Bullet bullet: playerData.getBulletListPlayer()){
            Image imageBulletUp = new Image(getResourcePathToBulletUp);
            ImageView imageViewBulletUp = new ImageView(imageBulletUp);
            imageViewBulletUp.setLayoutX(bullet.getX());
            imageViewBulletUp.setLayoutY(bullet.getY());
            bulletViewsUp.add(imageViewBulletUp);
            field.getChildren().add(imageViewBulletUp);
        }
        for (Bullet bullet: playerData.getBulletListEnemy()){
            Image imageBulletUp = new Image(resourcePathToBulletDown);
            ImageView imageViewBulletDown = new ImageView(imageBulletUp);
            imageViewBulletDown.setLayoutX(bullet.getX());
            imageViewBulletDown.setLayoutY(bullet.getY());
            bulletViewsDown.add(imageViewBulletDown);
            field.getChildren().add(imageViewBulletDown);
        }

    }




    public void moveTank(GameData playerData) {
        player.setLayoutX(playerData.getPositionPlayersX().get(0));
        enemy.setLayoutX(playerData.getPositionPlayersX().get(1));
    }
//public void clearField() {
//  for (var value: bulletViewsUp){
//    field.getChildren().remove(value);
//  }
//  for (var value: bulletViewsDown){
//    field.getChildren().remove(value);
//  }
//}
}
