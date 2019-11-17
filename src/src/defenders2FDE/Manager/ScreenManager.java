package defenders2FDE.Manager;

import javafx.scene.layout.Pane;
import defenders2FDE.Screen.Screen;

public class ScreenManager {

    public ScreenManager(){

    }

    public Pane setScreen(Screen screen){
        return screen.display();
    }

}
