import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.TableView;

public class Mork {
    JFrame mainFrame = new JFrame();
    DefaultListModel model = new DefaultListModel();
    DisplayList testList = new DisplayList(model,0,0,200,200);

    Mork(){
        testList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        testList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting() == true && testList.getSelectedIndex() != -1){
                    System.out.println(testList.getSelectedValue());
                }
            }
        });
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 102, 51));
        panel.setBounds(50, 64, 955, 888);
        panel.setLayout(null); //Absolute Layout
        XYSeries series = new XYSeries("XY Chart");
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Testing Chart", "Date", "Average Profit", dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
        // Drag and Drop
        testList.setDropTarget(new DropTarget(){
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
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedFlavorException e) {
                    e.printStackTrace();
                } catch (CsvValidationException e) {
                    e.printStackTrace();
                }
            }
        });

        testList.setModel(model);
        testList.setBackground(Color.BLACK);
        mainFrame.setTitle("test");
        mainFrame.setLayout(null);
        testList.setBounds(0,0,100,100);
        chartPanel.setBounds(testList.endX(),0,200,200);
        mainFrame.getContentPane().add(chartPanel);
        mainFrame.getContentPane().add(testList);
        mainFrame.getContentPane().setBackground(new Color(0,255,255));
        mainFrame.setSize(400,800);
        //mainFrame.setResizable(false);
        mainFrame.setVisible(true);

    }


    // Driver  method
    public static void main(String[] args)
    {
        new Mork();
    }
}

class DisplayList extends JList {
    DisplayList(ListModel model,int x,int y,int w,int h) {
        this.setBounds(x,y,w,h);
        this.setModel(model);
    }
    public int endY() {
        return this.getY()+this.getHeight();
    }
    public int endX() {
        return this.getX()+this.getWidth();
    }
}


