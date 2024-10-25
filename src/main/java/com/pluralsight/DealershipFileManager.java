/*
DealershipFileManager will be responsible for reading the dealership file, parsing the data,
 and creating a Dealership object full of vehicles from the file. It will also be responsible
 for saving a dealership and the vehicles back into the file in the same pipe-delimited format.
 */

package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager {

    public static Dealership getDealership() throws FileNotFoundException {
        Dealership dealership = null;
        try {
            // Create a Buffered Reader to load and read the inventory.csv file
            BufferedReader bufReader = new BufferedReader(new FileReader("src/main/resources/inventory.csv"));

            // Use data from file to create Dealership object
            String input = bufReader.readLine();
            String[] dealershipInfo = input.split("\\|");
            dealership = new Dealership(dealershipInfo[0], dealershipInfo[1], dealershipInfo[2]);

            // Read the file a line at a time to create Vehicle Objects for Dealership object
            while((input = bufReader.readLine()) != null){
                //  Split the string where you find the pipe ( | ) character
                String[] tokens = input.split("\\|");

                // populate the inventory with a list of Vehicles
                // Use the parts to create a Vehicle object and add the object to the ArrayList
                dealership.addVehicle(new Vehicle(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
                        tokens[2], tokens[3], tokens[4], tokens[5], Integer.parseInt(tokens[6]), Double.parseDouble(tokens[7])));
            }

            // close the Buffered Reader
            bufReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return dealership;
    }

    // Will overwrite the inventory.csv file with the current dealership
    public void saveDealership(Dealership dealership){
    }
}