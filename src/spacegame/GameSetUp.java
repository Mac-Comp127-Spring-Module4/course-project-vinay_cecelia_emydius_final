package spacegame;
import java.awt.Color;

import edu.macalester.graphics.*;
import edu.macalester.graphics.events.KeyboardEvent;
import edu.macalester.graphics.events.KeyboardEventHandler;

public class GameSetUp {
    private static final int CANVAS_WIDTH = 1100;
    private static final int CANVAS_HEIGHT = 900;
    private static final int CONSTANT_Y = 600;
    private int lives;

    private CanvasWindow canvas;
    private Player player;
    private Laser testLaser;
    private Alien alien;
   
    


    public GameSetUp(){
        canvas = new CanvasWindow("Space Shooter!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK);
        lives = 3;

        Player player = new Player(550-30, 600);
        player.addToCanvas(canvas);


        Alien alien = new Alien(300, 500);
        alien.addToCanvas(canvas);
        //alien.createAlienArmy(canvas);

        canvas.onMouseMove(event -> player.updatePosition(event.getPosition().getX(), canvas));

    }
    

   // if alien = 0, game winner
   // if lives = 0 and alien != 0, game loss

   // game winner method
   // game loss method


   // in alien class: num aliens method and a decrement method which subtracts the num aliens and
   // removes hit aliens from canvas

   // collision method for aliens
   // collision method for player (if an alien hits the player.... are we still doing that?)

    public static void main(String[] args){
        new GameSetUp();
    }

}
