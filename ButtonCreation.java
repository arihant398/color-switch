package Button;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ButtonCreation extends Button {

    public ButtonCreation(String text, int fitW, int fitH){
        ImageView view = new ImageView(getClass().getResource(text).toExternalForm());
        setGraphic(view);
        //setText(text);
        view.setFitWidth(fitW);
        view.setFitHeight(fitH);
        setPrefHeight(39);
        setPrefWidth(100);
        setFont(Font.font("Verdana"));
        iniButtonListerns();
        setStyle("-fx-background-color: transparent");
        final int[] check = {0};
        final ImageView[] newImg = new ImageView[1];
        if(text.equals("follow2.png")){
            Timeline fiveSecondsWonder = new Timeline(
                    new KeyFrame(Duration.seconds(1),
                            new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    if(check[0] == 0){
                                        newImg[0] = new ImageView(getClass().getResource("insta.png").toExternalForm());
                                        check[0]+=1;
                                    }
                                    else if(check[0] == 1){
                                        newImg[0] = new ImageView(getClass().getResource("follow2.png").toExternalForm());
                                        check[0] +=1;
                                    }
                                    else{
                                        newImg[0] = new ImageView(getClass().getResource("youtube.png").toExternalForm());
                                        check[0] -= 2;
                                    }
                                    setGraphic(newImg[0]);
                                    newImg[0].setFitWidth(fitW);
                                    newImg[0].setFitHeight(fitH);
                                }
                            }));
            fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
            fiveSecondsWonder.play();
        }
    }

    private void setButtonPressed(){
        setPrefHeight(35);
        setLayoutY(getLayoutY()+4);
    }
    private void setButtonReleased(){
        setPrefHeight(39);
        setLayoutY(getLayoutY()-4);
    }

    private void iniButtonListerns(){
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    setButtonPressed();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    setButtonReleased();
                }
            }
        });
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new Bloom());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
    }
}
