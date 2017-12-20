package game.manage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ScoreManager {
    private static ScoreManager scoreManager = new ScoreManager();
    public static ScoreManager getInstance(){ return scoreManager; }

    private int score = 0;
    private List<Integer> scores;

    private ScoreManager(){
        score = 0;
        scores = new ArrayList<>();
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

    private void downloadFile() {
        String server = "www.myserver.com";
        int port = 21;
        String user = "user";
        String pass = "pass";
    }

    public void writeToFile() {
        try (FileWriter fw = new FileWriter("scores.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(score);
        } catch (Exception e) {
            System.out.println("Cannot write to file.");
        }
    }

    private void parseScores() {
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
}
