import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    DataManager dataManager;
    Main() throws Exception {
        System.out.println("Loading File");
        dataManager = new DataManager("Superstore.csv");
        String s[] = {"Category","Sub-Category","Quantity"};
        HashMap map = dataManager.scanByList(s);
        dataManager.display(s,map);
        System.out.println(dataManager.getDimension());
    }
    public static void main(String[] args) throws Exception {
        new Main();
    }
}
