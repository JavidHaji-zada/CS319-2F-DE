package defenders2FDE;

import defenders2FDE.Screen.Screen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
        setPrefSize(600,800);
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Label title = new Label("Defenders");
        title.setTextFill(Color.WHITE);
        title.setFont(new Font("Arial", 48));
        title.layoutXProperty().bind(widthProperty().subtract(title.widthProperty()).divide(2));
        title.layoutYProperty().bind(heightProperty().subtract(title.heightProperty()).divide(4));

        Button closeButton = new Button("Exit");
        EventHandler<ActionEvent> closeEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                primaryStage.close();
            }
        };

        closeButton.setOnAction(closeEvent);
        getChildren().add(closeButton);
        getChildren().add(title);
        return this;
    }
}
