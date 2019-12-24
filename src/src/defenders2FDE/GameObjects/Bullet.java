package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.paint.Color;

import static defenders2FDE.Constants.*;

/**
 * @author      2FDE
 */
public class Bullet extends GameObject {

    double posx;
    double posy;

    public Bullet(double x, double y, int health, String type){
        super( x, y, health, type);
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

    @Override
    public int getCollisionDamage() {
        if(this.type.equals("darwinBullet"))
            return DarwinBulletCollisionDamage;
        else if(this.type.equals("enemyBullet"))
            return AlienBulletCollisionDamage;
        else if(this.type.equals("playerBullet"))
            return PlayerBulletCollisionDamage;

        return 0;
    }
}
