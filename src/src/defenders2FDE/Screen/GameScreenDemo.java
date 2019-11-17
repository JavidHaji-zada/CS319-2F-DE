package defenders2FDE.Screen;

import defenders2FDE.Constants;
import defenders2FDE.Main;
import defenders2FDE.objects.AlienSpaceShip;
import defenders2FDE.objects.Bullet;
import defenders2FDE.objects.GameObject;
import defenders2FDE.objects.SpaceShip;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.*;

public class GameScreenDemo extends Screen{


    private Stage primaryStage;
    private List<GameObject> gameObjects;
    private List<GameObject> enemyBullets;
    private double time = 0;
    private long lastEnemyTime = new Date().getTime();

    public GameScreenDemo(Stage primaryStage){
        this.primaryStage = primaryStage;
        gameObjects = new ArrayList<>();
        enemyBullets = new ArrayList<>();
    }

    private void update(){
        long currentTime = new Date().getTime();
        if ( currentTime - lastEnemyTime >= 5000){
            lastEnemyTime = currentTime;
            Random random = new Random();
            int high = (int) Constants.SCREEN_HEIGHT;
            int low = 0;
            double posY = random.nextInt(high);
            AlienSpaceShip alienSpaceShip = new AlienSpaceShip(Constants.SCREEN_WIDTH, posY, 20, 20, 10, "enemy",Color.BLUE);
            gameObjects.add(alienSpaceShip);
            getChildren().add(alienSpaceShip);
        }
        gameObjects.forEach(gameObject -> {
            gameObject.move();
            if (gameObject.type.equals("enemy")){
                Bullet enemyBullet = ((AlienSpaceShip) gameObject).fire();
                if (enemyBullet != null) {
                    enemyBullets.add(enemyBullet);
                    getChildren().add(enemyBullet);
                }
            }
        });
        enemyBullets.forEach(bullet -> {
            System.out.println("Type " + bullet.type);
            bullet.move();
        });
    }

    @Override
    public Pane display(){
        setPrefSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        SpaceShip player = new SpaceShip(300,300, Constants.SS_WIDTH,Constants.SS_HEIGHT, 100, "player", Color.WHITE);
        gameObjects.add(player);
        this.requestFocus();
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
                    gameObjects.add(bullet);
                    getChildren().add(bullet);
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

        primaryStage.setFullScreen(true);
        return this;
    }
}
