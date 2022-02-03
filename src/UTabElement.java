
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class UTabElement extends JPanel {
    boolean isVertZ = false;
    JLabel displayText = new JLabel();
    Color selectOverColor = new Color(200,200,200);
    Color overColor = new Color(64,64,64);
    Color selectColor = Color.white;
    Color selectColorText = Color.black;
    Color color = Color.black;
    Color colorText = Color.white;
    Color downColor = new Color(150,150,150);
    boolean isSelected = false;
    Point mouseDownLocation;
    Point downLocation;
    UTab uTab;
    int tabIndex = 0;
    int moveToIndex = -1;
    UTabElement uTabElement;
    UTabElement() {
        uTabElement = this;
        this.setBackground(Color.black);
        this.setLayout(null);

        displayText.setBorder(new EmptyBorder(0,10,0,0));
        displayText.setHorizontalAlignment(JLabel.LEFT);
        displayText.setForeground(Color.white);
        this.add(displayText);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 2) {
                    uTab.removeElements(uTabElement);
                } else {
                    select();
                    setBackground(downColor);
                    mouseDownLocation = e.getLocationOnScreen();
                    downLocation = getLocation();
                    moveToIndex = -1;
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isSelected) {
                    setBackground(selectColor);
                    displayText.setForeground(selectColorText);
                } else {
                    setBackground(color);
                    displayText.setForeground(colorText);
                }
                setLocation(downLocation);
                uTab.previewIndex(-1);
                if (moveToIndex != -1) {
                    uTab.elements.remove(uTabElement);
                    uTab.elements.add(moveToIndex,uTabElement);
                    uTab.update();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (isSelected) {
                    setBackground(selectOverColor);
                } else {
                    setBackground(overColor);
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (isSelected) {
                    setBackground(selectColor);
                } else {
                    setBackground(color);
                }

            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isVertZ) {
                    setLocation(new Point(downLocation.x,downLocation.y + (e.getYOnScreen() - mouseDownLocation.y)));
                    moveToIndex = uTab.previewIndexAtLocation(getY());
                } else {
                    setLocation(new Point(downLocation.x + (e.getXOnScreen() - mouseDownLocation.x),downLocation.y));
                    moveToIndex = uTab.previewIndexAtLocation(getX());
                }


            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }
    public void select() {
        if (!isSelected) {
            this.setBackground(selectColor);
            this.displayText.setForeground(selectColorText);
            uTab.tabSelectionChange(this);
            this.isSelected = true;
        }
    }
    public void deSelect() {
        this.setBackground(color);
        this.displayText.setForeground(colorText);
        this.isSelected = false;
    }
    UTabElement(String text) {
        this();
        this.displayText.setText(text);
    }
    Object bind;
    UTabElement(String text,Object bind) {
        this(text);
        this.bind = bind;
    }
    public void assign(UTab uTab,int tabIndex,Dimension size) {
        this.uTab = uTab;
        this.tabIndex = tabIndex;
        this.setSize(size);
        displayText.setBounds(0,0,size.width,size.height);
    }
}
