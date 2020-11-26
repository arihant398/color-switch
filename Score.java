package score;

import infoLabel.transparentLavel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.Serializable;

public class Score extends Label implements Serializable{
    private int score;

    public Score(int score){
        this.score = score;
        setStyle("-fx-background-color: transparent");
        setPrefHeight(49);
        setPrefWidth(100);
        setText(""+score);
        setWrapText(true);
        setFont(Font.font("Georgia",23));
        setLayoutY(5);
        setLayoutX(50);
        setTextFill(Color.web("#FFFFFF"));
        setAlignment(Pos.CENTER);
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}
