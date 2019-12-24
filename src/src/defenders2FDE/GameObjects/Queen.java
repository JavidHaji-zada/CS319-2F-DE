package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

import static defenders2FDE.Constants.*;

public class Queen extends GameObject{


    private long lastFiredTime = 0;
    private int speed;

    public Queen(double x, double y){
        super(x, y, QueenHealth, "Queen");
        setCollisionDamage(QueenCollisionDamage);
        setImagePath(QUEEN_IMAGE_PATH);
        setFitHeight(QueenSize);
        setFitWidth(QueenSize);
    }

    @Override
    public void move(){
        if ( !isStop())
            setTranslateX(getTranslateX() - QueenSpeed);
    }

    public AlienSpaceShip produce()
    {
        if ( new Date().getTime() - lastFiredTime >= 3000 && !isStop()) {
            lastFiredTime = new Date().getTime();
            return new AlienSpaceShip(getTranslateX() - QueenSize / 2, getTranslateY() + QueenSize / 2 - AlienSize / 2);
        }
        return null;
    }

}
