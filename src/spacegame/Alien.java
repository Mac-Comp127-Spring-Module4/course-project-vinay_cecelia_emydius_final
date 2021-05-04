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
 * The class draws the aliens used in the game, extended from our Sprite class. It includes methods to 
 * create multiple aliens, the number of aliens there are, a collision checker for when a laser collides
 * with an alien, and a remove method
 */
public class Alien extends Sprite {
    
    private static GraphicsGroup alienGroup = new GraphicsGroup();
    private static List<List<Alien>> aliens = new ArrayList<>();
    private static int numAliens;
    
   /**
    * Draws an alien based on the x and y parameters
    * @param x      The width of the alien
    * @param y      The height of the alien
    */
    public Alien(double x, double y) {
        super(x, y);
        setImage(new Image(x, y, "sprites/armedalien.png"));
        setDirectionFaced("down");
    }

    /**
     * Creates an alien army on the canvas that spaces them apart
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

    /**
     * Method that
     */
    public static List<List<Alien>> getAlienArmyList() {
        return aliens;
    }

    /**
     * Method that holds the total number of aliens and returns that value when called
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
     * Method to check if the laser has collided with any of the aliens that then removes that alien
     * if the lasers positions equals the aliens position
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
     * Removes an alien when collisionChecker is called (and it's true)
     * @param laser
     * @param canvas
     */
    public void removeAlien(Laser laser, CanvasWindow canvas){
        for (int i = 0; i < aliens.size(); i++) {
            collisionChecker(laser, canvas);
        } 
    }

}
