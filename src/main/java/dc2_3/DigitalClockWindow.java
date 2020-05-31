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

    public DigitalClockWindow(String title, SimpleDigitalClock panel) {
//        super(title);
        this.panel = panel;

        // Frame configuration
        setLayout(new BorderLayout());
        addWindowListener(new ApplicationCloseAdapter());
        getContentPane().add(panel);

        // Menu configuration
        this.popup = new JPopupMenu();
        changeFontMenu = new JMenu("Change font...");
        this.popup.add(changeFontMenu);

        changeFontSizeMenu = new JMenu("Change font size...");
        ChangeFontSizeListener fontSizeListener = new ChangeFontSizeListener(this, this. panel);
        for (int i = 10; i <= 250; i++) {
	      if (i % 5 != 0)
		  continue;
	      JMenuItem item = new JMenuItem(Integer.toString(i));
	      item.addActionListener(fontSizeListener);
	      changeFontSizeMenu.add(item);
	    }

        this.popup.add(changeFontSizeMenu);
        changeFontColorMenu = new JMenu("Change font color...");
	    for (DisplayColor c : DisplayColor.values()) {
	      JMenuItem item = new JMenuItem(c.toString());
	      item.addActionListener(new ChangeForegroundListener(panel));
	      changeFontColorMenu.add(item);
	    }
        this.popup.add(changeFontColorMenu);

        changeBackgroundColorMenu = new JMenu("Change background color...");
        for (DisplayColor c : DisplayColor.values()) {
	      JMenuItem item = new JMenuItem(c.toString());
	      item.addActionListener(new ChangeBackgroundListener(panel));
	      changeBackgroundColorMenu.add(item);
	    }
        this.popup.add(changeBackgroundColorMenu);

    // [Exit]
        this.popup.addSeparator();
        PopupMenuActionListener listener = new PopupMenuActionListener();
        JMenuItem exitMenu = new JMenuItem("Exit");
        exitMenu.addActionListener(listener);
	    popup.add(exitMenu);
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

    class PopupMenuActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    Object source = e.getSource();
//	    if (source.equals(exitMenu)) {
		System.out.println();
		System.exit(0);
//	    } else if (source.equals(aboutMenu)) {
//		new AboutDialog(null);
	    }
	}
