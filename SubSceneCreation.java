package SubSceneCreat;

import Button.ButtonCreation;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class SubSceneCreation extends SubScene {

    private boolean isHidden;

    public SubSceneCreation() {
        super(new AnchorPane(),400,600);
        prefHeight(600);
        prefWidth(400);
        BackgroundFill background_fill = new BackgroundFill(Color.web("#292929"), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        AnchorPane root2 = (AnchorPane)this.getRoot();
        root2.setBackground(background);
        isHidden = true;
        setLayoutX(0);
        setLayoutY(600);
    }

    public void moveSubScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if(isHidden){
            transition.setToY(-600);
            isHidden = false;
        }else{
            transition.setToY(0);
            isHidden = true;
        }
        transition.play();
    }

    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }
    
    public void goBackSubScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if(!isHidden){
            transition.setToY(600);
            isHidden = true;
        }else{
            transition.setToY(0);
            isHidden = false;
        }
        transition.play();
    }
}
