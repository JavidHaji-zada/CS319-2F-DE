package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import java.util.Date;

public class SpaceShip extends GameObject{

    private long lastFireTime = new Date().getTime();
    public SpaceShip(String path, double x, double y, int health, String type){
        super( path, x, y, health, type);
        setFitHeight(50);
        setFitWidth(50);
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
        long now = new Date().getTime();
        if (!isStop() && now - lastFireTime >= 500) {
            lastFireTime = now;
            String bulletImagePath = Constants.PLAYER_BULLET_IMAGE_PATH;
            return new Bullet(bulletImagePath, getTranslateX(), getTranslateY() + 25 , 10,  "playerBullet");
        }
        return null;
    }

}
