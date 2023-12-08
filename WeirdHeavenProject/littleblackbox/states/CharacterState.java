package littleblackbox.states;

import java.awt.Graphics;
import java.util.ArrayList;

import littleblackbox.gameObjects.Background;
import littleblackbox.graphics.Assets;
import littleblackbox.graphics.Sound;
import littleblackbox.main.Window;
import littleblackbox.math.Vector2D;
import littleblackbox.ui.Action;
import littleblackbox.ui.Button;

public class CharacterState extends State {
    private ArrayList<Button> buttons;
    private Background charSel;
    private Background bg;
    private Sound bgMusic;

    public CharacterState() {
        buttons = new ArrayList<>();
        bg = new Background(new Vector2D(0, 0), Assets.menubackground);
        charSel = new Background(new Vector2D(75, 50), Assets.characterselection);
        int buttonSpacing = 20; 

        
        int startY = ((Window.HEIGHT - (Assets.player1in.getHeight() * 2 + buttonSpacing)) / 2) + 75;

        
        int startX = (Window.WIDTH - (Assets.player1in.getWidth() * 2 + buttonSpacing)) / 2;

        buttons.add(new Button(Assets.player1out, Assets.player1in, startX - 10, startY,
                new Action() {
                    @Override
                    public void doSomething() {
                        
                        bgMusic.stop();

                        
                        int delayTime = 1000;

                        
                        Thread delayThread = new Thread(() -> {
                            try {
                                
                                Thread.sleep(delayTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            
                            int play = 1; 
                            State.changeState(new BackgroundState(play));
                        });

                        
                        delayThread.start();
                    }

                }));

        buttons.add(new Button(Assets.player2out, Assets.player2in,
                startX + Assets.player1in.getWidth() + buttonSpacing, startY,
                new Action() {
                    @Override
                    public void doSomething() {
                        
                        bgMusic.stop();

                        
                        int delayTime = 1000;

                        
                        Thread delayThread = new Thread(() -> {
                            try {
                                
                                Thread.sleep(delayTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            
                            int play = 2; 
                            State.changeState(new BackgroundState(play));
                        });

                        
                        delayThread.start();
                    }

                }));

        buttons.add(new Button(Assets.player3out, Assets.player3in, startX - 10,
                startY + Assets.player1in.getHeight() + buttonSpacing,
                new Action() {
                    @Override
                    public void doSomething() {
                        
                        bgMusic.stop();

                        
                        int delayTime = 1000;

                        
                        Thread delayThread = new Thread(() -> {
                            try {
                                
                                Thread.sleep(delayTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            
                            int play = 3; 
                            State.changeState(new BackgroundState(play));
                        });

                        
                        delayThread.start();
                    }

                }));

        buttons.add(
                new Button(Assets.player4out, Assets.player4in, startX + Assets.player1in.getWidth() + buttonSpacing,
                        startY + Assets.player1in.getHeight() + buttonSpacing,
                        new Action() {
                            @Override
                            public void doSomething() {
                            
                                bgMusic.stop();

                            
                                int delayTime = 1000;

                               
                                Thread delayThread = new Thread(() -> {
                                    try {
                                        
                                        Thread.sleep(delayTime);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                   
                                    int play = 4; 
                                    State.changeState(new BackgroundState(play));
                                });

                               
                                delayThread.start();
                            }

                        }));

        bgMusic = new Sound(Assets.bgMusicMenu);
        bgMusic.loop();
        bgMusic.changeVolume(-10);
    }

    @Override
    public void update() {
        for (Button b : buttons) {
            b.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        bg.draw(g);
        for (Button b : buttons) {
            b.draw(g);
        }
        charSel.draw(g);
    }
}
