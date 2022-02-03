import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    JFrame f1 = new JFrame("DataVisualization");
    UTabVertical datalist = new UTabVertical();
    DataManager dataManager;
    Container container = f1.getContentPane();
    Main() throws Exception {
        f1.setResizable(false);
        f1.setBounds(100,100,800,800);
        f1.setLayout(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(null);
        datalist.setBounds(0,0,200,800);
        f1.add(datalist);
        f1.setVisible(true);

        dataManager = new DataManager("Superstore.csv");
        updateDimensionList();
        System.out.println(dataManager.getDimension());
        String s[] = {"Category","Sub-Category","Quantity"};
        HashMap map = dataManager.scanByList(s);
        dataManager.display(s,map);
    }
    private void updateDimensionList() {
        datalist.elements.clear();
        for (Object dimension:dataManager.getDimension()) {
            UTabElement element = new UTabElement((String)dimension,dimension);
            datalist.addElements(element);
        }

    }
    public static void main(String[] args) throws Exception {
        new Main();
    }
}
