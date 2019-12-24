package defenders2FDE;

import javafx.stage.Screen;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Constants {

    // height and width values
    public static final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getMaxX();
    public static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getMaxY();

    public static final double HEADER_Y = SCREEN_HEIGHT / 10;
    public static final double FOOTER_Y = 9 * SCREEN_HEIGHT / 10;

    public static final int BACKGROUND_WIDTH = 2000;

    public static final int SS_WIDTH = (int) Screen.getPrimary().getBounds().getMaxX() / 48;
    public static final int SS_HEIGHT = (int) Screen.getPrimary().getBounds().getMaxX() / 48;

    public static final int ScreenSizePercent = (int) Screen.getPrimary().getBounds().getMaxX() / 50;

    //Size
    public static final int SpaceShipSize = 4 * ScreenSizePercent;
    public static final int AlienSize = 2 * ScreenSizePercent;
    public static final int QueenSize = 4 * ScreenSizePercent;
    public static final int DarwinSize = 5 * ScreenSizePercent;
    public static final int AsteroidSize = 3 * ScreenSizePercent;

    public static final int AstronautSize = 3 * ScreenSizePercent;

    //Speed
    public static final double SpaceShipSpeed = 20;
    public static final double AlienSpeed = 1;
    public static final double QueenSpeed = 0.7;
    public static final double DarwinSpeed = 1.5;
    public static final double AsteroidSpeed = 10;

    public static final double AstronautSpeed = 5;

    //Health
    public static final int SpaceShipHealth = 100;
    public static final int AlienHealth = 50;
    public static final int QueenHealth = 150;
    public static final int DarwinHealth = 250;
    public static final int AsteroidHealth = 50;

    //Collision Damage
    public static final int SpaceShipCollisionDamage = 0;
    public static final int AlienCollisionDamage = 50;
    public static final int QueenCollisionDamage = 100;
    public static final int DarwinCollisionDamage = 100;
    public static final int AsteroidCollisionDamage = 50;

    public static final int DarwinBulletCollisionDamage = 50;
    public static final int AlienBulletCollisionDamage = 20;
    public static final int PlayerBulletCollisionDamage = 50;

    //Points
    public static final int SpaceShipPoints = 0;
    public static final int AlienPoints = 100;
    public static final int QueenPoints = 300;
    public static final int DarwinPoints = 900;
    public static final int AsteroidPoints = 200;


    public static final int PAUSE_WIDTH = (int) Screen.getPrimary().getBounds().getMaxX() / 48;
    public static final int PAUSE_HEIGHT = (int) Screen.getPrimary().getBounds().getMaxX() / 48;

    // image paths
    public static final String GAME_BACKGROUND_IMAGE_PATH = "defenders2FDE/Assets/images/background.jpg";
    public static final String PLAYER_SPACESHIP_IMAGE_PATH_1 = "defenders2FDE/Assets/images/spaceship_1.png";
    public static final String PLAYER_SPACESHIP_IMAGE_PATH_2 = "defenders2FDE/Assets/images/spaceship_2.png";
    public static final String PLAYER_SPACESHIP_IMAGE_PATH_3 = "defenders2FDE/Assets/images/spaceship_3.png";
    public static final String PLAYER_SPACESHIP_IMAGE_PATH_4 = "defenders2FDE/Assets/images/spaceship_4.png";
    public static final String PLAYER_SPACESHIP_IMAGE_PATH_5 = "defenders2FDE/Assets/images/spaceship_5.png";
    public static final String PLAYER_SPACESHIP_IMAGE_PATH_6 = "defenders2FDE/Assets/images/spaceship_6.png";
    public static final String ENEMY_SPACESHIP_IMAGE_PATH = "defenders2FDE/Assets/images/alien.png";

    public static final String ASTEROID_IMAGE_PATH = "defenders2FDE/Assets/images/asteroid.png";
    public static final String ASTRONAUT_IMAGE_PATH = "defenders2FDE/Assets/images/astronaut.png";
    public static final String QUEEN_IMAGE_PATH = "defenders2FDE/Assets/images/queen.png";
    public static final String DARWIN_IMAGE_PATH = "defenders2FDE/Assets/images/darwin.png";

    public static final String PAUSE_IMAGE_PATH = "defenders2FDE/Assets/images/pause.jpg";
    public static final String RESUME_IMAGE_PATH = "defenders2FDE/Assets/images/resume.png";

    public static final String PLAYER_BULLET_IMAGE_PATH = "defenders2FDE/Assets/images/playerBullet.JPG";
    public static final String ENEMY_BULLET_IMAGE_PATH = "defenders2FDE/Assets/images/enemyBullet.JPG";

    public static final String BUTTON_CLICK_SOUND = "src/defenders2FDE/Assets/sounds/button_click_sound.mp3";
    public static final String PLAYER_FIRE_SOUND = "src/defenders2FDE/Assets/sounds/player_fire_sound.mpeg";

    // file paths
    public static final String highScoreFilePath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\Defender\\high_scores.dat";
    public static final String coinFilePath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\Defender\\coin.dat";
    public static final String shopFilePath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\Defender\\items.dat";
    public static final String folderPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\Defender";

    // Font
    public static final String FONT_NAME = "ARIAL";
    public static final int FONT_SIZE_MD = 32;
    public static final int FONT_SIZE_SM = 24;

}
