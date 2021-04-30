package spacegame;
import edu.macalester.graphics.*;

public class GameSetUp {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private Shooter ballObject1;


    public GameSetUp(){
        canvas = new CanvasWindow("Space Shooter!", CANVAS_WIDTH, CANVAS_HEIGHT);
        ballObject1 = new Shooter(300, 570, 7, 600, 800, canvas);
        ballObject1.addToCanvas(canvas);

    }


    
    public static void main(String[] args){
        new GameSetUp();
    }

}
