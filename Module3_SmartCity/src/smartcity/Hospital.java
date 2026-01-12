package smartcity;

/**
 * MODULE 3: Hospital Class - Demonstrates Inheritance
 *
 * This class demonstrates:
 * - INHERITANCE: extends Building class
 * - Constructor chaining with super()
 * - Method OVERRIDING
 * - Adding specialized attributes and methods
 */
public class Hospital extends Building {  // INHERITANCE: Hospital extends Building

    // Specialized attributes for Hospital
    private int emergencyBeds;
    private int numberOfDoctors;
    private boolean hasEmergencyRoom;
    private String specialization;

    // CONSTRUCTOR OVERLOADING in subclass

    // Constructor 1: Default constructor
    public Hospital() {
        super(); // Call parent class constructor
        this.type = "Hospital";
        this.emergencyBeds = 0;
        this.numberOfDoctors = 0;
        this.hasEmergencyRoom = false;
        this.specialization = "General";
        System.out.println("Hospital default constructor called");
    }

    // Constructor 2: Constructor with basic parameters
    public Hospital(String name, int floors, double area) {
        super(name, "Hospital", floors, area); // Call parent constructor with parameters
        this.emergencyBeds = 20;
        this.numberOfDoctors = 10;
        this.hasEmergencyRoom = true;
        this.specialization = "General";
        System.out.println("Hospital(name, floors, area) constructor called");
    }

    // Constructor 3: Constructor with all hospital-specific parameters
    public Hospital(String name, int floors, double area, int capacity,
                    int emergencyBeds, int numberOfDoctors, String specialization) {
        super(name, "Hospital", floors, area, capacity, true); // Call parent constructor
        this.emergencyBeds = emergencyBeds;
        this.numberOfDoctors = numberOfDoctors;
        this.hasEmergencyRoom = true;
        this.specialization = specialization;
        System.out.println("Hospital(all parameters) constructor called");
    }

    // Getters for hospital-specific attributes
    public int getEmergencyBeds() {
        return emergencyBeds;
    }

    public int getNumberOfDoctors() {
        return numberOfDoctors;
    }

    public boolean hasEmergencyRoom() {
        return hasEmergencyRoom;
    }

    public String getSpecialization() {
        return specialization;
    }

    // Setters
    public void setEmergencyBeds(int emergencyBeds) {
        if (emergencyBeds >= 0) {
            this.emergencyBeds = emergencyBeds;
        }
    }

    public void setNumberOfDoctors(int numberOfDoctors) {
        if (numberOfDoctors >= 0) {
            this.numberOfDoctors = numberOfDoctors;
        }
    }

    public void setHasEmergencyRoom(boolean hasEmergencyRoom) {
        this.hasEmergencyRoom = hasEmergencyRoom;
    }

    public void setSpecialization(String specialization) {
        if (specialization != null && !specialization.trim().isEmpty()) {
            this.specialization = specialization;
        }
    }

    // METHOD OVERRIDING: Override parent's displayInfo method
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call parent method first
        // Add hospital-specific information
        System.out.println("=== Hospital Specific Info ===");
        System.out.println("Specialization: " + specialization);
        System.out.println("Emergency Beds: " + emergencyBeds);
        System.out.println("Number of Doctors: " + numberOfDoctors);
        System.out.println("Emergency Room: " + (hasEmergencyRoom ? "Available" : "Not Available"));
    }

    // METHOD OVERRIDING: Override performMaintenance
    @Override
    public void performMaintenance() {
        System.out.println("\n=== Hospital Maintenance Check for " + name + " ===");
        System.out.println("- Checking medical equipment");
        System.out.println("- Sanitizing all areas");
        System.out.println("- Testing emergency systems");
        System.out.println("- Inspecting emergency room");
        System.out.println("- Checking oxygen supply systems");

        // Call parent's updateMaintenanceCost and add hospital-specific costs
        updateMaintenanceCost();
        maintenanceCost += emergencyBeds * 50; // Additional cost for emergency beds
        maintenanceCost += numberOfDoctors * 100; // Additional cost for medical equipment

        System.out.println("Hospital Maintenance completed! Total Cost: $" +
                String.format("%.2f", maintenanceCost));
    }

    // METHOD OVERRIDING: Override showServices
    @Override
    public void showServices() {
        System.out.println("\n=== Hospital Services ===");
        System.out.println("1. Emergency Care (24/7)");
        System.out.println("2. " + specialization + " Treatment");
        System.out.println("3. Outpatient Services");
        System.out.println("4. Diagnostic Services");
        System.out.println("5. Pharmacy");
        if (hasEmergencyRoom) {
            System.out.println("6. Emergency Room Services");
        }
    }

    // METHOD OVERLOADING: Additional method specific to Hospital
    public void admitPatient(String patientName) {
        System.out.println("Patient " + patientName + " admitted to " + name);
        System.out.println("Specialization: " + specialization);
    }

    // METHOD OVERLOADING: Admit patient with bed type
    public void admitPatient(String patientName, boolean isEmergency) {
        if (isEmergency) {
            if (emergencyBeds > 0) {
                System.out.println("EMERGENCY: Patient " + patientName + " admitted to emergency ward");
                System.out.println("Emergency beds available: " + emergencyBeds);
            } else {
                System.out.println("WARNING: No emergency beds available!");
            }
        } else {
            admitPatient(patientName); // Call single parameter version
        }
    }

    // Hospital-specific method
    public void assignDoctor(String patientName, String doctorName) {
        System.out.println("Doctor " + doctorName + " assigned to patient " + patientName);
        System.out.println("Department: " + specialization);
    }
}