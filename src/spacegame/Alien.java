package spacegame;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;

/**
 * 
 */
public class Alien extends Sprite {
    
    private static GraphicsGroup alienGroup = new GraphicsGroup();
    private static List<Alien> aliens = new ArrayList<>();
    private static int numAliens;
    
   /**
    * 
    * @param x
    * @param y
    */
    public Alien(double x, double y) {
        super(x, y);
        setImage(new Image(x, y, "sprites/armedalien.png"));
        setDirectionFaced("down");

    }

    /**
     * 
     * @param canvas
     */
    public static void createAlienArmy(CanvasWindow canvas){
        double margin = canvas.getWidth() * 0.05;
        double spacing = canvas.getWidth() * 0.01;
        double y = canvas.getWidth() * 0.15;
        double x = margin;
        double length = 0;
        for (int i = 0; i < 39; i++) {
            Alien alien= new Alien(x, y);
            length = length + alien.getImage().getImageWidth() + spacing;
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
        for (int i = 0; i < aliens.size(); i++)
            aliens.get(i).animationHandler();
    }

    /**
     * 
     * @return
     */
    public int getNumAliens() {
        return numAliens;
    }

    /**
     * Makes the alien shoot a laser every 3 seconds.
     */
    @Override
    public void animationHandler() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> {
            shootLaser();
        };
        executor.scheduleAtFixedRate(task, 3, 3, TimeUnit.SECONDS);

    }

    /**
     * @param laser
     * @param canvas
     */
    private void collisionChecker(Laser laser, CanvasWindow canvas){
        GraphicsObject possibleobj = canvas.getElementAt(alienGroup.getPosition());
            if(alienGroup.getPosition()!=null){
                if(laser.getBounds().equals(alienGroup.getBounds())){
                canvas.remove(possibleobj);
                alienGroup.remove(possibleobj);
                }
        } 
    }

    /**
     * 
     * @param laser
     * @param canvas
     */
    public void removeAlien(Laser laser, CanvasWindow canvas){
        for (int i = 0; i < aliens.size(); i++) {
            collisionChecker(laser, canvas);
        } 
    }

}
