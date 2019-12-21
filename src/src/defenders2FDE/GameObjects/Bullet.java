package defenders2FDE.GameObjects;

import javafx.scene.paint.Color;

public class Bullet extends GameObject {

    double posx;
    double posy;

    public Bullet(String path, double x, double y, int health, String type){
        super( path, x, y, health, type);
        this.posx = x;
        this.posy = y;
        setFitHeight(5);
        setFitWidth(10);
    }

    @Override
    public void move(){
        if (!isStop()){
            if (type.equals("enemyBullet")){
                setTranslateX(getTranslateX() - 5);

            }else if ( type.equals("playerBullet")){
                setTranslateX(getTranslateX() + 5);
            }
            else if ( type.equals("darwinBullet")){
                setTranslateX(getTranslateX() - 7);
            }
        }
    }
}
