package defenders2FDE;

import javafx.stage.Screen;

public class Constants {
    public static final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getMaxX();
    public static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getMaxY();

    public static final int SS_WIDTH = (int) Screen.getPrimary().getBounds().getMaxX() / 36;
    public static final int SS_HEIGHT = (int) Screen.getPrimary().getBounds().getMaxX() / 36;

    public static final int ScreenSizePercent = (int) Screen.getPrimary().getBounds().getMaxX() / 100;

    //Size
    public static final int SpaceShipSize = 4 * ScreenSizePercent;
    public static final int AlienSize = 2 * ScreenSizePercent;
    public static final int QueenSize = 4 * ScreenSizePercent;
    public static final int DarwinSize = 5 * ScreenSizePercent;
    public static final int AsteroidSize = 3 * ScreenSizePercent;

    public static final int AstronautSize = ScreenSizePercent;

    //Speed
    public static final int SpaceShipSpeed = 20;
    public static final int AlienSpeed = 3;
    public static final int QueenSpeed = 2;
    public static final int DarwinSpeed = 5;
    public static final int AsteroidSpeed = 10;

    public static final int AstronautSpeed = 5;

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

    //Points
    public static final int SpaceShipPoints = 0;
    public static final int AlienPoints = 100;
    public static final int QueenPoints = 300;
    public static final int DarwinPoints = 900;
    public static final int AsteroidPoints = 200;
}
