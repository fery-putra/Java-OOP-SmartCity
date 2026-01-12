package smartcity;

/**
 * MODULE 3: Park Class - Demonstrates Inheritance
 */
public class Park extends Building {  // INHERITANCE

    // Specialized attributes
    private double greenSpaceArea;
    private boolean hasPlayground;
    private boolean hasFountain;
    private int numberOfBenches;
    private String parkType; // Urban, Botanical, Recreation, Dog Park

    // CONSTRUCTORS

    public Park() {
        super();
        this.type = "Park";
        this.floors = 1; // Parks are typically single level
        this.greenSpaceArea = 0;
        this.hasPlayground = false;
        this.hasFountain = false;
        this.numberOfBenches = 0;
        this.parkType = "Urban";
        System.out.println("Park default constructor called");
    }

    public Park(String name, double area) {
        super(name, "Park", 1, area); // Parks have 1 floor
        this.greenSpaceArea = area * 0.7; // 70% green space
        this.hasPlayground = true;
        this.hasFountain = false;
        this.numberOfBenches = 10;
        this.parkType = "Urban";
        System.out.println("Park(name, area) constructor called");
    }

    public Park(String name, double area, int capacity,
                String parkType, boolean hasPlayground, boolean hasFountain) {
        super(name, "Park", 1, area, capacity, true);
        this.greenSpaceArea = area * 0.8;
        this.hasPlayground = hasPlayground;
        this.hasFountain = hasFountain;
        this.numberOfBenches = capacity / 10; // Estimate benches
        this.parkType = parkType;
        System.out.println("Park(all parameters) constructor called");
    }

    // Getters
    public double getGreenSpaceArea() { return greenSpaceArea; }
    public boolean hasPlayground() { return hasPlayground; }
    public boolean hasFountain() { return hasFountain; }
    public int getNumberOfBenches() { return numberOfBenches; }
    public String getParkType() { return parkType; }

    // Setters
    public void setGreenSpaceArea(double greenSpaceArea) {
        if (greenSpaceArea >= 0) {
            this.greenSpaceArea = greenSpaceArea;
        }
    }

    public void setHasPlayground(boolean hasPlayground) {
        this.hasPlayground = hasPlayground;
    }

    public void setHasFountain(boolean hasFountain) {
        this.hasFountain = hasFountain;
    }

    public void setNumberOfBenches(int numberOfBenches) {
        if (numberOfBenches >= 0) {
            this.numberOfBenches = numberOfBenches;
        }
    }

    public void setParkType(String parkType) {
        if (parkType != null) {
            this.parkType = parkType;
        }
    }

    // METHOD OVERRIDING
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("=== Park Specific Info ===");
        System.out.println("Park Type: " + parkType);
        System.out.println("Green Space: " + String.format("%.2f", greenSpaceArea) + " sq meters");
        System.out.println("Playground: " + (hasPlayground ? "Available" : "Not Available"));
        System.out.println("Fountain: " + (hasFountain ? "Available" : "Not Available"));
        System.out.println("Benches: " + numberOfBenches);
    }

    // METHOD OVERRIDING
    @Override
    public void performMaintenance() {
        System.out.println("\n=== Park Maintenance Check for " + name + " ===");
        System.out.println("- Maintaining green spaces");
        System.out.println("- Trimming trees and hedges");
        System.out.println("- Cleaning pathways");
        System.out.println("- Repairing benches");

        if (hasPlayground) {
            System.out.println("- Checking playground equipment safety");
        }
        if (hasFountain) {
            System.out.println("- Cleaning and maintaining fountain");
        }

        updateMaintenanceCost();
        maintenanceCost += greenSpaceArea * 0.3; // Green space maintenance
        maintenanceCost += numberOfBenches * 5;

        System.out.println("Park Maintenance completed! Total Cost: $" +
                String.format("%.2f", maintenanceCost));
    }

    // METHOD OVERRIDING
    @Override
    public void showServices() {
        System.out.println("\n=== Park Services ===");
        System.out.println("1. " + parkType + " Recreation");
        System.out.println("2. Walking and Jogging Paths");
        System.out.println("3. Relaxation Areas");

        if (hasPlayground) {
            System.out.println("4. Playground for Children");
        }
        if (hasFountain) {
            System.out.println("5. Decorative Fountain");
        }

        System.out.println("6. Picnic Areas");
    }

    // METHOD OVERRIDING: Calculate efficiency differently for parks
    @Override
    public void calculateEfficiency(int currentVisitors) {
        if (currentVisitors > capacity) {
            System.out.println("WARNING: Park is overcrowded!");
            System.out.println("Consider visitor limits for safety.");
        } else if (currentVisitors == 0) {
            System.out.println("Park has no visitors currently.");
        } else {
            double usage = (currentVisitors * 100.0) / capacity;
            System.out.println("Park Usage: " + String.format("%.2f", usage) + "%");
            System.out.println("Visitors per bench: " + (currentVisitors / numberOfBenches));
        }
    }

    // METHOD OVERLOADING
    public void organizeEvent(String eventName) {
        System.out.println("Event '" + eventName + "' organized at " + name);
        System.out.println("Park Type: " + parkType);
    }

    public void organizeEvent(String eventName, int expectedVisitors) {
        if (expectedVisitors <= capacity) {
            System.out.println("Event '" + eventName + "' approved");
            System.out.println("Expected Visitors: " + expectedVisitors);
            System.out.println("Park Capacity: " + capacity);
        } else {
            System.out.println("Event '" + eventName + "' exceeds park capacity!");
            System.out.println("Maximum capacity: " + capacity);
        }
    }

    public void organizeEvent(String eventName, int expectedVisitors, String date) {
        organizeEvent(eventName, expectedVisitors);
        System.out.println("Scheduled Date: " + date);
    }
}