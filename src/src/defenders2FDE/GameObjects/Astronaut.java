package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

import static defenders2FDE.Constants.*;

public class Astronaut extends GameObject{


    private long lastFiredTime = 0;

    public Astronaut(double x, double y){
        super(ASTRONAUT_IMAGE_PATH, x, y,0, "Astronaut");
        this.setCollisionDamage(0);
        setFitHeight(AstronautSize);
        setFitWidth(AstronautSize);
    }

    @Override
    public void move(){
        if ( !isStop())
            setTranslateY(getTranslateY() + AstronautSpeed);
    }

}
