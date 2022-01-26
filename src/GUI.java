import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
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

        XYSeries series = new XYSeries("XY Chart");
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Testing Chart", "Date", "Average Profit", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(DataLabel.endX(),DataLabel.endY(),580,600);

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

}


