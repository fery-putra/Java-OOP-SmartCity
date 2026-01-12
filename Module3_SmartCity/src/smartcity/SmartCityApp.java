package smartcity;

import java.util.Scanner;

/**
 * MODULE 3: Smart City Application with Inheritance

 * Demonstrates:
 * - Creating objects of different subclasses
 * - Using inherited and overridden methods
 * - Polymorphism basics (will be expanded in Module 4)
 */
public class SmartCityApp {

    private static final int MAX_BUILDINGS = 10;

    private static void displayMenu() {
        System.out.println("\n====== SMART CITY MANAGEMENT SYSTEM ======");
        System.out.println("Total Buildings: " + Building.getTotalBuildingsCreated());
        System.out.println("\n1. Add Hospital");
        System.out.println("2. Add School");
        System.out.println("3. Add Office");
        System.out.println("4. Add Park");
        System.out.println("5. Display All Buildings");
        System.out.println("6. Display Specific Building");
        System.out.println("7. Perform Maintenance");
        System.out.println("8. Show Building Services");
        System.out.println("9. Special Building Operations");
        System.out.println("10. Calculate City Statistics");
        System.out.println("11. Exit");
        System.out.print("Enter your choice: ");
    }

    private static Hospital createHospital(Scanner scanner) {
        System.out.println("\n=== Add New Hospital ===");
        System.out.print("Enter hospital name: ");
        String name = scanner.nextLine();

        System.out.print("Enter number of floors: ");
        int floors = scanner.nextInt();

        System.out.print("Enter area per floor (sq meters): ");
        double area = scanner.nextDouble();

        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt();

        System.out.print("Enter number of emergency beds: ");
        int emergencyBeds = scanner.nextInt();

        System.out.print("Enter number of doctors: ");
        int doctors = scanner.nextInt();

        scanner.nextLine();
        System.out.print("Enter specialization (General/Cardiac/Pediatric/etc): ");
        String specialization = scanner.nextLine();

        // Using parameterized constructor
        Hospital hospital = new Hospital(name, floors, area, capacity,
                emergencyBeds, doctors, specialization);

        System.out.println("\nHospital added successfully!");
        return hospital;
    }

    private static School createSchool(Scanner scanner) {
        System.out.println("\n=== Add New School ===");
        System.out.print("Enter school name: ");
        String name = scanner.nextLine();

        System.out.print("Enter number of floors: ");
        int floors = scanner.nextInt();

        System.out.print("Enter area per floor (sq meters): ");
        double area = scanner.nextDouble();

        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt();

        System.out.print("Enter number of classrooms: ");
        int classrooms = scanner.nextInt();

        System.out.print("Enter number of teachers: ");
        int teachers = scanner.nextInt();

        scanner.nextLine();
        System.out.print("Enter education level (Elementary/Middle/High School/University): ");
        String level = scanner.nextLine();

        School school = new School(name, floors, area, capacity,
                classrooms, teachers, level);

        System.out.println("\nSchool added successfully!");
        return school;
    }

    private static Office createOffice(Scanner scanner) {
        System.out.println("\n=== Add New Office ===");
        System.out.print("Enter building name: ");
        String name = scanner.nextLine();

        System.out.print("Enter number of floors: ");
        int floors = scanner.nextInt();

        System.out.print("Enter area per floor (sq meters): ");
        double area = scanner.nextDouble();

        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt();

        scanner.nextLine();
        System.out.print("Enter company name: ");
        String company = scanner.nextLine();

        System.out.print("Enter number of employees: ");
        int employees = scanner.nextInt();

        scanner.nextLine();
        System.out.print("Enter business type (Technology/Finance/Healthcare/etc): ");
        String businessType = scanner.nextLine();

        Office office = new Office(name, floors, area, capacity,
                company, employees, businessType);

        System.out.println("\nOffice added successfully!");
        return office;
    }

    private static Park createPark(Scanner scanner) {
        System.out.println("\n=== Add New Park ===");
        System.out.print("Enter park name: ");
        String name = scanner.nextLine();

        System.out.print("Enter park area (sq meters): ");
        double area = scanner.nextDouble();

        System.out.print("Enter visitor capacity: ");
        int capacity = scanner.nextInt();

        scanner.nextLine();
        System.out.print("Enter park type (Urban/Botanical/Recreation/Dog Park): ");
        String parkType = scanner.nextLine();

        System.out.print("Has playground? (true/false): ");
        boolean hasPlayground = scanner.nextBoolean();

        System.out.print("Has fountain? (true/false): ");
        boolean hasFountain = scanner.nextBoolean();

        Park park = new Park(name, area, capacity, parkType,
                hasPlayground, hasFountain);

        System.out.println("\nPark added successfully!");
        return park;
    }

