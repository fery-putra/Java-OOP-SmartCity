package smartcity;

/**
 * MODULE 2: Building Class with Encapsulation

 * This class demonstrates:
 * - Private attributes (encapsulation)
 * - Public getter and setter methods
 * - Method overloading
 * - Protected methods
 * - Static attributes and methods
 */
public class Building {

    // ENCAPSULATION: Private attributes - can only be accessed through getters/setters
    private String name;
    private String type;
    private int floors;
    private double area;
    private boolean isOperational;
    private int capacity;
    private String buildingId;

    // Protected attribute - accessible in subclasses (will be used in Module 3)
    protected double maintenanceCost;

    // STATIC ATTRIBUTE: Shared across all Building objects
    private static int totalBuildingsCreated = 0;
    private static final String CITY_NAME = "Smart Metro City"; // Static final constant

    // Constructor (no parameters)
    public Building() {
        totalBuildingsCreated++;
        this.buildingId = "BLD-" + totalBuildingsCreated;
        this.maintenanceCost = 0.0;
    }

    // GETTER METHODS: Provide read access to private attributes

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getFloors() {
        return floors;
    }

    public double getArea() {
        return area;
    }

    public boolean isOperational() {
        return isOperational;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public double getMaintenanceCost() {
        return maintenanceCost;
    }

    // SETTER METHODS: Provide controlled write access to private attributes

    public void setName(String name) {
        // Validation in setter
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid name!");
        }
    }

    public void setType(String type) {
        if (type != null && !type.trim().isEmpty()) {
            this.type = type;
        } else {
            System.out.println("Invalid type!");
        }
    }

    public void setFloors(int floors) {
        // Validation: floors must be positive
        if (floors > 0) {
            this.floors = floors;
        } else {
            System.out.println("Floors must be greater than 0!");
        }
    }

    public void setArea(double area) {
        // Validation: area must be positive
        if (area > 0) {
            this.area = area;
        } else {
            System.out.println("Area must be greater than 0!");
        }
    }

    public void setOperational(boolean operational) {
        isOperational = operational;
    }

    public void setCapacity(int capacity) {
        // Validation: capacity must be positive
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            System.out.println("Capacity must be greater than 0!");
        }
    }

    public void setMaintenanceCost(double cost) {
        if (cost >= 0) {
            this.maintenanceCost = cost;
        } else {
            System.out.println("Maintenance cost cannot be negative!");
        }
    }

    // STATIC METHOD: Can be called without creating an object
    public static int getTotalBuildingsCreated() {
        return totalBuildingsCreated;
    }

    // STATIC METHOD: Get city name
    public static String getCityName() {
        return CITY_NAME;
    }

    // METHOD OVERLOADING: Same method name, different parameters

    // Version 1: Display basic info
    public void displayInfo() {
        System.out.println("\n=== Building Information ===");
        System.out.println("Building ID: " + buildingId);
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Floors: " + floors);
        System.out.println("Area per floor: " + area + " sq meters");
        System.out.println("Status: " + (isOperational ? "Operational" : "Under Maintenance"));
        System.out.println("Capacity: " + capacity + " people");
    }

    // Version 2: Display info with additional detail flag (METHOD OVERLOADING)
    public void displayInfo(boolean showDetailed) {
        displayInfo(); // Call the basic version

        if (showDetailed) {
            System.out.println("Total Area: " + calculateTotalArea() + " sq meters");
            System.out.println("Maintenance Cost: $" + maintenanceCost);
            System.out.println("City: " + CITY_NAME);
        }
    }

    // Version 3: Display info with custom header (METHOD OVERLOADING)
    public void displayInfo(String customHeader) {
        System.out.println("\n=== " + customHeader + " ===");
        System.out.println("Building ID: " + buildingId);
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Floors: " + floors);
        System.out.println("Capacity: " + capacity + " people");
    }

    // METHOD OVERLOADING: Calculate efficiency with different parameters

    // Version 1: Calculate with current occupancy only
    public void calculateEfficiency(int currentOccupancy) {
        if (currentOccupancy > capacity) {
            System.out.println("WARNING: Building is overcrowded!");
        } else if (currentOccupancy == 0) {
            System.out.println("Building is empty.");
        } else {
            double efficiency = (currentOccupancy * 100.0) / capacity;
            System.out.println("Building Efficiency: " + String.format("%.2f", efficiency) + "%");
        }
    }

    // Version 2: Calculate efficiency and show recommendation (METHOD OVERLOADING)
    public void calculateEfficiency(int currentOccupancy, boolean showRecommendation) {
        calculateEfficiency(currentOccupancy); // Call version 1

        if (showRecommendation) {
            double efficiency = (currentOccupancy * 100.0) / capacity;
            System.out.println("\nRecommendation:");
            if (efficiency < 50) {
                System.out.println("- Building is underutilized. Consider reducing operational costs.");
            } else if (efficiency >= 50 && efficiency < 80) {
                System.out.println("- Building utilization is optimal.");
            } else if (efficiency >= 80 && efficiency < 100) {
                System.out.println("- Building is highly utilized. Monitor closely.");
            } else {
                System.out.println("- Building is at/over capacity. Immediate action required!");
            }
        }
    }

    // Version 3: Calculate efficiency for specific floor (METHOD OVERLOADING)
    public void calculateEfficiency(int currentOccupancy, int floorNumber) {
        if (floorNumber > 0 && floorNumber <= floors) {
            int floorCapacity = capacity / floors;
            double efficiency = (currentOccupancy * 100.0) / floorCapacity;
            System.out.println("Floor " + floorNumber + " Efficiency: " +
                    String.format("%.2f", efficiency) + "%");
        } else {
            System.out.println("Invalid floor number!");
        }
    }

    // Public method to calculate total area
    public double calculateTotalArea() {
        return area * floors;
    }

    // PROTECTED METHOD: Can be accessed by subclasses
    protected void updateMaintenanceCost() {
        // Calculate maintenance cost based on area and floors
        maintenanceCost = calculateTotalArea() * 0.5; // $0.5 per sq meter
    }

    // Public method for maintenance
    public void performMaintenance() {
        System.out.println("\n=== Maintenance Check for " + name + " ===");

        switch (type.toLowerCase()) {
            case "hospital":
                System.out.println("- Checking medical equipment");
                System.out.println("- Sanitizing all areas");
                System.out.println("- Testing emergency systems");
                break;
            case "school":
                System.out.println("- Inspecting classrooms");
                System.out.println("- Checking educational equipment");
                System.out.println("- Testing fire safety systems");
                break;
            case "office":
                System.out.println("- Checking HVAC systems");
                System.out.println("- Testing network infrastructure");
                System.out.println("- Inspecting elevators");
                break;
            case "park":
                System.out.println("- Maintaining green spaces");
                System.out.println("- Checking playground equipment");
                System.out.println("- Cleaning pathways");
                break;
            default:
                System.out.println("- General building inspection");
                System.out.println("- Checking basic utilities");
                break;
        }

        updateMaintenanceCost(); // Update cost after maintenance
        System.out.println("Maintenance completed! Cost: $" + maintenanceCost);
    }

    // STATIC METHOD: Reset building counter (for demonstration)
    public static void resetBuildingCounter() {
        totalBuildingsCreated = 0;
        System.out.println("Building counter has been reset.");
    }
}