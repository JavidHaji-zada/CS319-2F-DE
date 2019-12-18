package defenders2FDE.Manager;

import defenders2FDE.Constants;
import defenders2FDE.Screen.Screen;
import defenders2FDE.GameObjects.AlienSpaceShip;
import defenders2FDE.GameObjects.Bullet;
import defenders2FDE.GameObjects.GameObject;
import defenders2FDE.GameObjects.SpaceShip;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GameManager {

    private List<GameObject> gameObjects;
    private List<GameObject> enemyBullets;
    private int score = 0;
    private boolean isFinished = false;
    private long lastEnemyTime = new Date().getTime();
    private SpaceShip player;
    private Label scoreLabel;
    private Screen gameScreen;
    private Screen mainScreen;
    private Stage primaryStage;
    private AnimationTimer timer;

    public GameManager(Screen gameScreen, Stage primaryStage) {
        this.gameScreen = gameScreen;
        this.primaryStage = primaryStage;
        gameObjects = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        player = new SpaceShip(300,300, Constants.SS_WIDTH,Constants.SS_HEIGHT, 100, "player", Color.WHITE);
        gameObjects.add(player);
        scoreLabel = new Label("Score: " + this.score);
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setLayoutX(Constants.SCREEN_WIDTH * 9 / 10);
        scoreLabel.setLayoutY(Constants.SCREEN_HEIGHT / 20);
        gameScreen.getChildren().add(scoreLabel);
        gameScreen.getChildren().add(player);
    }

    public void setMain(Screen screen) {
        mainScreen = screen;
    }

    public void setTimer(AnimationTimer timer) {
        this.timer = timer;
    }

    public AlienSpaceShip addNewEnemy(){
        long currentTime = new Date().getTime();
        if ( currentTime - lastEnemyTime >= 5000 && !isFinished){
            lastEnemyTime = currentTime;
            Random random = new Random();
            int high = (int) Constants.SCREEN_HEIGHT - 220;
            double posY = 100 + random.nextInt(high);
            AlienSpaceShip alienSpaceShip = new AlienSpaceShip(Constants.SCREEN_WIDTH, posY, 20, 20, 10, "enemy", Color.BLUE);
            gameObjects.add(alienSpaceShip);
            return alienSpaceShip;
        }
        return null;
    }

    public void checkCollision(){
        gameObjects.forEach((GameObject gameObject) -> {
            gameObject.move();
            List<GameObject> toBeRemoved = new ArrayList<>();
            if (gameObject.type.equals("enemy")){
                if ( gameObject.isOutOfScreen()){
                    toBeRemoved.add(gameObject);
                }
                Bullet enemyBullet = ((AlienSpaceShip) gameObject).fire();
                if (enemyBullet != null) {
                    enemyBullets.add(enemyBullet);
                    gameScreen.getChildren().add(enemyBullet);
                }
            } else if (gameObject.type.equals("playerBullet")){
                gameObjects.stream().filter(e-> e.type.equals("enemy")).forEach(enemy -> {

                    // an enemy is down
                    if (gameObject.getBoundsInParent().intersects(enemy.getBoundsInParent())){
                        toBeRemoved.add(enemy);
                        toBeRemoved.add(gameObject);
                        score += 100;
                        scoreLabel.setText("Score: " + score );
                    }
                });
            }
            try {
                gameObjects.removeAll(toBeRemoved);
                toBeRemoved.forEach(obj -> {
                    Platform.runLater(() -> gameScreen.getChildren().remove(obj));
                });
            }catch (Exception ignored){
            }
        });
        enemyBullets.forEach(bullet -> {
            bullet.move();
            if (bullet.getBoundsInParent().intersects(player.getBoundsInParent())) {
                enemyBullets.forEach(bullet1 -> bullet1.stop(true));
                gameObjects.forEach(gameObject -> gameObject.stop(true));
                isFinished = true;
                timer.stop();
            }
        });
        if ( isFinished){
            primaryStage.setScene(mainScreen.getScene());
            primaryStage.getScene().getRoot().requestFocus();
        }
    }

    public double getPlayerTranslateY(){
        return player.getTranslateY();
    }

    public void moveLeft(){
        player.moveLeft();
    }

    public void moveRight(){
        player.moveRight();
    }

    public void moveUp(){
        player.moveUp();
    }

    public void moveDown(){
        player.moveDown();
    }

    public void fire(){
       Bullet bullet = player.fire();
        if ( bullet != null) {
            gameObjects.add(bullet);
            gameScreen.getChildren().add(bullet);
        }
    }
}
