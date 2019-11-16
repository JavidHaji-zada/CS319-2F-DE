package sample.Manager;

import javafx.scene.layout.Pane;
import sample.Screen.Screen;

public class ScreenManager {

    public ScreenManager(){

    }

    public Pane setScreen(Screen screen){
        return screen.display();
    }

}
