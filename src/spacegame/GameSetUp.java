package spacegame;
import java.awt.Color;

import edu.macalester.graphics.*;
import edu.macalester.graphics.events.KeyboardEvent;
import edu.macalester.graphics.events.KeyboardEventHandler;

public class GameSetUp {
    private static final int CANVAS_WIDTH = 1100;
    private static final int CANVAS_HEIGHT = 700;
    private static final int CONSTANT_Y = 600;

    private CanvasWindow canvas;
    private Player player;
    private Laser testLaser;
    private Alien alien;
    private int lives;
   
    


    public GameSetUp(){
        canvas = new CanvasWindow("Space Shooter!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK);
        lives = 3; 

        Player player = new Player(550-30, 600);
        player.addToCanvas(canvas);


        Alien alien = new Alien(300, 100);
        //alien.addToCanvas(canvas);
        alien.createAlienArmy(canvas);

        canvas.onMouseMove(event -> player.updatePosition(event.getPosition().getX(), canvas));

    }


    public void gameWinGameLoss(){
        if(lives == 0){
            endGame();
        }
        if(alien.numAliens() == 0){
            gameWinner();
        }
        // reset method here or in player class, for after the alien hits the player
            
    }

    public void endGame(){
        GraphicsText loss = new GraphicsText("Game Over", CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
        loss.setFontSize(20);
        loss.setFillColor(Color.GREEN);
        canvas.add(loss);
        canvas.draw();
        canvas.pause(2000);
        canvas.closeWindow();
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
