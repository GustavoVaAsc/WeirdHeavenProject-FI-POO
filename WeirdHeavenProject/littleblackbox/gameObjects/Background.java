package littleblackbox.gameObjects;

import littleblackbox.math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Background extends GameObject {
    public Background(Vector2D pos, BufferedImage texture) {
        super(pos,texture);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, (int) getPos().getX(), (int) getPos().getY(), null);
    }
    @Override
    public void update(){

    }
}
