package smartcity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * MODULE 5: Smart City Application - Exception Handling & ArrayList
 *
 * This application demonstrates:
 * - TRY-CATCH blocks for exception handling
 * - CUSTOM EXCEPTIONS (throwing and catching)
 * - ARRAYLIST methods: add, get, set, remove, size, isEmpty
 * - ITERATOR usage
 * - ENHANCED FOR LOOP (for-each)
 * - Multiple catch blocks
 * - Finally blocks
 * - Exception propagation
 */
public class SmartCityApp {

    // Use BuildingManager which uses ArrayList
    private static BuildingManager buildingManager = new BuildingManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║   SMART CITY MANAGEMENT SYSTEM - v5.0    ║");
        System.out.println("║  Exception Handling & ArrayList Demo     ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.println("\nCity: " + AbstractBuilding.getCityName());

        while (running) {
            try {
                displayMainMenu();
                int choice = getUserChoice();

                // TRY-CATCH for handling menu operations
                try {
                    switch (choice) {
                        case 1:
                            addHospital();
                            break;
                        case 2:
                            addSchool();
                            break;
                        case 3:
                            addOffice();
                            break;
                        case 4:
                            addPark();
                            break;
                        case 5:
                            displayAllBuildings();
                            break;
                        case 6:
                            displaySpecificBuilding();
                            break;
                        case 7:
                            searchBuildingByName();
                            break;
                        case 8:
                            searchBuildingById();
                            break;
                        case 9:
                            removeBuilding();
                            break;
                        case 10:
                            updateBuildingStatus();
                            break;
                        case 11:
                            filterByType();
                            break;
                        case 12:
                            filterByStatus();
                            break;
                        case 13:
                            checkOccupancy();
                            break;
                        case 14:
                            requestBuildingService();
                            break;
                        case 15:
                            displayWithIterator();
                            break;
                        case 16:
                            calculateMaintenanceCosts();
                            break;
                        case 17:
                            displayStatistics();
                            break;
                        case 18:
                            performBulkOperations();
                            break;
                        case 19:
                            running = false;
                            displayExitMessage();
                            break;
                        default:
                            System.out.println("\n⚠ Invalid choice! Please try again.");
                    }

                } catch (CityCapacityException e) {
                    // CATCH custom exception
                    System.err.println("\n❌ CITY CAPACITY ERROR:");
                    System.err.println(e.getDetailedMessage());

                } catch (BuildingNotFoundException e) {
                    // CATCH custom exception
                    System.err.println("\n❌ BUILDING NOT FOUND:");
                    System.err.println(e.getDetailedMessage());

                } catch (InvalidBuildingStatusException e) {
                    // CATCH custom exception
                    System.err.println("\n❌ INVALID STATUS ERROR:");
                    System.err.println(e.getDetailedMessage());

                } catch (BuildingCapacityException e) {
                    // CATCH custom exception
                    System.err.println("\n❌ CAPACITY ERROR:");
                    System.err.println(e.getDetailedMessage());

                } catch (ServiceUnavailableException e) {
                    // CATCH custom exception
                    System.err.println("\n❌ SERVICE ERROR:");
                    System.err.println(e.getDetailedMessage());

                } catch (Exception e) {
                    // CATCH any other unexpected exceptions
                    System.err.println("\n❌ UNEXPECTED ERROR: " + e.getMessage());
                    e.printStackTrace();
                }

            } catch (InputMismatchException e) {
                // CATCH invalid input type
                System.err.println("\n❌ INPUT ERROR: Please enter a valid number!");
                scanner.nextLine(); // Clear invalid input

            } catch (Exception e) {
                // CATCH any other exceptions in main loop
                System.err.println("\n❌ SYSTEM ERROR: " + e.getMessage());
            }
        }

        // FINALLY equivalent - cleanup
        scanner.close();
    }

