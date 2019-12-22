package defenders2FDE.Manager;

import defenders2FDE.Constants;
import defenders2FDE.GameObjects.*;
import defenders2FDE.Screen.Screen;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static defenders2FDE.Constants.*;

public class GameManagerStory {

//    private List<GameObject> gameObjects;
//    private List<GameObject> enemyBullets;
//    private int score = 0;
//    private boolean isFinished = false;
//    private long lastEnemyTime = new Date().getTime();
//    private SpaceShip player;
//    private Label scoreLabel;
//    private Label timerLabel;
//    private Screen gameScreen;
//    private Screen mainScreen;
//    private Stage primaryStage;
//    private AnimationTimer animationTimer;
//    private long lastFrameUpdateTime;
//    private int time = 0;
//    private long fraction = 0;
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

    public GameManagerStory(Screen gameScreen) {
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
        if (stop){
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

    //Newly Added
    public GameObject addNewAlien(){
        long currentTime = new Date().getTime();
        if ( currentTime - lastEnemyTime >= 5000){
            lastEnemyTime = currentTime;
            Random random = new Random();
            int high = (int) Constants.SCREEN_HEIGHT - 220;
            double posY = 100 + random.nextInt(high);
            AlienSpaceShip alienSpaceShip = new AlienSpaceShip(Constants.SCREEN_WIDTH, posY, 50, "enemy");
            gameObjects.add(alienSpaceShip);
            return alienSpaceShip;
        }

        return null;
    }

    //Newly Added
    public GameObject addNewQueen() {
        long currentTime = new Date().getTime();
        if ( currentTime - lastEnemyTime >= 5000){
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


    public GameObject addNewAsteroid() {
        long currentTime = new Date().getTime();
        if ( currentTime - lastEnemyTime >= 5000){
            lastEnemyTime = currentTime;
            Random random = new Random();
            int high = (int) Constants.SCREEN_WIDTH - 400;
            double posX = 200 + random.nextInt(high);
            Asteroid asteroid = new Asteroid(posX, 0);
            gameObjects.add(asteroid);
            return asteroid;
        }
        return null;
    }


    public GameObject addNewDarwin() {
        long currentTime = new Date().getTime();
        if ( currentTime - lastEnemyTime >= 5000){
            lastEnemyTime = currentTime;
            Random random = new Random();
            int high = (int) Constants.SCREEN_HEIGHT - 220;
            double posY = 100 + random.nextInt(high);
            Darwin darwin = new Darwin(Constants.SCREEN_WIDTH, posY);
            gameObjects.add(darwin);
            return darwin;
        }
        return null;
    }


    public GameObject addNewAstronaut() {
        long currentTime = new Date().getTime();
        if ( currentTime - lastEnemyTime >= 5000){
            lastEnemyTime = currentTime;
            Random random = new Random();
            int high = (int) Constants.SCREEN_WIDTH - 400;
            double posX = 200 + random.nextInt(high);
            Astronaut astronaut = new Astronaut(posX, 0);
            gameObjects.add(astronaut);
            return astronaut;
        }
        return null;
    }

    //Newly Added
    public boolean isAllEnemiesDead()
    {
        for(int i = 0; i < gameObjects.size(); i++)
        {
            if(gameObjects.get(i).type.equals("enemy"))
                return false;

            if(gameObjects.get(i).type.equals("Queen"))
                return false;

            if(gameObjects.get(i).type.equals("Darwin"))
                return false;

            if(gameObjects.get(i).type.equals("Asteroid"))
                return false;
        }
        return true;
    }


    //Newly Added
    public void checkCollision(){
        gameObjects.forEach((GameObject gameObject) -> {

            //Making all gameObjects move
            gameObject.move();

            //Initializing a new array to add gameObjects to remove
            List<GameObject> toBeRemoved = new ArrayList<>();

            //Checking collision with Alien
            if (gameObject.type.equals("enemy")) {
                if (gameObject.isOutOfScreen()) {
                    toBeRemoved.add(gameObject);
                }

                if (gameObject.getBoundsInParent().intersects(player.getBoundsInParent())) {

                    player.setHealth(Math.max(player.getHealth() - gameObject.getCollisionDamage(), 0));

                    if(player.getHealth() == 0)
                    {
                        isFinished = true;
                        animationTimer.stop();
                    }
                }

                Bullet enemyBullet = ((AlienSpaceShip) gameObject).fire();
                if (enemyBullet != null) {
                    enemyBullets.add(enemyBullet);
                    gameScreen.getChildren().add(enemyBullet);
                }
            }

            //Checking collision with Queen
            if (gameObject.type.equals("Queen")) {
                if (gameObject.isOutOfScreen()) {
                    toBeRemoved.add(gameObject);
                }

                if (gameObject.getBoundsInParent().intersects(player.getBoundsInParent())){

                    player.setHealth(Math.max(player.getHealth() - gameObject.getCollisionDamage(), 0));

                    if(player.getHealth() == 0)
                    {
                        isFinished = true;
                        animationTimer.stop();
                    }
                }

                AlienSpaceShip alienSpaceShip = ((Queen) gameObject).produce();
                if (alienSpaceShip != null) {
                    gameObjects.add(alienSpaceShip);
                    gameScreen.getChildren().add(alienSpaceShip);
                }
            }

            //Checking the collision of enemies with playerBullet
            else if (gameObject.type.equals("playerBullet")) {
                gameObjects.stream().filter(e-> e.type.equals("enemy")).forEach(enemy -> {

                    if (gameObject.getBoundsInParent().intersects(enemy.getBoundsInParent())) {

                        // Health of the Alien is decreased and the Alien is dead
                        enemy.setHealth(Math.max(enemy.getHealth() - PlayerBulletCollisionDamage, 0));

                        if(enemy.getHealth() == 0)
                        {
                            toBeRemoved.add(enemy);
                            toBeRemoved.add(gameObject);
                            score += AlienPoints;
                            scoreLabel.setText("Score: " + score );
                        }
                    }
                });

                gameObjects.stream().filter(e-> e.type.equals("Queen")).forEach(enemy -> {

                    enemy.setHealth(Math.max(enemy.getHealth() - PlayerBulletCollisionDamage, 0));

                    if (gameObject.getBoundsInParent().intersects(enemy.getBoundsInParent())){

                        // Health of the Queen is decreased
                        enemy.setHealth(Math.max(enemy.getHealth() - PlayerBulletCollisionDamage, 0));

                        if(enemy.getHealth() == 0)
                        {
                            toBeRemoved.add(enemy);
                            toBeRemoved.add(gameObject);
                            score += QueenPoints;
                            scoreLabel.setText("Score: " + score );
                        }
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
                player.setHealth(Math.max(player.getHealth() - bullet.getCollisionDamage(), 0));

                if(player.getHealth() == 0)
                {
                    enemyBullets.forEach(bullet1 -> bullet1.stop(true));
                    gameObjects.forEach(gameObject -> gameObject.stop(true));
                    isFinished = true;
                    animationTimer.stop();
                }
            }
        });
        if (isFinished){
            primaryStage.setScene(mainScreen.getScene());
            primaryStage.getScene().getRoot().requestFocus();
        }
    }

    private boolean isHighScore(){
        boolean isHighScore = false;
        for (int i = 0; i < highScores.length;i++){
            System.out.println("Array" + highScores[i]);
            System.out.println("Player score " + score);
            if ( score > highScores[i]){
                if ( i == 0) {
                    highScores[i] = score;
                    isHighScore =  true;
                    System.out.println("Is high score set tu true");
                }
                else{
                    int tmp = highScores[i];
                    highScores[i] = score;
                    highScores[i-1] = tmp;
                    isHighScore =  true;
                    System.out.println("Is high score set tu true");
                }
            }
        }
        System.out.println("Returning ishighscore" + isHighScore);
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
                    System.out.println("A");
                } catch (IOException e) {
                    System.out.println("B");
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
