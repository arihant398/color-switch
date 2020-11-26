package ball;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class Ball_details extends ImageView implements Serializable {
    Color color;
    int size;
    int coordinateX;
    int coordinateY;

    public Ball_details(){
        setImage(new Image(getClass().getResource("ball1.png").toExternalForm()));
        setLayoutY(550);
        setLayoutX(190);
        setFitHeight(20);
        setFitWidth(20);
    }

    public int getColor() {
        if(this.color == Color.MEDIUMPURPLE )
            return 1;
        else if( this.color == Color.CYAN)
            return 2;
        else if( this.color == Color.YELLOW)
            return 3;
        else return 4;
    }
    public void setColor(int color) {
        if(color == 1){
            this.color = Color.MEDIUMPURPLE;
        }
        else if(color == 2){
            this.color = Color.CYAN;
        }
        else if(color == 3){
            this.color = Color.YELLOW;
        }
        else{
            this.color = Color.MAGENTA;
        }
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getCoordinateX() {
        return coordinateX;
    }
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }
    public int getCoordinateY() {
        return coordinateY;
    }
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }
}
