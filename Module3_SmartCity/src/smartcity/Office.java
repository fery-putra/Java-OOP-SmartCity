package smartcity;

/**
 * MODULE 3: Office Class - Demonstrates Inheritance
 */
public class Office extends Building {  // INHERITANCE

    // Specialized attributes
    private String companyName;
    private int numberOfEmployees;
    private boolean hasParkingLot;
    private int parkingSpaces;
    private String businessType;

    // CONSTRUCTORS

    public Office() {
        super();
        this.type = "Office";
        this.companyName = "Unknown";
        this.numberOfEmployees = 0;
        this.hasParkingLot = false;
        this.parkingSpaces = 0;
        this.businessType = "General";
        System.out.println("Office default constructor called");
    }

    public Office(String name, int floors, double area) {
        super(name, "Office", floors, area);
        this.companyName = "Tech Corp";
        this.numberOfEmployees = 50;
        this.hasParkingLot = true;
        this.parkingSpaces = 20;
        this.businessType = "Technology";
        System.out.println("Office(name, floors, area) constructor called");
    }

    public Office(String name, int floors, double area, int capacity,
                  String companyName, int numberOfEmployees, String businessType) {
        super(name, "Office", floors, area, capacity, true);
        this.companyName = companyName;
        this.numberOfEmployees = numberOfEmployees;
        this.hasParkingLot = true;
        this.parkingSpaces = numberOfEmployees / 3; // Estimate
        this.businessType = businessType;
        System.out.println("Office(all parameters) constructor called");
    }

    // Getters
    public String getCompanyName() { return companyName; }
    public int getNumberOfEmployees() { return numberOfEmployees; }
    public boolean hasParkingLot() { return hasParkingLot; }
    public int getParkingSpaces() { return parkingSpaces; }
    public String getBusinessType() { return businessType; }

    // Setters
    public void setCompanyName(String companyName) {
        if (companyName != null && !companyName.trim().isEmpty()) {
            this.companyName = companyName;
        }
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        if (numberOfEmployees >= 0) {
            this.numberOfEmployees = numberOfEmployees;
        }
    }

    public void setHasParkingLot(boolean hasParkingLot) {
        this.hasParkingLot = hasParkingLot;
    }

    public void setParkingSpaces(int parkingSpaces) {
        if (parkingSpaces >= 0) {
            this.parkingSpaces = parkingSpaces;
        }
    }

    public void setBusinessType(String businessType) {
        if (businessType != null) {
            this.businessType = businessType;
        }
    }

    // METHOD OVERRIDING
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("=== Office Specific Info ===");
        System.out.println("Company: " + companyName);
        System.out.println("Business Type: " + businessType);
        System.out.println("Employees: " + numberOfEmployees);
        System.out.println("Parking: " + (hasParkingLot ? parkingSpaces + " spaces" : "Not Available"));
    }

    // METHOD OVERRIDING
    @Override
    public void performMaintenance() {
        System.out.println("\n=== Office Maintenance Check for " + name + " ===");
        System.out.println("- Checking HVAC systems");
        System.out.println("- Testing network infrastructure");
        System.out.println("- Inspecting elevators");
        System.out.println("- Cleaning common areas");

        if (hasParkingLot) {
            System.out.println("- Maintaining parking lot");
        }

        updateMaintenanceCost();
        maintenanceCost += numberOfEmployees * 15; // IT equipment maintenance
        if (hasParkingLot) {
            maintenanceCost += parkingSpaces * 10;
        }

        System.out.println("Office Maintenance completed! Total Cost: $" +
                String.format("%.2f", maintenanceCost));
    }

    // METHOD OVERRIDING
    @Override
    public void showServices() {
        System.out.println("\n=== Office Services ===");
        System.out.println("1. Workspace Solutions");
        System.out.println("2. " + businessType + " Business Operations");
        System.out.println("3. Meeting Rooms");
        System.out.println("4. High-Speed Internet");
        if (hasParkingLot) {
            System.out.println("5. Parking Facilities");
        }
    }

    // METHOD OVERLOADING
    public void registerEmployee(String employeeName) {
        System.out.println("Employee " + employeeName + " registered at " + companyName);
        numberOfEmployees++;
    }

    public void registerEmployee(String employeeName, String department) {
        System.out.println("Employee " + employeeName + " registered");
        System.out.println("Company: " + companyName);
        System.out.println("Department: " + department);
        numberOfEmployees++;
    }

    public void registerEmployee(String employeeName, String department, boolean needsParking) {
        registerEmployee(employeeName, department);
        if (needsParking) {
            if (hasParkingLot && parkingSpaces > 0) {
                System.out.println("Parking space allocated");
                parkingSpaces--;
            } else {
                System.out.println("No parking available");
            }
        }
    }
}