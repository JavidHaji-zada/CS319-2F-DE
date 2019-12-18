package defenders2FDE.GameObjects;

import javafx.scene.paint.Color;

public class Bullet extends GameObject {

    double posx;
    double posy;

    public Bullet(double x, double y, int w, int h, int health, String type, Color color){
        super(x,y,w,h,health,type, color);
        this.posx = x;
        this.posy = y;
    }

    @Override
    public void move(){
        if (!isStop()){
            if (type.equals("enemyBullet")){
                setTranslateX(getTranslateX() - 5);

            }else if ( type.equals("playerBullet")){
                setTranslateX(getTranslateX() + 5);

            }
        }
    }
}
