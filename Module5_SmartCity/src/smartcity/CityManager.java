package smartcity;

/**
 * MODULE 4: CityManager Utility Class
 *
 * Demonstrates POLYMORPHISM through managing different building types
 */
public class CityManager {

    // STATIC FINAL constant
    private static final int MAX_BUILDINGS = 10;

    // Private constructor - utility class
    private CityManager() {}

    /**
     * Demonstrate POLYMORPHISM - method accepts AbstractBuilding
     * Can work with any subclass (ModernHospital, ModernSchool, etc.)
     */
    public static void displayBuildingFullInfo(AbstractBuilding building) {
        System.out.println("\n========================================");
        building.displayBasicInfo(); // Calls concrete method from abstract class
        building.displaySpecificInfo(); // Calls overridden abstract method
        building.displayStatusDetails(); // Calls concrete method

        // Check if building implements Serviceable interface (POLYMORPHISM)
        if (building instanceof Serviceable) {
            System.out.println("\n--- This building provides services ---");
            Serviceable serviceable = (Serviceable) building; // DOWNCASTING
            serviceable.displayServiceInfo();
        }

        // Check if building implements Accessible interface (POLYMORPHISM)
        if (building instanceof Accessible) {
            Accessible accessible = (Accessible) building; // DOWNCASTING
            accessible.displayAccessibilityInfo();
        }

        System.out.println("========================================");
    }

    /**
     * POLYMORPHISM - array of AbstractBuilding can hold any subclass
     */
    public static void displayAllBuildings(AbstractBuilding[] buildings, int count) {
        if (count == 0) {
            System.out.println("\nNo buildings in the city!");
            return;
        }

        System.out.println("\n====== ALL BUILDINGS IN " + AbstractBuilding.getCityName() + " ======");

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Building " + (i + 1) + " ---");
            // POLYMORPHISM - calls appropriate overridden method based on actual type
            buildings[i].displayBasicInfo();
        }
    }

    /**
     * Calculate total maintenance cost using POLYMORPHISM
     */
    public static void calculateTotalMaintenance(AbstractBuilding[] buildings, int count) {
        if (count == 0) {
            System.out.println("\nNo buildings to calculate!");
            return;
        }

        double totalCost = 0;

        for (int i = 0; i < count; i++) {
            // POLYMORPHISM - each building calculates cost differently
            totalCost += buildings[i].calculateMaintenanceCost();
        }

        System.out.println("\n=== Total City Maintenance Cost ===");
        System.out.println("Total Buildings: " + count);
        System.out.println("Total Maintenance Cost: $" + String.format("%.2f", totalCost));
        System.out.println("Average per Building: $" + String.format("%.2f", totalCost / count));
    }

    /**
     * Perform operations on all buildings using POLYMORPHISM
     */
    public static void performAllSpecialOperations(AbstractBuilding[] buildings, int count) {
        if (count == 0) {
            System.out.println("\nNo buildings available!");
            return;
        }

        System.out.println("\n=== Performing Special Operations on All Buildings ===");

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Building: " + buildings[i].getName() + " ---");
            // POLYMORPHISM - calls appropriate method based on actual type
            buildings[i].performSpecialOperation();
        }
    }

    /**
     * Display buildings by type using POLYMORPHISM and instanceof
     */
    public static void displayBuildingsByType(AbstractBuilding[] buildings, int count, BuildingType type) {
        System.out.println("\n=== " + type.getName() + " Buildings ===");

        int found = 0;
        for (int i = 0; i < count; i++) {
            if (buildings[i].getBuildingType() == type) { // ENUM comparison
                buildings[i].displayBasicInfo();
                found++;
            }
        }

        if (found == 0) {
            System.out.println("No buildings of this type found.");
        } else {
            System.out.println("\nTotal " + type.getName() + " buildings: " + found);
        }
    }

    /**
     * Display buildings by status using ENUM
     */
    public static void displayBuildingsByStatus(AbstractBuilding[] buildings, int count, BuildingStatus status) {
        System.out.println("\n=== Buildings with Status: " + status.getDisplayName() + " ===");

        int found = 0;
        for (int i = 0; i < count; i++) {
            if (buildings[i].getStatus() == status) { // ENUM comparison
                System.out.println((found + 1) + ". " + buildings[i].getName() +
                        " (" + buildings[i].getBuildingType().getName() + ")");
                found++;
            }
        }

        if (found == 0) {
            System.out.println("No buildings with this status.");
        }
    }

    /**
     * Display all serviceable buildings (demonstrates interface polymorphism)
     */
    public static void displayServiceableBuildings(AbstractBuilding[] buildings, int count) {
        System.out.println("\n=== Serviceable Buildings ===");

        int found = 0;
        for (int i = 0; i < count; i++) {
            // Check if building implements Serviceable interface
            if (buildings[i] instanceof Serviceable) {
                System.out.println("\n" + (found + 1) + ". " + buildings[i].getName());
                Serviceable serviceable = (Serviceable) buildings[i]; // DOWNCASTING

                String[] services = serviceable.getAvailableServices();
                System.out.println("Available Services:");
                for (int j = 0; j < services.length && j < 3; j++) {
                    System.out.println("   - " + services[j]);
                }
                System.out.println("   ... and more");

                found++;
            }
        }

        if (found == 0) {
            System.out.println("No serviceable buildings found.");
        }
    }

    /**
     * Check accessibility compliance across all buildings
     */
    public static void checkAccessibilityCompliance(AbstractBuilding[] buildings, int count) {
        System.out.println("\n=== Accessibility Compliance Report ===");

        int compliant = 0;
        int total = 0;

        for (int i = 0; i < count; i++) {
            if (buildings[i] instanceof Accessible) {
                total++;
                Accessible accessible = (Accessible) buildings[i]; // DOWNCASTING

                if (accessible.isFullyAccessible()) {
                    compliant++;
                    System.out.println("✓ " + buildings[i].getName() + " - FULLY ACCESSIBLE");
                } else {
                    System.out.println("✗ " + buildings[i].getName() + " - NEEDS IMPROVEMENT");
                }
            }
        }

        System.out.println("\nTotal Accessible Buildings: " + total);
        System.out.println("Fully Compliant: " + compliant);
        if (total > 0) {
            double percentage = (compliant * 100.0) / total;
            System.out.println("Compliance Rate: " + String.format("%.1f", percentage) + "%");
        }
    }

    /**
     * Display statistics by building priority (using ENUM)
     */
    public static void displayPriorityStatistics(AbstractBuilding[] buildings, int count) {
        System.out.println("\n=== Building Priority Statistics ===");

        int criticalCount = 0;

        for (int i = 0; i < count; i++) {
            BuildingType type = buildings[i].getBuildingType();

            if (type.isCriticalInfrastructure()) {
                criticalCount++;
            }
        }

        System.out.println("Total Buildings: " + count);
        System.out.println("Critical Infrastructure: " + criticalCount);
        System.out.println("Regular Buildings: " + (count - criticalCount));
    }

    public static int getMaxBuildings() {
        return MAX_BUILDINGS;
    }
}