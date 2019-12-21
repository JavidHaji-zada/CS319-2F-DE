package defenders2FDE.Screen;

import defenders2FDE.Constants;
import defenders2FDE.Manager.GameManager;
import defenders2FDE.Manager.GameManagerStory;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class StoryMode extends Screen{

    private GameManagerStory gameManager;
    private Screen mainScreen;
    private AnimationTimer timer;
    private ParallelTransition parallelTransition;

    public StoryMode(Stage primaryStage){
        super(primaryStage);
        gameManager = new GameManagerStory(this, primaryStage);
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
                if ( gameManager.getPlayerTranslateY()>=5) {
                    gameManager.moveLeft();
                }
            }else if (event.getCode() == KeyCode.D){
                if ( gameManager.getPlayerTranslateX() <= Constants.SCREEN_WIDTH - 108) {
                    gameManager.moveRight();
                }
            }else if (event.getCode() == KeyCode.W){
                if ( gameManager.getPlayerTranslateY() > Constants.HEADER_Y + 12) {
                    gameManager.moveUp();
                }
            }else if (event.getCode() == KeyCode.S){
                if ( gameManager.getPlayerTranslateY() <= Constants.FOOTER_Y) {
                    gameManager.moveDown();
                }
            }else if (event.getCode() == KeyCode.LEFT){
                if ( gameManager.getPlayerTranslateY() >=5) {
                    gameManager.moveLeft();
                }
            }else if (event.getCode() == KeyCode.RIGHT){
                if ( gameManager.getPlayerTranslateX() <= Constants.SCREEN_WIDTH - 108) {
                    gameManager.moveRight();
                }
            }else if (event.getCode() == KeyCode.UP){
                if ( gameManager.getPlayerTranslateY() > Constants.HEADER_Y + 12) {
                    gameManager.moveUp();
                }
            }else if (event.getCode() == KeyCode.DOWN){
                if ( gameManager.getPlayerTranslateY() <= Constants.FOOTER_Y) {
                    gameManager.moveDown();
                }
            }
            if ( event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.F){
                 gameManager.fire();
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
