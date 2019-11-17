package defenders2FDE;

import defenders2FDE.Manager.ScreenManager;
import defenders2FDE.Screen.GameScreenDemo;
import defenders2FDE.Screen.Screen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class MainScreen extends Screen {

    private Stage primaryStage;

    public MainScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    @Override
    public Pane display(){
        setPrefSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Label title = new Label("Defenders");
        title.setTextFill(Color.WHITE);
        title.setFont(new Font("Arial", 48));
        title.layoutXProperty().bind(widthProperty().subtract(title.widthProperty()).divide(2));
        title.layoutYProperty().bind(heightProperty().subtract(title.heightProperty()).divide(4));

        Button closeButton = new Button("Exit");
        Button playButton = new Button("Play");

        EventHandler<ActionEvent> closeEvent = e -> primaryStage.close();

        Scene gameScene= new Scene( new ScreenManager().setScreen(new GameScreenDemo(primaryStage)));
        gameScene.getRoot().requestFocus();
        EventHandler<ActionEvent> playEvent = e -> primaryStage.setScene(gameScene);

        closeButton.setOnAction(closeEvent);
        playButton.setOnAction(playEvent);

        getChildren().add(closeButton);
        getChildren().add(playButton);

        getChildren().add(title);
        primaryStage.setFullScreen(true);
        return this;
    }
}
