package littleblackbox.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import javax.swing.JOptionPane;

import littleblackbox.graphics.Assets;
import littleblackbox.graphics.Sound;
import littleblackbox.io.ScoreData;
import littleblackbox.io.ScoreDataReader;
import littleblackbox.io.ScoreDataWriter;
import littleblackbox.main.Window;
import littleblackbox.math.Vector2D;
import littleblackbox.ui.Action;
import littleblackbox.ui.Button;

public class ScoreState extends State {

    private Button returnButton;
    private PriorityQueue<ScoreData> highScores;
    private Sound bgMusic;
    private Comparator<ScoreData> scoreComparator;

    private ScoreData[] auxArray;

    private final String scoreFilePath = "resources/scores/highscores.txt";

    public ScoreState() {
        bgMusic = new Sound(Assets.bgMusicScore);
        returnButton = new Button(Assets.returnout, Assets.returnin, 10,
                Window.HEIGHT - Assets.returnin.getHeight() - 40,
                new Action() {
                    @Override
                    public void doSomething() {
                        bgMusic.stop();
                        saveScores();
                        State.changeState(new MenuState());
                    }
                });

        scoreComparator = new Comparator<ScoreData>() {
            @Override
            public int compare(ScoreData o1, ScoreData o2) {
                return Long.compare(o2.getScore(), o1.getScore());
            }
        };

        highScores = new PriorityQueue<ScoreData>(10, scoreComparator);
        loadScores();
        bgMusic.loop();
        bgMusic.changeVolume(-10);
    }

    private void loadScores() {
        ScoreData[] savedScores = ScoreDataReader.readFromFile(scoreFilePath);
        highScores.addAll(Arrays.asList(savedScores));
    }

    private void saveScores() {
        ScoreData[] currentScores = highScores.toArray(new ScoreData[highScores.size()]);
        ScoreDataWriter.writeToFile(currentScores, scoreFilePath);
    }

    @Override
    public void update() {
        returnButton.update();
    }

    @Override
    public void draw(Graphics g) {
        returnButton.draw(g);

        auxArray = highScores.toArray(new ScoreData[highScores.size()]);
        Arrays.sort(auxArray, scoreComparator);

        Font titleFont = new Font("Arial", Font.BOLD, 30);
        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        String title = "TOP 10 INSANOS";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        int titleX = (Window.WIDTH - titleWidth) / 2;
        g.drawString(title, titleX - 20, 40);

        Vector2D scorePos = new Vector2D(Window.WIDTH / 2 - 150, 120);
        Vector2D datePos = new Vector2D(Window.WIDTH / 2 + 100, 120);

        Font labelFont = new Font("Arial", Font.BOLD, 20);
        g.setFont(labelFont);
        g.setColor(Color.WHITE);
        String nameLabel = "NAME";
        String scoreLabel = "SCORE";
        String dateLabel = "DATE";
        int labelX = (Window.WIDTH - g.getFontMetrics().stringWidth(scoreLabel)) / 2;
        g.drawString(nameLabel, labelX - 150, 90);
        g.drawString(scoreLabel, labelX - 25, 90);
        g.drawString(dateLabel, labelX + 100, 90);

        Font dataFont = new Font("Arial", Font.PLAIN, 16);
        g.setFont(dataFont);
        g.setColor(Color.WHITE);

        int displayCount = Math.min(auxArray.length, 10);
        for (int i = 0; i < displayCount; i++) {
            ScoreData data = auxArray[i];

            g.drawString(data.getName(), (int) scorePos.getX() - 20, (int) scorePos.getY());
            g.drawString(Long.toString(data.getScore()), (int) scorePos.getX() + 115, (int) scorePos.getY());
            g.drawString(data.getDate(), (int) datePos.getX() - 50, (int) datePos.getY());

            scorePos = scorePos.vectorSum(new Vector2D(0, 40));
            datePos = datePos.vectorSum(new Vector2D(0, 40));
        }
    }

}
