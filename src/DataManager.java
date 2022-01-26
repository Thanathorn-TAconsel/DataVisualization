import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class DataManager {
    HashMap<String,Integer> rowData = new HashMap<String, Integer>();
    HashMap<String,Integer> columnData = new HashMap<String, Integer>();
    ArrayList<String[]> dataList = new ArrayList<>();
    HashMap<String ,Integer> dimensionList = new HashMap<>();
    DataManager() throws Exception {
        System.out.println("Intallizing");
        loadData("Superstore.csv");
        System.out.println("Loaded");
    }
    public void loadData(String filename) throws Exception {
        FileInputStream fin = new FileInputStream(filename);
        Scanner scan = new Scanner(fin);
        String readline = scan.nextLine();
        String[] dataArray = readline.split(",");
        int i = 0;
        for (String data: dataArray) {
            dimensionList.put(data,i);
            i++;
        }
        while (scan.hasNextLine()) {
            readline = scan.nextLine();
            dataArray = readline.split(",");
            dataList.add(dataArray);
        }
        scan.close();
        fin.close();

    }
    public static void main(String[] args) throws Exception{
        new DataManager();
    }
}
