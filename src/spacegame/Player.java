package spacegame;

import edu.macalester.graphics.Image;

public class Player extends Sprite {

    public Player(double x, double y) {
        super(x, y);
        setImage(new Image(x, y, "sprites/player.png"));
        setDirectionFaced("up");
    }

    /**
     * Not implemented yet
     */
    @Override
    public void updatePosition() {
        return;
    }
    
}
