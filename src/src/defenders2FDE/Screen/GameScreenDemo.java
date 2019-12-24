package defenders2FDE.Screen;

import defenders2FDE.Constants;
import defenders2FDE.Manager.GameManager;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GameScreenDemo extends Screen{

    private GameManager gameManager;
    private Screen mainScreen;
    private AnimationTimer timer;
    private ParallelTransition parallelTransition;
    private Stage primaryStage;

    public GameScreenDemo() throws IOException {
        super();
        gameManager = new GameManager(this,1);
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
        gameManager.setPrimaryStage(primaryStage);
    }

    private void update(long now){
        if ( gameManager.getLastFrameUpdateTime() + 1000 <= now){
            gameManager.setLastFrameUpdateTime(now);
            gameManager.updateLabel();
        }
        Node newEnemy = gameManager.addNewEnemy();
        if ( newEnemy != null){
            getChildren().add(newEnemy);
        }
        gameManager.checkCollision();
    }

    public void setMain(Screen screen) {
        mainScreen = screen;
        gameManager.setMain(screen);
    }

    @Override
    public Pane display(){
        setPrefSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        Image image = new Image(Constants.GAME_BACKGROUND_IMAGE_PATH);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(backgroundImage));

        // Header Line
        Line headerLine = new Line(0, Constants.HEADER_Y, Constants.SCREEN_WIDTH, Constants.HEADER_Y);
        headerLine.setStroke(Color.WHITE);
        getChildren().add(headerLine);

        // prepare key-presses
        this.setOnKeyPressed(event -> {
            if ( event.getCode() == KeyCode.A){
                if ( gameManager.getPlayerTranslateY()>=5 && !gameManager.isStop()) {
                    gameManager.moveLeft();
                }
            }else if (event.getCode() == KeyCode.D){
                if ( gameManager.getPlayerTranslateX() <= Constants.SCREEN_WIDTH - 108 && !gameManager.isStop()) {
                    gameManager.moveRight();
                }
            }else if (event.getCode() == KeyCode.W){
                if ( gameManager.getPlayerTranslateY() > Constants.HEADER_Y + 12 && !gameManager.isStop()) {
                    gameManager.moveUp();
                }
            }else if (event.getCode() == KeyCode.S){
                if ( gameManager.getPlayerTranslateY() <= Constants.FOOTER_Y && !gameManager.isStop()) {
                    gameManager.moveDown();
                }
            }else if (event.getCode() == KeyCode.LEFT){
                if ( gameManager.getPlayerTranslateY() >=5 && !gameManager.isStop()) {
                    gameManager.moveLeft();
                }
            }else if (event.getCode() == KeyCode.RIGHT){
                if ( gameManager.getPlayerTranslateX() <= Constants.SCREEN_WIDTH - 108 && !gameManager.isStop()) {
                    gameManager.moveRight();
                }
            }else if (event.getCode() == KeyCode.UP){
                if ( gameManager.getPlayerTranslateY() > Constants.HEADER_Y + 12 && !gameManager.isStop()) {
                    gameManager.moveUp();
                }
            }else if (event.getCode() == KeyCode.DOWN){
                if ( gameManager.getPlayerTranslateY() <= Constants.FOOTER_Y && !gameManager.isStop()) {
                    gameManager.moveDown();
                }
            }else if ( (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.F) && !gameManager.isStop()){
                gameManager.fire();
            }else if ( event.getCode() == KeyCode.P || event.getCode() == KeyCode.ESCAPE){
                if ( gameManager.isStop()){
                    gameManager.resume();
                }else{
                    gameManager.pause();
                }
            }
        });

         timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(now);
            }
        };
         gameManager.setAnimationTimer(timer);
         timer.start();
         return this;
    }
}
