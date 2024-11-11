package edu.school21.serverTanks.model;

public class GameConstants {
    public static final int FIELD_WIDTH = 600;
    public static final int FIELD_HEIGHT = 600;
    public static final int TANK_HEIGHT = 85;
    public static final int TANK_WIDTH = 61;
    public static final double TANK_MOVEMENT_OFFSET = 10;
    public static final double MUZZLE_LENGTH = TANK_HEIGHT * 0.29;
    public static final double BULLET_LENGTH = 11.00;
    public static final double PLAYER_Y_POSITION = FIELD_HEIGHT - TANK_HEIGHT - GameConstants.HP_HEIGHT + MUZZLE_LENGTH;
    public static final double ENEMY_Y_POSITION = TANK_HEIGHT + GameConstants.HP_HEIGHT - MUZZLE_LENGTH;
    public static final double HP_HEIGHT = 24.0;
}