    private static void specialOperations(Scanner scanner, Building[] buildings, int count) {
        System.out.print("\nEnter building number (1-" + count + "): ");
        int num = scanner.nextInt();
        scanner.nextLine();

        if (num < 1 || num > count) {
            System.out.println("Invalid building number!");
            return;
        }

        Building building = buildings[num - 1];

        // Check type and perform specific operations
        if (building instanceof Hospital) {
            Hospital hospital = (Hospital) building; // Downcasting
            System.out.println("\n=== Hospital Operations ===");
            System.out.println("1. Admit Patient");
            System.out.println("2. Admit Emergency Patient");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter patient name: ");
            String patientName = scanner.nextLine();

            if (choice == 1) {
                hospital.admitPatient(patientName); // METHOD OVERLOADING
            } else {
                hospital.admitPatient(patientName, true); // METHOD OVERLOADING
            }

        } else if (building instanceof School) {
            School school = (School) building;
            System.out.println("\n=== School Operations ===");
            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();
            System.out.print("Enter grade: ");
            String grade = scanner.nextLine();

            school.enrollStudent(studentName, grade); // METHOD OVERLOADING

        } else if (building instanceof Office) {
            Office office = (Office) building;
            System.out.println("\n=== Office Operations ===");
            System.out.print("Enter employee name: ");
            String empName = scanner.nextLine();
            System.out.print("Enter department: ");
            String dept = scanner.nextLine();

            office.registerEmployee(empName, dept); // METHOD OVERLOADING

        } else if (building instanceof Park) {
            Park park = (Park) building;
            System.out.println("\n=== Park Operations ===");
            System.out.print("Enter event name: ");
            String eventName = scanner.nextLine();
            System.out.print("Expected visitors: ");
            int visitors = scanner.nextInt();

            park.organizeEvent(eventName, visitors); // METHOD OVERLOADING
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Building[] buildings = new Building[MAX_BUILDINGS];
        int buildingCount = 0;
        boolean running = true;

        System.out.println("Welcome to " + Building.getCityName() + "!");
        System.out.println("This system demonstrates INHERITANCE and METHOD OVERRIDING");

        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // Add Hospital
                    if (buildingCount < MAX_BUILDINGS) {
                        buildings[buildingCount++] = createHospital(scanner);
                    } else {
                        System.out.println("City is full!");
                    }
                    break;

                case 2: // Add School
                    if (buildingCount < MAX_BUILDINGS) {
                        buildings[buildingCount++] = createSchool(scanner);
                    } else {
                        System.out.println("City is full!");
                    }
                    break;

                case 3: // Add Office
                    if (buildingCount < MAX_BUILDINGS) {
                        buildings[buildingCount++] = createOffice(scanner);
                    } else {
                        System.out.println("City is full!");
                    }
                    break;

                case 4: // Add Park
                    if (buildingCount < MAX_BUILDINGS) {
                        buildings[buildingCount++] = createPark(scanner);
                    } else {
                        System.out.println("City is full!");
                    }
                    break;

                case 5: // Display All
                    if (buildingCount == 0) {
                        System.out.println("\nNo buildings in the city!");
                    } else {
                        for (int i = 0; i < buildingCount; i++) {
                            System.out.println("\n--- Building " + (i + 1) + " ---");
                            buildings[i].displayInfo(); // OVERRIDDEN METHOD called
                        }
                    }
                    break;

                case 6: // Display Specific
                    if (buildingCount > 0) {
                        System.out.print("Enter building number (1-" + buildingCount + "): ");
                        int num = scanner.nextInt();
                        if (num > 0 && num <= buildingCount) {
                            buildings[num - 1].displayInfo(true); // METHOD OVERLOADING
                        }
                    }
                    break;

                case 7: // Maintenance
                    if (buildingCount > 0) {
                        System.out.print("Enter building number (1-" + buildingCount + "): ");
                        int num = scanner.nextInt();
                        if (num > 0 && num <= buildingCount) {
                            buildings[num - 1].performMaintenance(); // OVERRIDDEN METHOD
                        }
                    }
                    break;

                case 8: // Show Services
                    if (buildingCount > 0) {
                        System.out.print("Enter building number (1-" + buildingCount + "): ");
                        int num = scanner.nextInt();
                        if (num > 0 && num <= buildingCount) {
                            buildings[num - 1].showServices(); // OVERRIDDEN METHOD
                        }
                    }
                    break;

                case 9: // Special Operations
                    if (buildingCount > 0) {
                        specialOperations(scanner, buildings, buildingCount);
                    }
                    break;

                case 10: // Statistics
                    if (buildingCount > 0) {
                        int hospitals = 0, schools = 0, offices = 0, parks = 0;
                        for (int i = 0; i < buildingCount; i++) {
                            if (buildings[i] instanceof Hospital) hospitals++;
                            else if (buildings[i] instanceof School) schools++;
                            else if (buildings[i] instanceof Office) offices++;
                            else if (buildings[i] instanceof Park) parks++;
                        }
                        System.out.println("\n=== City Statistics ===");
                        System.out.println("Total Buildings: " + buildingCount);
                        System.out.println("Hospitals: " + hospitals);
                        System.out.println("Schools: " + schools);
                        System.out.println("Offices: " + offices);
                        System.out.println("Parks: " + parks);
                    }
                    break;

                case 11: // Exit
                    System.out.println("\nThank you for using Smart City Management System!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        scanner.close();
    }
}