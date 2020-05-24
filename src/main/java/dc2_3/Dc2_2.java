package dc2_3;

import javax.swing.*;

public class Dc2_2 {
    public static void main(String[] args) {
        //JFrame frm = new JFrame();
        SimpleDigitalClock clock1 = new SimpleDigitalClock();
        DigitalClockWindow frm = new DigitalClockWindow("dc", clock1);
//        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frm.add(clock1);
        frm.pack();
        frm.setVisible(true);
    }
}