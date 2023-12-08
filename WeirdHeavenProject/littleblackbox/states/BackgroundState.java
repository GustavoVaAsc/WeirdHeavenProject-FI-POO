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

public class BackgroundState extends State {
    private ArrayList<Button> buttons;
    private Background scenSel;
    private Background bg;
    private Sound bgMusic;

    public BackgroundState(int play) {
        buttons = new ArrayList<>();
        bg = new Background(new Vector2D(0, 0), Assets.menubackground);
        scenSel = new Background(new Vector2D(125, 15), Assets.scenarioselection);
        int buttonSpacing = 20;

        int startY = ((Window.HEIGHT - (Assets.player1in.getHeight() * 2 + buttonSpacing)) / 2);
        int startX = 50 + (Window.WIDTH - (Assets.player1in.getWidth() * 2 + buttonSpacing)) / 2 ;

        buttons.add(new Button(Assets.background1out, Assets.background1in, startX - 10, startY,
                new Action() {
                    @Override
                    public void doSomething() {
                        bgMusic.stop();
                        int scen = 1;
                        State.changeState(new GameState(play, scen));
                    }
                }));

        buttons.add(new Button(Assets.background2out, Assets.background2in,
                startX + Assets.background1in.getWidth() + buttonSpacing, startY,
                new Action() {
                    @Override
                    public void doSomething() {
                        bgMusic.stop();
                        int scen = 2;
                        State.changeState(new GameState(play, scen));
                    }
                }));

        buttons.add(new Button(Assets.background3out, Assets.background3in, startX - 10,
                startY + Assets.background1in.getHeight() + buttonSpacing,
                new Action() {
                    @Override
                    public void doSomething() {
                        bgMusic.stop();
                        int scen = 3;
                        State.changeState(new GameState(play, scen));
                    }
                }));

        buttons.add(new Button(Assets.background4out, Assets.background4in,
                startX + Assets.background1in.getWidth() + buttonSpacing,
                startY + Assets.background1in.getHeight() + buttonSpacing,
                new Action() {
                    @Override
                    public void doSomething() {
                        bgMusic.stop();
                        int scen = 4;
                        State.changeState(new GameState(play, scen));
                    }
                }));

        
        buttons.add(new Button(Assets.background5out, Assets.background5in,
                startX + Assets.background1in.getWidth() - 70,
                startY + (Assets.background1in.getHeight() * 2) + (buttonSpacing * 2),
                new Action() {
                    @Override
                    public void doSomething() {
                        bgMusic.stop();
                        int scen = 5;
                        State.changeState(new GameState(play, scen));
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
        scenSel.draw(g);
        for (Button b : buttons) {
            b.draw(g);
        }
    }
}
