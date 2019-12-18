package defenders2FDE.objects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import static defenders2FDE.Constants.*;

public class SpaceShip extends GameObject{

    public SpaceShip(double x, double y){
        super(x, y, SpaceShipSize, SpaceShipSize, SpaceShipHealth, "Player", 0, Color.DODGERBLUE, SpaceShipSpeed);
    }

    @Override
    public void move(){

    }

    public void moveLeft() {
        if (!isStop() && getTranslateX()>= 20)
            setTranslateX(getTranslateX() - 20);
    }

    public void moveRight() {
        if (!isStop())
            setTranslateX(getTranslateX() + 20);
    }

    public void moveUp() {
        if (!isStop() && getTranslateY() >= 20)
            setTranslateY(getTranslateY() - 20);
    }

    public void moveDown() {
        if (!isStop())
            setTranslateY(getTranslateY() + 20);
    }

    public Bullet fire(){
        if (!isStop())
            return new Bullet(getTranslateX() + SpaceShipSize, getTranslateY() + SpaceShipSize / 2 - 5, 10, "PlayerBullet", 50, 5, Color.GREEN);
        return null;
    }

}
