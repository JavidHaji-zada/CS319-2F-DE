package defenders2FDE.GameObjects;

import defenders2FDE.Constants;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.Date;

/**
 * @author      2FDE
 */
public class SpaceShip extends GameObject{

    private long lastFireTime = new Date().getTime();
    public SpaceShip(double x, double y, int health, String type){
        super( x, y, health, type);
        setFitHeight(100);
        setFitWidth(100);
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
        if (!isStop() && now - lastFireTime >= 250) {
            // prepare sound effects
            String playerFireSoundPath = Constants.PLAYER_FIRE_SOUND;
            Media playerFireSound = new Media(new File(playerFireSoundPath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(playerFireSound);
            mediaPlayer.setOnEndOfMedia(mediaPlayer::stop);
            mediaPlayer.play();
            lastFireTime = now;
            String bulletImagePath = Constants.PLAYER_BULLET_IMAGE_PATH;
            Bullet bullet = new Bullet(getTranslateX() + 50, getTranslateY() + 25 , 10,  "playerBullet");
            bullet.setImagePath(bulletImagePath);
            return bullet;
        }
        return null;
    }

}
