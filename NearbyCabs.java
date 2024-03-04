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
