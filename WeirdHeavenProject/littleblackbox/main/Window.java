package littleblackbox.main;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import javax.swing.JFrame;

import littleblackbox.states.GameState;
import littleblackbox.states.LoadingState;
import littleblackbox.states.MenuState;
import littleblackbox.states.State;
import littleblackbox.graphics.Assets; 
import littleblackbox.input.KeyBoard;
import littleblackbox.input.MouseInput;

public class Window extends JFrame implements Runnable {
    public static final int WIDTH = 450, HEIGHT = 700;
    private Canvas canvas;
    private Thread th;
    private boolean isRunning = false;
    private BufferStrategy bufferStrategy;
    private Graphics g;
    
    private final int FPS = 120;
    private double targetTime =  1000000000/FPS;
    private double deltaT = 0;
    private int avgFPS = FPS;

    private GameState gameState;
    private KeyBoard kb;
    private MouseInput mouse;

    public Window(){
        setTitle("Bizarre Jump");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(rootPaneCheckingEnabled);
        setLocationRelativeTo(null);

        canvas = new Canvas();
        kb = new KeyBoard();
        mouse = new MouseInput();

        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setFocusable(true);
        add(canvas);
        canvas.addKeyListener(kb);
        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);
        setVisible(true);
    }

    @Override
    public void run(){

        long now = 0;
        long lastTime = System.nanoTime();
        int frames = 0;
        long time = 0;

        init();
        while (isRunning){
            now = System.nanoTime();
            deltaT += (now - lastTime)/targetTime;
            lastTime = now;
            if(deltaT >= 1){
                update();
                draw();
                deltaT--;
                frames++;
            }        
        }
        if(time >= 1000000000){
            avgFPS = frames;
            frames = 0;
            time = 0;
        }

        stop();
    }

    public void start(){
        th = new Thread(this);
        th.start();
        isRunning = true;
    }

    private void stop(){
        try {
            th.join();
            isRunning = false;
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private void update(){
        kb.update();
        State.getCurrentState().update();
    }

    private void draw(){
        bufferStrategy = canvas.getBufferStrategy();
        if(bufferStrategy == null){
            canvas.createBufferStrategy(3);
            return;
        }
        g = bufferStrategy.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        State.getCurrentState().draw(g);
        g.drawString("FPS: "+avgFPS, 360, 20);

        g.dispose();
        bufferStrategy.show();
    }

    private void init(){
        Thread loadThread = new Thread(new Runnable(){
            @Override
            public void run() {
                Assets.init();
            }
        });
        State.changeState(new LoadingState(loadThread));
    }
}
