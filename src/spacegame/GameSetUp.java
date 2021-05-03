package spacegame;
import java.awt.Color;

import edu.macalester.graphics.*;
import edu.macalester.graphics.events.KeyboardEvent;
import edu.macalester.graphics.events.KeyboardEventHandler;

public class GameSetUp {
    private static final int CANVAS_WIDTH = 1100;
    private static final int CANVAS_HEIGHT = 700;
    private static final int CONSTANT_Y = 600;
    private Laser laser;

    private CanvasWindow canvas;
    public GameSetUp(){
        canvas = new CanvasWindow("Space Shooter!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK);

        Player player = new Player(550-30, 600);
        player.addToCanvas(canvas);


        Alien alien = new Alien(300, 100);
        alien.createAlienArmy(canvas);
       // alien.removeAlien(laser, canvas);

        canvas.onMouseMove(event -> player.updatePosition(event.getPosition().getX(), canvas));

    }


    // public void inBoundsOutBounds(){
    //     if(ballObject.outOfBounds()){
    //         removeBall();
    //         lives -= + 1;
    //     if(lives == 0){
    //         endGame();
    //     }
    //     if(brickWall.numBricks() == 0){
    //         gameWinner();
    //     }
    //         ballObject.resetAfterBounds();
            
    //     }
    // }

    // public void endGame(){
    //     GraphicsText loss = new GraphicsText("Game Over", CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
    //     loss.setFontSize(20);
    //     loss.setFillColor(Color.GREEN);
    //     canvas.add(loss);
    //     canvas.draw();
    //     canvas.pause(2000);
    //     canvas.closeWindow();
    // }

    // /**
    // * Print method for game winner
    // */
    // public void gameWinner(){
    //     GraphicsText win = new GraphicsText("You Win!!!!", CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
    //     win.setFontSize(20);
    //     win.setFillColor(Color.YELLOW);
    //     canvas.add(win);
    //     canvas.draw();
    //     canvas.pause(2000);
    //     canvas.closeWindow();
    // }
    

   

    
    public static void main(String[] args){
        new GameSetUp();
    }

}
