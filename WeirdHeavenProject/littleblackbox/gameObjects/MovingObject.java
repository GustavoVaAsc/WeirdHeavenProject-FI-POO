package littleblackbox.gameObjects;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import littleblackbox.math.Vector2D;

public abstract class MovingObject extends GameObject {
    
    protected Vector2D velocity;
    protected double theta; 
    protected int width;
    protected int height;
    

    public MovingObject(Vector2D pos, Vector2D vel,BufferedImage texture) {
        super(pos, texture);
        this.velocity = vel;
        theta = 0;
        width = texture.getWidth();
        height = texture.getHeight();
    }

    public Rectangle getBoundingBox() {
        return new Rectangle((int) pos.getX(), (int) pos.getY(), texture.getWidth(), texture.getHeight());
    }
    
    public int getHeight() {return height;}
    public int getWidth() {return width;}
    public Vector2D getVelocity() {return velocity;}

    public void scroll(double scrollSpeed){
        velocity.setY(2*(-scrollSpeed));
    }
}
