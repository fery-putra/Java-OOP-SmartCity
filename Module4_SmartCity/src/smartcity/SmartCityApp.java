package smartcity;

import java.util.Scanner;

/**
 * MODULE 4: Smart City Application - Advanced OOP
 *
 * This application demonstrates:
 * - POLYMORPHISM (using AbstractBuilding reference for different types)
 * - UPCASTING (automatic casting to parent type)
 * - DOWNCASTING (explicit casting to child type)
 * - ENUM usage and ENUM in switch-case
 * - FINAL variables, methods, and classes
 * - ABSTRACT classes and methods
 * - INTERFACES and multiple implementation
 * - STATIC FINAL constants
 */
public class SmartCityApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // POLYMORPHISM - array of AbstractBuilding can hold any subclass
        AbstractBuilding[] buildings = new AbstractBuilding[CityManager.getMaxBuildings()];
        int buildingCount = 0;
        boolean running = true;

        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║   SMART CITY MANAGEMENT SYSTEM - v4.0    ║");
        System.out.println("║      Advanced OOP Demonstration          ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.println("\nCity: " + AbstractBuilding.getCityName());

        while (running) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // Add Hospital
                    if (buildingCount < CityManager.getMaxBuildings()) {
                        // UPCASTING - ModernHospital automatically cast to AbstractBuilding
                        buildings[buildingCount++] = createHospital(scanner);
                    } else {
                        System.out.println("\n⚠ City is full! Maximum buildings reached.");
                    }
                    break;

                case 2: // Add School
                    if (buildingCount < CityManager.getMaxBuildings()) {
                        // UPCASTING
                        buildings[buildingCount++] = createSchool(scanner);
                    } else {
                        System.out.println("\n⚠ City is full!");
                    }
                    break;

                case 3: // Add Office
                    if (buildingCount < CityManager.getMaxBuildings()) {
                        // UPCASTING
                        buildings[buildingCount++] = createOffice(scanner);
                    } else {
                        System.out.println("\n⚠ City is full!");
                    }
                    break;

                case 4: // Add Park
                    if (buildingCount < CityManager.getMaxBuildings()) {
                        // UPCASTING
                        buildings[buildingCount++] = createPark(scanner);
                    } else {
                        System.out.println("\n⚠ City is full!");
                    }
                    break;

                case 5: // Display All Buildings
                    CityManager.displayAllBuildings(buildings, buildingCount);
                    break;

                case 6: // Display Specific Building
                    if (buildingCount > 0) {
                        System.out.print("\nEnter building number (1-" + buildingCount + "): ");
                        int num = scanner.nextInt();
                        scanner.nextLine();

                        if (num > 0 && num <= buildingCount) {
                            // POLYMORPHISM - method works with any building type
                            CityManager.displayBuildingFullInfo(buildings[num - 1]);
                        } else {
                            System.out.println("Invalid building number!");
                        }
                    } else {
                        System.out.println("\nNo buildings in the city!");
                    }
                    break;

                case 7: // Change Building Status
                    if (buildingCount > 0) {
                        changeBuildingStatus(scanner, buildings, buildingCount);
                    } else {
                        System.out.println("\nNo buildings available!");
                    }
                    break;

                case 8: // Filter by Type
                    if (buildingCount > 0) {
                        filterByBuildingType(scanner, buildings, buildingCount);
                    } else {
                        System.out.println("\nNo buildings available!");
                    }
                    break;

                case 9: // Filter by Status
                    if (buildingCount > 0) {
                        filterByStatus(scanner, buildings, buildingCount);
                    } else {
                        System.out.println("\nNo buildings available!");
                    }
                    break;

                case 10: // Service Operations
                    if (buildingCount > 0) {
                        performServiceOperations(scanner, buildings, buildingCount);
                    } else {
                        System.out.println("\nNo buildings available!");
                    }
                    break;

                case 11: // Special Building Operations
                    if (buildingCount > 0) {
                        CityManager.performAllSpecialOperations(buildings, buildingCount);
                    } else {
                        System.out.println("\nNo buildings available!");
                    }
                    break;

                case 12: // Maintenance Report
                    if (buildingCount > 0) {
                        CityManager.calculateTotalMaintenance(buildings, buildingCount);
                    } else {
                        System.out.println("\nNo buildings available!");
                    }
                    break;

                case 13: // Accessibility Report
                    if (buildingCount > 0) {
                        CityManager.checkAccessibilityCompliance(buildings, buildingCount);
                    } else {
                        System.out.println("\nNo buildings available!");
                    }
                    break;

                case 14: // Priority Statistics
                    if (buildingCount > 0) {
                        CityManager.displayPriorityStatistics(buildings, buildingCount);
                    } else {
                        System.out.println("\nNo buildings available!");
                    }
                    break;

                case 15: // Serviceable Buildings
                    if (buildingCount > 0) {
                        CityManager.displayServiceableBuildings(buildings, buildingCount);
                    } else {
                        System.out.println("\nNo buildings available!");
                    }
                    break;

                case 16: // Type-Specific Operations
                    if (buildingCount > 0) {
                        performTypeSpecificOperations(scanner, buildings, buildingCount);
                    } else {
                        System.out.println("\nNo buildings available!");
                    }
                    break;

                case 17: // Exit
                    System.out.println("\n" + "=".repeat(50));
                    System.out.println("Thank you for using Smart City Management System!");
                    System.out.println("Total Buildings Created: " + AbstractBuilding.getBuildingCounter());
                    System.out.println("=".repeat(50));
                    running = false;
                    break;

                default:
                    System.out.println("\n⚠ Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("Building Management:");
        System.out.println("  1. Add Hospital");
        System.out.println("  2. Add School");
        System.out.println("  3. Add Office");
        System.out.println("  4. Add Park");
        System.out.println("\nView & Display:");
        System.out.println("  5. Display All Buildings");
        System.out.println("  6. Display Specific Building (Full Info)");
        System.out.println("  7. Change Building Status");
        System.out.println("  8. Filter Buildings by Type");
        System.out.println("  9. Filter Buildings by Status");
        System.out.println("\nOperations & Services:");
        System.out.println("  10. Service Operations");
        System.out.println("  11. Perform All Special Operations");
        System.out.println("  12. Maintenance Cost Report");
        System.out.println("  13. Accessibility Compliance Report");
        System.out.println("\nAnalytics:");
        System.out.println("  14. Priority Statistics");
        System.out.println("  15. View Serviceable Buildings");
        System.out.println("  16. Type-Specific Operations");
        System.out.println("\n  17. Exit");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice: ");
    }

    // UPCASTING example - returns ModernHospital as AbstractBuilding
    private static AbstractBuilding createHospital(Scanner scanner) {
        System.out.println("\n=== Add New Hospital ===");
        System.out.print("Hospital Name: ");
        String name = scanner.nextLine();

        System.out.print("Number of Floors: ");
        int floors = scanner.nextInt();

        System.out.print("Area per Floor (sq meters): ");
        double area = scanner.nextDouble();

        System.out.print("Total Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        // UPCASTING - ModernHospital is automatically cast to AbstractBuilding
        ModernHospital hospital = new ModernHospital(name, floors, area, capacity);

        System.out.println("\n✓ Hospital added successfully!");
        System.out.println("License Number: " + hospital.getLicenseNumber()); // FINAL variable

        return hospital; // UPCASTING happens here
    }

    private static AbstractBuilding createSchool(Scanner scanner) {
        System.out.println("\n=== Add New School ===");
        System.out.print("School Name: ");
        String name = scanner.nextLine();

        System.out.print("Number of Floors: ");
        int floors = scanner.nextInt();

        System.out.print("Area per Floor (sq meters): ");
        double area = scanner.nextDouble();

        System.out.print("Total Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Education Level (Elementary/Middle/High School/University): ");
        String level = scanner.nextLine();

        ModernSchool school = new ModernSchool(name, floors, area, capacity, level);

        System.out.println("\n✓ School added successfully!");
        System.out.println("Accreditation ID: " + school.getAccreditationId()); // FINAL

        return school; // UPCASTING
    }

    private static AbstractBuilding createOffice(Scanner scanner) {
        System.out.println("\n=== Add New Office ===");
        System.out.print("Building Name: ");
        String name = scanner.nextLine();

        System.out.print("Number of Floors: ");
        int floors = scanner.nextInt();

        System.out.print("Area per Floor (sq meters): ");
        double area = scanner.nextDouble();

        System.out.print("Total Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Company Name: ");
        String company = scanner.nextLine();

        System.out.print("Business Type (Technology/Finance/Healthcare/etc): ");
        String businessType = scanner.nextLine();

        ModernOffice office = new ModernOffice(name, floors, area, capacity, company, businessType);

        System.out.println("\n✓ Office added successfully!");
        System.out.println("Business License: " + office.getBusinessLicenseId()); // FINAL

        return office; // UPCASTING
    }

    private static AbstractBuilding createPark(Scanner scanner) {
        System.out.println("\n=== Add New Park ===");
        System.out.print("Park Name: ");
        String name = scanner.nextLine();

        System.out.print("Total Area (sq meters): ");
        double area = scanner.nextDouble();

        System.out.print("Visitor Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Park Type (Urban/Botanical/Recreation/Dog Park): ");
        String parkType = scanner.nextLine();

        PublicPark park = new PublicPark(name, area, capacity, parkType);

        System.out.println("\n✓ Park added successfully!");
        System.out.println("Registration ID: " + park.getParkRegistrationId()); // FINAL

        // Demonstrate FINAL METHOD
        park.openToPublic(); // This method cannot be overridden

        return park; // UPCASTING
    }

    // Demonstrates ENUM in switch-case and changing status
    private static void changeBuildingStatus(Scanner scanner, AbstractBuilding[] buildings, int count) {
        System.out.print("\nEnter building number (1-" + count + "): ");
        int num = scanner.nextInt();
        scanner.nextLine();

        if (num < 1 || num > count) {
            System.out.println("Invalid building number!");
            return;
        }

        AbstractBuilding building = buildings[num - 1];

        System.out.println("\nCurrent Status: " + building.getStatus().getDisplayName());
        System.out.println("\nSelect New Status:");

        // Display all ENUM values
        BuildingStatus[] statuses = BuildingStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.println((i + 1) + ". " + statuses[i].getDisplayName() +
                    " - " + statuses[i].getDescription());
        }

        System.out.print("\nEnter choice (1-" + statuses.length + "): ");
        int statusChoice = scanner.nextInt();
        scanner.nextLine();

        if (statusChoice > 0 && statusChoice <= statuses.length) {
            BuildingStatus newStatus = statuses[statusChoice - 1];
            building.changeStatus(newStatus); // ENUM usage
        } else {
            System.out.println("Invalid status choice!");
        }
    }

    // Demonstrates filtering using ENUM
    private static void filterByBuildingType(Scanner scanner, AbstractBuilding[] buildings, int count) {
        System.out.println("\nSelect Building Type:");

        BuildingType[] types = BuildingType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i].getName() +
                    " (" + types[i].getCategory() + ")");
        }

        System.out.print("\nEnter choice: ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        if (typeChoice > 0 && typeChoice <= types.length) {
            BuildingType selectedType = types[typeChoice - 1];
            CityManager.displayBuildingsByType(buildings, count, selectedType);
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private static void filterByStatus(Scanner scanner, AbstractBuilding[] buildings, int count) {
        System.out.println("\nSelect Status:");

        BuildingStatus[] statuses = BuildingStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.println((i + 1) + ". " + statuses[i].getDisplayName());
        }

        System.out.print("\nEnter choice: ");
        int statusChoice = scanner.nextInt();
        scanner.nextLine();

        if (statusChoice > 0 && statusChoice <= statuses.length) {
            BuildingStatus selectedStatus = statuses[statusChoice - 1];
            CityManager.displayBuildingsByStatus(buildings, count, selectedStatus);
        } else {
            System.out.println("Invalid choice!");
        }
    }

    // Demonstrates DOWNCASTING and interface polymorphism
    private static void performServiceOperations(Scanner scanner, AbstractBuilding[] buildings, int count) {
        System.out.print("\nEnter building number (1-" + count + "): ");
        int num = scanner.nextInt();
        scanner.nextLine();

        if (num < 1 || num > count) {
            System.out.println("Invalid building number!");
            return;
        }

        AbstractBuilding building = buildings[num - 1];

        // Check if building implements Serviceable interface
        if (building instanceof Serviceable) {
            Serviceable serviceable = (Serviceable) building; // DOWNCASTING

            System.out.println("\n=== Service Operations for " + building.getName() + " ===");
            String[] services = serviceable.getAvailableServices();

            System.out.println("\nAvailable Services:");
            for (int i = 0; i < services.length; i++) {
                System.out.println((i + 1) + ". " + services[i]);
            }

            System.out.print("\nSelect service (1-" + services.length + "): ");
            int serviceChoice = scanner.nextInt();
            scanner.nextLine();

            if (serviceChoice > 0 && serviceChoice <= services.length) {
                String selectedService = services[serviceChoice - 1];
                serviceable.provideService(selectedService); // Interface method

                System.out.print("\nHow many units of this service? ");
                int quantity = scanner.nextInt();
                serviceable.requestService(selectedService, quantity); // Interface default method
            } else {
                System.out.println("Invalid service choice!");
            }
        } else {
            System.out.println("\nThis building does not provide services.");
        }
    }

    // Demonstrates type-specific operations using DOWNCASTING
    private static void performTypeSpecificOperations(Scanner scanner, AbstractBuilding[] buildings, int count) {
        System.out.print("\nEnter building number (1-" + count + "): ");
        int num = scanner.nextInt();
        scanner.nextLine();

        if (num < 1 || num > count) {
            System.out.println("Invalid building number!");
            return;
        }

        AbstractBuilding building = buildings[num - 1];

        // DOWNCASTING based on actual type
        if (building instanceof ModernHospital) {
            ModernHospital hospital = (ModernHospital) building; // DOWNCASTING
            System.out.println("\n=== Hospital Operations ===");
            System.out.print("Enter patient name: ");
            String patientName = scanner.nextLine();
            System.out.print("Is this an emergency? (true/false): ");
            boolean isEmergency = scanner.nextBoolean();
            hospital.admitPatient(patientName, isEmergency);

        } else if (building instanceof ModernSchool) {
            ModernSchool school = (ModernSchool) building; // DOWNCASTING
            System.out.println("\n=== School Operations ===");
            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();
            System.out.print("Enter grade: ");
            String grade = scanner.nextLine();
            school.enrollStudent(studentName, grade);

        } else if (building instanceof PublicPark) {
            PublicPark park = (PublicPark) building; // DOWNCASTING
            System.out.println("\n=== Park Operations ===");
            System.out.print("Enter event name: ");
            String eventName = scanner.nextLine();
            System.out.print("Expected visitors: ");
            int visitors = scanner.nextInt();
            park.organizeEvent(eventName, visitors);

        } else if (building instanceof ModernOffice) {
            System.out.println("\n=== Office Operations ===");
            System.out.println("Office building operations completed.");

        } else {
            System.out.println("\nNo specific operations available for this building type.");
        }
    }
}