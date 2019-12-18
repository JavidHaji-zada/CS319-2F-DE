package defenders2FDE.objects;

import javafx.scene.paint.Color;

import java.util.Date;

import static defenders2FDE.Constants.*;

public class Queen extends GameObject{


    private long lastFiredTime = 0;
    private int speed;

    public Queen(double x, double y){
        super(x, y, QueenSize, QueenSize, QueenHealth, "Queen", QueenCollisionDamage, Color.GRAY, QueenSpeed);
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

    public Alien produce()
    {
        if ( new Date().getTime() - lastFiredTime >= 2000 && !isStop()) {
            lastFiredTime = new Date().getTime();
            return new Alien(getTranslateX() - QueenSize, getTranslateY() + QueenSize / 2 - AlienSize / 2);
        }
        return null;
    }

}
