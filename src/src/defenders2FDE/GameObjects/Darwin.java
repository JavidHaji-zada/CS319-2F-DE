package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

import static defenders2FDE.Constants.*;

public class Darwin extends GameObject{


    private long lastFiredTime = 0;

    public Darwin(double x, double y){
        super(DARWIN_IMAGE_PATH, x,y, DarwinHealth, "Darwin");
        this.setCollisionDamage(DarwinCollisionDamage);
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
            Bullet bullet = new Bullet(ENEMY_BULLET_IMAGE_PATH, getTranslateX() - DarwinSize, getTranslateY() + DarwinSize / 2 - 5, 0, "DarwinBullet");
            bullet.setCollisionDamage(DarwinBulletCollisionDamage);
            return bullet;
        }
        return null;
    }


}
