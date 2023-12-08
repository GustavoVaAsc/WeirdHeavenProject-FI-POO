package littleblackbox.states;

import java.awt.Graphics;

public abstract class State {

    private static State currentState = null;

    public abstract void update();
    public abstract void draw(Graphics g);

    public static void changeState(State newState){
        currentState = newState;
    }

    public static State getCurrentState() {
        return currentState;
    }
}
