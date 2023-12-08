package littleblackbox.states;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import littleblackbox.graphics.Assets;
import littleblackbox.main.Window;

public class LoadingState extends State {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 50;

    private Thread loadingThread;

    public LoadingState(Thread loadingThread) {
        this.loadingThread = loadingThread;
        this.loadingThread.start();
    }

    @Override
    public void update() {
        if (Assets.isLoaded) {
            State.changeState(new MenuState());
            try {
                loadingThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        GradientPaint gp = new GradientPaint(
                Window.WIDTH / 2 - WIDTH / 2, 
                Window.HEIGHT / 2 - HEIGHT / 2, 
                Color.WHITE, 
                Window.WIDTH / 2 + WIDTH / 2, 
                Window.HEIGHT / 2 + HEIGHT / 2, 
                Color.BLUE);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gp);

        float percentage = (Assets.charge / Assets.maxCharge);

        g2d.fillRect(Window.WIDTH / 2 - WIDTH / 2, 
                Window.HEIGHT / 2 - HEIGHT / 2, 
                (int) (WIDTH * percentage), HEIGHT);

        
        g.setColor(Color.BLACK);
        g.drawString("Loading...", Window.WIDTH / 2 - 40, Window.HEIGHT / 2 + 20);
    }
}