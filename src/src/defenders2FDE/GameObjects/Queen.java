package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

import static defenders2FDE.Constants.*;

/**
 * @author      2FDE
 */
public class Queen extends GameObject{


    private long lastFiredTime = 0;
    private int speed;
    private int children;

    public Queen(double x, double y){
        super(x, y, QueenHealth, "Queen");
        setCollisionDamage(QueenCollisionDamage);
        setImagePath(QUEEN_IMAGE_PATH);
        setFitHeight(QueenSize);
        setFitWidth(QueenSize);
        setDirection("left");
        setChildren(6);
    }

    @Override
    public void move(){
        if(getMode() == 2) {
            if(getDirection().equals("left") && !isStop()) {
                setTranslateX(getTranslateX() - QueenSpeed);
            }
            else if(getDirection().equals("right") && !isStop()) {
                setTranslateX(getTranslateX() + QueenSpeed);
            }
            updateDirection();
        }
        else if(getMode() == 1)
        {
            if(!isStop())
                setTranslateX(getTranslateX() - QueenSpeed);
        }
    }

    public void updateDirection()
    {
        if(getTranslateX() <= 54) {
            setDirection("right");
        }
        else if(getTranslateX() >= SCREEN_WIDTH - 54) {
            setDirection("left");
        }
    }

    public AlienSpaceShip produce()
    {
        if(getMode() == 2 && getChildren() > 0)
        {
            if ( new Date().getTime() - lastFiredTime >= 3000 && !isStop()) {
                lastFiredTime = new Date().getTime();
                if(getChildren() % 2 == 0)
                {
                    AlienSpaceShip alienSpaceShip = new AlienSpaceShip(getTranslateX() - QueenSize / 2, getTranslateY() + QueenSize * 3 / 2 + AlienSize / 2);
                    alienSpaceShip.setMode(this.getMode());
                    setChildren(getChildren() - 1);
                    return alienSpaceShip;
                }
                else
                {
                    AlienSpaceShip alienSpaceShip = new AlienSpaceShip(getTranslateX() - QueenSize / 2, getTranslateY() - QueenSize * 3 / 2 + AlienSize / 2);
                    alienSpaceShip.setMode(this.getMode());
                    setChildren(getChildren() - 1);
                    return alienSpaceShip;
                }
            }
        }

        else if (this.getMode() == 1 && getChildren() > 0)
        {
            if ( new Date().getTime() - lastFiredTime >= 3000 && !isStop()) {
                lastFiredTime = new Date().getTime();
                if(getChildren() % 2 == 0)
                {
                    AlienSpaceShip alienSpaceShip = new AlienSpaceShip(getTranslateX() - QueenSize / 2, getTranslateY() + QueenSize * 3 / 2);
                    alienSpaceShip.setMode(this.getMode());
                    setChildren(getChildren() - 1);
                    return alienSpaceShip;
                }
                else
                {
                    AlienSpaceShip alienSpaceShip = new AlienSpaceShip(getTranslateX() - QueenSize / 2, getTranslateY() - QueenSize * 3 / 2);
                    alienSpaceShip.setMode(this.getMode());
                    setChildren(getChildren() - 1);
                    return alienSpaceShip;
                }
            }
        }

        return null;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getChildren() {
        return children;
    }
}
