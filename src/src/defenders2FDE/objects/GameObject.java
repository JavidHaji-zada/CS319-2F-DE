package defenders2FDE.objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameObject extends Rectangle {

    private int health;
    private int speed;
    private Color color;
    public String type;

    public GameObject(double x, double y, int w, int h, int health, String type, Color color){
        super(w, h, color);
        setTranslateX(x);
        setTranslateY(y);
        this.color = color;
        this.health = health;
        this.type = type;
    }

    public void move(){
    }

}
