package smartcity;

/**
 * MODULE 4: ModernSchool Class
 *
 * Demonstrates implementing abstract class and interfaces
 */
public class ModernSchool extends AbstractBuilding implements Serviceable, Accessible {

    private int numberOfClassrooms;
    private int numberOfTeachers;
    private String educationLevel;
    private boolean hasLaboratory;
    private boolean hasLibrary;

    // Accessibility
    private boolean wheelchairAccess;
    private boolean hasElevatorSystem;
    private int accessibleParking;

    // FINAL variable
    private final String accreditationId;

    public ModernSchool(String name, int floors, double area, int capacity, String educationLevel) {
        super(name, BuildingType.SCHOOL);
        this.floors = floors;
        this.area = area;
        this.capacity = capacity;
        this.educationLevel = educationLevel;
        this.numberOfClassrooms = 15;
        this.numberOfTeachers = 20;
        this.hasLaboratory = true;
        this.hasLibrary = true;
        this.wheelchairAccess = true;
        this.hasElevatorSystem = floors > 1;
        this.accessibleParking = 5;
        this.accreditationId = "EDU-" + (int)(Math.random() * 10000); // FINAL
    }

    // IMPLEMENTING ABSTRACT METHODS

    @Override
    public double calculateMaintenanceCost() {
        double baseCost = calculateTotalArea() * MAINTENANCE_BASE_RATE;
        double classroomCost = numberOfClassrooms * 30;
        double equipmentCost = numberOfTeachers * 20;

        if (hasLaboratory) baseCost += 500;
        if (hasLibrary) baseCost += 300;

        return baseCost + classroomCost + equipmentCost;
    }

    @Override
    public void displaySpecificInfo() {
        System.out.println("\n=== School Specific Information ===");
        System.out.println("Accreditation ID: " + accreditationId); // FINAL
        System.out.println("Education Level: " + educationLevel);
        System.out.println("Classrooms: " + numberOfClassrooms);
        System.out.println("Teachers: " + numberOfTeachers);
        System.out.println("Laboratory: " + (hasLaboratory ? "Available" : "Not Available"));
        System.out.println("Library: " + (hasLibrary ? "Available" : "Not Available"));
        System.out.println("Maintenance Cost: $" + String.format("%.2f", calculateMaintenanceCost()));
    }

    @Override
    public void performSpecialOperation() {
        System.out.println("\n=== School Special Operation ===");
        System.out.println("Conducting safety drill...");
        System.out.println("Checking educational equipment...");
        System.out.println("Reviewing curriculum materials...");

        if (hasLaboratory) {
            System.out.println("Laboratory safety check: COMPLETED");
        }
        if (hasLibrary) {
            System.out.println("Library inventory: VERIFIED");
        }
    }

    // IMPLEMENTING Serviceable INTERFACE

    @Override
    public void provideService(String serviceType) {
        System.out.println("\n=== Providing Educational Service ===");
        System.out.println("School: " + name);
        System.out.println("Service: " + serviceType);
        System.out.println("Level: " + educationLevel);
    }

    @Override
    public double calculateServiceCost() {
        return 50.0 + (numberOfTeachers * 30.0);
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
                educationLevel + " Education",
                "Classroom Teaching",
                "Library Access",
                "Laboratory Sessions",
                "Sports Activities",
                "Counseling Services"
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

    // School-specific methods
    public void enrollStudent(String studentName, String grade) {
        System.out.println("\n=== Student Enrollment ===");
        System.out.println("Student: " + studentName);
        System.out.println("Grade: " + grade);
        System.out.println("School: " + name + " (" + educationLevel + ")");
    }

    public String getAccreditationId() {
        return accreditationId; // FINAL variable
    }
}