package defenders2FDE.Screen;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SplashScreen extends Screen{


    private Stage primaryStage;
    public SplashScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    @Override
    public Pane display(){
         setPrefSize(600,800);
         setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
         Label title = new Label("Welcome NIBBA !");
         title.setTextFill(Color.WHITE);
         title.setFont(new Font("Arial", 48));
         title.layoutXProperty().bind(widthProperty().subtract(title.widthProperty()).divide(2));
         title.layoutYProperty().bind(heightProperty().subtract(title.heightProperty()).divide(2));


         getChildren().add(title);
         return this;
    }
}