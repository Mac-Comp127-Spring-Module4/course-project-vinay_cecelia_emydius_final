package spacegame;
import java.awt.Color;
import java.util.Scanner;

import edu.macalester.graphics.*;
import edu.macalester.graphics.events.KeyboardEvent;
import edu.macalester.graphics.events.KeyboardEventHandler;

public class GameSetUp {
    Scanner sc= new Scanner(System.in);
    private static final int CANVAS_WIDTH = 1100;
    private static final int CANVAS_HEIGHT = 700;
    private static final int CONSTANT_Y = 600;
    private Laser laser;

    private CanvasWindow canvas;
    private Alien alien;
    private Player player;
   
    /**
     * 
     */
    public GameSetUp(){
        canvas = new CanvasWindow("Space Shooter!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK);
        Player player = new Player(550-30, 600);
        player.addToCanvas(canvas);


        Alien alien = new Alien(300, 100);
        alien.addToCanvas(canvas);
        // Alien.createAlienArmy(canvas);
       // alien.removeAlien(laser, canvas);
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
    

   

    
    public static void main(String[] args){
        new GameSetUp();
    }

}
