package smartcity;

/**
 * MODULE 4: AbstractBuilding - Abstract Base Class
 *
 * This demonstrates:
 * - ABSTRACT CLASS (cannot be instantiated)
 * - ABSTRACT METHODS (must be implemented by subclasses)
 * - CONCRETE METHODS (have implementation)
 * - FINAL VARIABLE
 * - STATIC FINAL constant
 * - Protected members for subclasses
 */
public abstract class AbstractBuilding {

    // STATIC FINAL constant (shared by all buildings, cannot be changed)
    protected static final String CITY_NAME = "Smart Metro City";
    protected static final double MAINTENANCE_BASE_RATE = 0.5;

    // Protected attributes for subclasses
    protected String buildingId;
    protected String name;
    protected BuildingType buildingType; // ENUM usage
    protected BuildingStatus status; // ENUM usage
    protected int floors;
    protected double area;
    protected int capacity;

    // FINAL VARIABLE (can be set once, usually in constructor)
    protected final String constructionDate;

    // Static counter
    private static int buildingCounter = 0;

    // Constructor
    public AbstractBuilding(String name, BuildingType buildingType) {
        buildingCounter++;
        this.buildingId = "BLD-" + buildingCounter;
        this.name = name;
        this.buildingType = buildingType;
        this.status = BuildingStatus.OPERATIONAL;
        this.constructionDate = java.time.LocalDate.now().toString(); // FINAL variable set once
    }

    // ABSTRACT METHODS - subclasses MUST implement these

    /**
     * Abstract method to calculate maintenance cost
     * Each building type calculates this differently
     */
    public abstract double calculateMaintenanceCost();

    /**
     * Abstract method to display building-specific information
     */
    public abstract void displaySpecificInfo();

    /**
     * Abstract method to perform type-specific operations
     */
    public abstract void performSpecialOperation();

    // CONCRETE METHODS - have implementation, can be used by all subclasses

    /**
     * Concrete method to display basic information
     */
    public void displayBasicInfo() {
        System.out.println("\n=== Building Information ===");
        System.out.println("ID: " + buildingId);
        System.out.println("Name: " + name);
        System.out.println("Type: " + buildingType.getName()); // Using ENUM
        System.out.println("Category: " + buildingType.getCategory()); // Using ENUM
        System.out.println("Status: " + status.getDisplayName()); // Using ENUM
        System.out.println("Construction Date: " + constructionDate); // FINAL variable
        System.out.println("Floors: " + floors);
        System.out.println("Total Area: " + calculateTotalArea() + " sq meters");
        System.out.println("Capacity: " + capacity);
    }

    /**
     * Concrete method to change building status
     */
    public void changeStatus(BuildingStatus newStatus) {
        System.out.println("\nChanging status from " + status.getDisplayName() +
                " to " + newStatus.getDisplayName());
        this.status = newStatus;

        // ENUM in SWITCH-CASE
        switch (newStatus) {
            case OPERATIONAL:
                System.out.println("Building is now fully operational!");
                break;
            case UNDER_MAINTENANCE:
                System.out.println("Building maintenance has started.");
                break;
            case UNDER_CONSTRUCTION:
                System.out.println("Construction work in progress.");
                break;
            case CLOSED:
                System.out.println("Building has been closed.");
                break;
            case EMERGENCY:
                System.out.println("EMERGENCY MODE ACTIVATED!");
                break;
        }

        System.out.println("Recommended Action: " + newStatus.getRecommendedAction());
    }

    /**
     * Concrete method to calculate total area
     */
    public double calculateTotalArea() {
        return area * floors;
    }

    /**
     * Concrete method to check if building is usable
     */
    public boolean isUsable() {
        return status.isUsable();
    }

    /**
     * Concrete method to display status details
     */
    public void displayStatusDetails() {
        System.out.println("\n=== Status Details ===");
        System.out.println("Current Status: " + status.getDisplayName());
        System.out.println("Description: " + status.getDescription());
        System.out.println("Operational Level: " + (status.getOperationalLevel() * 100) + "%");
        System.out.println("Color Code: " + status.getColorCode());
        System.out.println("Usable: " + (status.isUsable() ? "Yes" : "No"));
    }

    // Getters
    public String getBuildingId() { return buildingId; }
    public String getName() { return name; }
    public BuildingType getBuildingType() { return buildingType; }
    public BuildingStatus getStatus() { return status; }
    public int getFloors() { return floors; }
    public double getArea() { return area; }
    public int getCapacity() { return capacity; }
    public String getConstructionDate() { return constructionDate; } // FINAL variable getter

    // Setters (note: constructionDate has no setter because it's FINAL)
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
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

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        }
    }

    // STATIC FINAL constant getter
    public static String getCityName() {
        return CITY_NAME;
    }

    public static int getBuildingCounter() {
        return buildingCounter;
    }
}