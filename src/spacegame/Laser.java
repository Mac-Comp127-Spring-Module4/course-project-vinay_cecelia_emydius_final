package spacegame;

import edu.macalester.graphics.*;
import java.awt.*;

public class Laser extends Line{
    private double dYVelocity;
    private static final Color LINE_COLOR= new Color(200,150,100);

    /**
     * Constructor that makes a super reference to the Line class
     * @param startingPointx
     * @param startingPointY
     * @param endingPointX
     * @param endingPointY
     * @param dYVelocity
     */
    public Laser(
        double startingPointx, 
        double startingPointY, 
        double dYVelocity){
        super(startingPointx, startingPointY, startingPointx, startingPointY + 10);
        this.dYVelocity= dYVelocity;
        this.setStrokeColor(LINE_COLOR);
    }

    /**
     * Method to move the line while checking for the canvas window's walls to make sure that the line does
     * not go out of bounds.
     * @param canvas
     */
    public void moveLaser(CanvasWindow canvas){
        if(this.getY() >= canvas.getHeight() - this.getHeight()|| this.getY() < 0){
            // canvas.remove(this);
        }
        this.setPosition(this.getX(), this.getY()+dYVelocity);
       // this.moveBy(this.getX(), this.getY()+dYVelocity);
    }
}
