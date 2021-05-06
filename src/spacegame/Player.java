package spacegame;

import java.time.Duration;
import java.time.Instant;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

/**
 * Player class creates the player (or spaceship at the bottom of the screen), contains a method to 
 * ensure the player doesn't off the canvas, as well as a method to have the player shoot a laser when  
 * a key is pressed. 
 */
public class Player extends Sprite {
    private int lives;
    private Instant start;
    private Instant stop;

    public Player(double x, double y) {
        super(x, y);
        setImage(x, y, "sprites/spaceship.png");
        setDirectionFaced("up");
        setGameType("player");
        System.out.println(getImage().getPosition());
        lives=3;
    }
    
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * returns the number of lives 
     * @return
     */
    public int getLives() {
        return lives;
    }
    
    /**
     * Checks for canvas boundaries to make sure player does not go out of the canvas
     * frame and also, ensures that the player movement follows the mouse.
     * @param input
     * @param canvas
     */
    public void updatePosition(double input, CanvasWindow canvas) {
        // if (this.getImage().getCanvas() == null) {
        //     lives--;
        //     canvas.pause(3000);
        //     canvas.add(this.getImage());
        // }
        this.getImage().setPosition(input - (this.getImage().getImageWidth()/2), this.getY());
        setX(getImage().getX());
        setY(getImage().getY());
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
        start = Instant.now();
        getImage().getCanvas().onKeyUp(event -> {
            stop = Instant.now();
            if (Duration.between(start, stop).toMillis() > 2000) {
                shootLaser();
                start = Instant.now();
            }
        });
    }

     /**
     * Checks to see if an alien laser has hit the player, and if so, the player loses a life
     */
    
}
