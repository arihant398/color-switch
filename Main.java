package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import View.ViewManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewManager manager = new ViewManager();
        primaryStage = manager.getMainStage();
        manager.instaButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getHostServices().showDocument("https://www.instagram.com/");
            }
        });
        manager.twitterButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getHostServices().showDocument("https://www.twitter.com/");
            }
        });
        manager.youtubeButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getHostServices().showDocument("https://www.youtube.com/");
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private int user;
    private int score;


    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void pause_game(){}
    public void resume_game(){}
    public void display_game(){}
    public void add_score(){}
    public void back_navigate(){}
    public void home_navigate(){}
    public void settings(){}
}