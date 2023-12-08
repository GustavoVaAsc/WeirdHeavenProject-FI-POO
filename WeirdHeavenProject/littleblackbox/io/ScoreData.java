package littleblackbox.io;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class ScoreData {
    private String date;
    private long score;
    private String name;

    public ScoreData(long score, String name) {
        this.score = score;

        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.date = sdf.format(today);
        this.name = name;
    }

    public ScoreData(long score, String name, String date) {
        this.score = score;

        this.date = date;
        this.name = name;
    }

    private void setName() {
        String input = JOptionPane.showInputDialog("Enter your name (less than 5 letters):");
        
        if (input != null) {
            name = input.trim();
        } else {
            name = "Anonymous";
        }
    }

    public String getDate() {
        return date;
    }

    public long getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
