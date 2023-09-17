package assignment;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Earth {
    public double[][] arrayOfEarth;
    public HashMap<List<Double>, Double> mapOfEarth;

    public void readDataArray(String fileName) throws FileNotFoundException {
        Scanner input = new Scanner(new File(fileName));

        int countLines = 0;

        while(input.hasNextLine()){
            countLines++;
            input.nextLine();
        }
        arrayOfEarth = new double[countLines][3];

        input.close();
        input = new Scanner(new File(fileName));
        String line;
        double x, y, altitude;

        countLines = 0;

        while(input.hasNextLine()){
            line = input.nextLine();
            String[] lineData = line.split("\t");
            x = Double.parseDouble(lineData[0]);
            if (x >= 180 ) x = x-180;
            else x = x+180;
            y = Double.parseDouble(lineData[1]);
            altitude = Double.parseDouble(lineData[2]);

            arrayOfEarth[countLines][0] = x;
            arrayOfEarth[countLines][1] = y;
            arrayOfEarth[countLines][2] = altitude;

            countLines++;
        }
    }


    public List<Double> CoordinatesAbove(double altitude) {
        List<Double> CoordinatesAbove = new ArrayList<>();
        for (int i = 0; i < arrayOfEarth.length; i++) {
            if (altitude < arrayOfEarth[i][2]) {
                 CoordinatesAbove.add(arrayOfEarth[i][2]);
            }
        }
        return CoordinatesAbove;
    }

    public List<Double> CoordinatesBelow(double altitude) {
        List<Double> CoordinatesBelow = new ArrayList<>();
        for (int i = 0; i < arrayOfEarth.length; i++) {
            if (altitude > arrayOfEarth[i][2]) {
                CoordinatesBelow.add(arrayOfEarth[i][2]);
            }
        }
        return CoordinatesBelow;
    }
    public void percentageAbove(double altitude){
       List<Double> percentageAbove = new ArrayList<>();
        for (int i = 0; i < arrayOfEarth.length; i++) {
            if (altitude < arrayOfEarth[i][2]) {
                percentageAbove.add(arrayOfEarth[i][2]);
            }
        }
        float numberAbove = percentageAbove.size();
        float numberTotal = arrayOfEarth.length;
        float result = (((numberAbove) / (numberTotal))*100);
        double roundedResult = Math.round(result * 10) / 10.0;
        System.out.println( roundedResult + "%");

    }
    public void percentageBelow(double altitude){
        List<Double> percentageBelow = new ArrayList<>();
        for (int i = 0; i < arrayOfEarth.length; i++) {
            if (altitude > arrayOfEarth[i][2]) {
                percentageBelow.add(arrayOfEarth[i][2]);
            }
        }
        float numberAbove = percentageBelow.size();
        float numberTotal = arrayOfEarth.length;
        float result = (((numberAbove) / (numberTotal))*100);
        double roundedResult = Math.round(result * 10) / 10.0;
        System.out.println( roundedResult + "%");

    }
    public void readDataMap(String fileName) throws FileNotFoundException{
        mapOfEarth = new HashMap<>();
        Scanner input = new Scanner(new File(fileName));
        while (input.hasNextLine()){
            String[] line = input.nextLine().split("\t");
            List<Double> keys = new ArrayList<>();
            keys.add(Double.parseDouble(line[0]));
            keys.add(Double.parseDouble(line[1]));
            mapOfEarth.put(keys, Double.parseDouble(line[2]));
        }
    }
    public double getAltitude(double longitude, double latitude){
        List<Double> getKeys = new ArrayList<>();
        getKeys.add(longitude);
        getKeys.add(latitude);
        double value = mapOfEarth.get(getKeys);
        return value;
    }
    public void generateMap(double resolution){
    Random r = new Random();
    List<Double> keys = new ArrayList<>();
    double lat = (resolution)*360;
    double lon = (resolution)*180;
        for (double i = 0; i < lat*lon ; i++){
            keys.add(Math.random()*lat);
            keys.add(ThreadLocalRandom.current().nextDouble(-lon/2, lon/2));
            mapOfEarth.put(keys, ThreadLocalRandom.current().nextDouble(-7000, 6000));
            System.out.println(mapOfEarth);
            }
        }
}


