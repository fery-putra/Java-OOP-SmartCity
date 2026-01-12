package smartcity;

/**
 * MODULE 3: Building Base Class with Constructors and Inheritance

 * This class demonstrates:
 * - Base class for inheritance
 * - Multiple constructors (constructor overloading)
 * - Protected members for subclass access
 * - Methods that can be overridden
 */
public class Building {

    // Protected attributes - accessible by subclasses
    protected String name;
    protected String type;
    protected int floors;
    protected double area;
    protected boolean isOperational;
    protected int capacity;
    protected String buildingId;
    protected double maintenanceCost;

    // Private static attribute
    private static int totalBuildingsCreated = 0;
    private static final String CITY_NAME = "Smart Metro City";

    // CONSTRUCTOR OVERLOADING

    // Constructor 1: Default constructor (no parameters)
    public Building() {
        totalBuildingsCreated++;
        this.buildingId = "BLD-" + totalBuildingsCreated;
        this.maintenanceCost = 0.0;
        this.isOperational = true;
        System.out.println("Building default constructor called");
    }

    // Constructor 2: Constructor with basic parameters
    public Building(String name, String type) {
        this(); // Call default constructor (constructor chaining)
        this.name = name;
        this.type = type;
        System.out.println("Building(name, type) constructor called");
    }

    // Constructor 3: Constructor with more parameters
    public Building(String name, String type, int floors, double area) {
        this(name, type); // Call two-parameter constructor (constructor chaining)
        this.floors = floors;
        this.area = area;
        System.out.println("Building(name, type, floors, area) constructor called");
    }

    // Constructor 4: Constructor with all parameters
    public Building(String name, String type, int floors, double area, int capacity, boolean isOperational) {
        this(name, type, floors, area); // Call four-parameter constructor
        this.capacity = capacity;
        this.isOperational = isOperational;
        System.out.println("Building(all parameters) constructor called");
    }

    // Getters
    public String getName() { return name; }
    public String getType() { return type; }
    public int getFloors() { return floors; }
    public double getArea() { return area; }
    public boolean isOperational() { return isOperational; }
    public int getCapacity() { return capacity; }
    public String getBuildingId() { return buildingId; }
    public double getMaintenanceCost() { return maintenanceCost; }

    // Setters
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void setType(String type) {
        if (type != null && !type.trim().isEmpty()) {
            this.type = type;
        }
    }

    public void setFloors(int floors) {
        if (floors > 0) {
            this.floors = floors;
        }
    }

    public void setArea(double area) {
        if (area > 0) {
            this.area = area;
        }
    }

    public void setOperational(boolean operational) {
        isOperational = operational;
    }

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        }
    }

    public void setMaintenanceCost(double cost) {
        if (cost >= 0) {
            this.maintenanceCost = cost;
        }
    }

    // Static methods
    public static int getTotalBuildingsCreated() {
        return totalBuildingsCreated;
    }

    public static String getCityName() {
        return CITY_NAME;
    }

    // Method that can be OVERRIDDEN by subclasses
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

    // METHOD OVERLOADING
    public void displayInfo(boolean showDetailed) {
        displayInfo();
        if (showDetailed) {
            System.out.println("Total Area: " + calculateTotalArea() + " sq meters");
            System.out.println("Maintenance Cost: $" + maintenanceCost);
        }
    }

    // Method that can be OVERRIDDEN
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

    // Public method
    public double calculateTotalArea() {
        return area * floors;
    }

    // Method that can be OVERRIDDEN by subclasses
    public void performMaintenance() {
        System.out.println("\n=== Maintenance Check for " + name + " ===");
        System.out.println("- General building inspection");
        System.out.println("- Checking basic utilities");
        updateMaintenanceCost();
        System.out.println("Maintenance completed! Cost: $" + maintenanceCost);
    }

    // Protected method for subclasses
    protected void updateMaintenanceCost() {
        maintenanceCost = calculateTotalArea() * 0.5;
    }

    // Method that will be OVERRIDDEN by subclasses to show specific services
    public void showServices() {
        System.out.println("\nGeneral building services");
    }
}