package defenders2FDE.Manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static defenders2FDE.Constants.filePath;
import static defenders2FDE.Constants.folderPath;

public class DataManager {


    private int[] highScores = new int[]{};
    public DataManager(){
        try {
            highScores = retrieveHighScores();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // private methods
    private int[] retrieveHighScores() throws IOException {
        BufferedReader abc = new BufferedReader(new FileReader(filePath));
        List<Integer> scores = new ArrayList<Integer>();
        String line;
        while ((line = abc.readLine()) != null) {
            int index = line.indexOf(": ");
            int score = Integer.parseInt(line.substring(index + 2));
            scores.add(score);
        }
        abc.close();

        // convert to a int[]
        int size = scores.size();
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = scores.get(i);
        }
        return data;
    }

    // public methods
    public int[] getHighScores(){
        return highScores;
    }

    public boolean isHighScore(int score){
        boolean isHighScore = false;
        if ( highScores.length < 10) {
            isHighScore = true;
            if (highScores.length == 0){
                highScores = new int[]{score};
            }else{
                int[] newList = new int[highScores.length + 1];
                for ( int i = 0; i < newList.length; i++){
                    if (i == newList.length - 1){
                        newList[i] = score;
                    }else{
                        if ( score > highScores[i]){
                            newList[i]= highScores[i];
                        }else{
                            newList[i] = score;
                        }
                    }
                }
                highScores = new  int[]{};
                highScores = newList;
            }
        }

        else {
            for (int i = 0; i < highScores.length; i++) {
                if (score > highScores[i]) {
                    if (i == 0) {
                        highScores[i] = score;
                        isHighScore = true;
                    } else {
                        int tmp = highScores[i];
                        highScores[i] = score;
                        highScores[i - 1] = tmp;
                        isHighScore = true;
                    }
                }
            }
        }
        return isHighScore;
    }

    public void saveNewHighScoreList() throws IOException {
        File defenderFolder = new File(folderPath);
        defenderFolder.mkdir();
        File highScoresFile = new File(filePath);
        highScoresFile.createNewFile();
        FileOutputStream highScoreData = new FileOutputStream(filePath);
        PrintWriter pw = new PrintWriter(highScoreData);
        for (int highScore : highScores) {
            pw.println("Javid: " + highScore);
        }
        pw.close();
    }


}
