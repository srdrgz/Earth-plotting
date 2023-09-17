package assignment;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainExcercise1 {

        public static void main(String[] args) throws FileNotFoundException {

            Earth earth = new Earth();
            earth.readDataArray("earth.xyz");
            earth.readDataMap("earth.xyz");
            //ASKS FOR ALTITUDE, RETURNS % COORDINATES ABOVE UNTIL QUIT
            int i = 0;
            while (i >= 0){
                Scanner altitude = new Scanner(System.in);
                System.out.println("Please enter an altitude or \"quit\" to end program:");
                String myAltitude = altitude.nextLine();
                if (myAltitude.equals("quit")) break;
                else {
                    try {

                        Double validAltitude = Double.parseDouble(myAltitude);
                        System.out.println("Proportion of coordinates above " + myAltitude + " meters: ");
                        earth.percentageAbove(validAltitude);
                    } catch (NullPointerException | NumberFormatException e) {
                        System.out.println("Invalid altitude.");
                    }
                }
                i++;
            }

        }
    }

