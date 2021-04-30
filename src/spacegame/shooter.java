package spacegame;
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;

public class Shooter {

    public static final double BALL_RADIUS = 6;

    private double centerX;
    private double centerY;
    private double maxX;
    private double maxY;
    private double velocityX;
    private double velocityY;
    private CanvasWindow canvas;
    private double startingX;
    private double startingY;
    private double startingSpeed;
    private Ellipse ballShape;


    //the constructor
public Shooter (
        double centerX,
        double centerY,
        double initialSpeed,
        double maxX,
        double maxY,
        CanvasWindow canvas) {
            this.canvas = canvas;
            this.centerX = centerX;
            this.centerY = centerY;
            this.startingSpeed = initialSpeed;
            this.maxX = maxX;
            this.maxY = maxY;
            startingX = this.centerX;
            startingY = this.centerY;
            double randomAngle = Math.random() * 360;
            double initialAngleInRadians = Math.toRadians(randomAngle);
            velocityX = initialSpeed * Math.cos(initialAngleInRadians);
            velocityY = initialSpeed * -Math.sin(initialAngleInRadians);
            ballShape = new Ellipse(0, 0, BALL_RADIUS * 2, BALL_RADIUS * 2);
            ballShape.setFilled(true);
            ballShape.setFillColor(Color.ORANGE);
            ballShape.setCenter(centerX, centerY);
    }

    public double getCenterX() {
        return centerX;
    }
    public double getCenterY() {
        return centerY;
    }
    

    public boolean updatePosition(double dt) {
        double newx = centerX;
        double newY = centerY;
        for(int i = 10; i > 0; i--){
            newx += velocityX * (0.10 * dt);
            newY += velocityY * (0.10 * dt); 
            //collisionChecker(newx, newY);   
        }
            centerX = newx;
            centerY = newY;
            ballShape.setCenter(centerX, centerY);
            return true; 
    }


    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(ballShape);
    }

    public void RemoveToCanvas(CanvasWindow canvas) {
        canvas.remove(ballShape);
    }


// maybe a reset method after is checks for a collision? resets it to the spot where the shooter is. 
// I feel like it wouldn't be very fluid though
    
// public void resetAndReshoot(){

// }
} 
   // so how do we create it so it continues to measure the angle and shoot instead of random angles?
   // multiple balls at random places?