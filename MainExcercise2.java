package assignment;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class MainExcercise2 {

    public static void main(String[] args) throws FileNotFoundException {

        Earth earth = new Earth();
        earth.readDataMap("earth.xyz");
        //ASKS FOR COORDINATES AND RETURNS CORRESPONDING ALTITUDE
        int i = 0;
        while (i >= 0){
            Scanner latLon = new Scanner(System.in);
            System.out.println("Please enter a longitude (0 - 360) and latitude (-90 - 90) or \"quit\" to end program:");
            String myLatLon = latLon.nextLine();
            if (myLatLon.equals("quit")) {
                break;
            } else {
                try {
                    String[] latLonArr = myLatLon.split(" ");
                    if (latLonArr.length != 2) {
                        System.out.println("Invalid longitude/latitude.");
                    }
                    else {
                        System.out.println("The altitude at longitude " + Double.parseDouble(latLonArr[0]) + " and latitude " + Double.parseDouble(latLonArr[1]) + " is " + earth.getAltitude(Double.parseDouble(latLonArr[0]), Double.parseDouble(latLonArr[1])) + " meters.");
                    }
                } catch (NullPointerException | NumberFormatException e) {
                    System.out.println("Invalid longitude/latitude.");
                }
            }

            i++;
        }

    }
}
