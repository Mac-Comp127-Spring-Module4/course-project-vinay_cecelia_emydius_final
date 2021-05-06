package spacegame;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

/**
 * This class creates the various images we used for our space class game,
 * while also including various methods that set position, the alien is alive or dead, 
 * shoots the laser, and sets the direction that image or Sprite sound face
 */
public abstract class Sprite{

    private boolean visible,dead;
    private Image image;
    private String path;
    private double x,y,dX;
    private CanvasWindow canvas;
    private int directionFaced;
    private String gameType;

    /**
     * Creates the Sprite
     * @param x
     * @param y
     */
    public Sprite(double x, double y){
        this.x = x;
        this.y = y;
        canvas = null;
        visible = true;
        directionFaced = 0;
        Image image = null;
    }

    /**
     * Method that dictates if the Sprite has "died" and if it is visible, then it hasn't
     */    
    public void hasDied(){
        visible = false;
    }

    /**
     * Method that dictates if the Sprite is alive and if so, makes it visible
     */  
    public boolean isAlive(){
        return visible;
    }

    /**
     * Method makes the Sprite visible and can be refered to in other methods
     */ 
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }
    
    public double getCenterX() {
        return x + getImage().getWidth()/2;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void moveBy(double x, double y) {
        image.moveBy(x, y);
        this.x = image.getX();
        this.y = image.getY();
    }

    public double getY() {
        return y;
    }

    public double getCenterY() {
        return y + getImage().getHeight()/2;
    }

    /**
     * Sprite dead method that is a boolean and can be refered to in other methods
     */ 
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     * Method that dictates if the Sprite is newly dead
     */ 
    public boolean isDead(){
        return dead;
    }

    public void setImage(double imgX, double imgY, String imgPath) {
        this.image = new Image(imgX, imgY, imgPath);
        path = imgPath;
    }

    public void setImagePath(String path) {
        this.path = path;
        this.image.setImagePath(path);
    }

    public String getImagePath() {
        return path;
    }
    
    public Image getImage() {
        return image;
    }

    public CanvasWindow getCanvas() {
        return getImage().getCanvas();
    }

    public void setGameType(String type) {
        this.gameType = type;
    }

    public String getGameType() {
        return gameType;
    }

    /**
     * Sets the direction that the Sprite image should face
     * @param dir
     */
    public void setDirectionFaced(String dir) {
        if (dir.equals("up"))
            directionFaced = -1;
        else if (dir.equals("down"))
            directionFaced = 1;
        else throw new UnsupportedOperationException("SetDirectionFaced only takes \"up\" and \"down\"");
    }

    /**
     * Adds the image or Sprite created to canvas then calls the animation handler
     * @param canvas
     */
    public void addToCanvas(CanvasWindow canvas){
        this.canvas = canvas;
        canvas.add(image);
        animationHandler();
    }


    /**
     * This method creates a new laser, adds it to canvas, and updates it position as the 
     * laser is moving
     */
    public void shootLaser() {
        Laser newLaser = new Laser(image.getX()+image.getWidth()/2, image.getY()+image.getHeight()/2+image.getHeight()/2*directionFaced*1.2, 10 * directionFaced); 
        canvas.add(newLaser);
        newLaser.updatePosition();
    }

    /**
     * Method that calls the animation handler
     */ 
    public abstract void animationHandler();
}