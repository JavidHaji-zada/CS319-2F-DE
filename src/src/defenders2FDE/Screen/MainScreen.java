package defenders2FDE.Screen;

import defenders2FDE.Constants;
import defenders2FDE.Manager.ScreenManager;
import defenders2FDE.Screen.Screen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        title.layoutYProperty().bind(heightProperty().subtract(title.heightProperty()).divide(5));

        //Game Modes button
        Button modesButton = new Button("Game Modes");
        modesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene gameScene = new Scene(new ScreenManager().setScreen(new GameScreenDemo(primaryStage)));
                gameScene.getRoot().requestFocus();
                primaryStage.setScene(gameScene);
                System.out.println("game modes button working");
            }
        });

        //Shop Button
        Button shopButton = new Button("Shop");
        shopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Shop button working");
            }
        });

        //How to Play Button
        Button htpButton = new Button("How to Play");
        htpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("How to play button working");
            }
        });

        //Highest scores Button
        Button highScoreButton = new Button("Highest Scores");
        highScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Highest Scores button working");
            }
        });

        //Credits Button
        Button creditsButton = new Button("Credits");
        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Credits button working");
            }
        });

        //Exit Button
        Button closeButton = new Button("Exit");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        Button[] buttons = {modesButton, shopButton, htpButton, highScoreButton, creditsButton, closeButton};
        GridPane gridPaneButton = new GridPane();
        int b = 0;
        int a = 0;
        int c = 1;
        int d = 1;
        gridPaneButton.setAlignment(Pos.CENTER);
        for (Button button : buttons) {
            button.setMaxWidth(200);
            button.setStyle("-fx-background-color: rgb(23, 114, 189); ");
            button.setAlignment(Pos.CENTER);
            gridPaneButton.add(button, a, b, c, d);
            b++;
        }
        gridPaneButton.setVgap(20);
        gridPaneButton.setMinSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);


        //gridPaneButton.widthProperty()(modesButton, HPos.CENTER);
        gridPaneButton.setAlignment(Pos.CENTER);
        getChildren().add(gridPaneButton);

        //add title to screen
        getChildren().add(title);
        return this;
    }
}
