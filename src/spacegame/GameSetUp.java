package spacegame;
import java.awt.Color;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import edu.macalester.graphics.*;
import edu.macalester.graphics.events.KeyboardEvent;
import edu.macalester.graphics.events.KeyboardEventHandler;

public class GameSetUp {
    Scanner sc = new Scanner(System.in);
    private static final int CANVAS_WIDTH = 1100;
    private static final int CANVAS_HEIGHT = 700;
    private static final int CONSTANT_Y = 600;

    private CanvasWindow canvas;
    private Alien alien;
    private Player player;
   
    /**
     * 
     */
    public GameSetUp(){
        canvas = new CanvasWindow("Space Shooter!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK);
        player = new Player(550-30, 600);
        player.addToCanvas(canvas);


        // Alien alien = new Alien(300, 100);
        // alien.addToCanvas(canvas);
        Alien.createAlienArmy(canvas);
    //    alien.removeAlien(laser, canvas);
        alienShootingHandler();
        canvas.onMouseMove(event -> player.updatePosition(event.getPosition().getX(), canvas));
    }

    /**
     * 
     */
    public void gameWinGameLoss(){
        if(player.getLives() == 0){
            endGame();
        }
        if(alien.getNumAliens() == 0){
            gameWinner();
        }
        // reset method here or in player class, for after the alien hits the player
            
    }

    /**
     * 
     */
    public void endGame(){
        GraphicsText loss = new GraphicsText("Game Over", CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
        loss.setFontSize(20);
        loss.setFillColor(Color.GREEN);
        canvas.add(loss);
        canvas.draw();
        canvas.pause(2000);
        GraphicsText restart= new GraphicsText("Restart? (Y/N)", CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
        restart.setFontSize(20);
        restart.setFillColor(Color.red);
        canvas.add(restart);
        canvas.draw();
        String reset = sc.next();
        if(reset=="Y"){
            resetGame();
        }
        else{
            canvas.closeWindow();
        }
        
    }

    /**
     * 
     */
    public void resetGame(){
        new GameSetUp();
    }

    /**
    * Print method for game winner
    */
    public void gameWinner(){
        GraphicsText win = new GraphicsText("You Win!!!!", CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
        win.setFontSize(20);
        win.setFillColor(Color.YELLOW);
        canvas.add(win);
        canvas.draw();
        canvas.pause(2000);
        canvas.closeWindow();
    }
    
    /**
     * 
     */
    public void alienShootingHandler() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> {
            double distance = 1100;
            int closestColumnIndex = 0;
            for (int i = 0; i < Alien.getAlienArmyList().size(); i++) {
                if (Alien.getAlienArmyList().size() > 0) {
                    double currentDistance = Math.abs(
                        Alien.getAlienArmyList().get(i).get(0).getCenterX()
                         - player.getCenterX());
                    if (currentDistance < distance) {
                        distance = currentDistance;
                        closestColumnIndex = i;
                    }
                }
            }
            List<Alien> closestColumn = Alien.getAlienArmyList().get(closestColumnIndex);
            closestColumn.get(closestColumn.size()-1).shootLaser();
        };
        executor.scheduleAtFixedRate(task, 3, 3, TimeUnit.SECONDS);
    }
   

    
    public static void main(String[] args){
        new GameSetUp();
    }

}
