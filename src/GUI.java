import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    JFrame mainFrame = new JFrame();

    NLabel DataLabel = new NLabel("data",0,0,200,50);
    NLabel ColumnLabel = new NLabel("Column",DataLabel.endX(),0,100,DataLabel.getHeight()/2);
    NLabel RowLabel = new NLabel("Row", DataLabel.endX(),ColumnLabel.endY(),100,DataLabel.getHeight()/2);

    NList columnList = new NList(ColumnLabel.endX(),ColumnLabel.getY(),480,DataLabel.getHeight()/2);
    DefaultListModel columnModel = new DefaultListModel<>();

    NList rowList = new NList(RowLabel.endX(),RowLabel.getY(),480,DataLabel.getHeight()/2);
    DefaultListModel rowModel = new DefaultListModel<>();

    NList dimensionList = new NList(0,DataLabel.endY(),200,300);
    DefaultListModel dimensionModel = new DefaultListModel<>();


    NList measuresList = new NList(0,dimensionList.endY(),200,300);
    DefaultListModel measuresModel = new DefaultListModel<>();

    JComboBox graphSelection = new JComboBox();
    DefaultComboBoxModel graphSelectionModel = new DefaultComboBoxModel<>();

    //JPanel tablePanel = new JPanel();
    NTable tablePanel;
    NSList test;

    GUI(){
        DataLabel.setOpaque(true);DataLabel.setBackground(new Color(170,170,170));DataLabel.setHorizontalAlignment(SwingConstants.CENTER);DataLabel.setForeground(Color.WHITE);
        ColumnLabel.setOpaque(true);ColumnLabel.setBackground(new Color(85,85,85));ColumnLabel.setHorizontalAlignment(SwingConstants.CENTER);ColumnLabel.setForeground(Color.WHITE);
        RowLabel.setOpaque(true);RowLabel.setBackground(new Color(50,50,50));RowLabel.setHorizontalAlignment(SwingConstants.CENTER);RowLabel.setForeground(Color.WHITE);

        dimensionList.setOpaque(true);dimensionList.setBackground(new Color(255,242,204));dimensionList.setLayoutOrientation(JList.VERTICAL);dimensionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        measuresList.setOpaque(true);measuresList.setBackground(new Color(106,168,79));measuresList.setLayoutOrientation(JList.VERTICAL);measuresList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        columnList.setOpaque(true);columnList.setBackground(new Color(255,242,204));columnList.setLayoutOrientation(JList.HORIZONTAL_WRAP);columnList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);columnList.setVisibleRowCount(-1);
        rowList.setOpaque(true);rowList.setBackground(new Color(106,168,79));rowList.setLayoutOrientation(JList.HORIZONTAL_WRAP);rowList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);rowList.setVisibleRowCount(-1);

        ArrayList<String> graphType = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            columnModel.addElement(i);
            rowModel.addElement(i);
            dimensionModel.addElement(i);
            measuresModel.addElement(i);

            columnList.setModel(columnModel);
            rowList.setModel(rowModel);
            dimensionList.setModel(dimensionModel);
            measuresList.setModel(measuresModel);
            String temp = "Graph Type " + (i+1);
            graphType.add(temp);
        }

        graphSelection.setBounds(columnList.endX(),columnList.getY(),200,25);
        graphSelectionModel.addAll(graphType);
        graphSelection.setModel(graphSelectionModel);

        //test data
        String data[][] = {{"001","vinod","Bihar","India","Biology","65","First"},
                {"002","Raju","ABC","Kanada","Geography","58","second"},
                {"003","Aman","Delhi","India","computer","98","Dictontion"},
                {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"},
                {"001","vinod","Bihar","India","Biology","65","First"},
                {"002","Raju","ABC","Kanada","Geography","58","second"},
                {"003","Aman","Delhi","India","computer","98","Dictontion"},
                {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"},
                {"001","vinod","Bihar","India","Biology","65","First"},
                {"002","Raju","ABC","Kanada","Geography","58","second"},
                {"003","Aman","Delhi","India","computer","98","Dictontion"},
                {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"},
                {"001","vinod","Bihar","India","Biology","65","First"},
                {"002","Raju","ABC","Kanada","Geography","58","second"},
                {"003","Aman","Delhi","India","computer","98","Dictontion"},
                {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"},
                {"001","vinod","Bihar","India","Biology","65","First"},
                {"002","Raju","ABC","Kanada","Geography","58","second"},
                {"003","Aman","Delhi","India","computer","98","Dictontion"},
                {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"},
                {"001","vinod","Bihar","India","Biology","65","First"},
                {"002","Raju","ABC","Kanada","Geography","58","second"},
                {"003","Aman","Delhi","India","computer","98","Dictontion"},
                {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"},
                {"001","vinod","Bihar","India","Biology","65","First"},
                {"002","Raju","ABC","Kanada","Geography","58","second"},
                {"003","Aman","Delhi","India","computer","98","Dictontion"},
                {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"},
                {"001","vinod","Bihar","India","Biology","65","First"},
                {"002","Raju","ABC","Kanada","Geography","58","second"},
                {"003","Aman","Delhi","India","computer","98","Dictontion"},
                {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"}};
        String header[] = {"Roll","Name","State","country","Math","Marks","Grade"};
        String header2[] = {"Roll","Name","State","country","Math","Marks","Grade","Roll","Name","State","country","Math","Marks","Grade","Roll","Name","State","country","Math","Marks","Grade"};

        tablePanel = new NTable(data,header,rowList.endX(),rowList.getY(),200,400);
        //test = new NSList("test",header, tablePanel.getX(), tablePanel.endY(), 200, 100);



        XYSeries series = new XYSeries("XY Chart");
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Testing Chart", "Date", "Average Profit", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(DataLabel.endX(),DataLabel.endY(),580,600);

        //mainFrame.add(test);
        mainFrame.add(tablePanel);
        mainFrame.add(chartPanel);
        mainFrame.add(graphSelection);
        mainFrame.getContentPane().setBackground(new Color(230,145,56));
        mainFrame.add(ColumnLabel);mainFrame.add(RowLabel);
        mainFrame.add(dimensionList);mainFrame.add(measuresList);
        mainFrame.add(columnList);mainFrame.add(rowList);
        mainFrame.add(DataLabel);
        mainFrame.setTitle("Data Visualization");
        mainFrame.setSize(1000,685);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
    }
    public static void main(String[] args) {
        new GUI();
    }
    class NLabel extends JLabel{
        NLabel(String text,int x,int y,int w,int h){
            this.setText(text);
            this.setBounds(x,y,w,h);
        }
        public int endY() {
            return this.getY()+this.getHeight();
        }
        public int endX() {
            return this.getX()+this.getWidth();
        }
    }

    class NList extends JList{
        NList(int x,int y,int w,int h){
            this.setBounds(x,y,w,h);
        }
        public int endY() {
            return this.getY()+this.getHeight();
        }
        public int endX() {
            return this.getX()+this.getWidth();
        }
    }

    class NPanel extends JList{
        NPanel(int x,int y,int w,int h){
            this.setBounds(x,y,w,h);
        }
        public int endY() {
            return this.getY()+this.getHeight();
        }
        public int endX() {
            return this.getX()+this.getWidth();
        }
    }

    class NTable extends JPanel{
        JTable table;
        JScrollPane tableScroll;
        DefaultTableModel model;
        NTable(String[][] data,String[] header,int x,int y,int w,int h){
            model = new DefaultTableModel(data,header);
            table = new JTable(model);
            table.setPreferredScrollableViewportSize(new Dimension(450,63));
            table.setFillsViewportHeight(true);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tableScroll = new JScrollPane();
            tableScroll.setViewportView(table);
            tableScroll.setVisible(true);
            tableScroll.setBounds(0,0,w,h);
            this.setLayout(null);
            this.add(tableScroll);
            this.setBounds(x,y,w,h);
        }
        public int endY() {
            return this.getY()+this.getHeight();
        }
        public int endX() {
            return this.getX()+this.getWidth();
        }
    }

    class NSList extends JPanel{
        JList list = new JList<>();
        DefaultListModel model = new DefaultListModel();
        JLabel titleLabel;
        JScrollPane listScroll;
        int lebelHeight = 25;
        NSList(String Title,String[] data,int x,int y,int w,int h){
            for (int i = 0; i < data.length; i++) {
                model.addElement(data[i]);
            }
            list.setModel(model);
            titleLabel = new JLabel(Title);
            titleLabel.setOpaque(true);titleLabel.setHorizontalAlignment(SwingConstants.CENTER);titleLabel.setBackground(new Color(170,170,170));
            listScroll = new JScrollPane();
            listScroll.setViewportView(list);
            listScroll.setVisible(true);
            titleLabel.setBounds(0,0,w,lebelHeight);
            listScroll.setBounds(0,lebelHeight,w,h-lebelHeight);
            this.add(titleLabel);
            this.add(listScroll);
            this.setLayout(null);
            this.setBounds(x,y,w,h);
        }
        public void setBGColor(Color bg){list.setOpaque(true);list.setBackground(bg);}
        public void setFGColor(Color bg){list.setOpaque(true);list.setForeground(bg);}
        public void setOrientation(int layoutOrientation){list.setLayoutOrientation(layoutOrientation);}
        public int endY() {
            return this.getY()+this.getHeight();
        }
        public int endX() {
            return this.getX()+this.getWidth();
        }
    }

}


