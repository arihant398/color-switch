package obstacles;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.Objects;

public class Obstacles {
    private int number;
    private int speed;
    private float waiting_time;
    private int coordinateX;
    private int coordinateY;
    private int arcRotationTime = 5;
    private int obsNo;

    public Obstacles(int dec, int obsNo){
        this.obsNo = obsNo;
        createArcObstacle(dec, obsNo);
    }
    public int getObsNo(){
        return obsNo;
    }

    public arcCreation getArc1() {
        return arc1;
    }
    public arcCreation getArc2() {
        return arc2;
    }
    public arcCreation getArc3() {
        return arc3;
    }
    public arcCreation getArc4() {
        return arc4;
    }

    private arcCreation arc1;
    private arcCreation arc2;
    private arcCreation arc3;
    private arcCreation arc4;

    public void createArcObstacle(int dec,int type){
        arc1 = new arcCreation(0,1,dec, type);
        arc2 = new arcCreation(90,2,dec, type);
        arc3 = new arcCreation(180,3,dec, type);
        arc4 = new arcCreation(270,4,dec, type);
        initializeArc();
    }
    public void transportArc(){
        arc1.setCenterY(-330);
        arc2.setCenterY(-330);
        arc3.setCenterY(-330);
        arc4.setCenterY(-330);
    }
    public double getCenterYArc(){
        return arc1.getCenterY();
    }
    private void initializeArc() {
        Timeline animation = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(arc1.startAngleProperty(), arc1.getStartAngle(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(5), new KeyValue(arc1.startAngleProperty(), arc1.getStartAngle() - 360, Interpolator.LINEAR))

        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        Timeline animation2 = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(arc2.startAngleProperty(), arc2.getStartAngle(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(arcRotationTime), new KeyValue(arc2.startAngleProperty(), arc2.getStartAngle() - 360, Interpolator.LINEAR))

        );
        animation2.setCycleCount(Animation.INDEFINITE);
        animation2.play();

        Timeline animation3 = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(arc3.startAngleProperty(), arc3.getStartAngle(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(arcRotationTime), new KeyValue(arc3.startAngleProperty(), arc3.getStartAngle() - 360, Interpolator.LINEAR))

        );
        animation3.setCycleCount(Animation.INDEFINITE);
        animation3.play();

        Timeline animation4 = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(arc4.startAngleProperty(), arc4.getStartAngle(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(arcRotationTime), new KeyValue(arc4.startAngleProperty(), arc4.getStartAngle() - 360, Interpolator.LINEAR))

        );
        animation4.setCycleCount(Animation.INDEFINITE);
        animation4.play();
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public float getWaiting_time() {
        return waiting_time;
    }

    public void setWaiting_time(float waiting_time) {
        this.waiting_time = waiting_time;
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
