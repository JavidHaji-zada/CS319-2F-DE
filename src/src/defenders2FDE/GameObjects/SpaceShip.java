package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

public class SpaceShip extends GameObject{

    public SpaceShip(double x, double y, int w, int h, int health, String type, Color color){
        super(x,y,w,h,health, type, color);
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
            return new Bullet(getTranslateX() + Constants.SS_WIDTH, getTranslateY() + Constants.SS_HEIGHT / 2 - 5, 10, 10, 10, "playerBullet", Color.GREEN);
        return null;
    }

}
