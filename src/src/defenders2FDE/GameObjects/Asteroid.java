package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

import static defenders2FDE.Constants.*;

public class Asteroid extends GameObject{


    private long lastFiredTime = 0;

    public Asteroid(double x, double y){
        super(ASTEROID_IMAGE_PATH, x, y, AsteroidHealth, "Asteroid");
        this.setCollisionDamage(AsteroidCollisionDamage);
        setFitHeight(AsteroidSize);
        setFitWidth(AsteroidSize);
    }

    @Override
    public void move(){
        if ( !isStop())
            setTranslateY(getTranslateY() + AsteroidSpeed);
    }
}
