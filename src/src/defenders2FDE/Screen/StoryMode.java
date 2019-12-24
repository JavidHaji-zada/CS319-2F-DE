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
    private Stage primaryStage;

    private int endOfFirstStage;
    private int endOfSecondStage;
    private int endOfThirdStage;
    private int endOfFourthStage;

    private int numberOfEnemies = 0;

    private int stageTracker = 1;

    public StoryMode(){
        super();
        gameManager = new GameManagerStory(this);
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

        gameManager.checkCollision();

        //********************************************************************
        //                S T A G E   1   S T A R T S
        //********************************************************************



        //First Stage - Aliens
        if(stageTracker == 1 && numberOfEnemies < 5)
        {
            if(gameManager.getTimeSs() >= 100) {
                gameManager.stageMessage(100, "FIRST STAGE!");
            }

            Node newEnemy = gameManager.addNewAlien();
            addNewEnemy(newEnemy);

            //First Contact with Alien
            if(numberOfEnemies == 1)
            {
                //gameManager.meetAlien();
            }

        }
        //First Stage - End
        else if(stageTracker == 1 && gameManager.isAllEnemiesDead())
        {
            System.out.println("if 2");

            numberOfEnemies = 0;
            stageTracker++;

            endOfFirstStage = gameManager.getTimeSs();
        }

        //********************************************************************
        //                S T A G E   2   S T A R T S
        //********************************************************************

        //Second Stage - Aliens
        else if(stageTracker == 2 && numberOfEnemies < 4)
        {
            if(gameManager.getTimeSs() >= endOfFirstStage + 50)
            {
                gameManager.stageMessage(endOfFirstStage, "SECOND STAGE!");
            }

            if (gameManager.getTimeSs() >= endOfFirstStage + 250)
            {
                Node newEnemy = gameManager.addNewAlien();
                addNewEnemy(newEnemy);
            }
        }
        //Second Stage - Queens
        else if(stageTracker == 2 && numberOfEnemies < 7)
        {
            System.out.println("if 4");

            Node newEnemy = gameManager.addNewQueen();
            addNewEnemy(newEnemy);

            //First Contact with Queen
            if(numberOfEnemies == 5)
            {

            }

        }
        //Second Stage - End
        else if(stageTracker == 2 && gameManager.isAllEnemiesDead())
        {
            System.out.println("if 5");

            numberOfEnemies = 0;
            stageTracker++;

            endOfSecondStage = gameManager.getTimeSs();
        }

        //********************************************************************
        //                S T A G E   3   S T A R T S
        //********************************************************************

        //Third Stage - First 2 Aliens
        else if(stageTracker == 3 && numberOfEnemies < 2)
        {
            if(gameManager.getTimeSs() >= endOfSecondStage + 50)
            {
                gameManager.stageMessage(endOfSecondStage, "THIRD STAGE!");
            }

            if (gameManager.getTimeSs() >= endOfSecondStage + 250)
            {
                Node newEnemy = gameManager.addNewAlien();
                addNewEnemy(newEnemy);
            }
        }
        //Third Stage - First Asteroid
        else if(stageTracker == 3 && numberOfEnemies < 3)
        {
            System.out.println("if 2");
            Node newEnemy = gameManager.addNewAsteroid();
            addNewEnemy(newEnemy);

            //First Contact with Asteroid
            if(numberOfEnemies == 3)
            {

            }
        }

        //Third Stage - Second 1 Aliens
        else if(stageTracker == 3 && numberOfEnemies < 4)
        {

            System.out.println("if 3");
            Node newEnemy = gameManager.addNewAlien();
            addNewEnemy(newEnemy);
        }
        //Third Stage - Second Asteroid
        else if(stageTracker == 3 && numberOfEnemies < 5)
        {
            System.out.println("if 4");
            Node newEnemy = gameManager.addNewAsteroid();
            addNewEnemy(newEnemy);
        }
        //Third Stage - Third 2 Aliens
        else if(stageTracker == 3 && numberOfEnemies < 7)
        {
            System.out.println("if 5");
            Node newEnemy = gameManager.addNewAlien();
            addNewEnemy(newEnemy);
        }
        //Third Stage - Third Asteroid
        else if(stageTracker == 3 && numberOfEnemies < 8)
        {
            System.out.println("if 6");
            Node newEnemy = gameManager.addNewAsteroid();
            addNewEnemy(newEnemy);
        }
        //Third Stage - Last 2 Aliens
        else if(stageTracker == 3 && numberOfEnemies < 10)
        {
            System.out.println("if 7    " + gameManager.isAllEnemiesDead());
            Node newEnemy = gameManager.addNewAlien();
            addNewEnemy(newEnemy);
        }
        //Third Stage - End
        else if(stageTracker == 3 && gameManager.isAllEnemiesDead())
        {
            System.out.println("End of Stage 3");

            numberOfEnemies = 0;
            stageTracker++;

            endOfThirdStage = gameManager.getTimeSs();
        }


        //********************************************************************
        //                S T A G E   4   S T A R T S
        //********************************************************************

        else if(stageTracker == 4 && numberOfEnemies < 1)
        {
            System.out.println("Start of Stage 4");
            System.out.println(gameManager.getTimeSs() + "  " + endOfThirdStage);

            if(gameManager.getTimeSs() >= endOfThirdStage + 50)
            {
                gameManager.stageMessage(endOfThirdStage, "FOURTH STAGE!");
            }

            if (gameManager.getTimeSs() >= endOfThirdStage + 250)
            {
                Node newEnemy = gameManager.addNewAlien();
                addNewEnemy(newEnemy);
            }
        }
        else if(stageTracker == 4 && numberOfEnemies < 2)
        {
            Node newEnemy = gameManager.addNewQueen();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 3)
        {
            Node newEnemy = gameManager.addNewAsteroid();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 4)
        {
            Node newEnemy = gameManager.addNewAlien();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 5)
        {
            Node newEnemy = gameManager.addNewQueen();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 6)
        {
            Node newEnemy = gameManager.addNewAsteroid();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 7)
        {
            Node newEnemy = gameManager.addNewAlien();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 8)
        {
            Node newEnemy = gameManager.addNewAstronaut();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 9)
        {
            Node newEnemy = gameManager.addNewQueen();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 10)
        {
            Node newEnemy = gameManager.addNewAsteroid();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 11)
        {
            Node newEnemy = gameManager.addNewAlien();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 12)
        {
            Node newEnemy = gameManager.addNewAstronaut();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 13)
        {
            Node newEnemy = gameManager.addNewQueen();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 14)
        {
            Node newEnemy = gameManager.addNewAsteroid();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 15)
        {
            Node newEnemy = gameManager.addNewAlien();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 16)
        {
            Node newEnemy = gameManager.addNewAstronaut();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 17)
        {
            Node newEnemy = gameManager.addNewQueen();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 18)
        {
            Node newEnemy = gameManager.addNewAsteroid();
            addNewEnemy(newEnemy);
        }
        else if(stageTracker == 4 && numberOfEnemies < 19)
        {
            Node newEnemy = gameManager.addNewAlien();
            addNewEnemy(newEnemy);
        }
        //Fourth Stage - End
        else if(stageTracker == 4 && gameManager.isAllEnemiesDead())
        {
            numberOfEnemies = 0;
            stageTracker++;

            endOfFourthStage = gameManager.getTimeSs();
        }

        //********************************************************************
        //                S T A G E   5   S T A R T S
        //********************************************************************

        else if (stageTracker == 5 && numberOfEnemies < 2)
        {
            if(gameManager.getTimeSs() >= endOfFourthStage + 50)
            {
                gameManager.stageMessage(endOfFourthStage, "FIFTH STAGE!");
            }

            if (gameManager.getTimeSs() > endOfFourthStage + 250)
            {
                Node newEnemy = gameManager.addNewAlien();
                addNewEnemy(newEnemy);
            }
        }
        else if (stageTracker == 5 && numberOfEnemies < 3)
        {
            Node newEnemy = gameManager.addNewQueen();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 4)
        {
            Node newEnemy = gameManager.addNewAstronaut();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 5)
        {
            Node newEnemy = gameManager.addNewAlien();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 6)
        {
            Node newEnemy = gameManager.addNewAsteroid();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 7)
        {
            Node newEnemy = gameManager.addNewQueen();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 8)
        {
            Node newEnemy = gameManager.addNewAsteroid();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 9)
        {
            Node newEnemy = gameManager.addNewDarwin();
            addNewEnemy(newEnemy);

            //First Contact with Darwin
            if(numberOfEnemies == 9)
            {

            }
        }
        else if (stageTracker == 5 && numberOfEnemies < 10)
        {
            Node newEnemy = gameManager.addNewAsteroid();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 11)
        {
            Node newEnemy = gameManager.addNewAstronaut();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 12)
        {
            Node newEnemy = gameManager.addNewAlien();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 13)
        {
            Node newEnemy = gameManager.addNewAsteroid();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 15)
        {
            Node newEnemy = gameManager.addNewQueen();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 16)
        {
            Node newEnemy = gameManager.addNewDarwin();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 17)
        {
            Node newEnemy = gameManager.addNewAstronaut();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 18)
        {
            Node newEnemy = gameManager.addNewAlien();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 19)
        {
            Node newEnemy = gameManager.addNewDarwin();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 20)
        {
            Node newEnemy = gameManager.addNewAstronaut();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 22)
        {
            Node newEnemy = gameManager.addNewAsteroid();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 24)
        {
            Node newEnemy = gameManager.addNewAlien();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 25)
        {
            Node newEnemy = gameManager.addNewAstronaut();
            addNewEnemy(newEnemy);
        }
        else if (stageTracker == 5 && numberOfEnemies < 26)
        {
            Node newEnemy = gameManager.addNewAlien();
            addNewEnemy(newEnemy);
        }
        //Game Finished
        else if(stageTracker == 5 && gameManager.isAllEnemiesDead())
        {


        }

    }

    private void addNewEnemy(Node newEnemy)
    {
        if ( newEnemy != null){
            getChildren().add(newEnemy);
            numberOfEnemies ++;
        }
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
                gameManager.pause();
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
