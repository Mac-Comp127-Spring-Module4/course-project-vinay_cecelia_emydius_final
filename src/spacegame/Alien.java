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
    private int numAliens;
    
   
    public Alien(double x, double y) {
        super(x, y);
        setImage(new Image(x, y, "sprites/armedalien.png"));
        setDirectionFaced("down");
        //animationHandler();

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
            numAliens++;
        }
        canvas.add(alienGroup);
    }

    public int getNumAliens() {
        return numAliens;
    }


    /**
     * Makes the alien shoot a laser every 3 seconds.
     */
    @Override
    public void animationHandler() {
        start = Instant.now();
        this.getImage().getCanvas().animate(() -> {
            end = Instant.now();
            if (Duration.between(start, end).toSecondsPart() >= 3) {    
                shootLaser(); // This code works perfectly and everything in the if is executed every 3 seconds.
                // However, I can't call shootLaser() or else it throws a java.lang.reflect.InvocationTargetException
                // and a java.util.ConcurrentModificationException? Idk what is going on and so I just decided to go sleep.
                System.out.println("Alien shoots a laser!");
                start = Instant.now();
            }
        });
    }
    private void collisionChecker(Laser laser, CanvasWindow canvas){
        GraphicsObject possibleobj = canvas.getElementAt(alienGroup.getPosition());
            if(alienGroup.getPosition()!=null){
                if(laser.getBounds().equals(alienGroup.getBounds())){
                canvas.remove(possibleobj);
                alienGroup.remove(possibleobj);
                }
        } 
    }

    public void removeAlien(Laser laser, CanvasWindow canvas){
        for (int i = 0; i < aliens.size(); i++) {
            collisionChecker(laser, canvas);
        } 
    }

}
