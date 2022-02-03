
import java.awt.*;

public class UTabVertical extends UTab{
    int elementsSize = 40;

    @Override
    public void addElements(UTabElement uTabElement) {
        uTabElement.assign(this,elements.size(),new Dimension(this.getWidth() - spacing*2,elementsSize));
        uTabElement.isVertZ = true;
        if (elements.size() == 0) {
            uTabElement.select();
        }
        elements.add(uTabElement);
        uTabElement.repaint();
        uTabElement.displayText.repaint();
        update();
    }
    public int previewIndexAtLocation(int y) {
        //System.out.println(x/elementsWidthSize);
        return previewIndex(y/ elementsSize);
    }
    @Override
    public int previewIndex(int i) {
        if (i == -1) {
            insertPanel.setVisible(false);
        } else {
            if (i > elements.size()-1) {
                i = elements.size()-1;
            }
            insertPanel.setLocation(0,i* elementsSize);
            insertPanel.setVisible(true);
        }
        return i;
    }
    public void update() {
        this.removeAll();
        insertPanel.setBounds(0,0,this.getWidth(),spacing);
        insertPanel.setVisible(false);
        for (int i = 0;i < elements.size();i++) {
            UTabElement selectedElement = elements.get(i);
            selectedElement.setBounds(spacing,(i* elementsSize) + spacing, this.getWidth() - (spacing*2),elementsSize - (spacing));
            this.add(selectedElement);
        }
        this.add(insertPanel);
        if (selectedTab != null) {
            this.remove(selectedTab);
            this.add(selectedTab,0);
        }
        this.repaint();
    }
}
