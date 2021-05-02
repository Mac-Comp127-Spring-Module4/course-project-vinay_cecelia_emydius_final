package spacegame;

import edu.macalester.graphics.Image;

public class Alien extends Sprite {
    
    public Alien(double x, double y) {
        super(x, y);
        setImage(new Image(x, y, "sprites/armedalien.png"));
        setDirectionFaced("down");
    }

    /**
     * Not implemented yet
     */
    @Override
    public void updatePosition() {
        return;
    }

    /**
     * Not implemented yet
     */
    @Override
    public void animationHandler() {

    }

}
