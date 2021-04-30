package spacegame;

import edu.macalester.graphics.Image;
public class Sprite{

    private boolean visible,dead;
    private Image image;
    int x,y,dX;

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

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
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
}