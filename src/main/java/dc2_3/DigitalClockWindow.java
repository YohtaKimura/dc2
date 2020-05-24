package dc2_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class DigitalClockWindow extends JWindow implements ActionListener {
    private SimpleDigitalClock panel;
    private JPopupMenu popup;
    private JMenuItem changeFontMenu;
    private JMenuItem changeFontSizeMenu;
    private JMenuItem changeFontColorMenu;
    private JMenuItem changeBackgroundColorMenu;
    private JMenuItem aboutMenu;

    public DigitalClockWindow(String title, SimpleDigitalClock panel) {
//        super(title);
        this.panel = panel;

        // Frame configuration
        setLayout(new BorderLayout());
        addWindowListener(new ApplicationCloseAdapter());
//        setResizable(false);
        //setIgnoreRepaint(true);
        getContentPane().add(panel);

        // Menu configuration
        JMenuBar menuBar = new JMenuBar();
        // [View]
        this.popup = new JPopupMenu();
        JMenu viewMenu = new JMenu("View");
//        viewMenu.setShortcut(new MenuShortcut('V'));
        JMenuItem view = new JMenuItem("View");
        this.popup.add(view);
//        viewMenu.add(view);
//        menuBar.add(viewMenu);
        // [View] -> [Change font...]

        changeFontMenu = new JMenuItem("Change font...");
        changeFontMenu.addActionListener(this);
        this.popup.add(changeFontMenu);
//        viewMenu.add(changeFontMenu);
        // [View] -> [Change font size...]
        changeFontSizeMenu = new JMenuItem("Change font size...");
        changeFontSizeMenu.addActionListener(this);
        this.popup.add(changeFontSizeMenu);
//        viewMenu.add(changeFontSizeMenu);
        // [View] -> [Change font color...]
        changeFontColorMenu = new JMenuItem("Change font color...");
        changeFontColorMenu.addActionListener(this);
        this.popup.add(changeFontColorMenu);
//        viewMenu.add(changeFontColorMenu);
        // [View] -> [Change background color...]
        changeBackgroundColorMenu = new JMenuItem("Change background color...");
        changeBackgroundColorMenu.addActionListener(this);
        this.popup.add(changeBackgroundColorMenu);
//        viewMenu.add(changeBackgroundColorMenu);
        // [Help]
//        JMenu helpMenu = new JMenu("Help");
//        helpMenu.setShortcut(new MenuShortcut('H'));
//        menuBar.add(helpMenu);

//        setJMenuBar(menuBar);

//        TODO: Shut down menu
//        this.popup.addSeparator();
//        this.popup.add(new JMenuItem("SelectAll"));

        getContentPane().addMouseListener(new PopClickListener(this.popup));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(changeFontMenu)) {
            new ChangeFontDialog(this, panel);
        } else if (source.equals(changeFontSizeMenu)) {
            new ChangeFontSizeDialog(this, panel);
        } else if (source.equals(changeFontColorMenu)) {
            new ChangeFontColorDialog(this, panel);
        } else if (source.equals(changeBackgroundColorMenu)) {
            new ChangeBackgroundColorDialog(this, panel);
        }
    }

    void resize() {
        pack();
    }

    class PopClickListener extends MouseAdapter {
        private final JPopupMenu popup;
        PopClickListener(JPopupMenu popup) {
            this.popup = popup;
        }
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger())
                doPop(e);
        }

        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger())
                doPop(e);
        }

        private void doPop(MouseEvent e) {
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}

    class ApplicationCloseAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    /**
     * Window adapter for the dialog closing.
     */
    class DialogCloseAdapter extends WindowAdapter {
        Dialog dialog;

        public DialogCloseAdapter(Dialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            dialog.setVisible(false);
        }
    }