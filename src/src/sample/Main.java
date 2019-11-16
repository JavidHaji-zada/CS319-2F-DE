package sample;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Manager.ScreenManager;
import sample.Screen.MainScreen;
import sample.Screen.SplashScreen;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        SplashScreen splashScreen = new SplashScreen(primaryStage);
        MainScreen mainScreen = new MainScreen(primaryStage);
        ScreenManager screenManager = new ScreenManager();
        Scene appScene = new Scene(screenManager.setScreen(splashScreen));

        primaryStage.setScene(appScene);
        primaryStage.setTitle("Defenders");
        primaryStage.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished( event -> primaryStage.setScene(new Scene(screenManager.setScreen(mainScreen))));
        delay.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
