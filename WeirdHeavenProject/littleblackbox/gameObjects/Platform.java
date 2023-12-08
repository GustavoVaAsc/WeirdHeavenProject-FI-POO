package littleblackbox.gameObjects;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;

import littleblackbox.main.Window;
import littleblackbox.math.Vector2D;
import littleblackbox.states.GameState;

public abstract class Platform extends MovingObject {
    private boolean isMoving = false;
    private GameState gameState;

    double maxScrollDownLimit = 16.0;

    public Platform(Vector2D pos, Vector2D vel, BufferedImage texture, GameState gameState) {
        super(pos, vel, texture);
        this.velocity = vel;
        isMoving = !velocity.isZero();
        theta = 0;
        this.gameState = gameState;
        this.width /= 4;
        this.height /= 2;
    }

    @Override
    public void update() {
        pos.setX(pos.getX() + velocity.getX());
        pos.setY(pos.getY() + velocity.getY());

        if (pos.getX() < 0 || pos.getX() > Window.WIDTH - texture.getWidth()) {
            velocity.setX(-velocity.getX());
        }

        if (pos.getY() < 0 || pos.getY() > Window.HEIGHT - texture.getHeight()) {
            gameState.removePlatform(this);
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, (int) pos.getX(), (int) pos.getY(), null);
    }

    public boolean collidesWithPlayer(Player player) {
        Rectangle playerBoundingBox = player.getBoundingBox();
        Rectangle platformTop = new Rectangle(
                (int) pos.getX(),
                (int) pos.getY(),
                texture.getWidth(),
                texture.getHeight() / 8);

        return playerBoundingBox.intersects(platformTop);
    }

}
