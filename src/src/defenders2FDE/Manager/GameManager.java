package defenders2FDE.Manager;

import defenders2FDE.Constants;
import defenders2FDE.GameObjects.*;
import defenders2FDE.Screen.GameScreenDemo;
import defenders2FDE.Screen.Screen;
import defenders2FDE.Screen.StoryMode;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static defenders2FDE.Constants.*;

public class GameManager {

    private Stage primaryStage;
    private List<GameObject> gameObjects;
    private List<GameObject> enemyBullets;
    private int score = 10000;
    private int mode;
    private boolean isFinished = false;
    private long lastEnemyTime = new Date().getTime();
    private SpaceShip player;
    private Button pauseButton;
    private Label scoreLabel;
    private Label timerLabel;
    private ProgressBar healthBar;
    private Screen gameScreen;
    private Label messageLabel;
    private boolean isShown = false;
    private AnimationTimer animationTimer;
    private long lastFrameUpdateTime;
    private int time = 0;
    private boolean stop = false;
    private int[] highScores;
    private DataManager dataManager;
    private String buttonSoundPath = Constants.BUTTON_CLICK_SOUND;
    private Media buttonSound = new Media(new File(buttonSoundPath).toURI().toString());
    private MediaPlayer mediaPlayer = new MediaPlayer(buttonSound);

