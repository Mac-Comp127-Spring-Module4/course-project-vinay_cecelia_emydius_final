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
    private static List<List<Alien>> aliens = new ArrayList<>();
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
        double margin = canvas.getWidth() * 0.03;
        double spacing = canvas.getWidth() * 0.01;
        double x = margin;
        double length = 0;

        for (int i = 0; i < 13; i++) {
            double y = canvas.getWidth() * 0.15;
            aliens.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                Alien alien = new Alien(x, y);
                length = length + alien.getImage().getImageWidth() + spacing;
                y = y + spacing + alien.getImage().getImageHeight();
                alien.addToCanvas(canvas);
                aliens.get(i).add(alien);
                numAliens++;
            }
            x = x + aliens.get(0).get(0).getImage().getWidth() + canvas.getWidth() * 0.015;
        }
    }

    public static List<List<Alien>> getAlienArmyList() {
        return aliens;
    }

    /**
     * 
     * @return
     */
    public int getNumAliens() {
        return numAliens;
    }

    /**
     * Not implemented yet.
     * Will make alien move depending on position.
     */
    @Override
    public void animationHandler() {
        
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
