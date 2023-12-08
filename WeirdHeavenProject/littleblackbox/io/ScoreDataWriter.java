package littleblackbox.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ScoreDataWriter {

    public static void writeToFile(ScoreData[] scoreDataArray, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (ScoreData scoreData : scoreDataArray) {
                writer.write(scoreData.getDate() + "\t" + scoreData.getScore() + "\t" + scoreData.getName());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

}