    // constructor
    public GameManager(Screen gameScreen, int mode) throws IOException {
        this.gameScreen = gameScreen;
        this.mode = mode; // 1 - single endless  2 - story mode

        dataManager = new DataManager();
        int[] items = dataManager.getItems();
        // retrieve high scores list
        if (mode == 1) {
            try {
                highScores = dataManager.getHighScores();
            }catch (Exception e){
                highScores = new int[0];
            }
        }
        // initialize game objects and enemy bullets
        gameObjects = new ArrayList<>();
        enemyBullets = new ArrayList<>();

        // create player instance
        player = new SpaceShip( 300,300, 100, "player");
        System.out.println("Current item " + items[0]);
        if ( items[0] == 0){
            player.setImagePath(PLAYER_SPACESHIP_IMAGE_PATH_1);
        }else if ( items[0] == 1){
            player.setImagePath(PLAYER_SPACESHIP_IMAGE_PATH_2);
        }else if ( items[0] == 2){
            player.setImagePath(PLAYER_SPACESHIP_IMAGE_PATH_3);
        }else if ( items[0] == 3){
            player.setImagePath(PLAYER_SPACESHIP_IMAGE_PATH_4);
        }else if ( items[0] == 4){
            player.setImagePath(PLAYER_SPACESHIP_IMAGE_PATH_5);
        }else if ( items[0] == 5){
            player.setImagePath(PLAYER_SPACESHIP_IMAGE_PATH_6);
        }        gameObjects.add(player);

        // setup health bar
        setupHealthBar();

        if(mode == 1) {
            // setup score label
            setupScoreLabel();
        }

        // setup timer label
        setupTimerLabel();

        // setup openPauseDialog button
        setupPauseButton();

        gameScreen.getChildren().add(player);
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    private void setupScoreLabel(){
        scoreLabel = new Label("Score: " + this.score);
        scoreLabel.setFont(Font.font(FONT_NAME, FONT_SIZE_SM));
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setLayoutX(SCREEN_WIDTH * 8 / 10);
        scoreLabel.setLayoutY(HEADER_Y / 2 - 16);
        gameScreen.getChildren().add(scoreLabel);
    }

    private void setupHealthBar(){
        healthBar = new ProgressBar(1);
        healthBar.setLayoutX(SCREEN_WIDTH * 1 / 10);
        healthBar.setLayoutY(HEADER_Y / 2 - 16);
        healthBar.setPrefWidth(SCREEN_WIDTH / 5);
        gameScreen.getChildren().add(healthBar);
    }


    private void setupTimerLabel(){
        timerLabel = new Label("00:00");
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setFont(Font.font(FONT_NAME, FONT_SIZE_MD));
        timerLabel.setLayoutY(HEADER_Y / 2 - 16);
        timerLabel.setLayoutX(SCREEN_WIDTH / 2);
        gameScreen.getChildren().add(timerLabel);
    }

    private void setupPauseButton(){
        pauseButton = new Button();
        ImageView pauseIcon = new ImageView(new Image(Constants.PAUSE_IMAGE_PATH));
        pauseIcon.setFitHeight(Constants.SS_HEIGHT);
        pauseIcon.setFitWidth(Constants.SS_HEIGHT);
        pauseButton.setBackground(Background.EMPTY);
        pauseButton.setGraphic(pauseIcon);
        pauseButton.setLayoutX(SCREEN_WIDTH * 9 / 10);
        pauseButton.setLayoutY(HEADER_Y / 2 - 16);
        pauseButton.setFocusTraversable(false);
        pauseButton.setOnAction(event -> openPauseDialog());
        gameScreen.getChildren().add(pauseButton);

    }

    public void openPauseDialog(){
        showPauseDialog();
        animationTimer.stop();
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

    public GameObject addNewAlien(){
        long currentTime = new Date().getTime();
        if ( currentTime - lastEnemyTime >= 5000){
            lastEnemyTime = currentTime;
            Random random = new Random();
            int high = (int) Constants.SCREEN_HEIGHT - 220;
            double posY = 100 + random.nextInt(high);
            AlienSpaceShip alienSpaceShip = new AlienSpaceShip(Constants.SCREEN_WIDTH, posY);
            alienSpaceShip.setMode(mode);
            alienSpaceShip.setDirection("left");
            gameObjects.add(alienSpaceShip);
            return alienSpaceShip;
        }

        return null;
    }


    public GameObject addNewQueen() {
        long currentTime = new Date().getTime();
        if ( currentTime - lastEnemyTime >= 5000){
            lastEnemyTime = currentTime;
            Random random = new Random();
            int high = (int) Constants.SCREEN_HEIGHT - 500;
            double posY = 250 + random.nextInt(high);
            Queen queen = new Queen(Constants.SCREEN_WIDTH, posY);
            queen.setMode(mode);
            gameObjects.add(queen);
            return queen;
        }
        return null;
    }


    public GameObject addNewAsteroid() {
        long currentTime = new Date().getTime();
        if ( currentTime - lastEnemyTime >= 3000){
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
            darwin.setMode(mode);
            gameObjects.add(darwin);
            return darwin;
        }
        return null;
    }


    public GameObject addNewAstronaut() {
        long currentTime = new Date().getTime();
        if ( currentTime - lastEnemyTime >= 3000){
            lastEnemyTime = currentTime;
            Random random = new Random();
            int high = (int) Constants.SCREEN_WIDTH - 400;
            double posX = 200 + random.nextInt(high);
            high = (int) Constants.SCREEN_HEIGHT - 220;
            double posY = 100 + random.nextInt(high);
            Astronaut astronaut = new Astronaut(posX, posY);
            gameObjects.add(astronaut);
            return astronaut;
        }
        return null;
    }

    public boolean isAllEnemiesDead()
    {
        for(int i = 0; i < gameObjects.size(); i++)
        {
            System.out.println("The type of " + i + "th enemy is " + gameObjects.get(i).type);

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

    public void checkCollision() {

        if(mode == 1)
        {
            gameObjects.forEach((GameObject gameObject) -> {
                gameObject.move();
                List<GameObject> toBeRemoved = new ArrayList<>();
                if (gameObject.type.equals("enemy")){
                    if ( gameObject.isOutOfScreen()){
                        toBeRemoved.add(gameObject);
                    }
                    if (gameObject.getBoundsInParent().intersects(player.getBoundsInParent())) {

                        player.setHealth(Math.max(0, player.getHealth() - gameObject.getCollisionDamage()));
                        healthBar.setProgress((double) player.getHealth() / 100);

                        toBeRemoved.add(gameObject);

                        if(player.getHealth() == 0)
                            isFinished = true;
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

                    player.setHealth(Math.max(0, player.getHealth() - bullet.getCollisionDamage()));
                    healthBar.setProgress((double) player.getHealth() / 100);

                    gameScreen.getChildren().remove(bullet);
                    enemyBullets.remove(bullet);

                    if(player.getHealth() == 0)
                        isFinished = true;

                }
            });
            if (isFinished){
                animationTimer.stop();
                enemyBullets.forEach(bullet1 -> bullet1.stop(true));
                gameObjects.forEach(gameObject -> gameObject.stop(true));
                try {
                    dataManager.setCoin(score);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

        else if (mode == 2)
        {

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
                        healthBar.setProgress((double) player.getHealth() / 100);
                        toBeRemoved.add(gameObject);

                        if(player.getHealth() == 0)
                        {
                            isFinished = true;
                            //animationTimer.stop();
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
                        healthBar.setProgress((double) player.getHealth() / 100);
                        toBeRemoved.add(gameObject);

                        if(player.getHealth() == 0)
                        {
                            isFinished = true;
                            //animationTimer.stop();
                        }
                    }

                    AlienSpaceShip alienSpaceShip = ((Queen) gameObject).produce();
                    if (alienSpaceShip != null) {
                        gameObjects.add(alienSpaceShip);
                        gameScreen.getChildren().add(alienSpaceShip);
                    }
                }

                //Checking collision with Darwin
                if (gameObject.type.equals("Darwin")) {
                    if (gameObject.isOutOfScreen()) {
                        toBeRemoved.add(gameObject);
                    }

                    if (gameObject.getBoundsInParent().intersects(player.getBoundsInParent())){

                        player.setHealth(Math.max(player.getHealth() - gameObject.getCollisionDamage(), 0));
                        healthBar.setProgress((double) player.getHealth() / 100);
                        toBeRemoved.add(gameObject);

                        if(player.getHealth() == 0)
                        {
                            isFinished = true;
                            //animationTimer.stop();
                        }
                    }

                    Bullet bullet = ((Darwin) gameObject).fire();
                    if (bullet != null) {
                        enemyBullets.add(bullet);
                        gameScreen.getChildren().add(bullet);
                    }
                }

                //Checking collision with Asteroid
                if (gameObject.type.equals("Asteroid")) {
                    if (gameObject.isOutOfScreen()) {
                        toBeRemoved.add(gameObject);
                    }

                    if (gameObject.getBoundsInParent().intersects(player.getBoundsInParent())){

                        player.setHealth(Math.max(player.getHealth() - gameObject.getCollisionDamage(), 0));
                        healthBar.setProgress((double) player.getHealth() / 100);
                        toBeRemoved.add(gameObject);

                        if(player.getHealth() == 0)
                        {
                            isFinished = true;
                            //animationTimer.stop();
                        }
                    }
                }

                //Checking collision with Astronaut
                if (gameObject.type.equals("Astronaut")) {
                    if (gameObject.isOutOfScreen()) {
                        toBeRemoved.add(gameObject);
                    }

                    if (gameObject.getBoundsInParent().intersects(player.getBoundsInParent())){

                        player.setHealth(Math.min(player.getHealth() + 20, 100));
                        healthBar.setProgress((double) player.getHealth() / 100);
                        toBeRemoved.add(gameObject);
                    }
                }

                //Checking the collision of enemies with playerBullet
                else if (gameObject.type.equals("playerBullet")) {
                    gameObjects.stream().filter(e-> e.type.equals("enemy")).forEach(enemy -> {

                        if (gameObject.getBoundsInParent().intersects(enemy.getBoundsInParent())) {

                            // Health of the Alien is decreased and the Alien is dead
                            enemy.setHealth(Math.max(enemy.getHealth() - PlayerBulletCollisionDamage, 0));
                            toBeRemoved.add(gameObject);

                            if(enemy.getHealth() == 0)
                            {
                                toBeRemoved.add(enemy);
                                score += AlienPoints;
                                //scoreLabel.setText("Score: " + score );
                            }
                        }
                    });

                    gameObjects.stream().filter(e-> e.type.equals("Queen")).forEach(enemy -> {

                        if (gameObject.getBoundsInParent().intersects(enemy.getBoundsInParent())){

                            // Health of the Queen is decreased
                            enemy.setHealth(Math.max(enemy.getHealth() - PlayerBulletCollisionDamage, 0));
                            toBeRemoved.add(gameObject);

                            if(enemy.getHealth() == 0)
                            {
                                toBeRemoved.add(enemy);
                                toBeRemoved.add(gameObject);
                                score += QueenPoints;
                                //scoreLabel.setText("Score: " + score );
                            }
                        }
                    });

                    gameObjects.stream().filter(e-> e.type.equals("Darwin")).forEach(enemy -> {

                        if (gameObject.getBoundsInParent().intersects(enemy.getBoundsInParent())){

                            // Health of the Queen is decreased
                            enemy.setHealth(Math.max(enemy.getHealth() - PlayerBulletCollisionDamage, 0));
                            toBeRemoved.add(gameObject);

                            if(enemy.getHealth() == 0)
                            {
                                toBeRemoved.add(enemy);
                                toBeRemoved.add(gameObject);
                                score += DarwinPoints;
                                //scoreLabel.setText("Score: " + score );
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
                    healthBar.setProgress((double) player.getHealth() / 100);

                    gameScreen.getChildren().remove(bullet);
                    enemyBullets.remove(bullet);

                    System.out.println("Bullet damage : " + bullet.getCollisionDamage());
                    System.out.println("Player health : " + player.getHealth());

                    if(player.getHealth() == 0)
                    {
                        isFinished = true;
                    }
                }
            });

            if (isFinished){
                animationTimer.stop();
                enemyBullets.forEach(bullet1 -> bullet1.stop(true));
                gameObjects.forEach(gameObject -> gameObject.stop(true));

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

        }

    }

    public void showCongratulations(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CONGRATULATIONS!");
        alert.setHeaderText(null);
        alert.setContentText("You finished the story mode successfully!");

        alert.getButtonTypes().add(ButtonType.CANCEL);

        Button restart = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        restart.setText("Restart");
        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.play();
                System.out.println("button working");
                if(mode == 1)
                {
                    GameScreenDemo gameScreenDemo = null;
                    try {
                        gameScreenDemo = new GameScreenDemo();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //primaryStage = window;
                    gameScreenDemo.setPrimaryStage(primaryStage);
                    Scene gameScene = new Scene(new ScreenManager().setScreen(gameScreenDemo));
                    gameScene.getRoot().requestFocus();
                    primaryStage.setScene(gameScene);
                }
                else if (mode == 2)
                {
                    StoryMode gameScreenDemo = new StoryMode();
                    //primaryStage = window;
                    gameScreenDemo.setPrimaryStage(primaryStage);
                    Scene gameScene = new Scene(new ScreenManager().setScreen(gameScreenDemo));
                    gameScene.getRoot().requestFocus();
                    primaryStage.setScene(gameScene);
                }

                alert.close();
            }
        });

        Button exitButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        exitButton.setText("Go Back to Menu");
        exitButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("../Screen/fxml/MainScreen.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    System.out.println("A");
                } catch (IOException e) {
                    System.out.println("B");
                    e.printStackTrace();
                }

                alert.close();
            }
        });
        alert.show();
    }

    public void resume(){
        ImageView pauseIcon = new ImageView(new Image(Constants.PAUSE_IMAGE_PATH));
        pauseIcon.setFitHeight(Constants.SS_HEIGHT);
        pauseIcon.setFitWidth(Constants.SS_HEIGHT);
        pauseButton.setGraphic(pauseIcon);
        animationTimer.start();
        stop = false;
    }

    public void showPauseDialog(){
        ImageView resumeIcon = new ImageView(new Image(Constants.RESUME_IMAGE_PATH));
        resumeIcon.setFitHeight(Constants.SS_HEIGHT);
        resumeIcon.setFitWidth(Constants.SS_HEIGHT);
        pauseButton.setGraphic(resumeIcon);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Paused");
        alert.setHeaderText(null);

        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.APPLY);

        Button resume = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        resume.setText("Resume");
        resume.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resume();
            }
        });

        Button restart = (Button) alert.getDialogPane().lookupButton(ButtonType.APPLY);
        restart.setText("Restart");
        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.play();
                System.out.println("button working");
                if(mode == 1)
                {
                    GameScreenDemo gameScreenDemo = null;
                    try {
                        gameScreenDemo = new GameScreenDemo();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //primaryStage = window;
                    gameScreenDemo.setPrimaryStage(primaryStage);
                    Scene gameScene = new Scene(new ScreenManager().setScreen(gameScreenDemo));
                    gameScene.getRoot().requestFocus();
                    primaryStage.setScene(gameScene);
                }
                else if (mode == 2)
                {
                    StoryMode gameScreenDemo = new StoryMode();
                    //primaryStage = window;
                    gameScreenDemo.setPrimaryStage(primaryStage);
                    Scene gameScene = new Scene(new ScreenManager().setScreen(gameScreenDemo));
                    gameScene.getRoot().requestFocus();
                    primaryStage.setScene(gameScene);
                }

                alert.close();
            }
        });

