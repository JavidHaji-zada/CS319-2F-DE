package defenders2FDE.objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameObject extends Rectangle {

    private int health;
    private int speed;
    private Color color;
    public String type;
    private boolean stop;
    private boolean outOfScreen = false;


    public GameObject(double x, double y, int w, int h, int health, String type, Color color){
        super(w, h, color);
        setTranslateX(x);
        setTranslateY(y);
        this.color = color;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
