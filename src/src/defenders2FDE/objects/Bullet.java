package defenders2FDE.objects;

import javafx.scene.paint.Color;

public class Bullet extends GameObject {

    double posx;
    double posy;

    public Bullet(double x, double y, int size, String type, int damage, int speed, Color color){
        super(x, y, size, size, 0, type, damage, color, speed);
        this.posx = x;
        this.posy = y;
    }

    @Override
    public void move(){
        if (!isStop()){
            if (type.equals("AlienBullet")) {
                setTranslateX(getTranslateX() - 5);
            }
            else if ( type.equals("PlayerBullet")){
                setTranslateX(getTranslateX() + 5);
            }
            else if ( type.equals("DarwinBullet")){
                setTranslateX(getTranslateX() - 7);
            }
        }
    }
}
