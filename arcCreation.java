package obstacles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

public class arcCreation extends Arc {
    private int color; //1-purple,2-cyan,3-yellow,4-pink
    private int startAngle;
    private int centerX = 200;
    private int centerY = 350;
    private int radiusX = 70;
    private int radiusY = 70;
    private int length = 90;
    private int type;


    public arcCreation(int startAngle,int color, int dec,int type){
        this.type = type;
        this.startAngle = startAngle;
        this.color = color;
        if(type == 1){
            obsType1(dec);
        }
        else if(type == 2){
            obsType2(dec);
        }
        else if(type == 3){
            obsType3(dec);
        }
    }

    private void obsType1(int dec){
        setRadiusX(70);
        setRadiusY(70);
        setCenterX(centerX);
        setCenterY(centerY - dec);
        setLength(90);
        setStartAngle(startAngle);
        setFill(null);
        setType(ArcType.OPEN);
        setStroke(setColor(color));
        setStrokeWidth(10);
    }
    private void obsType2(int dec){
        setRadiusX(70);
        setRadiusY(70);
        setCenterX(centerX);
        setCenterY(centerY - dec);
        setLength(90);
        setStartAngle(startAngle);
        setFill(setColor(color));
        setType(ArcType.OPEN);
        setStroke(setColor(color));
        setStrokeWidth(10);
    }
    private void obsType3(int dec){
        setRadiusX(70);
        setRadiusY(70);
        setCenterX(centerX);
        setCenterY(centerY - dec);
        setLength(90);
        setStartAngle(startAngle);
        setFill(null);
        setType(ArcType.CHORD);
        setStroke(setColor(color));
        setStrokeWidth(10);
        setStrokeLineJoin(StrokeLineJoin.ROUND);
        setSmooth(true);
    }
    public int getObsType(){
        return type;
    }

    private Color setColor(int co){
        if(co == 1){
            return Color.MEDIUMPURPLE;
        }
        else if(co == 2){
            return Color.CYAN;
        }
        else if(co == 3){
            return Color.YELLOW;
        }
        else{
            return Color.MAGENTA;
        }
    }
    public int getColor(){
        return color;
    }
}
