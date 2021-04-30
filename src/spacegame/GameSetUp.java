package spacegame;
import edu.macalester.graphics.*;

public class GameSetUp {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private ShooterFriend ballObject1;


    public GameSetUp(){
        canvas = new CanvasWindow("Space Shooter!", CANVAS_WIDTH, CANVAS_HEIGHT);
    //     ballObject1 = new ShooterFriend(300, 400, 7, 600, 800, canvas);
    //    ballObject1.addToCanvas(canvas);
       Laser testLaser = new Laser(300, 300, 300, 350, 3);
       canvas.add(testLaser);
        canvas.animate(()->testLaser.moveLaser(canvas));
    
    }


    
    public static void main(String[] args){
        new GameSetUp();
    }

}
