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
   
    


    public GameSetUp(){
        canvas = new CanvasWindow("Space Shooter!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK);

        Player player = new Player(550-30, 600);
        player.addToCanvas(canvas);


        Alien alien = new Alien(300, 100);
        //alien.addToCanvas(canvas);
        alien.createAlienArmy(canvas);

        canvas.onMouseMove(event -> player.updatePosition(event.getPosition().getX(), canvas));

    }

    // I shouldn't have to make a new setPosition method because that should be a built in method
    // public void updatePosition(Point point){
    //         player.updatePosition(point.getX(), CONSTANT_Y);
    //         }
    

   

    
    public static void main(String[] args){
        new GameSetUp();
    }

}
