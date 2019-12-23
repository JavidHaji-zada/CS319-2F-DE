package defenders2FDE.Manager;

import defenders2FDE.Constants;
import defenders2FDE.Screen.Screen;
import defenders2FDE.GameObjects.AlienSpaceShip;
import defenders2FDE.GameObjects.Bullet;
import defenders2FDE.GameObjects.GameObject;
import defenders2FDE.GameObjects.SpaceShip;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventDispatchChain;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class GameManager {

    private Stage primaryStage;
    private List<GameObject> gameObjects;
    private List<GameObject> enemyBullets;
    private int score = 50;
    private boolean isFinished = false;
    private long lastEnemyTime = new Date().getTime();
    private SpaceShip player;
    private Button pauseButton;
    private Label scoreLabel;
    private Label timerLabel;
    private Screen gameScreen;
    private Screen mainScreen;
    private AnimationTimer animationTimer;
    private long lastFrameUpdateTime;
    private int time = 0;
    private long fraction = 0;
    private boolean stop = false;
    private int[] highScores = new int[]{100,200,300,400,500,600,700, 800,900,1000};

    // constructor
    public GameManager(Screen gameScreen) {
        this.gameScreen = gameScreen;

        // initialize game objects and enemy bullets
        gameObjects = new ArrayList<>();
        enemyBullets = new ArrayList<>();

        // create player instance
        player = new SpaceShip( 300,300, 100, "player");
        player.setImagePath(Constants.PLAYER_SPACESHIP_IMAGE_PATH);
        gameObjects.add(player);

        // setup score label
        setupScoreLabel();

        // setup timer label
        setupTimerLabel();

        // setup pause button
        setupPauseButton();

        gameScreen.getChildren().add(player);
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void setMain(Screen screen) {
        mainScreen = screen;
    }

    private void setupScoreLabel(){
        scoreLabel = new Label("Score: " + this.score);
        scoreLabel.setFont(Font.font(Constants.FONT_NAME, Constants.FONT_SIZE_SM));
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setLayoutX(Constants.SCREEN_WIDTH * 8 / 10);
        scoreLabel.setLayoutY(Constants.HEADER_Y / 2 - 16);
        gameScreen.getChildren().add(scoreLabel);
    }


    private void setupTimerLabel(){
        timerLabel = new Label("00:00");
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setFont(Font.font(Constants.FONT_NAME, Constants.FONT_SIZE_MD));
        timerLabel.setLayoutY(Constants.HEADER_Y / 2 - 16);
        timerLabel.setLayoutX(Constants.SCREEN_WIDTH / 2);
        gameScreen.getChildren().add(timerLabel);
    }

    private void setupPauseButton(){
        pauseButton = new Button();
        ImageView pauseIcon = new ImageView(new Image(Constants.PAUSE_IMAGE_PATH));
        pauseIcon.setFitHeight(Constants.SS_HEIGHT);
        pauseIcon.setFitWidth(Constants.SS_HEIGHT);
        pauseButton.setBackground(Background.EMPTY);
        pauseButton.setGraphic(pauseIcon);
        pauseButton.setLayoutX(Constants.SCREEN_WIDTH * 9 / 10);
        pauseButton.setLayoutY(Constants.HEADER_Y / 2 - 16);
        pauseButton.setFocusTraversable(false);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pause();
            }
        });
        gameScreen.getChildren().add(pauseButton);

    }

    public void pause(){
        if ( stop){
            ImageView pauseIcon = new ImageView(new Image(Constants.PAUSE_IMAGE_PATH));
            pauseIcon.setFitHeight(Constants.SS_HEIGHT);
            pauseIcon.setFitWidth(Constants.SS_HEIGHT);
            pauseButton.setGraphic(pauseIcon);
            animationTimer.start();
        }else{
            ImageView resumeIcon = new ImageView(new Image(Constants.RESUME_IMAGE_PATH));
            resumeIcon.setFitHeight(Constants.SS_HEIGHT);
            resumeIcon.setFitWidth(Constants.SS_HEIGHT);
            pauseButton.setGraphic(resumeIcon);
            animationTimer.stop();
        }
        stop = !stop;
    }

    public boolean isStop(){
        return stop;
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
        timerStrBuilder.append(secs);
        timerLabel.setText(timerStrBuilder.toString());
    }

    public AlienSpaceShip addNewEnemy(){
        long currentTime = new Date().getTime();
        if ( currentTime - lastEnemyTime >= 5000 && !isFinished){
            lastEnemyTime = currentTime;
            Random random = new Random();
            int high = (int) Constants.SCREEN_HEIGHT - 220;
            double posY = 100 + random.nextInt(high);
            AlienSpaceShip alienSpaceShip = new AlienSpaceShip(Constants.SCREEN_WIDTH, posY, 20, "enemy");
            alienSpaceShip.setImagePath(Constants.ENEMY_SPACESHIP_IMAGE_PATH);
            gameObjects.add(alienSpaceShip);
            return alienSpaceShip;
        }
        return null;
    }

    public void checkCollision() {
        gameObjects.forEach((GameObject gameObject) -> {
            gameObject.move();
            List<GameObject> toBeRemoved = new ArrayList<>();
            if (gameObject.type.equals("enemy")){
                if ( gameObject.isOutOfScreen()){
                    toBeRemoved.add(gameObject);
                }
                if (gameObject.getBoundsInParent().intersects(player.getBoundsInParent())){
                    isFinished = true;
                    animationTimer.stop();
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
                isFinished = true;
                animationTimer.stop();
            }
        });
        if ( isFinished){
            enemyBullets.forEach(bullet1 -> bullet1.stop(true));
            gameObjects.forEach(gameObject -> gameObject.stop(true));
            if ( isHighScore()){
                showHighScoreDialog();
                try {
                    saveNewHighScoreList();
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                } catch (IOException e) {
                    System.out.println("IOException");
                    e.printStackTrace();
                }
            } else {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../Screen/fxml/MainScreen.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveNewHighScoreList() throws IOException {
        String filePath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\Defender\\high_scores.dat";
        System.out.println("FilePath\n" + filePath);
        File highScoresFile = new File(filePath);
        highScoresFile.createNewFile();
        FileOutputStream highScoreData = new FileOutputStream(filePath);
            PrintWriter pw = new PrintWriter(highScoreData);
            for (int highScore : highScores )
                pw.println("Javid: " + highScore);
            pw.close();
    }

    private boolean isHighScore(){
        boolean isHighScore = false;
        if ( highScores.length == 0)
            isHighScore = true;
        for (int i = 0; i < highScores.length;i++){
            if ( score > highScores[i]){
                if ( i == 0) {
                    highScores[i] = score;
                    isHighScore =  true;
                }
                else{
                    int tmp = highScores[i];
                    highScores[i] = score;
                    highScores[i-1] = tmp;
                    isHighScore =  true;
                }
            }
        }
        return isHighScore;
    }

    private void showHighScoreDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText(null);
        alert.setContentText("Your score entered top 10");
        ButtonType backToMenu = new ButtonType("Menu");
        ButtonType exitGame = new ButtonType("Exit");
//        alert.getButtonTypes().setAll(backToMenu, exitGame);
        alert.getButtonTypes().add(ButtonType.CANCEL);

        Button menuButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        menuButton.setText("Menu");
        menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../Screen/fxml/MainScreen.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button exitButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        exitButton.setText("Exit");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        alert.show();
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
