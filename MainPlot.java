package assignment;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class MainPlot {
    public static void main(String[] args) throws FileNotFoundException {
        JFrame jf = new JFrame("My Earth Map");
        PlotEarth pm = new PlotEarth("earth.xyz");
        pm.seaLevel(0);
        pm.MouseMapCoordinates();
        jf.getContentPane().setPreferredSize(new Dimension(720, 360));
        jf.add(pm);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
