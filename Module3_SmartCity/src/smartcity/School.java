package smartcity;

/**
 * MODULE 3: School Class - Demonstrates Inheritance
 *
 * INHERITANCE from Building class
 * Constructor chaining, Method overriding, and specialized attributes
 */
public class School extends Building {  // INHERITANCE

    // Specialized attributes for School
    private int numberOfClassrooms;
    private int numberOfTeachers;
    private String educationLevel; // Elementary, Middle, High School, University
    private boolean hasLaboratory;
    private boolean hasLibrary;

    // CONSTRUCTOR OVERLOADING

    // Constructor 1: Default constructor
    public School() {
        super(); // Call parent constructor
        this.type = "School";
        this.numberOfClassrooms = 0;
        this.numberOfTeachers = 0;
        this.educationLevel = "Elementary";
        this.hasLaboratory = false;
        this.hasLibrary = false;
        System.out.println("School default constructor called");
    }

    // Constructor 2: Basic parameters
    public School(String name, int floors, double area) {
        super(name, "School", floors, area); // SUPER keyword - call parent constructor
        this.numberOfClassrooms = 10;
        this.numberOfTeachers = 15;
        this.educationLevel = "Elementary";
        this.hasLaboratory = false;
        this.hasLibrary = true;
        System.out.println("School(name, floors, area) constructor called");
    }

    // Constructor 3: All school-specific parameters
    public School(String name, int floors, double area, int capacity,
                  int numberOfClassrooms, int numberOfTeachers, String educationLevel) {
        super(name, "School", floors, area, capacity, true); // Call parent with all params
        this.numberOfClassrooms = numberOfClassrooms;
        this.numberOfTeachers = numberOfTeachers;
        this.educationLevel = educationLevel;
        this.hasLaboratory = educationLevel.equals("High School") || educationLevel.equals("University");
        this.hasLibrary = true;
        System.out.println("School(all parameters) constructor called");
    }

    // Getters
    public int getNumberOfClassrooms() { return numberOfClassrooms; }
    public int getNumberOfTeachers() { return numberOfTeachers; }
    public String getEducationLevel() { return educationLevel; }
    public boolean hasLaboratory() { return hasLaboratory; }
    public boolean hasLibrary() { return hasLibrary; }

    // Setters
    public void setNumberOfClassrooms(int numberOfClassrooms) {
        if (numberOfClassrooms >= 0) {
            this.numberOfClassrooms = numberOfClassrooms;
        }
    }

    public void setNumberOfTeachers(int numberOfTeachers) {
        if (numberOfTeachers >= 0) {
            this.numberOfTeachers = numberOfTeachers;
        }
    }

    public void setEducationLevel(String educationLevel) {
        if (educationLevel != null) {
            this.educationLevel = educationLevel;
        }
    }

    public void setHasLaboratory(boolean hasLaboratory) {
        this.hasLaboratory = hasLaboratory;
    }

    public void setHasLibrary(boolean hasLibrary) {
        this.hasLibrary = hasLibrary;
    }

    // METHOD OVERRIDING: Override displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call parent's displayInfo
        System.out.println("=== School Specific Info ===");
        System.out.println("Education Level: " + educationLevel);
        System.out.println("Classrooms: " + numberOfClassrooms);
        System.out.println("Teachers: " + numberOfTeachers);
        System.out.println("Laboratory: " + (hasLaboratory ? "Available" : "Not Available"));
        System.out.println("Library: " + (hasLibrary ? "Available" : "Not Available"));
    }

    // METHOD OVERRIDING: Override performMaintenance
    @Override
    public void performMaintenance() {
        System.out.println("\n=== School Maintenance Check for " + name + " ===");
        System.out.println("- Inspecting classrooms");
        System.out.println("- Checking educational equipment");
        System.out.println("- Testing fire safety systems");
        System.out.println("- Maintaining playground area");

        if (hasLaboratory) {
            System.out.println("- Checking laboratory equipment");
        }
        if (hasLibrary) {
            System.out.println("- Organizing library");
        }

        updateMaintenanceCost();
        maintenanceCost += numberOfClassrooms * 30; // Cost per classroom
        maintenanceCost += numberOfTeachers * 20; // Equipment per teacher

        System.out.println("School Maintenance completed! Total Cost: $" +
                String.format("%.2f", maintenanceCost));
    }

    // METHOD OVERRIDING: Override showServices
    @Override
    public void showServices() {
        System.out.println("\n=== School Services ===");
        System.out.println("1. " + educationLevel + " Education");
        System.out.println("2. Classroom Teaching");
        System.out.println("3. Extracurricular Activities");

        if (hasLibrary) {
            System.out.println("4. Library Services");
        }
        if (hasLaboratory) {
            System.out.println("5. Laboratory Facilities");
        }

        System.out.println("6. Sports and Recreation");
    }

    // METHOD OVERLOADING: School-specific methods
    public void enrollStudent(String studentName) {
        System.out.println("Student " + studentName + " enrolled in " + name);
        System.out.println("Education Level: " + educationLevel);
    }

    // METHOD OVERLOADING: Enroll with grade
    public void enrollStudent(String studentName, String grade) {
        System.out.println("Student " + studentName + " enrolled in " + name);
        System.out.println("Grade: " + grade + " (" + educationLevel + ")");
    }

    // METHOD OVERLOADING: Enroll with grade and classroom
    public void enrollStudent(String studentName, String grade, int classroomNumber) {
        if (classroomNumber > 0 && classroomNumber <= numberOfClassrooms) {
            System.out.println("Student " + studentName + " enrolled successfully");
            System.out.println("Grade: " + grade);
            System.out.println("Classroom: " + classroomNumber);
        } else {
            System.out.println("Invalid classroom number!");
        }
    }

    // School-specific method
    public void assignTeacher(String teacherName, String subject) {
        System.out.println("Teacher " + teacherName + " assigned to teach " + subject);
        System.out.println("School: " + name + " (" + educationLevel + ")");
    }
}