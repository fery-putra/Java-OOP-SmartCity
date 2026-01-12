package smartcity;

/**
 * MODULE 4: ModernOffice Class
 *
 * Demonstrates abstract class and interface implementation
 */
public class ModernOffice extends AbstractBuilding implements Serviceable, Accessible {

    private String companyName;
    private int numberOfEmployees;
    private String businessType;
    private boolean hasParkingLot;
    private int parkingSpaces;

    // Accessibility
    private boolean wheelchairAccess;
    private boolean hasElevatorSystem;
    private int accessibleParking;

    // FINAL variable
    private final String businessLicenseId;

    public ModernOffice(String name, int floors, double area, int capacity, String companyName, String businessType) {
        super(name, BuildingType.OFFICE);
        this.floors = floors;
        this.area = area;
        this.capacity = capacity;
        this.companyName = companyName;
        this.businessType = businessType;
        this.numberOfEmployees = 100;
        this.hasParkingLot = true;
        this.parkingSpaces = 50;
        this.wheelchairAccess = true;
        this.hasElevatorSystem = true;
        this.accessibleParking = 5;
        this.businessLicenseId = "BIZ-" + (int)(Math.random() * 100000); // FINAL
    }

    // IMPLEMENTING ABSTRACT METHODS

    @Override
    public double calculateMaintenanceCost() {
        double baseCost = calculateTotalArea() * MAINTENANCE_BASE_RATE;
        double itCost = numberOfEmployees * 15;
        double parkingCost = hasParkingLot ? parkingSpaces * 10 : 0;
        return baseCost + itCost + parkingCost;
    }

    @Override
    public void displaySpecificInfo() {
        System.out.println("\n=== Office Specific Information ===");
        System.out.println("Business License: " + businessLicenseId); // FINAL
        System.out.println("Company: " + companyName);
        System.out.println("Business Type: " + businessType);
        System.out.println("Employees: " + numberOfEmployees);
        System.out.println("Parking: " + (hasParkingLot ? parkingSpaces + " spaces" : "Not Available"));
        System.out.println("Maintenance Cost: $" + String.format("%.2f", calculateMaintenanceCost()));
    }

    @Override
    public void performSpecialOperation() {
        System.out.println("\n=== Office Special Operation ===");
        System.out.println("Running network diagnostics...");
        System.out.println("Testing HVAC systems...");
        System.out.println("Checking security systems...");

        if (hasParkingLot) {
            System.out.println("Parking lot maintenance: SCHEDULED");
        }
    }

    // IMPLEMENTING Serviceable INTERFACE

    @Override
    public void provideService(String serviceType) {
        System.out.println("\n=== Providing Business Service ===");
        System.out.println("Company: " + companyName);
        System.out.println("Service: " + serviceType);
        System.out.println("Business Type: " + businessType);
    }

    @Override
    public double calculateServiceCost() {
        return 75.0 + (numberOfEmployees * 10.0);
    }

    @Override
    public boolean isServiceAvailable(String serviceType) {
        String[] services = getAvailableServices();
        for (String service : services) {
            if (service.equalsIgnoreCase(serviceType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String[] getAvailableServices() {
        return new String[]{
                businessType + " Services",
                "Office Space Rental",
                "Meeting Rooms",
                "High-Speed Internet",
                "Parking Services",
                "Reception Services"
        };
    }

    // IMPLEMENTING Accessible INTERFACE

    @Override
    public boolean hasWheelchairAccess() {
        return wheelchairAccess;
    }

    @Override
    public boolean hasElevator() {
        return hasElevatorSystem;
    }

    @Override
    public int getAccessibleParkingSpots() {
        return accessibleParking;
    }

    public String getBusinessLicenseId() {
        return businessLicenseId; // FINAL
    }
}