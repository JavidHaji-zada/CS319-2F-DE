package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

import static defenders2FDE.Constants.*;

public class Asteroid extends GameObject{


    private long lastFiredTime = 0;

    public Asteroid(double x, double y){
        super(x, y, AsteroidHealth, "Asteroid");
        this.setCollisionDamage(AsteroidCollisionDamage);
        this.setImagePath(ASTEROID_IMAGE_PATH);
        setFitHeight(AsteroidSize);
        setFitWidth(AsteroidSize);
    }

    @Override
    public void move(){
        if ( !isStop())
            setTranslateY(getTranslateY() + AsteroidSpeed);
    }
}
