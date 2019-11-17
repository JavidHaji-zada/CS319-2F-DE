package defenders2FDE;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import defenders2FDE.Manager.ScreenManager;
import defenders2FDE.Screen.SplashScreen;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        SplashScreen splashScreen = new SplashScreen(primaryStage);
        MainScreen mainScreen = new MainScreen(primaryStage);
        ScreenManager screenManager = new ScreenManager();
        Scene appScene = new Scene(screenManager.setScreen(splashScreen));

        primaryStage.setScene(appScene);

        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        primaryStage.setMaximized(true);
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
