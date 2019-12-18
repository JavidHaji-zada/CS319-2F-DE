package defenders2FDE.Screen;

import defenders2FDE.Constants;
import defenders2FDE.Manager.GameManager;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameScreenDemo extends Screen{

    private GameManager gameManager;
    private Screen mainScreen;
    private AnimationTimer timer;
    private ParallelTransition parallelTransition;

    public GameScreenDemo(Stage primaryStage){
        super(primaryStage);
        gameManager = new GameManager(this, primaryStage);
    }

    private void update(){
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
        Image image = new Image("defenders2FDE/Assets/images/background.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        setBackground(new Background(backgroundImage));
        // Header Line
        Line headerLine = new Line(0, Constants.HEADER_Y, Constants.SCREEN_WIDTH, Constants.HEADER_Y);
        headerLine.setStroke(Color.WHITE);
        getChildren().add(headerLine);

        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if ( event.getCode() == KeyCode.A){
                    if ( gameManager.getPlayerTranslateY()>=5) {
                        gameManager.moveLeft();
                    }
                }else if (event.getCode() == KeyCode.D){
                    if ( gameManager.getPlayerTranslateY() <= Constants.SCREEN_WIDTH - 10) {
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
                    if ( gameManager.getPlayerTranslateY() <= Constants.SCREEN_WIDTH - 12) {
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
                }else if ( event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.F){
                     gameManager.fire();
                }
            }
        });
         timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
         gameManager.setTimer(timer);
         timer.start();
         return this;
    }
}
