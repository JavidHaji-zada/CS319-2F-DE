package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Manager.ScreenManager;
import sample.Screen.MainScreen;
import sample.Screen.SplashScreen;

import java.util.concurrent.TimeUnit;

public class Main extends Application {

    private static Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception{
        SplashScreen splashScreen = new SplashScreen();
        MainScreen mainScreen = new MainScreen();
        ScreenManager screenManager = new ScreenManager();
        Scene appScene = new Scene(screenManager.setScreen(splashScreen));

        primaryStage.setScene(appScene);
        primaryStage.setTitle("Defenders");
        this.primaryStage = primaryStage;
        this.primaryStage.show();
//        appScene.setRoot(mainScreen.display());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
