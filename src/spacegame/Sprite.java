package spacegame;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public abstract class Sprite{

    private boolean visible,dead;
    private Image image;
    private double x,y,dX;
    private List<Laser> laserHolder;
    private CanvasWindow canvas;
    private int directionFaced;

    public Sprite(double x, double y){
        canvas = null;
        visible = true;
        directionFaced = 0;
        laserHolder = new ArrayList<>();
    }

    public void hasDied(){
        visible = false;
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

    public void setDirectionFaced(String dir) {
        if (dir.equals("up"))
            directionFaced = 1;
        else if (dir.equals("down"))
            directionFaced = -1;
        else throw new UnsupportedOperationException("SetDirectionFaced only takes \"up\" and \"down\"");
    }

    public void addToCanvas(CanvasWindow canvas){
        this.canvas = canvas;
        canvas.add(image);
    }

    public void shootLaser() {
        Laser newLaser = new Laser(x, y, 10 * directionFaced); 
        laserHolder.add(newLaser);
        canvas.add(newLaser);
    }

    public abstract void updatePosition();
}