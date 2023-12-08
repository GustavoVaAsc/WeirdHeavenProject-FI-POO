package littleblackbox.gameObjects;

import littleblackbox.math.Vector2D;
import littleblackbox.states.GameState;
import java.awt.image.BufferedImage;

public class NormalPlatform extends Platform {
    public NormalPlatform(Vector2D pos, Vector2D vel, BufferedImage texture, GameState gameState) {
        super(pos, vel,texture, gameState);
    }
}
