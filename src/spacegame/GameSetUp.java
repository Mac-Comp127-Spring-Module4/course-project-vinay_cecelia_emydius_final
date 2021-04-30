package spacegame;

import edu.macalester.graphics.*;
import java.awt.Color;

public class GameSetUp {
    private static final int CANVAS_WIDTH = 1280;
    private static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private ShooterFriend ballObject1;


    public GameSetUp(){
        canvas = new CanvasWindow("Space Shooter!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK);
    //     ballObject1 = new ShooterFriend(300, 400, 7, 600, 800, canvas);
    //    ballObject1.addToCanvas(canvas);
    //    Laser testLaser = new Laser(300, 300, 300, 350, 3);
    //    canvas.add(testLaser);
    //     canvas.animate(()->testLaser.moveLaser(canvas));
        Alien alien = new Alien(300, 400);
        alien.addToCanvas(canvas);
    
    }


    
    public static void main(String[] args){
        new GameSetUp();
    }

}
