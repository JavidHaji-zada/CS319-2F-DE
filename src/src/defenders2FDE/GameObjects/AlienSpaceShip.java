package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

import static defenders2FDE.Constants.*;

public class AlienSpaceShip extends GameObject{

    private long lastFiredTime = 0;
    public AlienSpaceShip(double x, double y){
        super( x, y, AlienHealth, "enemy");
        setImagePath(ENEMY_SPACESHIP_IMAGE_PATH);
        setFitHeight(SS_HEIGHT);
        setFitWidth(SS_WIDTH);
        setCollisionDamage(AlienCollisionDamage);
    }

    @Override
    public void move(){
        if ( !isStop())
            setTranslateX(getTranslateX() - 1);
    }

    public Bullet fire(){
        if ( new Date().getTime() - lastFiredTime >= 2000 && !isStop()) {
            lastFiredTime = new Date().getTime();
            Bullet bullet = new Bullet( getTranslateX() - SS_WIDTH, getTranslateY() + SS_HEIGHT / 2, 10,  "enemyBullet");
            bullet.setImagePath(ENEMY_BULLET_IMAGE_PATH);
            return bullet;
        }
        return null;
    }
}
