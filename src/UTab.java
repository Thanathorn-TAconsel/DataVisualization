

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

public class UTab extends JPanel{
    UTabElement dragingContent;
    Vector<UTabElement> elements = new Vector<>();
    JPanel insertPanel = new JPanel();
    int elementsSize = 200;
    int spacing = 5;
    UTabElement selectedTab;
    UTab sender;
    UTab s;
    UTab() {
        s = this;
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.setLayout(null);
        insertPanel.setBackground(Color.red);
        this.add(insertPanel);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (sender != null) {
                    if (sender.dragingContent != null) {
                        /*
                        previewIndexAtLocation(e.getX());
                        System.out.println(e.getX());

                         */
                        sender.dragingContent.setDes(s);
/*
                        UTabElement selectedElements = sender.dragingContent.clone();
                        sender.dragingContent = null;
                        selectedElements.isVertZ=false;
                        addElements(selectedElements);

*/
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
    public void addElements(UTabElement uTabElement) {
        uTabElement.assign(this,elements.size(),new Dimension(elementsSize,this.getHeight() - spacing*2));
        if (elements.size() == 0) {
            uTabElement.select();
        }
        elements.add(uTabElement);
        uTabElement.repaint();
        uTabElement.displayText.repaint();
        update();
    }
    public void tabSelectionChange(UTabElement uTabElement) {
        if (selectedTab != null) {
            selectedTab.deSelect();
        }
        this.remove(uTabElement);
        this.add(uTabElement,0);

        selectedTab = uTabElement;
    }
    public void removeElements(UTabElement element) {
        if (selectedTab == element) {
            selectedTab = null;
        }
        this.elements.remove(element);
        this.update();
    }

    public int previewIndexAtLocation(int x) {
        //System.out.println(x/elementsWidthSize);
        return previewIndex(x/ elementsSize);
    }
    public int previewIndex(int i) {
        if (i == -1) {
            insertPanel.setLocation(-spacing,0);
            insertPanel.setVisible(false);
        } else {
            if (i > elements.size()-1) {
                i = elements.size()-1;
            }
            insertPanel.setLocation(i* elementsSize,0);
            insertPanel.setVisible(true);
        }
        return i;
    }
    public void addActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }
    ActionListener actionListener;
    public void update() {
        this.removeAll();
        insertPanel.setBounds(0,0,spacing,this.getHeight());
        insertPanel.setVisible(false);
        for (int i = 0;i < elements.size();i++) {
            UTabElement selectedElement = elements.get(i);
            selectedElement.setBounds((i* elementsSize) + spacing,spacing, elementsSize - (spacing),this.getHeight() - (spacing*2));
            this.add(selectedElement);
        }
        this.add(insertPanel);
        if (selectedTab != null) {
            this.remove(selectedTab);
            this.add(selectedTab,0);
        }
        this.repaint();
        if (actionListener != null) {
            actionListener.actionPerformed(null);
        }
    }
}
