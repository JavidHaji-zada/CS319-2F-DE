package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

import static defenders2FDE.Constants.*;

/**
 * @author      2FDE
 */
public class Darwin extends GameObject{


    private long lastFiredTime = 0;

    public Darwin(double x, double y){
        super(x,y, DarwinHealth, "Darwin");
        setCollisionDamage(DarwinCollisionDamage);
        setImagePath(DARWIN_IMAGE_PATH);
        setFitHeight(DarwinSize);
        setFitWidth(DarwinSize);
        setDirection("left");
    }

    @Override
    public void move(){
        if(getMode() == 2) {
            if(getDirection().equals("left") && !isStop()) {
                setTranslateX(getTranslateX() - DarwinSpeed);
            }
            else if(getDirection().equals("right") && !isStop()) {
                setTranslateX(getTranslateX() + DarwinSpeed);
            }
            updateDirection();
        }
        else if(getMode() == 1)
        {
            if(!isStop())
                setTranslateX(getTranslateX() - DarwinSpeed);
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
                    Bullet bullet = new Bullet(getTranslateX() - DarwinSize, getTranslateY() + DarwinSize / 2 - 5, 0, "darwinBullet");
                    bullet.setCollisionDamage(DarwinBulletCollisionDamage);
                    bullet.setImagePath(ENEMY_BULLET_IMAGE_PATH);
                    return bullet;
                }
                else if(getDirection().equals("right"))
                {
                    Bullet bullet = new Bullet(getTranslateX(), getTranslateY() + DarwinSize / 2 - 5, 0, "darwinBullet");
                    bullet.setCollisionDamage(DarwinBulletCollisionDamage);
                    bullet.setImagePath(ENEMY_BULLET_IMAGE_PATH);
                    return bullet;
                }
            }
        }

        else if (this.getMode() == 1)
        {
            if ( new Date().getTime() - lastFiredTime >= 2000 && !isStop()) {
                lastFiredTime = new Date().getTime();
                Bullet bullet = new Bullet(getTranslateX() - DarwinSize, getTranslateY() + DarwinSize / 2 - 5, 0, "darwinBullet");
                bullet.setCollisionDamage(DarwinBulletCollisionDamage);
                bullet.setImagePath(ENEMY_BULLET_IMAGE_PATH);
                return bullet;
            }
        }

        return null;
    }

}
