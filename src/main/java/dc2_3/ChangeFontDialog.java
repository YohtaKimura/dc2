package dc2_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ChangeFontDialog extends JDialog implements ActionListener {
    private JWindow owner;
    private Choice fontChoise;
    private SimpleDigitalClock panel;

    ChangeFontDialog(JWindow owner, SimpleDigitalClock panel) {
        super(owner);
        this.panel = panel;
        this.owner = owner;

        setLayout(new FlowLayout());
        setTitle("Change font");

        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        Font[] fonts = ge.getAllFonts();
        fontChoise = new Choice();
        for (Font f : fonts) {
            fontChoise.add(f.getName());
        }
        add(fontChoise);

        Button okButton = new Button("OK");
        okButton.addActionListener(this);
        add(okButton);

        pack();
        setLocationRelativeTo(null);
        addWindowListener(new DialogCloseAdapter(this));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Font currentFont = panel.getClockFont();
        panel.setClockFont(new Font(fontChoise.getSelectedItem(), currentFont
                .getStyle(), currentFont.getSize()));
        owner.pack();
        owner.setLocationRelativeTo(null);
        setVisible(false);
    }
}