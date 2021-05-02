package spacegame;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class Player extends Sprite {

    public Player(double x, double y) {
        super(x, y);
        setImage(new Image(x, y, "sprites/spaceship.png"));
        setDirectionFaced("up");
        System.out.println(getImage().getPosition());
    }

    /**
     * Not implemented yet
     */
    @Override
    public void updatePosition() {
        this.getX();
        this.setY(700);
        return;
    }


    /**
    * Adds paddles to campus
    */
    // public void addToCanvas(CanvasWindow canvas) {
    //     this.addToCanvas(canvas);
    // }


    /**
     * Runs canvas's onKeyDown method only after added to canvas.
     */
    @Override
    public void animationHandler() {
        getImage().getCanvas().onKeyUp(event -> shootLaser());
    }
    
}
