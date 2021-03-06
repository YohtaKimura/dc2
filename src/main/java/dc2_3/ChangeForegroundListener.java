package dc2_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangeForegroundListener implements ItemListener, ActionListener {

    private SimpleDigitalClock panel;

    public ChangeForegroundListener(SimpleDigitalClock panel) {
	this.panel = panel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
	setForeground(e.getItem().toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	setForeground(e.getActionCommand());
    }

    private void setForeground(String color) {
	panel.setClockColor(DisplayColor.nameOf(color).getValue());
    }
}
