package dc2_4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
class DigitalClockMenu extends JMenuBar {

    private JFrame frame;
    private SimpleDigitalClock panel;
    private JMenuItem propertiesMenu;
    private JMenuItem aboutMenu;

    public DigitalClockMenu(JFrame frame, SimpleDigitalClock panel) {

        this.frame = frame;
        this.panel = panel;

        MenuActionListener listener = new MenuActionListener();

        // [View]
        JMenu viewMenu = new JMenu("View");
        viewMenu.setMnemonic('V');
        add(viewMenu);

        // Menu [View] -> [Look & Feel]
        JMenu lfMenu = new JMenu("Look & Feel");
        lfMenu.setMnemonic('L');
        viewMenu.add(lfMenu);
        ActionListener lfActionListener = new LFActionListener();
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            JMenuItem item = new JMenuItem(info.getName());
            item.addActionListener(lfActionListener);
            lfMenu.add(item);
        }

        // [View] -> [Properties...]
        propertiesMenu = new JMenuItem("Properties...");
        propertiesMenu.setMnemonic('P');
        propertiesMenu.addActionListener(listener);
        viewMenu.add(propertiesMenu);
    }

    private class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new PropertiesDialog(frame, panel);
        }
    }

    private class LFActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (((JMenuItem) e.getSource()).getText()
                        .equals(info.getName())) {
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                    } catch (ClassNotFoundException | InstantiationException
                            | IllegalAccessException
                            | UnsupportedLookAndFeelException e1) {
                        e1.printStackTrace();
                    }
                    SwingUtilities.updateComponentTreeUI(frame);
                    frame.pack();
                    break;
                }
            }
        }
    }
}
