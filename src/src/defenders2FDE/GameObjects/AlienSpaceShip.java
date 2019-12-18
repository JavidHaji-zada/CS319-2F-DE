package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

public class AlienSpaceShip extends GameObject{

    private long lastFiredTime = 0;
    public AlienSpaceShip(double x, double y, int w, int h, int health, String type, Color color){
        super(x,y,w,h,health, type, color);
    }

    @Override
    public void move(){
        if ( !isStop())
            setTranslateX(getTranslateX() - 1);
    }

    public Bullet fire(){
        if ( new Date().getTime() - lastFiredTime >= 2000 && !isStop()) {
            lastFiredTime = new Date().getTime();
            return new Bullet(getTranslateX() - Constants.SS_WIDTH, getTranslateY() + Constants.SS_HEIGHT / 2 - 5, 10, 10, 35, "enemyBullet", Color.RED);
        }
        return null;
    }
}
