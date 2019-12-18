package defenders2FDE.objects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

import static defenders2FDE.Constants.*;

public class Astronaut extends GameObject{


    private long lastFiredTime = 0;

    public Astronaut(double x, double y){
        super(x, y, AstronautSize, AstronautSize, 0, "Astronaut", 0, Color.NAVY, AstronautSpeed);
    }

    @Override
    public void move(){
        if ( !isStop())
            setTranslateY(getTranslateY() + AstronautSpeed);
    }

}
