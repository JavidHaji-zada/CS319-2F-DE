package defenders2FDE.Manager;

import defenders2FDE.Constants;
import defenders2FDE.GameObjects.*;
import defenders2FDE.Screen.Screen;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static defenders2FDE.Constants.AlienPoints;
import static defenders2FDE.Constants.QueenPoints;

public class GameManagerStory {

    private List<GameObject> gameObjects;
    private List<GameObject> enemyBullets;
    private int score = 0;
    private boolean isFinished = false;
    private long lastEnemyTime = new Date().getTime();
    private SpaceShip player;
    private Label scoreLabel;
    private Label timerLabel;
    private Screen gameScreen;
    private Screen mainScreen;
    private Stage primaryStage;
    private AnimationTimer animationTimer;
    private long lastFrameUpdateTime;
    private int time = 0;
    private long fraction = 0;

    public GameManagerStory(Screen gameScreen, Stage primaryStage) {
        this.gameScreen = gameScreen;
        this.primaryStage = primaryStage;
        gameObjects = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        player = new SpaceShip(Constants.PLAYER_SPACESHIP_IMAGE_PATH, 300,300, 100, "player");
        gameObjects.add(player);

        // setup score label
        scoreLabel = new Label("Score: " + this.score);
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setLayoutX(Constants.SCREEN_WIDTH * 9 / 10);
        scoreLabel.setLayoutY(Constants.SCREEN_HEIGHT / 20);
        gameScreen.getChildren().add(scoreLabel);

        // setup timer label
        timerLabel = new Label("00:00");
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setFont(Font.font("Arial", 32));
        timerLabel.setLayoutY(Constants.HEADER_Y / 2 - 16);
        timerLabel.setLayoutX(Constants.SCREEN_WIDTH / 2);
        gameScreen.getChildren().add(timerLabel);

        gameScreen.getChildren().add(player);
    }

    public void setMain(Screen screen) {
        mainScreen = screen;
    }

    public void setAnimationTimer(AnimationTimer animationTimer) {
        this.animationTimer = animationTimer;
    }

    public long getLastFrameUpdateTime() {
        return lastFrameUpdateTime;
    }

    public void setLastFrameUpdateTime(long lastFrameUpdateTime) {
        this.lastFrameUpdateTime = lastFrameUpdateTime;
    }

    public void updateLabel(){
        time++;
        StringBuilder timerStrBuilder = new StringBuilder();
        int hours = time / 216000;
        int mins = time / 3600;
        int secs = time / 60;
        secs %= 60;
        if ( hours > 0){
            if ( hours < 10) {
                timerStrBuilder.append("0");
            }
            timerStrBuilder.append(hours);
            timerStrBuilder.append(":");
        }
        if ( mins < 10)
            timerStrBuilder.append(0);
        timerStrBuilder.append(mins);
        timerStrBuilder.append(":");
        if (secs < 10) {
            timerStrBuilder.append(0);
        }
        System.out.println("Hours" + hours + " mins " + mins + " secs " + secs + " timer " + time);
        timerStrBuilder.append(secs);
        timerLabel.setText(timerStrBuilder.toString());
    }

    public GameObject addNewEnemy(){
        long currentTime = new Date().getTime();
        if ( currentTime - lastEnemyTime >= 5000 && !isFinished){
            lastEnemyTime = currentTime;
            Random random = new Random();
            int high = (int) Constants.SCREEN_HEIGHT - 220;
            double posY = 100 + random.nextInt(high);
            Queen queen = new Queen(Constants.SCREEN_WIDTH, posY);
            gameObjects.add(queen);
            return queen;
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
            }
            if (gameObject.type.equals("Queen")){
                if ( gameObject.isOutOfScreen()){
                    toBeRemoved.add(gameObject);
                }
                AlienSpaceShip alienSpaceShip = ((Queen) gameObject).produce();
                if (alienSpaceShip != null) {
                    gameObjects.add(alienSpaceShip);
                    gameScreen.getChildren().add(alienSpaceShip);
                }
            }
            else if (gameObject.type.equals("playerBullet")){
                gameObjects.stream().filter(e-> e.type.equals("enemy")).forEach(enemy -> {

                    // an enemy is down
                    if (gameObject.getBoundsInParent().intersects(enemy.getBoundsInParent())){
                        toBeRemoved.add(enemy);
                        toBeRemoved.add(gameObject);
                        score += AlienPoints;
                        scoreLabel.setText("Score: " + score );
                    }
                });

                gameObjects.stream().filter(e-> e.type.equals("Queen")).forEach(enemy -> {
                    // an enemy is down
                    if (gameObject.getBoundsInParent().intersects(enemy.getBoundsInParent())){
                        toBeRemoved.add(enemy);
                        toBeRemoved.add(gameObject);
                        score += QueenPoints;
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
                animationTimer.stop();
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

    public double getPlayerTranslateX(){
        return player.getTranslateX();
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
