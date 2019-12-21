package defenders2FDE.Screen;

import defenders2FDE.Manager.ScreenManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

public class MainScreenController {

    public void pophtp(javafx.event.ActionEvent actionEvent) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("htpPopUp.fxml"));
        Stage window = new Stage();
        Scene scene = new Scene(root);

        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.DECORATED);
        window.setScene(scene);
        window.show();
        window.centerOnScreen();
        window.setResizable(false);
    }

    public void PopGameModes(javafx.event.ActionEvent actionEvent) throws Exception
    {
        /*

        Stage window = new Stage();
        GameScreenDemo gameScreenDemo = new GameScreenDemo(window);
        MainScreen mainScreen = new MainScreen(window);
        gameScreenDemo.setMain(mainScreen);
        Scene gameScene = new Scene(new ScreenManager().setScreen(gameScreenDemo));
        gameScene.getRoot().requestFocus();
        window.setScene(gameScene);
        */


    }

    public void popExit(javafx.event.ActionEvent actionEvent) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("exitPopUp.fxml"));
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
