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
    private static CanvasWindow alienCanvas;
    private static GameSetUp game;
    private static int numAliens;
    private static boolean stunned = false;
    private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private static char moveDirection = 'r';

    
   /**
    * Draws an alien based on the x and y parameters
    * @param x      The width of the alien
    * @param y      The height of the alien
    */
    public Alien(double x, double y) {
        super(x, y);
        setImage(x, y, "sprites/armedalien.png");
        setDirectionFaced("down");
        setGameType("alien");
    }

    /**
     * Creates an alien army on the canvas that spaces them apart
     */
    public static void createAlienArmy(CanvasWindow canvas, GameSetUp gameSetUp){
        game = gameSetUp;
        alienCanvas = canvas;
        double margin = canvas.getWidth() * 0.03;
        double spacing = canvas.getWidth() * 0.01;
        double x = margin;
        double length = 0;

        for (int i = 0; i < 13; i++) {
            double y = canvas.getWidth() * 0.15;
            aliens.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                Alien alien = new Alien(x, y);
                if (j == 0)
                    alien.setImage(x, y, "sprites/redalien.png");
                length = length + alien.getImage().getImageWidth() + spacing;
                y = y + spacing + alien.getImage().getImageHeight();
                alien.addToCanvas(canvas);
                aliens.get(i).add(alien);
                numAliens++;
            }
            x = x + aliens.get(0).get(0).getImage().getWidth() + canvas.getWidth() * 0.015;
        }
        staticAnimationHandler();
    }

    /**
     * Method that changes the appearance of the aliens if they are hit with a laser. 
     */
    public static void stunAliens() {
        stunned = !stunned;
        for (List<Alien> list : aliens) {
            for (Alien alien : list) {
                if (stunned == true) {
                    if (alien.getImagePath() == "sprites/redalien.png") {
                        alien.setImagePath("sprites/stunnedredalien.png");
                    }
                    else {
                        alien.setImagePath("sprites/stunnedalien.png");
                    }
                }
                else {
                    if (alien.getImagePath() == "sprites/stunnedredalien.png") {
                        alien.setImagePath("sprites/redalien.png");
                    }
                    else {
                        alien.setImagePath("sprites/armedalien.png");
                    }
                }
            }
        }
    }

    public static void removeAlienArmy(CanvasWindow canvas) {
        for (int i = 0; i < aliens.size(); i++) {
            for (int j = 0; j < aliens.get(i).size(); j++) {
                canvas.remove(aliens.get(i).get(j).getImage());
                aliens.get(i).remove(j);
                j--;
            }
            aliens.remove(i);
            i--;
        }
    }

    public static void updateAlienList() {
        int newAlienCount = 0;
        for (int i = 0; i < aliens.size(); i++) {
            if (aliens.get(i).size() == 0) {
                aliens.remove(i);
                i--;
                game.alienShootingHandler();
                continue;
            }
            for (int j = 0; j < aliens.get(i).size(); j++) {
                if (aliens.get(i).get(j).getCanvas() == null) {
                    if (aliens.get(i).get(j).getImagePath() == "sprites/redalien.png")
                        stunAliens();
                    if (aliens.get(i).get(j).getImagePath() == "sprites/stunnedalien.png")
                        stunAliens();
                    aliens.get(i).remove(j);    
                    j--;
                }
                else newAlienCount++;
            }
        }
        numAliens = newAlienCount;
        System.out.println("numAliens = " + numAliens);
    }

    /**
     * Method that returs the aliens
     */
    public static List<List<Alien>> getAlienArmyList() {
        return aliens;
    }

    /**
     * Method that holds the total number of aliens and returns that value when called
     */
    public static int getNumAliens() {
        return numAliens;
    }

    /**
     * Not implemented yet.
     * Will make alien move depending on position.
     */
    @Override
    public void animationHandler(){
    }

     /**
     * Not implemented yet.
     * Will make alien move depending on position.
     */
    public static void staticAnimationHandler() {
        Runnable task = () -> {
            if (moveDirection == 'r') {
                for (int i = 0; i < aliens.size(); i++) {
                    for (int j = 0; j < aliens.get(i).size(); j++) {
                        aliens.get(i).get(j).getImage().moveBy(10, 2);
                    }
                }
                System.out.println("runs 1");
                Alien referenceAlien = aliens.get(aliens.size()-1).get(0);
                System.out.println("runs 2");
                if (referenceAlien.getImage().getX() + referenceAlien.getImage().getWidth() > 1100) {
                    moveDirection = 'l';
                }
                System.out.println("runs 3");
            }
            if (moveDirection == 'l') {
                for (int i = 0; i < aliens.size(); i++) {
                    for (int j = 0; j < aliens.get(i).size(); j++) {
                        aliens.get(i).get(j).getImage().moveBy(-10, 2);
                    }
                }
                Alien referenceAlien = aliens.get(0).get(0);
                if (referenceAlien.getImage().getX() < 0) {
                    moveDirection = 'r';
                }
            }
            
        };
        executor.scheduleAtFixedRate(task, 3500, 3000, TimeUnit.MILLISECONDS);
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
