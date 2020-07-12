package dc2_4;

import dc2_3.DisplayColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangeBackgroundListener implements ItemListener, ActionListener {

    private SimpleDigitalClock panel;

    public ChangeBackgroundListener(SimpleDigitalClock panel) {
	this.panel = panel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
	setBackground(e.getItem().toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	setBackground(e.getActionCommand());
    }

    private void setBackground(String color) {
	panel.setBackground(DisplayColor.nameOf(color).getValue());
    }
}
