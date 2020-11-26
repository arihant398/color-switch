package infoLabel;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class InfoLabel extends Label {
    private String labelBg = "temp.jpeg";

    public InfoLabel(String text){
        setPrefHeight(49);
        setPrefWidth(340);
        setText(text);
        setWrapText(true);
        setFont(Font.font("Georgia",23));
        setAlignment(Pos.CENTER);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource(labelBg).toExternalForm(),340,49, false,true),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        setBackground(new Background(backgroundImage));
    }

}