    /**
     * Get user choice with TRY-CATCH
     */
    private static int getUserChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            return choice;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear buffer
            throw e; // Re-throw to be handled by caller
        }
    }

    /**
     * Add hospital with exception handling
     * Demonstrates: TRY-CATCH, throwing exceptions
     */
    private static void addHospital() throws CityCapacityException {
        System.out.println("\n=== Add New Hospital ===");

        try {
            System.out.print("Hospital Name: ");
            String name = scanner.nextLine();

            // Validate input
            if (name == null || name.trim().isEmpty()) {
                throw new InvalidBuildingDataException(
                        "Hospital name cannot be empty",
                        "name"
                );
            }

            System.out.print("Number of Floors: ");
            int floors = scanner.nextInt();

            if (floors <= 0) {
                throw new InvalidBuildingDataException(
                        "Number of floors must be greater than 0",
                        "floors",
                        floors
                );
            }

            System.out.print("Area per Floor (sq meters): ");
            double area = scanner.nextDouble();

            if (area <= 0) {
                throw new InvalidBuildingDataException(
                        "Area must be greater than 0",
                        "area",
                        area
                );
            }

            System.out.print("Total Capacity: ");
            int capacity = scanner.nextInt();
            scanner.nextLine();

            if (capacity <= 0) {
                throw new InvalidBuildingDataException(
                        "Capacity must be greater than 0",
                        "capacity",
                        capacity
                );
            }

            ModernHospital hospital = new ModernHospital(name, floors, area, capacity);

            // ArrayList ADD method - may throw CityCapacityException
            buildingManager.addBuilding(hospital);

            System.out.println("License Number: " + hospital.getLicenseNumber());

        } catch (InvalidBuildingDataException e) {
            System.err.println("\n❌ INVALID DATA:");
            System.err.println(e.getDetailedMessage());
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear buffer
            System.err.println("\n❌ Please enter valid numeric values!");
        } catch (CityCapacityException e) {
            // Re-throw to be handled by main
            throw e;
        }
    }

    /**
     * Add school with exception handling
     */
    private static void addSchool() throws CityCapacityException {
        System.out.println("\n=== Add New School ===");

        try {
            System.out.print("School Name: ");
            String name = scanner.nextLine();

            if (name == null || name.trim().isEmpty()) {
                throw new InvalidBuildingDataException("School name cannot be empty", "name");
            }

            System.out.print("Number of Floors: ");
            int floors = scanner.nextInt();

            System.out.print("Area per Floor (sq meters): ");
            double area = scanner.nextDouble();

            System.out.print("Total Capacity: ");
            int capacity = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Education Level: ");
            String level = scanner.nextLine();

            ModernSchool school = new ModernSchool(name, floors, area, capacity, level);
            buildingManager.addBuilding(school); // ArrayList ADD

            System.out.println("Accreditation ID: " + school.getAccreditationId());

        } catch (InvalidBuildingDataException e) {
            System.err.println("\n❌ " + e.getDetailedMessage());
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.err.println("\n❌ Invalid input type!");
        }
    }

    /**
     * Add office with exception handling
     */
    private static void addOffice() throws CityCapacityException {
        System.out.println("\n=== Add New Office ===");

        try {
            System.out.print("Building Name: ");
            String name = scanner.nextLine();

            System.out.print("Number of Floors: ");
            int floors = scanner.nextInt();

            System.out.print("Area per Floor: ");
            double area = scanner.nextDouble();

            System.out.print("Total Capacity: ");
            int capacity = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Company Name: ");
            String company = scanner.nextLine();

            System.out.print("Business Type: ");
            String businessType = scanner.nextLine();

            ModernOffice office = new ModernOffice(name, floors, area, capacity, company, businessType);
            buildingManager.addBuilding(office); // ArrayList ADD

        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.err.println("\n❌ Invalid input!");
        }
    }

    /**
     * Add park with exception handling
     */
    private static void addPark() throws CityCapacityException {
        System.out.println("\n=== Add New Park ===");

        try {
            System.out.print("Park Name: ");
            String name = scanner.nextLine();

            System.out.print("Total Area: ");
            double area = scanner.nextDouble();

            System.out.print("Visitor Capacity: ");
            int capacity = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Park Type: ");
            String parkType = scanner.nextLine();

            PublicPark park = new PublicPark(name, area, capacity, parkType);
            buildingManager.addBuilding(park); // ArrayList ADD

        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.err.println("\n❌ Invalid input!");
        }
    }

    /**
     * Display all buildings - demonstrates ENHANCED FOR LOOP
     */
    private static void displayAllBuildings() {
        buildingManager.displayAllBuildings(); // Uses enhanced for loop internally
    }

    /**
     * Display specific building using ArrayList GET
     * Demonstrates: TRY-CATCH with ArrayList.get()
     */
    private static void displaySpecificBuilding() throws BuildingNotFoundException {
        if (buildingManager.isEmpty()) { // ArrayList ISEMPTY
            System.out.println("\nNo buildings in the city!");
            return;
        }

        try {
            System.out.print("\nEnter building number (1-" + buildingManager.getBuildingCount() + "): ");
            int num = scanner.nextInt();
            scanner.nextLine();

            // ArrayList GET - may throw exception
            AbstractBuilding building = buildingManager.getBuilding(num - 1);

            CityManager.displayBuildingFullInfo(building);

        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.err.println("\n❌ Please enter a valid number!");
        }
    }

    /**
     * Search building by name
     * Demonstrates: Exception handling with search
     */
    private static void searchBuildingByName() throws BuildingNotFoundException {
        System.out.print("\nEnter building name to search: ");
        String name = scanner.nextLine();

        try {
            AbstractBuilding building = buildingManager.findBuildingByName(name);
            System.out.println("\n✓ Building found!");
            building.displayBasicInfo();

        } catch (BuildingNotFoundException e) {
            // Re-throw with additional context
            throw new BuildingNotFoundException(
                    "Search failed: " + e.getMessage(),
                    name
            );
        }
    }

    /**
     * Search building by ID
     */
    private static void searchBuildingById() throws BuildingNotFoundException {
        System.out.print("\nEnter building ID: ");
        String id = scanner.nextLine();

        AbstractBuilding building = buildingManager.findBuildingById(id);
        System.out.println("\n✓ Building found!");
        building.displayBasicInfo();
    }

    /**
     * Remove building using ArrayList REMOVE
     * Demonstrates: TRY-CATCH with ArrayList.remove()
     */
    private static void removeBuilding() throws BuildingNotFoundException {
        if (buildingManager.isEmpty()) {
            System.out.println("\nNo buildings to remove!");
            return;
        }

        try {
            System.out.println("\nCurrent buildings: " + buildingManager.getBuildingCount());
            System.out.print("Enter building number to remove (1-" +
                    buildingManager.getBuildingCount() + "): ");
            int num = scanner.nextInt();
            scanner.nextLine();

            // ArrayList REMOVE - may throw exception
            buildingManager.removeBuilding(num - 1);

        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.err.println("\n❌ Invalid input!");
        }
    }

    /**
     * Update building status
     */
    private static void updateBuildingStatus() throws BuildingNotFoundException {
        if (buildingManager.isEmpty()) {
            System.out.println("\nNo buildings available!");
            return;
        }

        try {
            System.out.print("\nEnter building number: ");
            int num = scanner.nextInt();
            scanner.nextLine();

            AbstractBuilding building = buildingManager.getBuilding(num - 1);

            System.out.println("\nCurrent Status: " + building.getStatus().getDisplayName());
            System.out.println("\nSelect New Status:");

            BuildingStatus[] statuses = BuildingStatus.values();
            for (int i = 0; i < statuses.length; i++) {
                System.out.println((i + 1) + ". " + statuses[i].getDisplayName());
            }

            System.out.print("\nChoice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice > 0 && choice <= statuses.length) {
                building.changeStatus(statuses[choice - 1]);
            }

        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.err.println("\n❌ Invalid input!");
        }
    }

    /**
     * Filter by building type
     * Demonstrates: ArrayList filtering and ENHANCED FOR LOOP
     */
    private static void filterByType() throws BuildingNotFoundException {
        System.out.println("\nSelect Building Type:");

        BuildingType[] types = BuildingType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i].getName());
        }

        try {
            System.out.print("\nChoice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice > 0 && choice <= types.length) {
                BuildingType selectedType = types[choice - 1];

                // Returns ArrayList - may throw exception if none found
                ArrayList<AbstractBuilding> filtered =
                        buildingManager.findBuildingsByType(selectedType);

                System.out.println("\n=== " + selectedType.getName() + " Buildings ===");
                System.out.println("Found: " + filtered.size()); // ArrayList SIZE

                // ENHANCED FOR LOOP to display results
                for (AbstractBuilding building : filtered) {
                    System.out.println("- " + building.getName() + " [" + building.getBuildingId() + "]");
                }
            }

        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.err.println("\n❌ Invalid input!");
        }
    }

    /**
     * Filter by status
     */
    private static void filterByStatus() throws BuildingNotFoundException {
        System.out.println("\nSelect Status:");

        BuildingStatus[] statuses = BuildingStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.println((i + 1) + ". " + statuses[i].getDisplayName());
        }

        try {
            System.out.print("\nChoice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice > 0 && choice <= statuses.length) {
                BuildingStatus selectedStatus = statuses[choice - 1];

                ArrayList<AbstractBuilding> filtered =
                        buildingManager.findBuildingsByStatus(selectedStatus);

                System.out.println("\n=== Buildings with status: " +
                        selectedStatus.getDisplayName() + " ===");

                for (AbstractBuilding building : filtered) {
                    System.out.println("- " + building.getName());
                }
            }

        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.err.println("\n❌ Invalid input!");
        }
    }

    /**
     * Check occupancy - demonstrates custom exception handling
     */
    private static void checkOccupancy()
            throws BuildingNotFoundException, BuildingCapacityException, InvalidBuildingStatusException {

        if (buildingManager.isEmpty()) {
            System.out.println("\nNo buildings available!");
            return;
        }

        try {
            System.out.print("\nEnter building number: ");
            int num = scanner.nextInt();
            scanner.nextLine();

            AbstractBuilding building = buildingManager.getBuilding(num - 1);

            System.out.println("\nBuilding: " + building.getName());
            System.out.println("Maximum Capacity: " + building.getCapacity());
            System.out.print("Enter current occupancy: ");
            int occupancy = scanner.nextInt();
            scanner.nextLine();

            // This method throws custom exceptions
            buildingManager.validateOccupancy(building, occupancy);

        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.err.println("\n❌ Invalid input!");
        }
    }

    /**
     * Request service - demonstrates exception handling
     */
    private static void requestBuildingService()
            throws BuildingNotFoundException, ServiceUnavailableException, InvalidBuildingStatusException {

        if (buildingManager.isEmpty()) {
            System.out.println("\nNo buildings available!");
            return;
        }

        try {
            System.out.print("\nEnter building number: ");
            int num = scanner.nextInt();
            scanner.nextLine();

            AbstractBuilding building = buildingManager.getBuilding(num - 1);

            if (!(building instanceof Serviceable)) {
                throw new ServiceUnavailableException(
                        "This building does not provide services",
                        "N/A",
                        building.getName()
                );
            }

            Serviceable serviceable = (Serviceable) building;
            String[] services = serviceable.getAvailableServices();

            System.out.println("\nAvailable Services:");
            for (int i = 0; i < services.length; i++) {
                System.out.println((i + 1) + ". " + services[i]);
            }

            System.out.print("\nSelect service: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice > 0 && choice <= services.length) {
                String selectedService = services[choice - 1];
                buildingManager.requestService(building, selectedService);
            }

        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.err.println("\n❌ Invalid input!");
        }
    }

    /**
     * Display buildings using ITERATOR
     * Demonstrates: Iterator usage
     */
    private static void displayWithIterator() {
        if (buildingManager.isEmpty()) {
            System.out.println("\nNo buildings in the city!");
            return;
        }

        System.out.println("\n=== Buildings (Iterator Mode) ===");

        try {
            ArrayList<AbstractBuilding> buildings = buildingManager.getAllBuildings();

            // ITERATOR usage
            Iterator<AbstractBuilding> iterator = buildings.iterator();

            int count = 1;
            while (iterator.hasNext()) {
                AbstractBuilding building = iterator.next();
                System.out.println(count + ". " + building.getName() +
                        " [" + building.getBuildingType().getName() + "] " +
                        "- Status: " + building.getStatus().getDisplayName());
                count++;
            }

            System.out.println("\nTotal: " + buildings.size()); // ArrayList SIZE

        } catch (Exception e) {
            System.err.println("\n❌ Error displaying buildings: " + e.getMessage());
        }
    }

    /**
     * Calculate maintenance costs
     */
    private static void calculateMaintenanceCosts() {
        if (buildingManager.isEmpty()) {
            System.out.println("\nNo buildings to calculate!");
            return;
        }

        try {
            double total = buildingManager.calculateTotalMaintenance();
            int count = buildingManager.getBuildingCount();

            System.out.println("\n=== Maintenance Cost Report ===");
            System.out.println("Total Buildings: " + count);
            System.out.println("Total Cost: $" + String.format("%.2f", total));
            System.out.println("Average per Building: $" + String.format("%.2f", total / count));

        } catch (Exception e) {
            System.err.println("\n❌ Error calculating costs: " + e.getMessage());
        }
    }

    /**
     * Display statistics
     */
    private static void displayStatistics() {
        buildingManager.displayStatistics();
    }

    /**
     * Bulk operations demonstrating ArrayList operations
     */
    private static void performBulkOperations() {
        System.out.println("\n=== Bulk Operations ===");
        System.out.println("1. List all building IDs");
        System.out.println("2. Count buildings by type");
        System.out.println("3. List operational buildings");
        System.out.print("\nChoice: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            ArrayList<AbstractBuilding> buildings = buildingManager.getAllBuildings();

            switch (choice) {
                case 1:
                    System.out.println("\n=== All Building IDs ===");
                    // ENHANCED FOR LOOP
                    for (AbstractBuilding building : buildings) {
                        System.out.println(building.getBuildingId() + " - " + building.getName());
                    }
                    break;

                case 2:
                    System.out.println("\n=== Count by Type ===");
                    for (BuildingType type : BuildingType.values()) {
                        int count = 0;
                        for (AbstractBuilding building : buildings) {
                            if (building.getBuildingType() == type) {
                                count++;
                            }
                        }
                        if (count > 0) {
                            System.out.println(type.getName() + ": " + count);
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n=== Operational Buildings ===");
                    int operationalCount = 0;
                    for (AbstractBuilding building : buildings) {
                        if (building.isUsable()) {
                            System.out.println("- " + building.getName());
                            operationalCount++;
                        }
                    }
                    System.out.println("\nTotal Operational: " + operationalCount);
                    break;
            }

        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.err.println("\n❌ Invalid input!");
        } catch (Exception e) {
            System.err.println("\n❌ Error: " + e.getMessage());
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SMART CITY MANAGEMENT - MODULE 5");
        System.out.println("Buildings in city: " + buildingManager.getBuildingCount());
        System.out.println("=".repeat(60));
        System.out.println("Building Management:");
        System.out.println("  1. Add Hospital        2. Add School");
        System.out.println("  3. Add Office          4. Add Park");
        System.out.println("  5. Display All         6. Display Specific");
        System.out.println("  7. Search by Name      8. Search by ID");
        System.out.println("  9. Remove Building     10. Update Status");
        System.out.println("\nFiltering & Search:");
        System.out.println("  11. Filter by Type     12. Filter by Status");
        System.out.println("\nOperations:");
        System.out.println("  13. Check Occupancy    14. Request Service");
        System.out.println("  15. Display (Iterator) 16. Maintenance Costs");
        System.out.println("\nReports:");
        System.out.println("  17. Statistics         18. Bulk Operations");
        System.out.println("\n  19. Exit");
        System.out.println("=".repeat(60));
        System.out.print("Choice: ");
    }

    private static void displayExitMessage() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Thank you for using Smart City Management System!");
        System.out.println("Total Buildings: " + buildingManager.getBuildingCount());
        System.out.println("=".repeat(60));
    }
}