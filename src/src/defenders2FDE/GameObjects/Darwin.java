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
    }

    @Override
    public void move(){
        if ( !isStop())
            setTranslateX(getTranslateX() - 1);
    }

    public Bullet fire(){
        if ( new Date().getTime() - lastFiredTime >= 2000 && !isStop()) {
            lastFiredTime = new Date().getTime();
            Bullet bullet = new Bullet(getTranslateX() - DarwinSize, getTranslateY() + DarwinSize / 2 - 5, 0, "darwinBullet");
            bullet.setCollisionDamage(DarwinBulletCollisionDamage);
            bullet.setImagePath(ENEMY_BULLET_IMAGE_PATH);
            return bullet;
        }
        return null;
    }


}
