package game.manage;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class ScoreManager {
    private static ScoreManager scoreManager = new ScoreManager();
    public static ScoreManager getInstance(){ return scoreManager; }

    private int score = 0;
    private List<Integer> scores;
    private Path baseDir;

    private ScoreManager(){
        score = 0;
        scores = new ArrayList<>();
        baseDir = Paths.get(System.getProperty("user.home")).resolve("Projects/Flappy_bird/");
    }

    public void incrementScore() {
        score += 1;
    }
    public int getScore() {
        return score;
    }
    public List<Integer> getScores() {
        parseScores();
        return scores;
    }
    public void resetScore() { score = 0; }

    public void writeToFile() {
        try (FileWriter fw = new FileWriter("scores.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(score);
            FTPManager.getInstance().uploadFile("scores.txt");
        } catch (Exception e) {
            System.out.println("Cannot write to file.");
        }
    }

    private void parseScores() {
        String fileName = getFileNameFromUrl("http://atavarecek.8u.cz/scores.txt");
        FTPManager.getInstance().downloadFile("http://atavarecek.8u.cz/scores.txt", baseDir.resolve(fileName));
        scores.clear();
        try {
            File scoresFile = new File("scores.txt");
            Scanner input = new Scanner(scoresFile);

            while(input.hasNext()) {
                scores.add(Integer.parseInt(input.nextLine()));
            }
            scores.sort(Collections.reverseOrder());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getFileNameFromUrl(String urlName) {
        return urlName.substring(urlName.lastIndexOf('/') + 1);
    }

}
