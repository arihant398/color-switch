package score;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Star extends ImageView {
    private int countStars;

    public Star(int dec){
        setImage(new Image(getClass().getResource("star.jpeg").toExternalForm()));
        setFitHeight(30);
        setFitWidth(30);
        setLayoutX(185);
        setLayoutY(335 - dec);
    }

    public int getCountStars() {
        return countStars;
    }
    public void setCountStars(int countStars) {
        this.countStars = countStars;
    }
}
