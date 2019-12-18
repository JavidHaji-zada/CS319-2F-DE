package defenders2FDE.objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameObject extends Rectangle {

    private int health;
    private int speed;
    private Color color;
    private int collisionDamage;
    private boolean stop;
    private boolean outOfScreen = false;
    public String type;


    public GameObject(double x, double y, int w, int h, int health, String type, int collisionDamage, Color color, int speed){
        super(w, h, color);
        setTranslateX(x);
        setTranslateY(y);
        this.color = color;
        this.health = health;
        this.collisionDamage = collisionDamage;
        this.speed = speed;
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

}
