package defenders2FDE;

import javafx.stage.Screen;

public class Constants {
    public static final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getMaxX();
    public static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getMaxY();

    public static final double HEADER_Y = SCREEN_HEIGHT / 10;
    public static final double FOOTER_Y = 9 * SCREEN_HEIGHT / 10;

    public static final int BACKGROUND_WIDTH = 2000;

    public static final int SS_WIDTH = (int) Screen.getPrimary().getBounds().getMaxX() / 48;
    public static final int SS_HEIGHT = (int) Screen.getPrimary().getBounds().getMaxX() / 48;

    // image paths
    public static final String GAME_BACKGROUND_IMAGE_PATH = "defenders2FDE/Assets/images/background.jpg";
    public static final String PLAYER_SPACESHIP_IMAGE_PATH = "defenders2FDE/Assets/images/spaceship.png";
    public static final String ENEMY_SPACESHIP_IMAGE_PATH = "defenders2FDE/Assets/images/alienspaceship.png";

    public static final String PLAYER_BULLET_IMAGE_PATH = "defenders2FDE/Assets/images/playerBullet.JPG";
    public static final String ENEMY_BULLET_IMAGE_PATH = "defenders2FDE/Assets/images/enemyBullet.JPG";

    public static final String BUTTON_CLICK_SOUND = "src/defenders2FDE/Assets/sounds/button_click_sound.mp3";
    public static final String PLAYER_FIRE_SOUND = "src/defenders2FDE/Assets/sounds/player_fire_sound.mpeg";
}
