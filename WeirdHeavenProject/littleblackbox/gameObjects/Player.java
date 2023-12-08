package littleblackbox.gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import littleblackbox.main.Window;
import java.awt.image.BufferedImage;
import littleblackbox.math.Vector2D;
import littleblackbox.graphics.Assets;
import littleblackbox.graphics.Sound;
import littleblackbox.input.KeyBoard;
import littleblackbox.states.GameState;
import java.awt.Rectangle;
import java.awt.Color;

public class Player extends MovingObject {

    private Vector2D accel;
    private final double gravity = 0.09;
    private boolean canJump = true;
    private double jumpStrength = -4.0;
    private boolean isBoosted = false;
    private boolean isSlowed = false;
    private GameState gameState;

    private boolean jumpCooldown = false;
    private int jumpCooldownDuration = 800;
    private int currentJumpCooldown = 0;

    private Sound jumpEffect;
    private Sound jumpBoostEffect;

    public Player(Vector2D pos, Vector2D vel, BufferedImage texture, GameState gameState) {
        super(pos, vel, texture);
        accel = new Vector2D(0, gravity);
        this.gameState = gameState;
        jumpEffect = new Sound(Assets.jumpeffect);
        jumpBoostEffect = new Sound(Assets.jumpboosteffect);
    }

    @Override
    public void update() {

        if (KeyBoard.right) {
            velocity.setX(5);
        } else if (KeyBoard.left) {
            velocity.setX(-5);
        } else {
            velocity.setX(0);
        }

        velocity = velocity.vectorSum(accel);

        for (MovingObject obj : gameState.getMovingObjects()) {
            if (obj instanceof Platform) {
                Platform platform = (Platform) obj;
                if (platform.collidesWithPlayer(this)) {
                    handlePlatformCollision(platform);
                    if (velocity.getY() >= 0 && canJump) {
                        if (platform instanceof BoostPlatform) {
                            jumpBoostEffect.play();
                            velocity.setY(2 * jumpStrength);
                            velocity = velocity.vectorSum(accel);
                            canJump = false;
                            jumpCooldown = true;
                        } else if (platform instanceof NormalPlatform) {
                            jumpEffect.play();
                            velocity.setY(jumpStrength);
                            velocity = velocity.vectorSum(accel);
                            canJump = false;
                            jumpCooldown = true;
                        }
                    }
                }
            }
        }

        if (pos.getY() + texture.getHeight() >= Window.HEIGHT) {
            if (velocity.getY() >= 0) {
                canJump = true;

                pos.setY(Window.HEIGHT - texture.getHeight());

                velocity.setY(jumpStrength);
                canJump = false;
            }
        }

        if (pos.getX() > Window.WIDTH) {
            pos.setX(0);
        }

        if (pos.getX() < 0) {
            pos.setX(Window.WIDTH);
        }

        if (jumpCooldown) {
            currentJumpCooldown++;
            if (currentJumpCooldown >= jumpCooldownDuration) {
                jumpCooldown = false;
                currentJumpCooldown = 0;
                canJump = true;
            }
        }

        pos = pos.vectorSum(velocity);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, (int) pos.getX(), (int) pos.getY(), null);
        Graphics2D g2d = (Graphics2D) g.create();

    }

    private void handlePlatformCollision(Platform platform) {
        double playerBottom = pos.getY() + getHeight();
        double platformTop = platform.getPos().getY();

        if (velocity.getY() >= 0 && playerBottom > platformTop) {
            canJump = true;
        }
    }

    public double getJumpStrength() {
        return jumpStrength;
    }

}
