package spacegame;
import java.awt.Color;

import edu.macalester.graphics.*;
import edu.macalester.graphics.events.KeyboardEvent;
import edu.macalester.graphics.events.KeyboardEventHandler;

public class GameSetUp {
    private static final int CANVAS_WIDTH = 1100;
    private static final int CANVAS_HEIGHT = 700;

    private CanvasWindow canvas;
    private Player player;
    private Laser testLaser;
    private Alien alien;
   
    


    public GameSetUp(){
        canvas = new CanvasWindow("Space Shooter!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK);

        Player player = new Player(550-30, 600);
        player.addToCanvas(canvas);


        Alien alien = new Alien(20, 20);
        alien.addToCanvas(canvas);

    }

   

    
    public static void main(String[] args){
        new GameSetUp();
    }

}
