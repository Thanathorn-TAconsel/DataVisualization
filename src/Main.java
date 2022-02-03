import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    JFrame f1 = new JFrame("DataVisualization");
    UTabVertical datalist = new UTabVertical();

    UTab column = new UTab();
    UTab row = new UTab();
    DataManager dataManager;
    Container container = f1.getContentPane();
    NTable table;
    Main() throws Exception {
        f1.setResizable(false);
        f1.setBounds(100,0,1500,1000);
        f1.setLayout(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(null);

        datalist.setBounds(0,0,200,1000);
        f1.add(datalist);

        row.sender = datalist;
        row.setBounds(200,0,1300,45);
        f1.add(row);

        column.sender = datalist;
        column.setBounds(200,45,1300,45);
        //f1.add(column);


        f1.setVisible(true);

        row.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 0;
                rowData = new String[row.elements.size()];
                for (UTabElement element:row.elements) {
                    rowData[i] = (String)element.bind;
                    i++;
                }
                dataUpdate();
            }
        });
        column.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 0;
                columnData = new String[column.elements.size()];
                for (UTabElement element:column.elements) {

                    columnData[i] = (String)element.bind;
                    i++;
                }
                dataUpdate();
            }
        });

        dataManager = new DataManager("Superstore.csv");
        updateDimensionList();
        System.out.println(dataManager.getDimension());
        //dimensionList.setModel(header2);
        //measuresList.setModel(header2);
        table = new NTable(null,null,200,90,container.getWidth() - 200,container.getHeight() - 90);
        //table.table.setTableHeader();
        f1.add(table);
    }
    String[] rowData;
    String[] columnData;

    private void dataUpdate() {
        System.out.println(Arrays.toString(rowData));

        HashMap map = dataManager.scanByList(rowData);
        ArrayList<String[]> array = dataManager.display(rowData,map);
        String[][] data = new String[array.size()][rowData.length];
        int i =0;
        for (String[] obj: array) {
            data[i] = obj;
            i++;
        }

        DefaultTableModel list = new DefaultTableModel(data,rowData);
        table.table.setModel(list);
    }
    private void updateDimensionList() {
        datalist.elements.clear();
        for (Object dimension:dataManager.getDimension()) {
            if (!dataManager.isMeasure((String)dimension)) {
                UTabElement element = new UTabElement("* " + (String)dimension,dimension);
                datalist.addElements(element);
            }

        }
        for (Object dimension:dataManager.getDimension()) {
            if (dataManager.isMeasure((String)dimension)) {
                UTabElement element = new UTabElement("# " + (String)dimension,dimension);
                datalist.addElements(element);
            }

        }

    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}
