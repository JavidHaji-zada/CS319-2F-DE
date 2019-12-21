package defenders2FDE.Screen;

import defenders2FDE.Constants;
import defenders2FDE.Manager.ScreenManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Popup;

import java.io.File;

public class MainScreen extends Screen {

    public MainScreen(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Pane display() {



        // prepare screen
        setPrefSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Label title = new Label("Defenders");
        title.setTextFill(Color.WHITE);
        title.setFont(new Font("Arial", 48));
        title.layoutXProperty().bind(widthProperty().subtract(title.widthProperty()).divide(2));
        title.layoutYProperty().bind(heightProperty().subtract(title.heightProperty()).divide(3));

        // create a label
        Label htpLabel01 = new Label("Key Bindings");
        Label htpLabel02 = new Label("Action");
        Label htpLabel03 = new Label("Alternative Key");
        Label htpLabel04 = new Label("Up Arrow");
        Label htpLabel05 = new Label("Move Up");
        Label htpLabel06 = new Label("W");
        Label htpLabel07 = new Label("Right Arrow");
        Label htpLabel08 = new Label("Move Right");
        Label htpLabel09 = new Label("D");
        Label htpLabel10 = new Label("Down Arrow");
        Label htpLabel11 = new Label("Move Down");
        Label htpLabel12 = new Label("S");
        Label htpLabel13 = new Label("Left Arrow");
        Label htpLabel14 = new Label("Move Left");
        Label htpLabel15 = new Label("A");
        Label htpLabel16 = new Label("Space");
        Label htpLabel17 = new Label("Fire");
        Label htpLabel18 = new Label("F");
        Label htpLabel19 = new Label("P");
        Label htpLabel20 = new Label("Pause Game");
        Label htpLabel21 = new Label("ESC");

        Label[] labels = { htpLabel01, htpLabel02, htpLabel03, htpLabel04, htpLabel05, htpLabel06, htpLabel07,
                htpLabel08, htpLabel09, htpLabel10, htpLabel11, htpLabel12, htpLabel13, htpLabel14, htpLabel15,
                htpLabel16, htpLabel17, htpLabel18, htpLabel19, htpLabel20, htpLabel21 };

        GridPane htpGridPane = new GridPane();
        // create a popup
        Popup htpPopup = new Popup();
        Popup exitPopup = new Popup();
        Popup creditsPopup = new Popup();

        // ------------credits popup
        Label creditsLabel = new Label("Büşra Ünver\nCavit Haci-zade\nCelal Bayraktar\nSamir Süleymanlı\nSelen Uysal");
        creditsLabel.setStyle("-fx-background-color: #000000; ");
        // creditsLabel.setBackground();
        creditsLabel.setMinSize((Constants.SCREEN_WIDTH) / 2, (Constants.SCREEN_HEIGHT) / 2);
        creditsLabel.setAlignment(Pos.CENTER);
        creditsPopup.getContent().add(creditsLabel);
        // --------------Exit pop up

        GridPane exitPane = new GridPane();
        Label exitLabel = new Label("Are you sure you want to exit ヾ( ๑´д`๑)ﾂ");
        exitLabel.setStyle("-fx-background-color: #ffffff; ");

        // prepare button click sound
        String buttonSoundPath = Constants.BUTTON_CLICK_SOUND;
        Media buttonSound = new Media(new File(buttonSoundPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(buttonSound);
        mediaPlayer.setOnEndOfMedia(mediaPlayer::stop);


        Button exitButton = new Button("Yes");
        exitButton.setStyle("-fx-background-color: rgb(23, 114, 189); ");
        exitButton.setAlignment(Pos.CENTER);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("How to play button working");
                getPrimaryStage().close();
            }
        });
        exitPane.setAlignment(Pos.CENTER);
        exitPane.setMinSize((Constants.SCREEN_WIDTH) / 2, (Constants.SCREEN_HEIGHT) / 2);
        exitPane.add(exitLabel, 0, 0, 1, 1);
        exitPane.add(exitButton, 0, 1, 1, 1);
        exitPopup.getContent().add(exitPane);

        int num = 1;

        for (int i = 0; i < 21; i++) {
            if (i % 2 == 0)
                labels[i].setStyle(" -fx-background-color: white;");
            else
                labels[i].setStyle(" -fx-background-color: white;");
            labels[i].setMinWidth(80);
        }

        // set background for how to play popup
        int count = 0;
        for (int i = 0; i < 21; i++) {
            if (count == 0) {
                labels[i].setStyle(" -fx-background-color: rgb(235, 232, 63);");
            }
            if (count == 1) {
                labels[i].setStyle(" -fx-background-color: rgb(84, 97, 240);");
            }
            if ((i + 1) % 3 == 0 && count == 1) {
                count = 0;
            } else if ((i + 1) % 3 == 0 && count == 0) {
                count = 1;
            }
        }

        // add the label
        int htprow = 0;
        int htpcol = 0;
        for (int i = 0; i < 21; i++) {
            htpGridPane.add(labels[i], htprow, htpcol, 1, 1);
            htprow++;
            if (((i + 1) % 3 == 0)) {
                htpcol++;
                htprow = 0;
            }
        }
        htpGridPane.setAlignment(Pos.CENTER);
        htpGridPane.setMinSize((Constants.SCREEN_WIDTH) / 2, (Constants.SCREEN_HEIGHT) / 2);

        htpPopup.getContent().add(htpGridPane);

        // set auto hide
        htpPopup.setAutoHide(true);
        exitPopup.setAutoHide(true);
        creditsPopup.setAutoHide(true);

        // Game Modes button
//        Button modesButton = new Button("Play Game");
//        modesButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                mediaPlayer.play();
//                GameScreenDemo gameScreenDemo = new GameScreenDemo(getPrimaryStage());
//                gameScreenDemo.setMain(MainScreen.this);
//                Scene gameScene = new Scene(new ScreenManager().setScreen(gameScreenDemo));
//                gameScene.getRoot().requestFocus();
//                getPrimaryStage().setScene(gameScene);
//                System.out.println("game modes button working");
//            }
//        });

        Button modesButton = new Button("Play Game");
        modesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.play();
                StoryMode gameScreenDemo = new StoryMode(getPrimaryStage());
                gameScreenDemo.setMain(MainScreen.this);
                Scene gameScene = new Scene(new ScreenManager().setScreen(gameScreenDemo));
                gameScene.getRoot().requestFocus();
                getPrimaryStage().setScene(gameScene);
                System.out.println("game modes button working");
            }
        });

        // Shop Button
        Button shopButton = new Button("Shop");
        shopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.play();
                System.out.println("Shop button working");
            }
        });

        // How to Play Button
        Button htpButton = new Button("How to Play");
        htpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.play();
                System.out.println("How to play button working");
                if (!htpPopup.isShowing())
                    htpPopup.show(getPrimaryStage());
            }
        });

        // Highest scores Button
        Button highScoreButton = new Button("Highest Scores");
        highScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.play();
                System.out.println("Highest Scores button working");
            }
        });

        // Credits Button
        Button creditsButton = new Button("Credits");
        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.play();
                if (!creditsPopup.isShowing())
                    creditsPopup.show(getPrimaryStage());
            }
        });
        Button closeButton = new Button("Exit");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.play();
                if (!exitPopup.isShowing())
                    exitPopup.show(getPrimaryStage());
                getPrimaryStage().close();
            }
        });

        Button[] buttons = { modesButton, shopButton, htpButton, highScoreButton, creditsButton, closeButton };
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
            button.requestFocus();
            gridPaneButton.add(button, a, b, c, d);
            b++;
        }
        gridPaneButton.setVgap(20);
        gridPaneButton.setMinSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // gridPaneButton.widthProperty()(modesButton, HPos.CENTER);
        // gridPaneButton.setAlignment(Pos.CENTER);
        getChildren().add(gridPaneButton);

        // add title to screen
        getChildren().add(title);
        return this;
    }
}
