package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

import static defenders2FDE.Constants.*;

public class Queen extends GameObject{


    private long lastFiredTime = 0;
    private int speed;

    public Queen(double x, double y){
        super(QUEEN_IMAGE_PATH, x, y, QueenHealth, "Queen");
        this.setCollisionDamage(QueenCollisionDamage);
        setFitHeight(QueenSize);
        setFitWidth(QueenSize);
    }

    @Override
    public void move(){
        if ( !isStop())
            setTranslateX(getTranslateX() - QueenSpeed);
    }

    /*public Bullet fire(){
        if ( new Date().getTime() - lastFiredTime >= 2000 && !isStop()) {
            lastFiredTime = new Date().getTime();
            return new Bullet(getTranslateX() - Constants.SS_WIDTH, getTranslateY() + Constants.SS_HEIGHT / 2 - 5, 10, 10, 10, "enemyBullet", Color.RED);
        }
        return null;
    }*/

    public AlienSpaceShip produce()
    {
        if ( new Date().getTime() - lastFiredTime >= 3000 && !isStop()) {
            lastFiredTime = new Date().getTime();
            return new AlienSpaceShip(ENEMY_SPACESHIP_IMAGE_PATH, getTranslateX() - QueenSize / 2, getTranslateY() + QueenSize / 2 - AlienSize / 2, SpaceShipHealth, "enemy");
        }
        return null;
    }

}
