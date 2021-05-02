package spacegame;

import java.time.Duration;
import java.time.Instant;

import edu.macalester.graphics.Image;

public class Alien extends Sprite {
    
    private Instant start;
    private Instant end;

    public Alien(double x, double y) {
        super(x, y);
        setImage(new Image(x, y, "sprites/armedalien.png"));
        setDirectionFaced("down");
    }

    /**
     * Not implemented yet
     */
    @Override
    public void updatePosition() {
        return;
    }

    /**
     * Makes the alien shoot a laser every 3 seconds.
     */
    @Override
    public void animationHandler() {
        start = Instant.now();
        getImage().getCanvas().animate(() -> {
            end = Instant.now();
            if (Duration.between(start, end).toSecondsPart() >= 3) {
                // shootLaser(); // This code works perfectly and everything in the if is executed every 3 seconds.
                // However, I can't call shootLaser() or else it throws a java.lang.reflect.InvocationTargetException
                // and a java.util.ConcurrentModificationException? Idk what is going on and so I just decided to go sleep.
                System.out.println("Alien shoots a laser!");
                start = Instant.now();
            }
        });
    }

}
