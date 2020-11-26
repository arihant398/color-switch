package View;

import Button.ButtonCreation;
import SubSceneCreat.SubSceneCreation;
import gameViewManager.GameViewManager;
import infoLabel.InfoLabel;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {
    int width = 400;
    int height = 600;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private int menuButtonStartX = 150;
    private int menuButtonStartY = 80;
    List<ButtonCreation> menuButtons;

    SubSceneCreation settingsSS;
    SubSceneCreation playSS;
    SubSceneCreation followSS;
    SubSceneCreation statsSS;
    SubSceneCreation scoreSS;
    SubSceneCreation creditSS;
    SubSceneCreation ssToHide;

    SubSceneCreation loadGameSS;

    MediaPlayer mediaPlayer;
    private Slider volumeSlider;

    private int website;

    public ViewManager(){
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,width,height);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createBackground();
        createLogo();
        createButtons();
        createSubScenes();
    }

    public Stage getMainStage(){
        return mainStage;
    }
    private void createBackground(){
        BackgroundFill background_fill = new BackgroundFill(Color.web("#292929"), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        mainPane.setBackground(background);
    }
    private void music(){
        String s = "C:\\Users\\erza1\\Desktop\\java\\AP_deadline2\\src\\View\\home.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
    }
    public int getWebsite(){
        return website;
    }
    private void createLogo(){
        ImageView logo = new ImageView(getClass().getResource("WhatsApp Image 2020-11-16 at 4.31.02 PM.jpeg").toExternalForm());
        logo.setFitHeight(170);
        logo.setFitWidth(350);
        logo.setLayoutX(25);
        logo.setLayoutY(10);
        mainPane.getChildren().add(logo);
    }

    private void addMenuButtons(ButtonCreation buttonCreation){
        /*buttonCreation.setLayoutX(menuButtonStartX);
        buttonCreation.setLayoutY(menuButtonStartY+(menuButtons.size()*70));*/
        menuButtons.add(buttonCreation);
        mainPane.getChildren().add(buttonCreation);
    }
    private void createButtons(){
        createPlayButton();
        createSettingsButton();
        createStatsButton();
        createExitButton();
        createFollowButton();
        createScoreButton();
        createCreditsButton();
    }
    private void createPlayButton(){
        ButtonCreation playButton = new ButtonCreation("circle.png",150,150);
        playButton.setLayoutY(210);
        playButton.setLayoutX(110);
        addMenuButtons(playButton);
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chosenSubScene(playSS);
            }
        });
    }
    private void createSettingsButton(){
        ButtonCreation settingsButton = new ButtonCreation("settings.png",50,50);
        settingsButton.setLayoutY(210);
        settingsButton.setLayoutX(0);
        addMenuButtons(settingsButton);
        rotateClockwise(settingsButton);
        settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chosenSubScene(settingsSS);
            }
        });
    }
    private void createStatsButton(){
        ButtonCreation statsButton = new ButtonCreation("stats.png",50,50);
        statsButton.setLayoutY(310);
        statsButton.setLayoutX(0);
        addMenuButtons(statsButton);
        rotateClockwise(statsButton);
        statsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chosenSubScene(statsSS);
            }
        });
    }
    private void createExitButton(){
        ButtonCreation exitButton = new ButtonCreation("exitFin.png",50,50);
        exitButton.setLayoutY(500);
        exitButton.setLayoutX(140);
        addMenuButtons(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }
    private void createFollowButton(){
        ButtonCreation followButton = new ButtonCreation("follow2.png",50,50);
        followButton.setLayoutY(210);
        followButton.setLayoutX(280);
        addMenuButtons(followButton);
        rotateAntiClockwise(followButton);
        followButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chosenSubScene(followSS);
            }
        });
    }

    private void createScoreButton(){
        ButtonCreation scoreButton = new ButtonCreation("scoreFin.png",50,50);
        scoreButton.setLayoutX(280);
        scoreButton.setLayoutY(310);
        addMenuButtons(scoreButton);
        rotateAntiClockwise(scoreButton);
        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chosenSubScene(scoreSS);
            }
        });
    }

    private void createCreditsButton(){
        ButtonCreation creditsButton = new ButtonCreation("exi.png",50,50);
        creditsButton.setLayoutY(400);
        creditsButton.setLayoutX(140);
        addMenuButtons(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chosenSubScene(creditSS);
            }
        });
    }

    private void createPlaySS(){
        playSS = new SubSceneCreation();
        mainPane.getChildren().add(playSS);
        playSS.getPane().getChildren().add(createBackButton());
        playSS.getPane().getChildren().add(newGameB());
        playSS.getPane().getChildren().add(loadGameB());
    }
    private ButtonCreation newGameB(){
        ButtonCreation newB = new ButtonCreation("startGame.jpeg",200,80);
        newB.setLayoutX(100);
        newB.setLayoutY(150);
        newB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameViewManager gameViewManager = new GameViewManager();
                gameViewManager.createNewGame(mainStage);
            }
        });
        return newB;
    }
    private ButtonCreation loadGameB(){
        ButtonCreation loadB = new ButtonCreation("loadGame.jpeg",200,80);
        loadB.setLayoutX(100);
        loadB.setLayoutY(250);
        loadB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chosenSubScene(loadGameSS);
            }
        });
        return loadB;
    }

    private void createLoadSS(){
        loadGameSS = new SubSceneCreation();
        mainPane.getChildren().add(loadGameSS);
        InfoLabel infLoad = new InfoLabel("Saved Game:");
        infLoad.setLayoutY(80);
        infLoad.setLayoutX(30);
        loadGameSS.getPane().getChildren().add(infLoad);
        loadGameSS.getPane().getChildren().add(createBackButton());
    }

    private void createStatsSS(){
        statsSS = new SubSceneCreation();
        mainPane.getChildren().add(statsSS);
        InfoLabel infStats = new InfoLabel("Total Stars Earned: ");
        infStats.setLayoutX(30);
        infStats.setLayoutY(200);
        InfoLabel infStats2 = new InfoLabel("Total Squares Earned: ");
        infStats2.setLayoutX(30);
        infStats2.setLayoutY(300);
        statsSS.getPane().getChildren().add(infStats);
        statsSS.getPane().getChildren().add(infStats2);
        statsSS.getPane().getChildren().add(createBackButton());
    }
    private void createScoreSS(){
        scoreSS = new SubSceneCreation();
        mainPane.getChildren().add(scoreSS);
        InfoLabel infScore = new InfoLabel("Current Number of Stars: ");
        infScore.setLayoutX(30);
        infScore.setLayoutY(200);
        InfoLabel infScore2 = new InfoLabel("Current Number of Squares: ");
        infScore2.setLayoutX(30);
        infScore2.setLayoutY(300);
        scoreSS.getPane().getChildren().add(infScore);
        scoreSS.getPane().getChildren().add(infScore2);
        scoreSS.getPane().getChildren().add(createBackButton());
    }
    private void createCreditSS(){
        creditSS = new SubSceneCreation();
        mainPane.getChildren().add(creditSS);
        InfoLabel infCredit = new InfoLabel("Produced By: ");
        infCredit.setLayoutX(30);
        infCredit.setLayoutY(150);
        InfoLabel infCredit2 = new InfoLabel("Smiti & Arihant: ");
        infCredit2.setLayoutX(30);
        infCredit2.setLayoutY(199);
        creditSS.getPane().getChildren().add(infCredit);
        creditSS.getPane().getChildren().add(infCredit2);
        creditSS.getPane().getChildren().add(createBackButton());
    }
    private void createSettingsSS(){
        settingsSS = new SubSceneCreation();
        mainPane.getChildren().add(settingsSS);
        settingsSS.getPane().getChildren().add(createBackButton());
        settingsSS.getPane().getChildren().add(createStopSoundButton());
        settingsSS.getPane().getChildren().add(createStopMusicButton());
    }
    private ButtonCreation createStopSoundButton(){
        ButtonCreation stopButton = new ButtonCreation("changeV.jpeg",200,80);
        stopButton.setLayoutY(250);
        stopButton.setLayoutX(100);
        final int[] check2 = {0};
        Slider sli = slider();
        sli.setLayoutX(110);
        sli.setLayoutY(340);

        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(check2[0] == 0){
                    settingsSS.getPane().getChildren().add(sli);
                    check2[0] += 1;
                }
                else{
                    settingsSS.getPane().getChildren().remove(sli);
                    check2[0] -= 1;
                }

            }
        });
        return stopButton;
    }
    private ButtonCreation createStopMusicButton(){
        ButtonCreation stopMusicButton = new ButtonCreation("stopM.jpeg",200,80);
        stopMusicButton.setLayoutY(150);
        stopMusicButton.setLayoutX(100);
        final int[] check1 = {0};
        final ImageView[] newI = new ImageView[1];
        String s = "C:\\Users\\erza1\\Desktop\\java\\AP_deadline2\\src\\View\\music.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();
        stopMusicButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(check1[0] == 0){
                    newI[0] = new ImageView(getClass().getResource("playM.jpeg").toExternalForm());
                    check1[0] += 1;
                    mediaPlayer.pause();
                }
                else{
                    newI[0] =new ImageView(getClass().getResource("stopM.jpeg").toExternalForm());
                    check1[0] -= 1;
                    mediaPlayer.setOnEndOfMedia(new Runnable() {
                        public void run() {
                            mediaPlayer.seek(Duration.ZERO);
                        }
                    });
                    mediaPlayer.play();
                }
                stopMusicButton.setGraphic(newI[0]);
                newI[0].setFitHeight(80);
                newI[0].setFitWidth(200);
            }
        });
        return stopMusicButton;
    }
    private void createFollowSS(){
        followSS = new SubSceneCreation();
        mainPane.getChildren().add(followSS);
        followSS.getPane().getChildren().add(createBackButton());
    }
    public ButtonCreation instaButton(){
        ButtonCreation instaB = new ButtonCreation("insta.png",50,50);
        instaB.setLayoutX(150);
        instaB.setLayoutY(150);
        followSS.getPane().getChildren().add(instaB);
        return instaB;
    }
    public ButtonCreation twitterButton(){
        ButtonCreation twitterB = new ButtonCreation("twitter.png",50,50);
        twitterB.setLayoutX(150);
        twitterB.setLayoutY(250);
        followSS.getPane().getChildren().add(twitterB);
        return twitterB;
    }
    public ButtonCreation youtubeButton(){
        ButtonCreation youtubeB = new ButtonCreation("youtube.png",50,50);
        youtubeB.setLayoutX(150);
        youtubeB.setLayoutY(350);
        followSS.getPane().getChildren().add(youtubeB);
        return youtubeB;
    }


    private void createSubScenes(){
        createPlaySS();
        createStatsSS();
        createScoreSS();
        createCreditSS();
        createSettingsSS();
        createFollowSS();
        createLoadSS();
    }
    private void chosenSubScene(SubSceneCreation ss){
        if(ssToHide != null){
            ssToHide.moveSubScene();
        }
        ss.moveSubScene();
        ssToHide = ss;
    }
    private void removeSubScene(){
        if(ssToHide != null){
            ssToHide.goBackSubScene();
        }
        ssToHide = null;
    }
    private ButtonCreation createBackButton(){
        ButtonCreation backButton = new ButtonCreation("back.png",50,50);
        backButton.setLayoutY(5);
        backButton.setLayoutX(0);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                removeSubScene();
            }
        });
        return backButton;
    }
    private void rotateClockwise(ButtonCreation buttonCreation){
        RotateTransition rt = new RotateTransition(Duration.millis(2000), buttonCreation);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }
    private void rotateAntiClockwise(ButtonCreation buttonCreation){
        RotateTransition rt = new RotateTransition(Duration.millis(2000), buttonCreation);
        rt.setByAngle(-360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }
    private Slider slider(){
        volumeSlider = new Slider();
        volumeSlider.setPrefWidth(200);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (volumeSlider.isValueChanging()) {
                    mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
                }
            }
        });
        return volumeSlider;
    }
    protected void updateValues() {
        if (volumeSlider != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    if (!volumeSlider.isValueChanging()) {
                        volumeSlider.setValue((int)Math.round(mediaPlayer.getVolume() * 100));
                    }
                }
            });
        }
    }
}
