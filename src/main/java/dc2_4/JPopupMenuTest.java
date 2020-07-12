package dc2_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class JPopupMenuTest extends JWindow {
   private JPopupMenu popup;
   public JPopupMenuTest() {
      //setTitle("JPopupMenu Test");
      Container contentPane = getContentPane() ;
      popup = new JPopupMenu();
      // add menu items to popup
      JMenuItem cutMenu = new JMenuItem("Cut");
      popup.add(cutMenu);
      PopupMenuActionListener listener = new PopupMenuActionListener();
      JMenuItem one = new JMenuItem("1");
      one.addActionListener(listener);
      cutMenu.add(one);
      //        for (int i = 10; i <= 250; i++) {
//	      if (i % 5 != 0)
//		  continue;
//	      JMenuItem item = new JMenuItem(Integer.toString(i));
//	    item.addActionListener(fontSizeListener);
//	      changeFontSizeMenu.add(item);
//	    }
      popup.add(new JMenuItem("Copy"));
      popup.add(new JMenuItem("Paste"));
      popup.addSeparator();
      popup.add(new JMenuItem("SelectAll"));
      // [Exit]

      JMenuItem exitMenu = new JMenuItem("Exit");
//      exitMenu.setMnemonic('E');
      exitMenu.addActionListener(listener);
	  popup.add(exitMenu);

      contentPane.addMouseListener(new MouseAdapter() {
         public void mouseReleased(MouseEvent me) {
            showPopup(me); // showPopup() is our own user-defined method
         }
      }) ;
      setSize(375, 250);
//      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setVisible(true);
   }
   void showPopup(MouseEvent me) {
      if(me.isPopupTrigger())
         popup.show(me.getComponent(), me.getX(), me.getY());
   }

   private class PopupMenuActionListener implements ActionListener {
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

   public static void main(String args[]) {
      new JPopupMenuTest();
   }
}