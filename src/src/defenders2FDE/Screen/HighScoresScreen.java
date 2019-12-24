package defenders2FDE.Screen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HighScoresScreen {
    @FXML
    Button backButton;

    public void backToMain(javafx.event.ActionEvent actionEvent) throws Exception{
        Stage window;
        window = (Stage) backButton.getScene().getWindow();
        Pane root = FXMLLoader.load(getClass().getResource("fxml/MainScreen.fxml"));
        window.setScene(new Scene(root));
    }
}
