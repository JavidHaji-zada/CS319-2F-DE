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
        setDirection("left");
    }

    @Override
    public void move(){
        if(getMode() == 2) {
            if(getDirection().equals("left") && !isStop()) {
                setTranslateX(getTranslateX() - AlienSpeed);
            }
            else if(getDirection().equals("right") && !isStop()) {
                setTranslateX(getTranslateX() + AlienSpeed);
            }
            updateDirection();
        }
        else if(getMode() == 1)
        {
            if(!isStop())
                setTranslateX(getTranslateX() - AlienSpeed);
        }
    }

    public void updateDirection()
    {
        if(getTranslateX() <= 54) {
            setDirection("right");
        }
        else if(getTranslateX() >= SCREEN_WIDTH - 54) {
            setDirection("left");
        }
    }

    public Bullet fire() {

        if(getMode() == 2)
        {
            if ( new Date().getTime() - lastFiredTime >= 2000 && !isStop()) {
                lastFiredTime = new Date().getTime();

                if(getDirection().equals("left"))
                {
                    Bullet bullet = new Bullet( getTranslateX() - AlienSize, getTranslateY() + AlienSize / 2, 10,  "enemyBullet");
                    bullet.setImagePath(ENEMY_BULLET_IMAGE_PATH);
                    return bullet;
                }
                else if(getDirection().equals("right"))
                {
                    Bullet bullet = new Bullet( getTranslateX() + AlienSize, getTranslateY() + AlienSize / 2, 10,  "enemyBullet");
                    bullet.setImagePath(ENEMY_BULLET_IMAGE_PATH);
                    return bullet;
                }
            }
        }

        else if (this.getMode() == 1)
        {
            if ( new Date().getTime() - lastFiredTime >= 2000 && !isStop()) {
                lastFiredTime = new Date().getTime();
                Bullet bullet = new Bullet( getTranslateX() - AlienSize, getTranslateY() + AlienSize / 2, 10,  "enemyBullet");
                bullet.setImagePath(ENEMY_BULLET_IMAGE_PATH);
                return bullet;
            }
        }

        return null;
    }
}
