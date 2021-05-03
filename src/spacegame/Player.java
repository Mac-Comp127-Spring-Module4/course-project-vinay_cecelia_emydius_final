package spacegame;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class Player extends Sprite {
    private int lives;
    public Player(double x, double y) {
        super(x, y);
        setImage(new Image(x, y, "sprites/spaceship.png"));
        setDirectionFaced("up");
        System.out.println(getImage().getPosition());
        lives=3;
    }
    
    public int getLives() {
        return lives;
    }
    
    public void updatePosition(double input, CanvasWindow canvas) {
        this.getImage().setPosition(input - (this.getImage().getImageWidth()/2), this.getY());

        if (this.getX() < 5) {
            this.getImage().setPosition(2, this.getY());
        }
        if (this.getX() + this.getImage().getImageWidth() > canvas.getWidth()) {
            this.getImage().setPosition(canvas.getWidth() - (this.getImage().getImageWidth() + 5), this.getY());
        }  
    }

    /**
     * Runs canvas's onKeyDown method only after added to canvas.
     */
    @Override
    public void animationHandler() {
        getImage().getCanvas().onKeyUp(event -> shootLaser());
    }
    
}