        Button exitButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        exitButton.setText("Exit the Game");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("../Screen/fxml/MainScreen.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    System.out.println("A");
                } catch (IOException e) {
                    System.out.println("B");
                    e.printStackTrace();
                }

                alert.close();
            }
        });
        alert.show();
    }

    public void saveNewHighScoreList() throws IOException {
        File defenderFolder = new File(folderPath);
        defenderFolder.mkdir();
        File highScoresFile = new File(highScoreFilePath);
        highScoresFile.createNewFile();
        FileOutputStream highScoreData = new FileOutputStream(highScoreFilePath);
        PrintWriter pw = new PrintWriter(highScoreData);
        if (highScores.length == 0){
            pw.println("Javid: " + score);
        }
        else {
            for (int highScore : highScores)
                pw.println("Javid: " + highScore);
        }
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
        alert.getButtonTypes().add(ButtonType.CANCEL);

        Button menuButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        menuButton.setText("Menu");
        menuButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
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
        exitButton.setOnAction(event -> primaryStage.close());
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

    public void stageMessage(int start, String message)
    {
        if(time >= start && !isShown)
        {
            isShown = true;
            messageLabel = new Label(message);
            messageLabel.setTextFill(Color.WHITE);
            messageLabel.setFont(Font.font(Constants.FONT_NAME, Constants.FONT_SIZE_MD * 2.5));
            messageLabel.setAlignment(Pos.CENTER);
            messageLabel.setPrefWidth(SCREEN_WIDTH * 5 / 10);
            messageLabel.setPrefHeight(SCREEN_HEIGHT * 3 / 10);
            messageLabel.setLayoutX(SCREEN_WIDTH * 5 / 20);
            messageLabel.setLayoutY(SCREEN_HEIGHT * 7 / 20);

            gameScreen.getChildren().add(messageLabel);
        }

        if(time >= start + 150 && isShown)
        {
            gameScreen.getChildren().remove(messageLabel);
            isShown = false;
        }
    }

    public int getTimeSs()
    {
        return time;
    }
}
