package dc2_2;

import javax.swing.*;
import java.awt.*;

public class TestFrameMenubar {
    public static void main(String args[]) {

        new TestFrame(new SimpleDigitalClock());
    }

    static class TestFrame extends JFrame {
        TestFrame(JPanel panel){
            setSize(new Dimension(200,200));
            JMenuBar menuBar = new JMenuBar();
            // [View]
            JMenu viewMenu = new JMenu("View");
//        viewMenu.setShortcut(new MenuShortcut('V'));
            JMenuItem view = new JMenuItem("View");
            viewMenu.add(view);
            menuBar.add(viewMenu);
            setJMenuBar(menuBar);
            setVisible(true);
            getContentPane().add(panel);
        }
    }
}
