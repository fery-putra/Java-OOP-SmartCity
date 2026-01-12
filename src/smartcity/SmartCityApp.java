package smartcity;

import java.util.Scanner;

/**
 * MODULE 2: Smart City Application

 * This application demonstrates:
 * - Using classes with proper encapsulation
 * - Accessing private attributes through getters/setters
 * - Using static methods and attributes
 * - Method overloading in action
 */
public class SmartCityApp {

    // STATIC METHOD: Create Building from user input
    private static Building createBuilding(Scanner scanner) {
        Building building = new Building(); // Create New Building Object

        System.out.print("\nEnter building name: ");
        String name = scanner.nextLine();
        building.setName(name); // Use setter method

        System.out.print("Enter building type (Hospital/School/Office/Park): ");
        String type = scanner.nextLine();
        building.setType(type); // Use setter method

        System.out.print("Enter number of floors: ");
        int floors = scanner.nextInt();
        building.setFloors(floors); // Use Setter Method (with validation)

        System.out.print("Enter area per floor (sq meters): ");
        double area = scanner.nextDouble();
        building.setArea(area); // Use Setter Method (with validation)

        System.out.print("Enter capacity (number of people): ");
        int capacity = scanner.nextInt();
        building.setCapacity(capacity); // Use Setter Method (with validation)

        System.out.print("Is building operational? (true/false): ");
        boolean operational = scanner.nextBoolean();
        building.setOperational(operational); // Use setter method

        scanner.nextLine(); // Consume newline

        System.out.println("\nBuilding added successfully!");
        System.out.println("Building ID: " + building.getBuildingId()); // Use getter method

        return building;
    }

