package defenders2FDE.Screen;

import defenders2FDE.Constants;
import defenders2FDE.Main;
import defenders2FDE.Manager.ScreenManager;
import defenders2FDE.objects.AlienSpaceShip;
import defenders2FDE.objects.Bullet;
import defenders2FDE.objects.GameObject;
import defenders2FDE.objects.SpaceShip;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class GameScreenDemo extends Screen{


    private Stage primaryStage;
    private List<GameObject> gameObjects;
    private List<GameObject> enemyBullets;
    private int score = 0;
    private boolean isFinished = false;
    private long lastEnemyTime = new Date().getTime();
    private SpaceShip player;
    private Label scoreLabel;

    public GameScreenDemo(Stage primaryStage){
        super(primaryStage);
        score = 0;
        gameObjects = new ArrayList<>();
        enemyBullets = new ArrayList<>();
    }

    private void addNewEnemy(){
        long currentTime = new Date().getTime();
        if ( currentTime - lastEnemyTime >= 5000 && !isFinished){
            lastEnemyTime = currentTime;
            Random random = new Random();
            int high = (int) Constants.SCREEN_HEIGHT - 20;
            double posY = random.nextInt(high);
            AlienSpaceShip alienSpaceShip = new AlienSpaceShip(Constants.SCREEN_WIDTH, posY, 20, 20, 10, "enemy",Color.BLUE);
            gameObjects.add(alienSpaceShip);
            getChildren().add(alienSpaceShip);
        }
    }

    private void update(){
        addNewEnemy();
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
                    getChildren().add(enemyBullet);
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
                    Platform.runLater(() -> getChildren().remove(obj));
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
            }
        });
        if ( isFinished){
            Scene mainScreen = new Scene(new ScreenManager().setScreen(new MainScreen(primaryStage)));
            mainScreen.getRoot().requestFocus();
            primaryStage.setScene(mainScreen);
        }
    }

    @Override
    public Pane display(){
        setPrefSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        player = new SpaceShip(300,300, Constants.SS_WIDTH,Constants.SS_HEIGHT, 100, "player", Color.WHITE);
        gameObjects.add(player);
        scoreLabel = new Label("Score: " + this.score);
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setLayoutX(Constants.SCREEN_WIDTH * 9 / 10);
        scoreLabel.setLayoutY(Constants.SCREEN_HEIGHT / 20);
        getChildren().add(scoreLabel);



        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if ( event.getCode() == KeyCode.A){
                    player.moveLeft();
                }else if (event.getCode() == KeyCode.D){
                    player.moveRight();
                }else if (event.getCode() == KeyCode.W){
                    player.moveUp();
                }else if (event.getCode() == KeyCode.S){
                    player.moveDown();
                }else if (event.getCode() == KeyCode.LEFT){
                    player.moveLeft();
                }else if (event.getCode() == KeyCode.RIGHT){
                    player.moveRight();
                }else if (event.getCode() == KeyCode.UP){
                    player.moveUp();
                }else if (event.getCode() == KeyCode.DOWN){
                    player.moveDown();
                }else if ( event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.F){
                    Bullet bullet = player.fire();
                    if ( bullet != null) {
                        gameObjects.add(bullet);
                        getChildren().add(bullet);
                    }
                }
            }
        });
        getChildren().add(player);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
        return this;
    }
}
