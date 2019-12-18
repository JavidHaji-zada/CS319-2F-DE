package defenders2FDE.objects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

import static defenders2FDE.Constants.*;

public class Asteroid extends GameObject{


    private long lastFiredTime = 0;

    public Asteroid(double x, double y){
        super(x, y, AsteroidSize, AsteroidSize, AsteroidHealth, "Asteroid", AsteroidCollisionDamage, Color.SEASHELL, AsteroidSpeed);
    }

    @Override
    public void move(){
        if ( !isStop())
            setTranslateY(getTranslateY() + AsteroidSpeed);
    }
}
