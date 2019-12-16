package defenders2FDE.Screen;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;

public abstract class  Screen extends Pane {

    private Stage primaryStage;
    public Screen(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
    public abstract Pane display();

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
