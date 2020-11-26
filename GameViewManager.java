package gameViewManager;

import Button.ButtonCreation;
import SubSceneCreat.SubSceneCreation;
import javafx.animation.*;
import obstacles.Obstacles;
import obstacles.arcCreation;
import ball.Ball_details;
import infoLabel.InfoLabel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import infoLabel.transparentLavel;
import javafx.util.Duration;
import score.Score;
import score.Star;
import switcher.Switcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameViewManager {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private Stage mainStage;
    private Ball_details view;
    private ImageView obsView;
    private ImageView switcher;

    List<Switcher> allSwitcher;
    private int switIndex = 0;
    private Switcher swit1;
    private Switcher swit2;

    private SubSceneCreation pauseSS;
    private SubSceneCreation endGameSS;
    private SubSceneCreation ssToHide;

    private static final int gameWidth = 400;
    private static final int gameHeight = 600;

    private boolean isSpaceBarPressed = false;
    private AnimationTimer gameTimer;

    private Obstacles obs;
    private Obstacles obs2;
    private Obstacles obs3;
    private Obstacles obs4;
    private Obstacles obs5;
    List<Obstacles> allObs;
    private int arcRotationTime = 5;
    private double collidedObsY;

    private Group allObjects;

    private int ballColor;

    private Score tl1;
    private int score = 0;
    private Star starImg;
    private Star starImg2;
    List<Star> allStar;

    private transparentLavel tl;

    private int upwardMove = 6;
    private int gravity = 1;
    private int objectDown = 5;

    private boolean isCollide = true;
    private boolean isResumeButtonUsed = false;

    private boolean gameOn = false;
    double tempBallY;

    public GameViewManager(){
        allSwitcher = new ArrayList<>();
        //arcGroup = new ArrayList<>();
        allStar = new ArrayList<>();
        allObs = new ArrayList<>();
        initializeStage();
        createBackground();
        createGameButtons();
        createScore();
        createKeyListeners();
        createStar();
        createOBS();
        createSwitcher();
        createScoreCunter();
        createGameSS();
        /*createAllObjectGroup();*/
        createGameBall();
    }

    private void createAllObjectGroup(){
        allObjects = new Group();
        //allObjects.getChildren().addAll(root,starImg);
        for(Switcher i:allSwitcher){
            allObjects.getChildren().add(i);
        }
        gamePane.getChildren().add(allObjects);
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane,gameWidth,gameHeight);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }
    public void createNewGame(Stage mainStage){
        this.mainStage = mainStage;
        this.mainStage.hide();
        createGameLoop();
        gameStage.show();
    }
    private void createKeyListeners(){
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.A){
                    isSpaceBarPressed = true;
                }
            }
        });
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.A){
                    isSpaceBarPressed = false;
                }
            }
        });
    }
    private void createGameBall(){
        view = new Ball_details();
        ballColor = view.getColor();
        gamePane.getChildren().add(view);
        tempBallY = view.getLayoutY();
    }
    private void createGameLoop(){
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveBall();
                moveObsDown();
                updateScore();
                for(Switcher i:allSwitcher){
                    checkCollisionBallSwitch(i,view);
                }
                for(Obstacles i:allObs){
                        checkCollisionBallOBS(view,i.getArc1());
                        checkCollisionBallOBS(view,i.getArc2());
                        checkCollisionBallOBS(view,i.getArc3());
                        checkCollisionBallOBS(view,i.getArc4());
                }
                for(Star i:allStar){
                    checkCollisionBallStar(view,i);
                }
                transferObs();
                gameOn = true;
            }
        };
        gameTimer.start();
    }
    private void moveBall(){
        if(isSpaceBarPressed){
            if(view.getLayoutY()>0){
                view.setLayoutY(view.getLayoutY() - upwardMove);
            }
        }
        if(!isSpaceBarPressed){
            if(view.getLayoutY()<gameHeight-30){
                view.setLayoutY(view.getLayoutY() + gravity);
            }
        }
    }

    private void addSwitcher(Switcher swi){
        allSwitcher.add(swi);
        gamePane.getChildren().add(swi);
    }
    private void createSwitcher(){
        swit1 = new Switcher(allSwitcher.size() + 1,0);
        addSwitcher(swit1);
        swit2 = new Switcher(allSwitcher.size() + 1,250);
        addSwitcher(swit2);

    }
    private void createBackground(){
        BackgroundFill background_fill = new BackgroundFill(Color.web("#292929"), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        gamePane.setBackground(background);
    }
    private void createGameButtons(){
        createPauseButton();
    }
    private void createGameSS(){
        createPauseSS();
    }
    private void createScore(){
        tl = new transparentLavel("Score:");
        tl.setLayoutX(5);
        tl.setLayoutY(5);
        tl.setTextFill(Color.web("#FFFFFF"));
        gamePane.getChildren().add(tl);
    }
    private void createScoreCunter(){
        tl1 = new Score(score);
        gamePane.getChildren().add(tl1);
    }
    private void updateScore(){
        gamePane.getChildren().remove(tl1);
        createScoreCunter();
    }
    /* private void createTempObs(){
         obsView = new ImageView(getClass().getResource("obs2.png").toExternalForm());
         obsView.setFitWidth(150);
         obsView.setFitHeight(150);
         obsView.setLayoutX(125);
         obsView.setLayoutY(200);
         rotateAntiClockwise(obsView);
         gamePane.getChildren().add(obsView);
     }
 */

    private void generateArcs(Obstacles o){
        gamePane.getChildren().add(o.getArc1());
        gamePane.getChildren().add(o.getArc2());
        gamePane.getChildren().add(o.getArc3());
        gamePane.getChildren().add(o.getArc4());
    }
    private void createOBS(){
        obs = new Obstacles(0,1);
        obs2 = new Obstacles(250,2);
        obs3 = new Obstacles(500,3);
        obs4 = new Obstacles(750,2);
        obs5 = new Obstacles(1000,1);

        generateArcs(obs);
        generateArcs(obs2);
        generateArcs(obs3);
        generateArcs(obs4);
        generateArcs(obs5);

        allObs.add(obs);
        allObs.add(obs2);
        allObs.add(obs3);
        allObs.add(obs4);
        allObs.add(obs5);
    }

    private void createPauseButton(){
        ButtonCreation pauseButton = new ButtonCreation("finPause.png",50,50);
        pauseButton.setLayoutY(0);
        pauseButton.setLayoutX(300);
        gamePane.getChildren().add(pauseButton);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chosenSubScene(pauseSS);
                gameTimer.stop();
                gameOn = false;
                gamePane.getChildren().remove(tl);
                gamePane.getChildren().remove(tl1);
                gamePane.getChildren().remove(view);
                for(Star i:allStar){
                    gamePane.getChildren().remove(i);
                }
                for(Switcher i: allSwitcher){
                    gamePane.getChildren().remove(i);
                }
                for(Obstacles i:allObs){
                    gamePane.getChildren().remove(i.getArc1());
                    gamePane.getChildren().remove(i.getArc2());
                    gamePane.getChildren().remove(i.getArc3());
                    gamePane.getChildren().remove(i.getArc4());
                }
            }
        });
    }
    private void createStar(){
        starImg = new Star(0);
        gamePane.getChildren().add(starImg);
        starImg2 = new Star(250);
        gamePane.getChildren().add(starImg2);
        allStar.add(starImg);
        allStar.add(starImg2);
    }

    private ButtonCreation createSaveButton(){
        ButtonCreation saveButton = new ButtonCreation("saveGame.jpeg",200,80);
        saveButton.setLayoutX(100);
        saveButton.setLayoutY(150);
        gamePane.getChildren().add(saveButton);
        return saveButton;
    }
    private ButtonCreation createExitButton(){
        ButtonCreation exitButton = new ButtonCreation("exitGame.jpeg",200,80);
        exitButton.setLayoutX(100);
        exitButton.setLayoutY(250);
        gamePane.getChildren().add(exitButton);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStage.hide();
                mainStage.show();
            }
        });
        return exitButton;
    }
    private ButtonCreation createReviveButton(){
        ButtonCreation reviveButton = new ButtonCreation("temp.jpeg",200,80);
        reviveButton.setLayoutY(350);
        reviveButton.setLayoutX(100);
        reviveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                removeSubScene();
                gameTimer.start();
                view.setLayoutY(collidedObsY + 100);
                gamePane.getChildren().add(tl);
                gamePane.getChildren().add(tl1);
                for(Star i:allStar){
                    gamePane.getChildren().add(i);
                }
                for(Obstacles i:allObs){
                    gamePane.getChildren().add(i.getArc1());
                    gamePane.getChildren().add(i.getArc2());
                    gamePane.getChildren().add(i.getArc3());
                    gamePane.getChildren().add(i.getArc4());
                }
                for(Switcher i: allSwitcher){
                    gamePane.getChildren().add(i);
                }
                gamePane.getChildren().add(view);
                isResumeButtonUsed = true;
            }

        });
        return reviveButton;
    }

    private void createPauseSS(){
        pauseSS = new SubSceneCreation();
        gamePane.getChildren().add(pauseSS);
        pauseSS.getPane().getChildren().add(createBackButton());
        pauseSS.getPane().getChildren().add(createSaveButton());
        pauseSS.getPane().getChildren().add(createExitButton());
    }

    private void createEndGameSS(){
        endGameSS = new SubSceneCreation();
        InfoLabel inf1 = new InfoLabel("GAME OVER");
        inf1.setLayoutX(30);
        inf1.setLayoutY(200);
        InfoLabel inf2 = new InfoLabel("YOUR SCORE: " + score);
        inf2.setLayoutX(30);
        inf2.setLayoutY(100);
        gamePane.getChildren().add(endGameSS);
        endGameSS.getPane().getChildren().add(inf1);
        endGameSS.getPane().getChildren().add(inf2);
        endGameSS.getPane().getChildren().add(createExitButton());
        if(score>10){
            endGameSS.getPane().getChildren().add(createReviveButton());
        }
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
                gameTimer.start();
                gamePane.getChildren().add(tl);
                gamePane.getChildren().add(tl1);
                for(Star i:allStar){
                    gamePane.getChildren().add(i);
                }
                /*for(Group i:arcGroup){
                    gamePane.getChildren().add(i);
                }*/
                for(Obstacles i:allObs){
                    gamePane.getChildren().add(i.getArc1());
                    gamePane.getChildren().add(i.getArc2());
                    gamePane.getChildren().add(i.getArc3());
                    gamePane.getChildren().add(i.getArc4());
                }
                for(Switcher i: allSwitcher){
                    gamePane.getChildren().add(i);
                }
                gamePane.getChildren().add(view);
            }
        });
        return backButton;
    }
    private void rotateClockwise(Group buttonCreation){
        RotateTransition rt = new RotateTransition(Duration.millis(5000), buttonCreation);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }
    private void rotateAntiClockwise(Group buttonCreation){
        RotateTransition rt = new RotateTransition(Duration.millis(5000), buttonCreation);
        rt.setByAngle(-360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }

    private void rotateTestAntiClockwise(arcCreation buttonCreation){
        RotateTransition rt = new RotateTransition(Duration.millis(5000), buttonCreation);
        rt.setByAngle(-360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }

    public int collision(ImageView a,Ball_details b) {
        Random rand = new Random();

        if (b.getBoundsInParent().intersects(a.getBoundsInParent())) {
            switIndex += 1;
            return rand.nextInt(4)+1;
        }
        return 0;
    }
    private void checkCollisionBallSwitch(Switcher a, Ball_details b){
        int check1 = collision(a,b);
        Random ran = new Random();
        while(check1 == ballColor){
            check1 = ran.nextInt(4) + 1;
        }
        if(check1 == 4){
            b.setImage(new Image(getClass().getResource("pink.png").toExternalForm()));
            a.setLayoutY(a.getLayoutY() - 500);
            ballColor = 4;
        }
        else if(check1 == 1){
            b.setImage(new Image(getClass().getResource("purple.png").toExternalForm()));
            a.setLayoutY(a.getLayoutY() - 500);
            ballColor = 1;
        }
        else if(check1 == 3){
            b.setImage(new Image(getClass().getResource("yellow.png").toExternalForm()));
            a.setLayoutY(a.getLayoutY() - 500);
            ballColor = 3;
        }
        else if(check1 == 2){
            b.setImage(new Image(getClass().getResource("ball1.png").toExternalForm()));
            a.setLayoutY(a.getLayoutY() - 500);
            ballColor = 2;
        }
    }
    private void gameEnd(){
        gameTimer.stop();
        createEndGameSS();
        chosenSubScene(endGameSS);
        gameOn = false;
        gamePane.getChildren().remove(tl);
        gamePane.getChildren().remove(tl1);
        gamePane.getChildren().remove(view);
        for(Star i:allStar){
            gamePane.getChildren().remove(i);
        }
        for(Switcher i: allSwitcher){
            gamePane.getChildren().remove(i);
        }
        for(Obstacles i:allObs){
            gamePane.getChildren().remove(i.getArc1());
            gamePane.getChildren().remove(i.getArc2());
            gamePane.getChildren().remove(i.getArc3());
            gamePane.getChildren().remove(i.getArc4());
        }
    }
    private double dist( Ball_details a, arcCreation b){
        return a.getLayoutY() - b.getCenterY();
    }
    private int collisionOBS(Ball_details a, arcCreation b) {
        if (b.getBoundsInParent().intersects(a.getBoundsInParent())) {
            return b.getColor();
        }
        return 0;
    }
    private void checkCollisionBallOBS(Ball_details a,arcCreation b ){
        int check2 = collisionOBS(a,b);
        if(check2 != 0){
            if(check2 != ballColor){
                if(b.getObsType() == 1){
                    if((dist(a,b) > 0 && dist(a,b) > 65) || (dist(a,b) < 0 && (-dist(a,b)) > 65)){
                        collidedObsY = b.getCenterY();
                        gameEnd();
                    }
                }
                else if(b.getObsType() == 2 || b.getObsType() == 3){
                    if((dist(a,b) > 0 && dist(a,b) > 37) || (dist(a,b) < 0 && (-dist(a,b)) > 55)){
                        collidedObsY = b.getCenterY();
                        gameEnd();
                    }
                }
            }
        }
    }

    private int collisionStar(Ball_details a, Star b) {
        if (b.getBoundsInParent().intersects(a.getBoundsInParent())) {
            return 1;
        }
        return 0;
    }
    private void checkCollisionBallStar(Ball_details a,Star b ){
        int check3 = collisionStar(a,b);
        if(check3 == 1){
            score += 1;
            b.setLayoutY(b.getLayoutY() - 500);
        }
    }

    private void moveObsDown(){
        if(isSpaceBarPressed){
            if((view.getLayoutY()) < gameHeight/2){
                for(Obstacles i:allObs){
                    i.getArc1().setCenterY(i.getArc1().getCenterY() + objectDown);
                    i.getArc2().setCenterY(i.getArc2().getCenterY() + objectDown);
                    i.getArc3().setCenterY(i.getArc3().getCenterY() + objectDown);
                    i.getArc4().setCenterY(i.getArc4().getCenterY() + objectDown);
                }
                for(Star i:allStar){
                    i.setLayoutY(i.getLayoutY() + objectDown);
                }
                for(Switcher i:allSwitcher){
                    i.setLayoutY(i.getLayoutY() + objectDown);
                }
                tempBallY = view.getLayoutY();
            }
        }
    }
    private void transferObs(){
        for(Obstacles i:allObs){
            if(i.getArc1().getCenterY() >= 670){
                i.transportArc();
            }
        }
    }
}