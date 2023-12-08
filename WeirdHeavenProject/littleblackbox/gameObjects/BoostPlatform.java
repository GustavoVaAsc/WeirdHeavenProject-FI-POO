package littleblackbox.gameObjects;

import java.awt.image.BufferedImage;
import littleblackbox.math.Vector2D;
import littleblackbox.states.GameState;

public class BoostPlatform extends Platform {
    public BoostPlatform(Vector2D pos, Vector2D vel, BufferedImage texture, GameState gameState) {
        super(pos, vel,texture, gameState);
    }
}
