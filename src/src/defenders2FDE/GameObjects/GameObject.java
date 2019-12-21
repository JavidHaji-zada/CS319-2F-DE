package defenders2FDE.GameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameObject extends ImageView {

    private int health;
    private int speed;
    public String type;
    private boolean stop;
    private boolean outOfScreen = false;
    private int collisionDamage;


    public GameObject(double x, double y, int health, String type){
        super();
        setTranslateX(x);
        setTranslateY(y);
        this.health = health;
        this.type = type;
        stop = false;
    }

    public boolean isOutOfScreen() {
        return getTranslateX() <=0;
    }

    public void setOutOfScreen(boolean outOfScreen) {
        this.outOfScreen = outOfScreen;
    }

    public boolean isStop() {
        return stop;
    }

    public void stop(boolean stop) {
        this.stop = stop;
    }

    public void move(){
    }

    public void setImagePath(String path){
        Image objectImage = new Image(path);
        setImage(objectImage);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCollisionDamage() {
        return collisionDamage;
    }

    public void setCollisionDamage(int collisionDamage) {
        this.collisionDamage = collisionDamage;
    }
}
