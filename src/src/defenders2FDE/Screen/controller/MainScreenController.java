package defenders2FDE.Screen.controller;

import defenders2FDE.Constants;
import defenders2FDE.Manager.ScreenManager;
import defenders2FDE.Screen.GameScreenDemo;
import defenders2FDE.Screen.StoryMode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.io.IOException;
import java.lang.*;

import java.io.File;

public class MainScreenController {
    //creating objects we're gonna use for game modes pop up
    private Popup gamePopup = new Popup();
    private VBox verBox = new VBox();
    private HBox forButtons = new HBox();
    private Label modesPrompt = new Label("Which mode do you want to play?");
    private Button endlessButton = new Button("Endless Mode");
    private Button storyButton = new Button("Story Mode");
    @FXML
    Button gameModesButton;

    // prepare button click sound
    private String buttonSoundPath = Constants.BUTTON_CLICK_SOUND;
    private Media buttonSound = new Media(new File(buttonSoundPath).toURI().toString());
    private MediaPlayer mediaPlayer = new MediaPlayer(buttonSound);
    //mediaPlayer.setOnEndOfMedia(mediaPlayer::stop);

    //create stage
    private Stage primaryStage;
    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    //How to play popup
    public void popHtp(javafx.event.ActionEvent actionEvent) throws Exception
    {
        mediaPlayer.setOnEndOfMedia(mediaPlayer::stop);
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/htpPopUp.fxml"));
        Stage window = new Stage();
        Scene scene = new Scene(root);

        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UTILITY);
        window.setScene(scene);
        window.show();
        window.centerOnScreen();
        window.setResizable(false);
    }

    //game modes pop up
    boolean entered = false;
    public void gameModesPopUp(){
        System.out.println("I'm here again");

        //button sound
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(mediaPlayer::stop);

        gamePopup.setAutoHide(true);
        modesPrompt.setMinSize(200, 40);

        //aligning objects
        modesPrompt.setAlignment(Pos.CENTER);
        endlessButton.setAlignment(Pos.CENTER);
        storyButton.setAlignment(Pos.CENTER);
        forButtons.setAlignment(Pos.CENTER);
        verBox.setAlignment(Pos.CENTER);

        //styling of objects
        modesPrompt.setTextFill(Color.web("#ffffff"));
        endlessButton.setStyle("-fx-focus-color: transparent;" + "-fx-faint-focus-color: transparent;");
        storyButton.setStyle("-fx-focus-color: transparent;" + "-fx-faint-focus-color: transparent;");
        verBox.setStyle("-fx-background-color: #151531;" +  "-fx-border-width: 2;" + "-fx-border-insets: 0;" + "-fx-border-radius: 0;"+ "-fx-border-color: black;");
        verBox.setSpacing(5);
        verBox.setPadding(new Insets(15, 12, 15, 12));
        forButtons.setPadding(new Insets(15, 12, 15, 12));
        forButtons.setSpacing(10);

        //add children objects
        if(!entered) {
            verBox.getChildren().addAll(modesPrompt, forButtons);
            forButtons.getChildren().addAll(endlessButton, storyButton);
            gamePopup.getContent().add(verBox);
            entered = true;
        }
        //setting stage
        Stage window = (Stage) gameModesButton.getScene().getWindow();
        gamePopup.show(window); //showing pop up

        //Action for endless mode
        endlessButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.play();
                gamePopup.hide();
                GameScreenDemo gameScreenDemo = null;
                try {
                    gameScreenDemo = new GameScreenDemo();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                primaryStage = window;
                gameScreenDemo.setPrimaryStage(primaryStage);
                Scene gameScene = new Scene(new ScreenManager().setScreen(gameScreenDemo));
                gameScene.getRoot().requestFocus();
                primaryStage.setScene(gameScene);
            }
        });

        //action for story button
        storyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.play();
                System.out.println("button working");
                gamePopup.hide();
                StoryMode gameScreenDemo = new StoryMode();
                primaryStage = window;
                gameScreenDemo.setPrimaryStage(primaryStage);
                Scene gameScene = new Scene(new ScreenManager().setScreen(gameScreenDemo));
                gameScene.getRoot().requestFocus();
                primaryStage.setScene(gameScene);
            }
        });
    }

    //Shop feature popup
    @FXML
    Button shopButton;
    public void PopShop(javafx.event.ActionEvent actionEvent) throws Exception
    {
        mediaPlayer.setOnEndOfMedia(mediaPlayer::stop);
        mediaPlayer.play();

        Stage window = (Stage) shopButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/shop.fxml"));
        Parent root = loader.load();
        ShopController shopController = loader.getController();
        shopController.initializeShopController();
        Scene shopScene = new Scene(root);
        window.setFullScreen(true);
        window.setScene(shopScene);
    }

    //credits scene
    @FXML
    Button creditsButton;
    public void PopCredits(javafx.event.ActionEvent actionEvent) throws Exception
    {
        mediaPlayer.setOnEndOfMedia(mediaPlayer::stop);
        mediaPlayer.play();
        Stage window = (Stage) creditsButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/creditsScreen.fxml"));
        Scene creditsScene = new Scene(root);
        window.setScene(creditsScene);
    }

    //high scores scene
    @FXML
    Button highestScoresButton;
    public void PopHighScores(javafx.event.ActionEvent actionEvent) throws Exception
    {
        mediaPlayer.setOnEndOfMedia(mediaPlayer::stop);
        mediaPlayer.play();

        Stage window = (Stage) highestScoresButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/highScores.fxml"));
        Scene creditsScene = new Scene(root);
        window.setScene(creditsScene);
    }

    //Exit dialog
    public void popExit(javafx.event.ActionEvent actionEvent) throws Exception
    {
        mediaPlayer.setOnEndOfMedia(mediaPlayer::stop);
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/exitPopUp.fxml"));
        Stage window = new Stage();
        Scene scene = new Scene(root);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setScene(scene);
        window.centerOnScreen();
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }
}

