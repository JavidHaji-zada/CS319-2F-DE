package defenders2FDE.objects;

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
        setTranslateX(getTranslateX() - 20);
    }

    public void moveRight() {
        setTranslateX(getTranslateX() + 20);
    }

    public void moveUp() {
        setTranslateY(getTranslateY() - 20);
    }

    public void moveDown() {
        setTranslateY(getTranslateY() + 20);
    }

    public Bullet fire(){

        return new Bullet(getTranslateX() + Constants.SS_WIDTH, getTranslateY() + Constants.SS_HEIGHT / 2 - 5, 10, 10, 10, "playerBullet", Color.GREEN);
    }

}
