package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;
import java.util.Random;

import static defenders2FDE.Constants.*;

/**
 * @author      2FDE
 */
public class Asteroid extends GameObject{


    private long lastFiredTime = 0;
    double xSpeed = AsteroidSpeed / 3;
    double ySpeed = AsteroidSpeed;

    public Asteroid(double x, double y){
        super(x, y, AsteroidHealth, "Asteroid");
        setCollisionDamage(AsteroidCollisionDamage);
        setImagePath(ASTEROID_IMAGE_PATH);
        setFitHeight(AsteroidSize);
        setFitWidth(AsteroidSize);

        Random random = new Random();
        int randomNumber = random.nextInt(100);

        if(randomNumber % 2 == 0)
            xSpeed *= -1;
    }

    @Override
    public void move(){
        if ( !isStop()) {
            setTranslateX(getTranslateX() + xSpeed);
            setTranslateY(getTranslateY() + ySpeed);
        }
    }
}
