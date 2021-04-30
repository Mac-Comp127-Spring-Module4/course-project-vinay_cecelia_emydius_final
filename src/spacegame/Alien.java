package spacegame;

import edu.macalester.graphics.Image;

public class Alien extends Sprite {
    
    public Alien(double x, double y) {
        super();
        setX(x);
        setY(y);
        setImage(new Image(x, y, "sprites/alien.png"));
    }

}
