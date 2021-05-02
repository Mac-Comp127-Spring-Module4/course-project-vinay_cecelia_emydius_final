package spacegame;
import java.awt.Color;

import edu.macalester.graphics.*;
import edu.macalester.graphics.events.KeyboardEvent;
import edu.macalester.graphics.events.KeyboardEventHandler;

public class GameSetUp {
    private static final int CANVAS_WIDTH = 1280;
    private static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private ShooterFriend ballObject1;
    private Player player;
    private Laser testLaser;
    private Alien alien;
   
    


    public GameSetUp(){
        canvas = new CanvasWindow("Space Shooter!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK);
        ballObject1 = new ShooterFriend (300, 570, 7, 600, 800, canvas);
        ballObject1.addToCanvas(canvas);
        Player player = new Player(640, 450);
        player.addToCanvas(canvas);
        //Laser testLaser = new Laser(300, 300, 3);
        //Laser testLaser = new Laser(300, 300, 300, 350, 3);
        // canvas.add(testLaser);
        //canvas.onMouseMove(event -> ballObject1.updatePosition(event.getPosition()));
        //canvas.animate(()->testLaser.moveLaser(canvas));
        canvas.onKeyDown(event -> player.shootLaser());
        Alien alien = new Alien(640, 400);
        alien.addToCanvas(canvas);
        

    }

   

    
    public static void main(String[] args){
        new GameSetUp();
    }

}
