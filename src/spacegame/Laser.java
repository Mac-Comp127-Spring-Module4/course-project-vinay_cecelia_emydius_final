package spacegame;

import edu.macalester.graphics.*;
import edu.macalester.graphics.Point;

import java.awt.*;

/**
 * 
 */
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
        super(startingPointx, startingPointY, startingPointx, startingPointY - 10);
        this.dYVelocity= dYVelocity;
        this.setStrokeColor(LINE_COLOR);
        this.setStrokeWidth(5);
    }

    /**
     * 
     */
    public void updatePosition() {
        getCanvas().animate(() -> {
        // while (getCanvas() != null) {
            if (getCanvas() != null) {
                this.moveBy(0, dYVelocity);
                getCanvas().draw();
                if (collisionChecker()) {
                }
                else if (getY() <= 0 || getY() >= getCanvas().getHeight()) {
                    getCanvas().remove(this);
                    System.out.println("Removed!");
                }
            }
        // }
        });
    }

    public boolean collisionChecker() {
        GraphicsObject potentialObject = getCanvas().getElementAt(getPosition().subtract(Point.UNIT_Y));
        if (potentialObject != null) {
            getCanvas().remove(potentialObject);
            getCanvas().remove(this);
            return true;
        }
        else return false;
    }

    
}
