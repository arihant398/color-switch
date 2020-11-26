package switcher;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Switcher extends ImageView {

    public Switcher(int multiplier,int incY){
        setImage(new Image(getClass().getResource("colorSwitcher.png").toExternalForm()));
        setFitWidth(30);
        setFitHeight(30);
        int coorY = 450;
        int coorX = 185;
        setLayoutY(coorY - incY);
        setLayoutX(coorX);
    }
}
