package defenders2FDE.Manager;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static defenders2FDE.Constants.*;

public class DataManager {


    private int[] highScores = new int[]{};
    private int coin;
    private int[] items = new int[]{};

    public DataManager(){
        try {
            highScores = retrieveHighScores();
            items = retrieveShopData();
            coin = retrieveCoin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // private methods
    private int[] retrieveHighScores() throws IOException {
        File defenderFolder = new File(folderPath);
        defenderFolder.mkdir();
        File file = new File(highScoreFilePath);

        if ( file.exists()) {
            BufferedReader abc = new BufferedReader(new FileReader(highScoreFilePath));
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
        return new int[]{};
    }

    private int retrieveCoin() throws IOException {
        File defenderFolder = new File(folderPath);
        defenderFolder.mkdir();
        defenderFolder.mkdir();
        File file = new File(coinFilePath);

        if ( file.exists()) {
            BufferedReader abc = new BufferedReader(new FileReader(coinFilePath));
            return Integer.parseInt(abc.readLine());
        }
        return 0;
    }

    private int[] retrieveShopData() throws IOException {
        File defenderFolder = new File(folderPath);
        defenderFolder.mkdir();
        File file = new File(shopFilePath);
        if (file.exists()) {
            BufferedReader abc = new BufferedReader(new FileReader(shopFilePath));
            ArrayList<Integer> items = new ArrayList<>();
            String line;
            while ((line = abc.readLine()) != null) {
                items.add(Integer.valueOf(line));
            }
            this.items = new int[items.size()];
            for ( int i = 0; i < items.size(); i++){
                this.items[i] = items.get(i);
            }
            return this.items;
        }else{
            items = new int[]{0, 1,0,0,0,0,0};
            saveShopData();
            return items;
        }
    }

    // public methods
    public int[] getHighScores(){
        return highScores;
    }

    public boolean isHighScore(int score){
        boolean isHighScore = false;
        if ( highScores.length < 10) {
            int[] newList = new int[highScores.length + 1];
            for ( int i = 0; i < newList.length; i++ ){
                if ( i != newList.length - 1){
                    newList[i] = highScores[i];
                }else{
                    newList[i] = score;
                }
            }
            Arrays.sort(newList);
            highScores = newList;
            isHighScore = true;
        }else{
            if (score > highScores[0]){
                highScores[0] = score;
                isHighScore = true;
                Arrays.sort(highScores);
            }
        }
        return isHighScore;
    }

    public void saveNewHighScoreList() throws IOException {
        File defenderFolder = new File(folderPath);
        defenderFolder.mkdir();
        File highScoresFile = new File(highScoreFilePath);
        highScoresFile.createNewFile();
        FileOutputStream highScoreData = new FileOutputStream(highScoreFilePath);
        PrintWriter pw = new PrintWriter(highScoreData);
        for (int highScore : highScores) {
            pw.println("Javid: " + highScore);
        }
        pw.close();
    }

    public int[] getItems() {
        return items;
    }

    public void saveShopData()throws IOException {
        File defenderFolder = new File(folderPath);
        defenderFolder.mkdir();
        File shopDataFile = new File(shopFilePath);
        shopDataFile.createNewFile();
        FileOutputStream shopData = new FileOutputStream(shopFilePath);
        PrintWriter pw = new PrintWriter(shopData);
        for (int highScore : items) {
            pw.println(highScore);
        }
        pw.close();
    }
    public void saveShopData(int[] items)throws IOException {
        File defenderFolder = new File(folderPath);
        defenderFolder.mkdir();
        File shopDataFile = new File(shopFilePath);
        shopDataFile.createNewFile();
        FileOutputStream shopData = new FileOutputStream(shopFilePath);
        PrintWriter pw = new PrintWriter(shopData);
        for (int highScore : items) {
            pw.println(highScore);
        }
        pw.close();
    }

    public int getCoin() throws IOException{
        return coin;
    }

    public void setCoin(int coin) throws IOException {
        File defenderFolder = new File(folderPath);
        defenderFolder.mkdir();
        File highScoresFile = new File(coinFilePath);
        highScoresFile.createNewFile();
        FileOutputStream highScoreData = new FileOutputStream(coinFilePath);
        PrintWriter pw = new PrintWriter(highScoreData);
        pw.println(this.coin + coin);
        pw.close();
    }
}
