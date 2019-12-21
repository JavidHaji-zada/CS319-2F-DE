package defenders2FDE.Screen;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class exitPopUp {
    public void exitGame(javafx.event.ActionEvent actionEvent) throws Exception
    {
        Platform.exit();
    }

}
