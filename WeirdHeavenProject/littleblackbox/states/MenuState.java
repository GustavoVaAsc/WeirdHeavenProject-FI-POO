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

public class MenuState extends State {

    private ArrayList<Button> buttons;
    private Background bg;
    private Background title;
    private Sound bgMusic;

    public MenuState(){
        buttons = new ArrayList<Button>();
        bg = new Background(new Vector2D(0,0),Assets.menubackground);
        title = new Background(new Vector2D(27,75),Assets.gametitle);
        buttons.add(new Button(Assets.playout, Assets.playin, (Window.WIDTH/2) - Assets.playin.getWidth()/2
        , (Window.HEIGHT/2) -  Assets.playin.getHeight(), 
        new Action(){
            @Override
            public void doSomething(){
                bgMusic.stop();
                State.changeState(new CharacterState());
            }
        }
        ));

        buttons.add(new Button(Assets.exitout, Assets.exitin, (Window.WIDTH/2) - Assets.exitin.getWidth()/2
        , (Window.HEIGHT/2) +  Assets.exitin.getHeight()*2, 
        new Action(){
            @Override
            public void doSomething(){
                System.exit(0);
            }
        }
        ));

        buttons.add(new Button(Assets.scoreout, Assets.scorein, (Window.WIDTH/2) - Assets.scorein.getWidth()/2
        , (Window.HEIGHT/2) +  Assets.scorein.getHeight()/2, 
        new Action(){
            @Override
            public void doSomething(){
                bgMusic.stop();
                State.changeState(new ScoreState());
            }
        }
        ));

        bgMusic = new Sound(Assets.bgMusicMenu);
        bgMusic.loop();
        bgMusic.changeVolume(-10);

    }

    @Override
    public void update(){
        for(Button b : buttons){
            b.update();
        }
    }

    @Override
    public void draw(Graphics g){
        bg.draw(g);
        for(Button b: buttons){
            b.draw(g);
        }
        title.draw(g);
    }
}
