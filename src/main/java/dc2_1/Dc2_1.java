package dc2_1;

import javax.swing.*;

public class Dc2_1 {
    public static void main(String[] args) {
        JFrame frm = new JFrame();
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SimpleDigitalClock clock1 = new SimpleDigitalClock();
        frm.add(clock1);
        frm.pack();
        frm.setVisible(true);
    }
}