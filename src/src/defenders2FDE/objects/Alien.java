package defenders2FDE.objects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

import static defenders2FDE.Constants.*;

public class Alien extends GameObject{


    private long lastFiredTime = 0;

    public Alien(double x, double y) {
        super(x, y, AlienSize, AlienSize, AlienHealth, "Alien", AsteroidCollisionDamage, Color.ANTIQUEWHITE, AlienSpeed);
    }

    @Override
    public void move(){
        if ( !isStop())
            setTranslateX(getTranslateX() - 1);
    }

    public Bullet fire(){
        if ( new Date().getTime() - lastFiredTime >= 2000 && !isStop()) {
            lastFiredTime = new Date().getTime();
            return new Bullet(getTranslateX() - AlienSize, getTranslateY() + AlienSize / 2 - 5, 10, "AlienBullet", 20, 5, Color.RED);
        }
        return null;
    }


}
