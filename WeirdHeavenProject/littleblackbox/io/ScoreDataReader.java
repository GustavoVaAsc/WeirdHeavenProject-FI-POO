package littleblackbox.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScoreDataReader {

    public static ScoreData[] readFromFile(String filePath) {
        List<ScoreData> scoreDataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 3) {
                    String date = parts[0];
                    long score = Long.parseLong(parts[1]);
                    String name = parts[2];
                    ScoreData scoreData = new ScoreData(score, name, date);
                    scoreDataList.add(scoreData);
                } else {
                    System.err.println("Invalid line: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return scoreDataList.toArray(new ScoreData[0]);
    }
}