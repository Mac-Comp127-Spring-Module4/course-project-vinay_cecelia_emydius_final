package spacegame;

import java.util.List;

import edu.macalester.graphics.Image;

public class Sprite{

    private boolean visible,dead;
    private Image image;
    private double x,y,dX;
    private List<Laser> laserHolder;

    public Sprite(){
        visible=true;
    }

    public void hasDied(){
        visible=false;
    }

    public boolean isAlive(){
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead(){
        return dead;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    public Image getImage() {
        return image;
    }

    public void shootLaser() {
        laserHolder.add(new Laser(x, y, 10));
    }
}