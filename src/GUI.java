import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    JFrame mainFrame = new JFrame();

    NLabel DataLabel = new NLabel("data",0,0,200,50);
    /* ColumnLabel = new NLabel("Column",DataLabel.endX(),0,100,DataLabel.getHeight()/2);
    NLabel RowLabel = new NLabel("Row", DataLabel.endX(),ColumnLabel.endY(),100,DataLabel.getHeight()/2);

     */

    /*NList columnList = new NList(ColumnLabel.endX(),ColumnLabel.getY(),480,DataLabel.getHeight()/2);
    DefaultListModel columnModel = new DefaultListModel<>();

    NList rowList = new NList(RowLabel.endX(),RowLabel.getY(),480,DataLabel.getHeight()/2);
    DefaultListModel rowModel = new DefaultListModel<>();*/

    /*NList dimensionList = new NList(0,DataLabel.endY(),200,300);
    DefaultListModel dimensionModel = new DefaultListModel<>();

    NList measuresList = new NList(0,dimensionList.endY(),200,300);
    DefaultListModel measuresModel = new DefaultListModel<>();*/

    VNSList dimensionList,measuresList;
    HNSList rowList,columnList;

    JComboBox graphSelection = new JComboBox();
    DefaultComboBoxModel graphSelectionModel = new DefaultComboBoxModel<>();

    //JPanel tablePanel = new JPanel();
    NTable tablePanel;
    VNSList test;

    GUI(){
        DataLabel.setOpaque(true);DataLabel.setBackground(new Color(170,170,170));DataLabel.setHorizontalAlignment(SwingConstants.CENTER);DataLabel.setForeground(Color.WHITE);
        dimensionList = new VNSList("Dimension",0,DataLabel.endY(),200,300);dimensionList.setBGColor(new Color(255,242,204));
        measuresList = new VNSList("Measures",0,dimensionList.endY(),200,300);measuresList.setBGColor(new Color(106,168,79));
        columnList = new HNSList("Column",DataLabel.endX(),0,580,DataLabel.getHeight()/2);columnList.setOrientation(JList.HORIZONTAL_WRAP);
        rowList = new HNSList("Row", DataLabel.endX(),columnList.endY(),580,DataLabel.getHeight()/2);rowList.setOrientation(JList.HORIZONTAL_WRAP);

        //test data
        ArrayList<String> graphType = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String temp = "Graph Type " + (i+1);
            graphType.add(temp);
        }

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
        //dimensionList.setModel(header2);
        //measuresList.setModel(header2);
        rowList.setModel(header2);
        columnList.setModel(header2);
        tablePanel = new NTable(data,header,rowList.endX(),rowList.getY(),200,625);

        graphSelection.setBounds(columnList.endX(),columnList.getY(),200,25);
        graphSelectionModel.addAll(graphType);
        graphSelection.setModel(graphSelectionModel);

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
        //mainFrame.add(ColumnLabel);mainFrame.add(RowLabel);
        mainFrame.add(dimensionList);mainFrame.add(measuresList);
        mainFrame.add(columnList);mainFrame.add(rowList);
        mainFrame.add(DataLabel);
        mainFrame.setTitle("Data Visualization");
        mainFrame.setSize(995,685);
        mainFrame.setResizable(false);
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

    class VNSList extends JPanel{
        JList list = new JList<>();
        DefaultListModel model = new DefaultListModel();
        JLabel titleLabel;
        JScrollPane listScroll;
        int lebelHeight = 25;
        VNSList(String Title,int x,int y,int w,int h){
            titleLabel = new JLabel(Title);
            titleLabel.setOpaque(true);titleLabel.setHorizontalAlignment(SwingConstants.CENTER);titleLabel.setBackground(new Color(150,150,150));
            listScroll = new JScrollPane();
            listScroll.setViewportView(list);
            listScroll.setVisible(true);
            titleLabel.setBounds(0,0,w,lebelHeight);
            listScroll.setBounds(0,lebelHeight,w,h-lebelHeight);
            list.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(e.getValueIsAdjusting() == true && list.getSelectedIndex() != -1){
                        System.out.println(list.getSelectedValue());
                    }
                }
            });

            list.setDropTarget(new DropTarget(){
                public synchronized void drop(DropTargetDropEvent evt){
                    try{
                        evt.acceptDrop(DnDConstants.ACTION_COPY);
                        List<File> droppedFiles = (List<File>)
                                evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                        for (File file : droppedFiles){
                            CSVReader reader = new CSVReader(new FileReader(file));
                            ArrayList<String> Dimension = new ArrayList<String>();

                            for (String token : reader.readNext()){
                                Dimension.add(token);
                                model.addElement(token);
                            }
                            while(reader.readNext() != null){
                                ArrayList<String> data = new ArrayList<String>();
                                for (String token : reader.readNext()){
                                    data.add(token);
                                }
                                for (int i = 0;i < data.size();i++){
                                    System.out.println(Dimension.get(i) + " : " + data.get(i));
                                }
                                System.out.println("--------------------------------------------------");
                            }
                        }
                        list.setModel(model);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (UnsupportedFlavorException e) {
                        e.printStackTrace();
                    } catch (CsvValidationException e) {
                        e.printStackTrace();
                    }
                }
            });

            this.add(titleLabel);
            this.add(listScroll);
            this.setLayout(null);
            this.setBounds(x,y,w,h);
        }
        public void setModel(String data[]){
            for (int i = 0; i < data.length; i++) {
                model.addElement(data[i]);
            }
            list.setModel(model);
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

    class HNSList extends JPanel{
        JList list = new JList<>();
        DefaultListModel model = new DefaultListModel();
        JLabel titleLabel;
        JScrollPane listScroll;
        int lebelHeight = 25;
        HNSList(String Title,int x,int y,int w,int h){
            list.setVisibleRowCount(-1);
            titleLabel = new JLabel(Title);
            titleLabel.setOpaque(true);titleLabel.setHorizontalAlignment(SwingConstants.CENTER);titleLabel.setBackground(new Color(150,150,150));
            listScroll = new JScrollPane();
            listScroll.setViewportView(list);
            listScroll.setVisible(true);
            listScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            listScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            titleLabel.setBounds(0,0,w/5,lebelHeight);
            listScroll.setBounds(w/5,0,w-(w/5),lebelHeight);
            this.add(titleLabel);
            this.add(listScroll);
            this.setLayout(null);
            this.setBounds(x,y,w,h);
        }
        public void setModel(String data[]){
            for (int i = 0; i < data.length; i++) {
                model.addElement(data[i]);
            }
            list.setModel(model);
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


