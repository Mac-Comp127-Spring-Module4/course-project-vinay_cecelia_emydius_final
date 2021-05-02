package spacegame;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Line;

public abstract class Sprite{

    private boolean visible,dead;
    private Image image;
    private double x,y,dX;
    // private List<Laser> laserHolder;
    private CanvasWindow canvas;
    private int directionFaced;

    public Sprite(double x, double y){
        this.x = x;
        this.y = y;
        canvas = null;
        visible = true;
        directionFaced = 0;
        // laserHolder = new ArrayList<>();
        Image image = null;
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
            directionFaced = -1;
        else if (dir.equals("down"))
            directionFaced = 1;
        else throw new UnsupportedOperationException("SetDirectionFaced only takes \"up\" and \"down\"");
    }

    public void addToCanvas(CanvasWindow canvas){
        this.canvas = canvas;
        canvas.add(image);
        animationHandler();
    }

    public void shootLaser() {
        System.out.println("This runs");
        System.out.println("x: " + x + ", y: " + y);
        Laser newLaser = new Laser(x + image.getWidth()/2, y, 10 * directionFaced); 
        canvas.add(newLaser);
        newLaser.updatePosition();
    }

    public abstract void updatePosition();
    public abstract void animationHandler();
}