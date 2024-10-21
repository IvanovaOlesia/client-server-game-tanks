package edu.school21.clientTanks.view;


import edu.school21.clientTanks.JSONModel.GameData;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;



public class View {
    @FXML
    private ImageView player;
    @FXML
    private ImageView enemy;
    @FXML
    private AnchorPane field;
    private Scene scene;
    private  String resourcePathToBulletDown = getClass().getResource("/bulletDown.png").toExternalForm();
    private  String getResourcePathToBulletUp = getClass().getResource("/bulletUp.png").toExternalForm();



    public View() {
    }

    public View(Scene scene) {
        this.scene = scene;
    }

    @FXML
    public void initialize() {

    }
    public void moveTank(GameData playerData) {
        player.setLayoutX(playerData.getPositionPlayersX().get(0));
        enemy.setLayoutX(playerData.getPositionPlayersX().get(1));

    }

}
