package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

public class AlienSpaceShip extends GameObject{

    private long lastFiredTime = 0;
    public AlienSpaceShip( double x, double y, int health, String type){
        super( x, y, health, type);
        setFitHeight(Constants.SS_HEIGHT);
        setFitWidth(Constants.SS_WIDTH);
    }

    @Override
    public void move(){
        if ( !isStop())
            setTranslateX(getTranslateX() - 1);
    }

    public Bullet fire(){
        if ( new Date().getTime() - lastFiredTime >= 2000 && !isStop()) {
            lastFiredTime = new Date().getTime();
            String bulletImagePath = Constants.ENEMY_BULLET_IMAGE_PATH;
            Bullet bullet = new Bullet( getTranslateX() - Constants.SS_WIDTH, getTranslateY() + Constants.SS_HEIGHT / 2, 10,  "enemyBullet");
            bullet.setImagePath(bulletImagePath);
            return bullet;
        }
        return null;
    }
}
