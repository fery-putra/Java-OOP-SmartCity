package smartcity;

/**
 * MODULE 2: CityManager Class

 * This class demonstrates:
 * - Static methods for utility functions
 * - Method overloading for different display options
 * - Encapsulation with private helper methods
 */
public class CityManager {

    // STATIC ATTRIBUTE: Maximum buildings allowed
    private static final int MAX_BUILDINGS = 10;

    // Private constructor to prevent instantiation (utility class)
    private CityManager() {
        // This is a utility class, should not be instantiated
    }

    // STATIC METHOD: Display menu
    public static void displayMenu() {
        System.out.println("\n====== SMART CITY MANAGEMENT SYSTEM ======");
        System.out.println("City: " + Building.getCityName());
        System.out.println("Total Buildings Created: " + Building.getTotalBuildingsCreated());
        System.out.println("\n1. Add New Building");
        System.out.println("2. Display All Buildings");
        System.out.println("3. Display Building Details");
        System.out.println("4. Check Building Efficiency");
        System.out.println("5. Perform Maintenance");
        System.out.println("6. Calculate City Statistics");
        System.out.println("7. Update Building Information");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    // STATIC METHOD: Get maximum buildings allowed
    public static int getMaxBuildings() {
        return MAX_BUILDINGS;
    }

    // METHOD OVERLOADING: Display all buildings

    // Version 1: Display basic list
    public static void displayAllBuildings(Building[] buildings, int count) {
        if (count == 0) {
            System.out.println("\nNo buildings in the city yet!");
            return;
        }

        System.out.println("\n====== ALL BUILDINGS IN " + Building.getCityName() + " ======");

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Building " + (i + 1) + " ---");
            buildings[i].displayInfo();
        }
    }

    // Version 2: Display with detailed information (METHOD OVERLOADING)
    public static void displayAllBuildings(Building[] buildings, int count, boolean detailed) {
        if (count == 0) {
            System.out.println("\nNo buildings in the city yet!");
            return;
        }

        System.out.println("\n====== ALL BUILDINGS IN " + Building.getCityName() + " ======");

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Building " + (i + 1) + " ---");
            buildings[i].displayInfo(detailed);
        }
    }

    // Version 3: Display buildings of Specific Type (METHOD OVERLOADING)
    public static void displayAllBuildings(Building[] buildings, int count, String type) {
        if (count == 0) {
            System.out.println("\nNo buildings in the city yet!");
            return;
        }

        System.out.println("\n====== " + type.toUpperCase() + " BUILDINGS ======");

        int found = 0;
        for (int i = 0; i < count; i++) {
            if (buildings[i].getType().equalsIgnoreCase(type)) {
                buildings[i].displayInfo();
                found++;
            }
        }

        if (found == 0) {
            System.out.println("No buildings of type '" + type + "' found.");
        }
    }

    // STATIC METHOD: Calculate city statistics
    public static void calculateCityStats(Building[] buildings, int count) {
        if (count == 0) {
            System.out.println("\nNo buildings to calculate statistics!");
            return;
        }

        int totalFloors = 0;
        double totalArea = 0;
        int operationalCount = 0;
        double totalMaintenanceCost = 0;

        for (int i = 0; i < count; i++) {
            totalFloors += buildings[i].getFloors();
            totalArea += buildings[i].calculateTotalArea();
            totalMaintenanceCost += buildings[i].getMaintenanceCost();

            if (buildings[i].isOperational()) {
                operationalCount++;
            }
        }

        System.out.println("\n====== CITY STATISTICS ======");
        System.out.println("City Name: " + Building.getCityName());
        System.out.println("Total Buildings: " + count);
        System.out.println("Total Floors: " + totalFloors);
        System.out.println("Total Area: " + String.format("%.2f", totalArea) + " sq meters");
        System.out.println("Operational Buildings: " + operationalCount);
        System.out.println("Under Maintenance: " + (count - operationalCount));
        System.out.println("Total Maintenance Cost: $" + String.format("%.2f", totalMaintenanceCost));

        double averageArea = totalArea / count;
        System.out.println("Average Building Area: " + String.format("%.2f", averageArea) + " sq meters");
    }

    // METHOD OVERLOADING: Calculate statistics with category breakdown
    public static void calculateCityStats(Building[] buildings, int count, boolean showCategories) {
        calculateCityStats(buildings, count); // Call basic version

        if (showCategories && count > 0) {
            System.out.println("\n=== Building Categories ===");

            // Count buildings by type
            int hospitals = 0, schools = 0, offices = 0, parks = 0, others = 0;

            for (int i = 0; i < count; i++) {
                String type = buildings[i].getType().toLowerCase();
                switch (type) {
                    case "hospital":
                        hospitals++;
                        break;
                    case "school":
                        schools++;
                        break;
                    case "office":
                        offices++;
                        break;
                    case "park":
                        parks++;
                        break;
                    default:
                        others++;
                        break;
                }
            }

            System.out.println("Hospitals: " + hospitals);
            System.out.println("Schools: " + schools);
            System.out.println("Offices: " + offices);
            System.out.println("Parks: " + parks);
            System.out.println("Others: " + others);
        }
    }

    // STATIC METHOD: Validate building number
    public static boolean isValidBuildingNumber(int buildingNum, int count) {
        return buildingNum > 0 && buildingNum <= count;
    }

    // STATIC METHOD: Check if City is full
    public static boolean isCityFull(int currentCount) {
        return currentCount >= MAX_BUILDINGS;
    }
}