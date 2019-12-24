package defenders2FDE;

import defenders2FDE.Screen.MainScreenController;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import defenders2FDE.Manager.ScreenManager;
import defenders2FDE.Screen.SplashScreen;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        SplashScreen splashScreen = new SplashScreen();
        ScreenManager screenManager = new ScreenManager();
        Scene appScene = new Scene(screenManager.setScreen(splashScreen));
        primaryStage.setScene(appScene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Defenders");
        //primaryStage.setFullScreen(true);
        //primaryStage.setMaximized(true);
        primaryStage.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Screen/fxml/MainScreen.fxml"));
        Parent root = (Parent) loader.load();
        MainScreenController controller = (MainScreenController) loader.getController();

        controller.setStage(primaryStage);
        Scene mainScene = new Scene(root);
        primaryStage.setFullScreen(true);
        delay.setOnFinished( event -> primaryStage.setScene(mainScene));
        delay.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
