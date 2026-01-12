package smartcity;

/**
 * MODULE 4: ModernHospital Class
 *
 * This demonstrates:
 * - Extending ABSTRACT CLASS
 * - Implementing INTERFACES (multiple interface implementation)
 * - Implementing ABSTRACT METHODS
 * - Using CONCRETE METHODS from abstract class
 * - POLYMORPHISM through inheritance and interfaces
 */
public class ModernHospital extends AbstractBuilding implements Serviceable, Accessible {

    // Hospital-specific attributes
    private int emergencyBeds;
    private int numberOfDoctors;
    private boolean hasEmergencyRoom;
    private String specialization;

    // Accessibility features
    private boolean wheelchairAccess;
    private boolean hasElevatorSystem;
    private int accessibleParking;

    // FINAL variable - cannot be changed after construction
    private final int licenseNumber;

    // Constructor
    public ModernHospital(String name, int floors, double area, int capacity) {
        super(name, BuildingType.HOSPITAL); // Call abstract class constructor
        this.floors = floors;
        this.area = area;
        this.capacity = capacity;
        this.emergencyBeds = 20;
        this.numberOfDoctors = 15;
        this.hasEmergencyRoom = true;
        this.specialization = "General";
        this.wheelchairAccess = true;
        this.hasElevatorSystem = true;
        this.accessibleParking = 10;
        this.licenseNumber = (int)(Math.random() * 100000); // FINAL - set once
    }

    // IMPLEMENTING ABSTRACT METHOD from AbstractBuilding
    @Override
    public double calculateMaintenanceCost() {
        double baseCost = calculateTotalArea() * MAINTENANCE_BASE_RATE;
        double emergencyCost = emergencyBeds * 50;
        double equipmentCost = numberOfDoctors * 100;
        return baseCost + emergencyCost + equipmentCost;
    }

    // IMPLEMENTING ABSTRACT METHOD from AbstractBuilding
    @Override
    public void displaySpecificInfo() {
        System.out.println("\n=== Hospital Specific Information ===");
        System.out.println("License Number: " + licenseNumber); // FINAL variable
        System.out.println("Specialization: " + specialization);
        System.out.println("Emergency Beds: " + emergencyBeds);
        System.out.println("Number of Doctors: " + numberOfDoctors);
        System.out.println("Emergency Room: " + (hasEmergencyRoom ? "Available" : "Not Available"));
        System.out.println("Maintenance Cost: $" + String.format("%.2f", calculateMaintenanceCost()));
    }

    // IMPLEMENTING ABSTRACT METHOD from AbstractBuilding
    @Override
    public void performSpecialOperation() {
        System.out.println("\n=== Hospital Special Operation ===");
        System.out.println("Conducting emergency drill...");
        System.out.println("Testing medical equipment...");
        System.out.println("Verifying emergency protocols...");

        if (hasEmergencyRoom) {
            System.out.println("Emergency room readiness: CONFIRMED");
        }
    }

    // IMPLEMENTING INTERFACE METHOD from Serviceable
    @Override
    public void provideService(String serviceType) {
        System.out.println("\n=== Providing Medical Service ===");
        System.out.println("Hospital: " + name);
        System.out.println("Service: " + serviceType);
        System.out.println("Specialization: " + specialization);

        if (isServiceAvailable(serviceType)) {
            System.out.println("Service is being provided by qualified medical staff.");
        } else {
            System.out.println("This service is not available at this facility.");
        }
    }

    // IMPLEMENTING INTERFACE METHOD from Serviceable
    @Override
    public double calculateServiceCost() {
        return 100.0 + (numberOfDoctors * 50.0); // Base cost plus doctor availability
    }

    // IMPLEMENTING INTERFACE METHOD from Serviceable
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

    // IMPLEMENTING INTERFACE METHOD from Serviceable
    @Override
    public String[] getAvailableServices() {
        return new String[]{
                "Emergency Care",
                specialization + " Treatment",
                "Diagnostic Services",
                "Pharmacy",
                "Laboratory Tests",
                "Outpatient Consultation"
        };
    }

    // IMPLEMENTING INTERFACE METHOD from Accessible
    @Override
    public boolean hasWheelchairAccess() {
        return wheelchairAccess;
    }

    // IMPLEMENTING INTERFACE METHOD from Accessible
    @Override
    public boolean hasElevator() {
        return hasElevatorSystem;
    }

    // IMPLEMENTING INTERFACE METHOD from Accessible
    @Override
    public int getAccessibleParkingSpots() {
        return accessibleParking;
    }

    // Additional hospital-specific methods
    public void admitPatient(String patientName, boolean isEmergency) {
        System.out.println("\n=== Patient Admission ===");
        if (isEmergency && hasEmergencyRoom) {
            System.out.println("EMERGENCY patient admitted: " + patientName);
            System.out.println("Emergency beds available: " + emergencyBeds);
        } else {
            System.out.println("Patient admitted: " + patientName);
        }
        System.out.println("Hospital: " + name);
    }

    // Getters
    public int getLicenseNumber() { return licenseNumber; } // FINAL variable
    public String getSpecialization() { return specialization; }
    public int getEmergencyBeds() { return emergencyBeds; }

    // Setters (note: no setter for licenseNumber because it's FINAL)
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setEmergencyBeds(int emergencyBeds) {
        this.emergencyBeds = emergencyBeds;
    }
}