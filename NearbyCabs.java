package com.mycompany.nearbycabsnogui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class NearbyCabs {
 final static double RADIUS_EARTH = 6357.00;
 private static final String CAB_DRIVER_FILE = 
"C:\\Users\\wwwsu\\OneDrive\\Documents\\NetBeansProjects\\NearbyCabs\\src\\mai
n\\java\\com\\mycompany\\nearbycabs\\Car Driver.txt"; // File containing cab driver 
details
 private static final String[] MAJOR_CITIES = {"Delhi", "Mumbai", "Kolkata", 
"Chennai", "Bengaluru", "Hyderabad"};
 private static final Map<String, double[]> CITY_COORDINATES = new 
HashMap<>();
 static {
 CITY_COORDINATES.put("Delhi", new double[]{28.7041, 77.1025});
 CITY_COORDINATES.put("Mumbai", new double[]{19.076, 72.8777});
 CITY_COORDINATES.put("Kolkata", new double[]{22.5726, 88.3639});
 CITY_COORDINATES.put("Chennai", new double[]{13.0827, 80.2707});
 CITY_COORDINATES.put("Bengaluru", new double[]{12.9716, 77.5946});
 CITY_COORDINATES.put("Hyderabad", new double[]{17.385, 78.4867});
 }
 public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your location: ");
            String location = scanner.nextLine();
            double lat, lon;
if (Arrays.asList(MAJOR_CITIES).contains(location)) {
lat = getCityLatitude (location);

lon = getCityLongitude (location);

} else {

System.out.print("Enter your latitude: ");

lat = Double.parseDouble(scanner.nextLine());

System.out.print("Enter your longitude: ");

lon = Double.parseDouble(scanner.nextLine());

}

String result = findNearbyCabs (lat, lon);

System.out.println(result);

scanner.close();

} catch (Exception e) {

e.printStackTrace();

}
 }
private static double getCityLatitude(String city) {

 return CITY_COORDINATES.get(city)[0];

 }

 private static double getCityLongitude(String city) {

 return CITY_COORDINATES.get(city)[1];

 }

 private static String findNearbyCabs(double lat, double lon) throws IOException {

 StringBuilder result = new StringBuilder();
  DecimalFormat df = new DecimalFormat("#0.000");

 boolean cabsFound = false;

 try (BufferedReader in = new BufferedReader(new 

FileReader(CAB_DRIVER_FILE))) {

 String line;

 result.append("Nearby cabs around location 

(").append(df.format(lat)).append(", ").append(df.format(lon)).append(")");

 result.append("\n\n");
 while ((line = in.readLine()) != null) {
                String[] details = line.split(" ");
                if (details.length >= 5) { // Check if the line contains all necessary details
                    double d = distance(toRadians(lat), toRadians(lon), toRadians(Double.parseDouble(details[1])), toRadians(Double.parseDouble(details[2])));
                    if (d <= 100.0) { // Check if the distance is less than or equal to 100 km
                        result.append("Name: ").append(details[3]).append("\nDriver ID: ").append(details[0]).append("\nPhone: ").append(details[4]).append("\n");
                        result.append("Distance: ").append(df.format(d)).append(" km\n\n");
                        cabsFound = true;
                    }
                } else {
                    System.err.println("Invalid data format in line: " + line);
                }
            }
        }
  if (!cabsFound) {

result.append("No nearby cabs available within 100 km.");

}

return result.toString();
 }
 private static double toRadians(double value) {
        return value * (Math.PI / 180.0);
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double alpha = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
        return alpha * RADIUS_EARTH;
    }
}
