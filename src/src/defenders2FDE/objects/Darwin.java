package defenders2FDE.objects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

import static defenders2FDE.Constants.*;

public class Darwin extends GameObject{


    private long lastFiredTime = 0;

    public Darwin(double x, double y){
        super(x,y, DarwinSize, DarwinSize, DarwinHealth, "Darwin", DarwinCollisionDamage, Color.FUCHSIA, DarwinSpeed);
    }

    @Override
    public void move(){
        if ( !isStop())
            setTranslateX(getTranslateX() - 1);
    }

    public Bullet fire(){
        if ( new Date().getTime() - lastFiredTime >= 2000 && !isStop()) {
            lastFiredTime = new Date().getTime();
            return new Bullet(getTranslateX() - DarwinSize, getTranslateY() + DarwinSize / 2 - 5, 10, "DarwinBullet", 50, 7, Color.RED);
        }
        return null;
    }


}
