package littleblackbox.states;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Arrays;

import littleblackbox.gameObjects.*;
import littleblackbox.graphics.Assets;
import littleblackbox.graphics.Sound;
import littleblackbox.math.Vector2D;
import littleblackbox.main.Window;
import littleblackbox.io.*;
import javax.swing.JOptionPane;

public class GameState extends State {

    private Player player;
    private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
    private Background bg;
    private double lastPlatformY = Window.HEIGHT;
    private long score = 0;

    private Sound bgMusic;

    private int screenHeight = Window.HEIGHT;
    private int scrollSpeed = 5; 

    public GameState(int play, int scen){
        spawnPlatform();
        Assets.loadPlayer(play);
        if(scen == 5){
            Assets.loadSong(scen);
            Assets.loadScen(scen);
        }else{
            Assets.loadSong(scen);
            Assets.loadScen(scen);
        }
        bg = new Background(new Vector2D(0,0),Assets.background);
        player = new Player(new Vector2D(200,400), new Vector2D(), Assets.player, this);
        movingObjects.add(player);
        bgMusic = new Sound(Assets.bgMusic);
        bgMusic.loop();
        bgMusic.changeVolume(-10);
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        
        bg.draw(g);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);

        for(int i = 0; i<movingObjects.size(); i++){
            movingObjects.get(i).draw(g);
        }

    }

    public void update() {
        for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).update();
        }

        if(score > 20){
            if (player.getPos().getY() > Window.HEIGHT - player.getHeight()) {

                movingObjects.remove(player);
                gameOver();
            }
        }

        checkScoring();

        if(player.getPos().getY() < screenHeight/2 && player.getVelocity().getY() < 0) {
            for(int i = 0; i < movingObjects.size(); i++){
                if(movingObjects.get(i) instanceof Platform){
                    movingObjects.get(i).scroll(player.getVelocity().getY());
                }  
            }
        }


        if (movingObjects.size() < 6) {
            spawnPlatform();
        }
    }
    

    public ArrayList<MovingObject> getMovingObjects(){
        return movingObjects;
    }


    private void spawnPlatform() {
        double minSpacing = 50; 
        
        Vector2D platformPosition;
        double rnd;
        double setSpeed;

        int maxAttempts = 100;
        int attempts = 0;

        do {
            
            platformPosition = new Vector2D(Math.random() * 400, Math.abs(lastPlatformY) - minSpacing);
            rnd = Math.random();
            setSpeed = rnd < 0.8 ? 0 : Math.random() * 2;
            attempts++;
            
        } while (platformPosition.getY() < 0 && attempts < maxAttempts); 
        rnd = Math.random();
        if(rnd > 0.9){
            BoostPlatform platform = new BoostPlatform(platformPosition, new Vector2D(setSpeed, 0), Assets.boostPlatform, this);
            movingObjects.add(platform);
        }else{
            NormalPlatform platform = new NormalPlatform(platformPosition, new Vector2D(setSpeed, 0), Assets.normalPlatform, this);
            movingObjects.add(platform);
        }
    
        lastPlatformY = platformPosition.getY();
    }


    public void removePlatform(Platform platform) {
        movingObjects.remove(platform);
    }

    private void checkScoring() {
        if (player.getPos().getY() < Window.HEIGHT/2){
            score += 1;
        }
    }

    private void gameOver() {
        bgMusic.stop();
        JOptionPane.showMessageDialog(null, "Mamaste! :D \nTu puntaje: " + score, "Game over :(", JOptionPane.INFORMATION_MESSAGE);

        
        writeScoreToData(score);

        State.changeState(new MenuState());
    }

    private void writeScoreToData(long playerScore) {
        try {
            
            ScoreData[] existingScores = ScoreDataReader.readFromFile("resources/scores/highscores.txt");
    
            String playerName = askForName();
    
            ScoreData playerScoreData = new ScoreData((int) playerScore, playerName);
    
            ScoreData[] updatedScores = Arrays.copyOf(existingScores, existingScores.length + 1);
            updatedScores[existingScores.length] = playerScoreData;
    
            
            ScoreDataWriter.writeToFile(updatedScores, "resources/scores/highscores.txt");
    
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
    
    private String askForName() {
        String input = JOptionPane.showInputDialog("Ingresa tu nombre\n Maximo 5 letras:");
        return (input != null) ? input.trim() : "Anonymous";
    }
}
