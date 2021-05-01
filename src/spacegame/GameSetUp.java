package spacegame;
import java.awt.Color;

import edu.macalester.graphics.*;
import edu.macalester.graphics.events.KeyboardEvent;

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
        // ballObject1.addToCanvas(canvas);
        //Laser testLaser = new Laser(300, 300, 3);
        //Laser testLaser = new Laser(300, 300, 300, 350, 3);
        // canvas.add(testLaser);
        //canvas.onMouseMove(event -> ballObject1.updatePosition(event.getPosition()));
        //canvas.onKeyUp(event -> player.updatePosition());
        canvas.animate(()->testLaser.moveLaser(canvas));
        Alien alien = new Alien(640, 400);
        alien.addToCanvas(canvas);
        Player player = new Player(640, 450);
        player.addToCanvas(canvas);

    }

    // public void action(KeyboardEvent e){
    //     if (e.getKeyCode()==KeyEvent.VK_SPACE){

    //     }


    
    public static void main(String[] args){
        new GameSetUp();
    }

}
