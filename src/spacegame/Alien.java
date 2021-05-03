package spacegame;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;

public class Alien extends Sprite {
    
    private Instant start;
    private Instant end;
    private GraphicsGroup alienGroup;
    private List<Alien> aliens= new ArrayList<>();

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
    public void createAlienArmy(CanvasWindow canvas){
        alienGroup= new GraphicsGroup();
        double margin = canvas.getWidth() * 0.05;
        double spacing = canvas.getWidth() * 0.01;
        double y = canvas.getWidth() * 0.15;
        double x = margin;
        double length = 0;
        for (int i = 0; i < 39; i++) {
            Alien alien= new Alien(x, y);
            length = length + alien.getImage().getImageWidth()+ spacing;
            if (length < canvas.getWidth() - 4 * margin) {
                x = x + alien.getImage().getImageWidth() + spacing;
            } 
            else {
                x = margin;
                length = 0;
                y = y + spacing + alien.getImage().getImageHeight();
            }
            alienGroup.add(alien.getImage());
            aliens.add(alien);
        }
        canvas.add(alienGroup);
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
                //shootLaser(); // This code works perfectly and everything in the if is executed every 3 seconds.
                // However, I can't call shootLaser() or else it throws a java.lang.reflect.InvocationTargetException
                // and a java.util.ConcurrentModificationException? Idk what is going on and so I just decided to go sleep.
                System.out.println("Alien shoots a laser!");
                start = Instant.now();
            }
        });
    }

    // public void collisionChecker(double newx, double newY, BrickWall bWall){
    //     GraphicsObject possibleobj = canvas.getElementAt(newx + 2 * BALL_RADIUS, newY);
    //         if(canvas.getElementAt(newx + 2 * BALL_RADIUS, newY) != null){
    //         velocityY *= -1;
    //         if(possibleobj instanceof Brick){
    //             canvas.remove(possibleobj);
    //             bWall.decrement();
    //         }
    //         }
    //         else if (canvas.getElementAt(newx, newY + 2 * BALL_RADIUS) != null){
    //         velocityX *= -1;
    //         }  
    //         if((newx <= 0 || newx >= maxX)){
    //             velocityX = -velocityX;
    //             }
    //         if((newY < 0)){
    //             velocityY = -velocityY;
    //         }
    // }

}
