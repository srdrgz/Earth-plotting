package assignment;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import java.util.List;

public class PlotEarth extends Plot {
    Earth earth;
    int blocksize = 2;
    double resolution = 1;

    public PlotEarth(String filename) throws FileNotFoundException {
        earth = new Earth();
        earth.readDataArray(filename);
        setScaleX(0, 360);
        setScaleY(-90, 90);
    }

    public void seaLevel(double shift) {
        if (shift > 0) {
            for (int i = 0; i < earth.arrayOfEarth.length; i++) {
                earth.arrayOfEarth[i][2] = earth.arrayOfEarth[i][2] - shift;
            }
        } else {
            for (int i = 0; i < earth.arrayOfEarth.length; i++) {
                earth.arrayOfEarth[i][2] = earth.arrayOfEarth[i][2] + shift;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        this.width = getWidth();
        this.height = getHeight();
        for (int i = 0; i < earth.arrayOfEarth.length; i++) {

            double x = earth.arrayOfEarth[i][0];
            double y = earth.arrayOfEarth[i][1];
            double z = earth.arrayOfEarth[i][2];

            if (z < 0) {
                g2D.setColor(new Color(30, 80, 100));
            }
            if (z < -1500) {
                g2D.setColor(new Color(30, 50, 100));
            }
            if (z < -3000) {
                g2D.setColor(new Color(30, 70, 100));
            }

            if (z > 0) {
                g2D.setColor(new Color(40, 100, 0));
            }
            if (z > 400) {
                g2D.setColor(new Color(50, 70, 0));
            }
            if (z > 4000) {
                g2D.setColor(new Color(200, 200, 120));
            }
            if (z > 1500) {
                g2D.setColor(new Color(70, 90, 0));
            }

            g.fillRect(scaleX(x), scaleY(y), blocksize, blocksize);
        }

    }

    public void MouseMapCoordinates() {
        addMouseListener(new MouseListener());
    }

    class MouseListener extends MouseAdapter {
        public List<Double[]> MapCoordinate = new ArrayList();

        @Override
        public void mouseClicked(MouseEvent ev) {
            if (ev.getButton() == MouseEvent.BUTTON1) {
                double x = (ev.getX() * 360) / 720;
                double y1 = (ev.getY() * 180) / 360;
                double y = 90 - y1;
                try {
                    earth.readDataMap("earth.xyz");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Double[] Coordinates = new Double[3];
                double alt = earth.getAltitude(x, y);
                Coordinates[0] = x;
                Coordinates[1] = y;
                Coordinates[2] = alt;
                MapCoordinate.add(Coordinates);
                int lastCoords = MapCoordinate.size() - 1;
                if (MapCoordinate.size() >= 2) {
                    MapCoordinate MC = new MapCoordinate(MapCoordinate.get(lastCoords - 1)[0], MapCoordinate.get(lastCoords - 1)[1], MapCoordinate.get(lastCoords - 1)[2]);
                    MapCoordinate mc = new MapCoordinate(x, y, alt);
                    double distance = MC.distanceTo(mc);
                    System.out.println("Coordinates: " + x + "\t" + y + "\t" + alt + "\t | Distance to last position: " + distance + " km.");
                } else {
                    System.out.println("Coordinates: " + x + "\t" + y + "\t" + alt);
                }
                File fileName = new File("newFile.xyz");
                if (fileName.exists()) {
                    try {
                        BufferedWriter output = new BufferedWriter(new FileWriter(fileName, true));
                        output.write(Coordinates[0] + "\t" + Coordinates[1] + "\t" + Coordinates[2]+"\n");
                        output.close();
                    } catch (IOException e) {e.printStackTrace(); }
                } else {
                    try {
                        if (fileName.createNewFile()) {
                            System.out.println("New file created in project root directory");
                            try {
                                BufferedWriter output = new BufferedWriter(new FileWriter(fileName, true));
                                output.write(Coordinates[0] + "\t" + Coordinates[1] + "\t" + Coordinates[2]+"\n");
                                output.close();
                            } catch (IOException e) {e.printStackTrace(); }
                        } else {
                            System.out.println("Could not create new file");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (ev.getButton() == MouseEvent.BUTTON3 && MapCoordinate.size() > 0) {
                int removeCoord = MapCoordinate.size() - 1;
                System.out.println("Deleted coordinates: " + MapCoordinate.get(removeCoord)[0] + "\t" + MapCoordinate.get(removeCoord)[1] + "\t" + MapCoordinate.get(removeCoord)[2]);
                MapCoordinate.remove(removeCoord);
            }
        }
    }
}