    // STATIC METHOD: Update building information
    private static void updateBuilding(Scanner scanner, Building building) {
        System.out.println("\n=== Update Building Information ===");
        System.out.println("Current Building: " + building.getName());
        System.out.println("\n1. Update Name");
        System.out.println("2. Update Floors");
        System.out.println("3. Update Area");
        System.out.println("4. Update Capacity");
        System.out.println("5. Toggle Operational Status");
        System.out.println("6. Cancel");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                building.setName(newName); // Setter validates the input
                System.out.println("Name updated successfully!");
                break;

            case 2:
                System.out.print("Enter new number of floors: ");
                int newFloors = scanner.nextInt();
                building.setFloors(newFloors); // Setter validates the input
                System.out.println("Floors updated successfully!");
                break;

            case 3:
                System.out.print("Enter new area per floor: ");
                double newArea = scanner.nextDouble();
                building.setArea(newArea); // Setter validates the input
                System.out.println("Area updated successfully!");
                break;

            case 4:
                System.out.print("Enter new capacity: ");
                int newCapacity = scanner.nextInt();
                building.setCapacity(newCapacity); // Setter validates the input
                System.out.println("Capacity updated successfully!");
                break;

            case 5:
                // Toggle operational status
                building.setOperational(!building.isOperational());
                System.out.println("Operational status updated to: " +
                        (building.isOperational() ? "Operational" : "Under Maintenance"));
                break;

            case 6:
                System.out.println("Update cancelled.");
                break;

            default:
                System.out.println("Invalid choice!");
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Array to store buildings
        Building[] buildings = new Building[CityManager.getMaxBuildings()]; // Use static method
        int buildingCount = 0;

        boolean running = true;

        System.out.println("Welcome to " + Building.getCityName() + "!"); // Use static method

        while (running) {
            CityManager.displayMenu(); // Use static method from CityManager
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add New Building
                    if (CityManager.isCityFull(buildingCount)) { // Use static method
                        System.out.println("\nCity is full! Cannot add more buildings.");
                        System.out.println("Maximum allowed: " + CityManager.getMaxBuildings());
                    } else {
                        buildings[buildingCount] = createBuilding(scanner);
                        buildingCount++;
                    }
                    break;

                case 2: // Display All Buildings
                    if (buildingCount == 0) {
                        System.out.println("\nNo buildings available!");
                    } else {
                        System.out.println("\nDisplay options:");
                        System.out.println("1. Basic information");
                        System.out.println("2. Detailed information");
                        System.out.println("3. Filter by type");
                        System.out.print("Choose option: ");
                        int displayChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (displayChoice) {
                            case 1:
                                // METHOD OVERLOADING: Version 1
                                CityManager.displayAllBuildings(buildings, buildingCount);
                                break;
                            case 2:
                                // METHOD OVERLOADING: Version 2 with detailed flag
                                CityManager.displayAllBuildings(buildings, buildingCount, true);
                                break;
                            case 3:
                                System.out.print("Enter building type to filter: ");
                                String filterType = scanner.nextLine();
                                // METHOD OVERLOADING: Version 3 with type filter
                                CityManager.displayAllBuildings(buildings, buildingCount, filterType);
                                break;
                            default:
                                System.out.println("Invalid option!");
                        }
                    }
                    break;

                case 3: // Display Building Details
                    if (buildingCount == 0) {
                        System.out.println("\nNo buildings available!");
                    } else {
                        System.out.print("\nEnter building number (1-" + buildingCount + "): ");
                        int buildingNum = scanner.nextInt();
                        scanner.nextLine();

                        if (CityManager.isValidBuildingNumber(buildingNum, buildingCount)) {
                            Building selected = buildings[buildingNum - 1];

                            System.out.println("\nDisplay options:");
                            System.out.println("1. Basic info");
                            System.out.println("2. Detailed info");
                            System.out.println("3. Custom header");
                            System.out.print("Choose option: ");
                            int detailChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (detailChoice) {
                                case 1:
                                    // METHOD OVERLOADING: displayInfo() - no parameters
                                    selected.displayInfo();
                                    break;
                                case 2:
                                    // METHOD OVERLOADING: displayInfo(boolean)
                                    selected.displayInfo(true);
                                    break;
                                case 3:
                                    System.out.print("Enter custom header: ");
                                    String header = scanner.nextLine();
                                    // METHOD OVERLOADING: displayInfo(String)
                                    selected.displayInfo(header);
                                    break;
                                default:
                                    System.out.println("Invalid option!");
                            }
                        } else {
                            System.out.println("Invalid building number!");
                        }
                    }
                    break;

                case 4: // Check Building Efficiency
                    if (buildingCount == 0) {
                        System.out.println("\nNo buildings available!");
                    } else {
                        System.out.print("\nEnter building number (1-" + buildingCount + "): ");
                        int buildingNum = scanner.nextInt();

                        if (CityManager.isValidBuildingNumber(buildingNum, buildingCount)) {
                            Building selected = buildings[buildingNum - 1];

                            System.out.println("\nCheck options:");
                            System.out.println("1. Overall building efficiency");
                            System.out.println("2. Efficiency with recommendation");
                            System.out.println("3. Specific floor efficiency");
                            System.out.print("Choose option: ");
                            int effChoice = scanner.nextInt();

                            System.out.print("Enter current occupancy: ");
                            int occupancy = scanner.nextInt();

                            switch (effChoice) {
                                case 1:
                                    // METHOD OVERLOADING: calculateEfficiency(int)
                                    selected.calculateEfficiency(occupancy);
                                    break;
                                case 2:
                                    // METHOD OVERLOADING: calculateEfficiency(int, boolean)
                                    selected.calculateEfficiency(occupancy, true);
                                    break;
                                case 3:
                                    System.out.print("Enter floor number: ");
                                    int floorNum = scanner.nextInt();
                                    // METHOD OVERLOADING: calculateEfficiency(int, int)
                                    selected.calculateEfficiency(occupancy, floorNum);
                                    break;
                                default:
                                    System.out.println("Invalid option!");
                            }
                        } else {
                            System.out.println("Invalid building number!");
                        }
                    }
                    break;

                case 5: // Perform Maintenance
                    if (buildingCount == 0) {
                        System.out.println("\nNo buildings available!");
                    } else {
                        System.out.print("\nEnter building number (1-" + buildingCount + "): ");
                        int buildingNum = scanner.nextInt();

                        if (CityManager.isValidBuildingNumber(buildingNum, buildingCount)) {
                            buildings[buildingNum - 1].performMaintenance();
                        } else {
                            System.out.println("Invalid building number!");
                        }
                    }
                    break;

                case 6: // Calculate City Statistics
                    if (buildingCount == 0) {
                        System.out.println("\nNo buildings available!");
                    } else {
                        System.out.println("\nStatistics options:");
                        System.out.println("1. Basic statistics");
                        System.out.println("2. Statistics with category breakdown");
                        System.out.print("Choose option: ");
                        int statsChoice = scanner.nextInt();

                        switch (statsChoice) {
                            case 1:
                                // METHOD OVERLOADING: Version 1
                                CityManager.calculateCityStats(buildings, buildingCount);
                                break;
                            case 2:
                                // METHOD OVERLOADING: Version 2 with categories
                                CityManager.calculateCityStats(buildings, buildingCount, true);
                                break;
                            default:
                                System.out.println("Invalid option!");
                        }
                    }
                    break;

                case 7: // Update Building Information
                    if (buildingCount == 0) {
                        System.out.println("\nNo buildings available!");
                    } else {
                        System.out.print("\nEnter building number (1-" + buildingCount + "): ");
                        int buildingNum = scanner.nextInt();
                        scanner.nextLine();

                        if (CityManager.isValidBuildingNumber(buildingNum, buildingCount)) {
                            updateBuilding(scanner, buildings[buildingNum - 1]);
                        } else {
                            System.out.println("Invalid building number!");
                        }
                    }
                    break;

                case 8: // Exit
                    System.out.println("\n====== City Summary ======");
                    System.out.println("Total buildings created: " + Building.getTotalBuildingsCreated());
                    System.out.println("Thank you for using Smart City Management System!");
                    running = false;
                    break;

                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
        }

        scanner.close();
    }
}