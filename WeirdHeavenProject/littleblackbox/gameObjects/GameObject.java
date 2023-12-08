package littleblackbox.gameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import littleblackbox.math.Vector2D;

public abstract class GameObject {
    protected BufferedImage texture;
    protected Vector2D pos;

    public GameObject(Vector2D pos, BufferedImage texture) {
        this.pos = pos;
        this.texture = texture;
    }

    public abstract void update();
    public abstract void draw(Graphics g);

    public Vector2D getPos(){return pos;}
    public void setPos(Vector2D pos){this.pos = pos;}
}
