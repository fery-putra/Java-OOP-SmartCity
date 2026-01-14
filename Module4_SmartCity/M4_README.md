# MODULE 4: Advanced OOP Concepts

## Table of Contents
1. [Final Keyword](#1-final-keyword)
2. [Enum](#2-enum)
3. [Abstract Class](#3-abstract-class)
4. [Interface](#4-interface)
5. [Polymorphism](#5-polymorphism)
6. [Upcasting and Downcasting](#6-upcasting-and-downcasting)

---

## 1. FINAL Keyword

### Concept
Prevents modification or extension. Can be applied to variables, methods, and classes.

### a) FINAL VARIABLE
Cannot be changed after initialization.

```java
protected final String constructionDate;

public AbstractBuilding(String name, BuildingType type) {
    this.constructionDate = java.time.LocalDate.now().toString();
    // Set ONCE in constructor
}

// Later:
building.constructionDate = "2025-01-01";  // ✗ ERROR! Cannot change
```

**Local Final Variable:**
```java
public void method() {
    final int MAX = 100;
    MAX = 200;  // ✗ ERROR! Cannot reassign final variable
}
```

**Instance Final Variable:**
```java
public class ModernHospital {
    private final int licenseNumber;  // Must be initialized
    
    public ModernHospital() {
        this.licenseNumber = generateLicense();  // Set once in constructor
    }
    
    // No setter - licenseNumber cannot change
    public int getLicenseNumber() {
        return licenseNumber;  // Only getter
    }
}
```

**Use Cases for Final Variables:**
- Constants
- Values that should never change (license numbers, IDs, dates)
- Immutable objects

### b) STATIC FINAL
Constants shared by all objects.

```java
protected static final String CITY_NAME = "Smart Metro City";
protected static final double MAINTENANCE_BASE_RATE = 0.5;

// Interface constants are automatically static final
interface Serviceable {
    int MAX_SERVICE_REQUESTS = 100;  // public static final by default
    double SERVICE_FEE_MULTIPLIER = 1.5;
}
```

**Naming Convention:** UPPERCASE_WITH_UNDERSCORES

```java
public static final double PI = 3.14159;
public static final int MAX_CAPACITY = 1000;
public static final String DEFAULT_COLOR = "WHITE";
```

### c) FINAL METHOD
Cannot be overridden by subclasses.

```java
public final class PublicPark {
    // This method cannot be overridden
    public final void openToPublic() {
        System.out.println("Park is OPEN!");
        // This critical logic cannot be changed by subclasses
    }
    
    public final void closeToPublic() {
        System.out.println("Park is CLOSED!");
        // This critical logic is protected
    }
}
```

**Why Make Methods Final?**
1. **Preserve critical functionality**: Ensure behavior doesn't change
2. **Security**: Prevent malicious overriding
3. **Design decision**: Method is complete and shouldn't be modified

**Example:**
```java
public class Building {
    // Final method - subclasses cannot override
    public final void recordConstruction() {
        // Critical logging that should never change
        System.out.println("Building constructed on: " + new Date());
    }
    
    // Non-final method - can be overridden
    public void displayInfo() {
        // Subclasses can override this
    }
}

public class Hospital extends Building {
    @Override
    public void recordConstruction() { }  // ✗ ERROR! Cannot override final method
    
    @Override
    public void displayInfo() { }  // ✓ OK - not final
}
```

### d) FINAL CLASS
Cannot be extended/inherited.

```java
public final class PublicPark extends AbstractBuilding {
    // PublicPark is final - no class can extend it
}

// This would be an ERROR:
public class SpecialPark extends PublicPark {  // ✗ ERROR!
    // Cannot extend final class
}
```

**Why Make a Class Final?**
1. **Security**: Prevent malicious extensions
2. **Design decision**: Class is complete, no extension needed
3. **Performance**: Compiler can optimize final classes
4. **Immutability**: Common with immutable classes

**Famous Examples:**
- `String` class in Java is final
- `Integer`, `Double`, etc. wrapper classes are final
- `Math` class is final

### Final Comparison Table

| Type | Can Change Value? | Can Override? | Can Extend? |
|------|------------------|---------------|-------------|
| final variable | ✗ | - | - |
| final method | - | ✗ | - |
| final class | - | - | ✗ |

---

## 2. ENUM (Enumeration)

### Concept
Special class for representing fixed set of constants. Type-safe way to represent a group of related constants.

### Basic Enum

```java
public enum BuildingStatus {
    OPERATIONAL,
    UNDER_MAINTENANCE,
    UNDER_CONSTRUCTION,
    CLOSED,
    EMERGENCY
}
```

### Why Use Enum Instead of Constants?

**BAD (Using String constants):**
```java
String status = "operational";  // Typo! No compile error
String status = "xyz";          // Invalid value, no error!
if (status == "OPERATIONAL") { } // Wrong comparison, won't work
```

**GOOD (Using Enum):**
```java
BuildingStatus status = BuildingStatus.OPERATIONAL;  // Type-safe
BuildingStatus status = BuildingStatus.XYZ;  // ✗ Compile error!
if (status == BuildingStatus.OPERATIONAL) { } // ✓ Correct comparison
```

### Enum with Attributes and Methods

```java
public enum BuildingStatus {
    // Each constant with its values
    OPERATIONAL("Operational", "Building is fully functional", 1.0),
    UNDER_MAINTENANCE("Under Maintenance", "Being maintained", 0.5),
    UNDER_CONSTRUCTION("Under Construction", "Being built", 0.0),
    CLOSED("Closed", "Building is closed", 0.0),
    EMERGENCY("Emergency", "Emergency mode", 0.8);
    
    // Attributes for each enum constant (final by default)
    private final String displayName;
    private final String description;
    private final double operationalLevel;
    
    // Constructor (always private or package-private)
    BuildingStatus(String displayName, String description, double level) {
        this.displayName = displayName;
        this.description = description;
        this.operationalLevel = level;
    }
    
    // Getter methods
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getOperationalLevel() {
        return operationalLevel;
    }
    
    // Enum-specific methods
    public boolean isUsable() {
        return operationalLevel > 0.0;
    }
}
```

### Using Enum

```java
BuildingStatus status = BuildingStatus.OPERATIONAL;

// Access enum attributes
System.out.println(status.getDisplayName());    // "Operational"
System.out.println(status.getDescription());    // "Building is fully functional"
System.out.println(status.getOperationalLevel()); // 1.0

// Call enum methods
if (status.isUsable()) {
    System.out.println("Building can be used");
}

// Comparison
if (status == BuildingStatus.OPERATIONAL) {
    System.out.println("Building is operational");
}
```

### ENUM in SWITCH-CASE

```java
public void changeStatus(BuildingStatus newStatus) {
    switch (newStatus) {
        case OPERATIONAL:
            System.out.println("Building is now fully operational!");
            break;
        case UNDER_MAINTENANCE:
            System.out.println("Building maintenance has started.");
            break;
        case UNDER_CONSTRUCTION:
            System.out.println("Construction work in progress.");
            break;
        case CLOSED:
            System.out.println("Building has been closed.");
            break;
        case EMERGENCY:
            System.out.println("EMERGENCY MODE ACTIVATED!");
            break;
    }
}
```

**Advantages of Enum in Switch:**
- Compiler ensures all cases are covered (or default is provided)
- Type-safe (can't use invalid values)
- Better than string comparison
- No magic strings

### Enum Methods

```java
// Get all enum values
BuildingStatus[] allStatuses = BuildingStatus.values();
for (BuildingStatus status : allStatuses) {
    System.out.println(status);
}

// Convert string to enum
BuildingStatus status = BuildingStatus.valueOf("OPERATIONAL");

// Get enum name
String name = BuildingStatus.OPERATIONAL.name();  // "OPERATIONAL"

// Get enum ordinal (position)
int position = BuildingStatus.OPERATIONAL.ordinal();  // 0 (first position)
```

### Enum with Methods

```java
public enum BuildingStatus {
    OPERATIONAL("Operational", 1.0),
    CLOSED("Closed", 0.0);
    
    private final String displayName;
    private final double level;
    
    BuildingStatus(String displayName, double level) {
        this.displayName = displayName;
        this.level = level;
    }
    
    // Method using switch on enum itself
    public String getColorCode() {
        switch (this) {
            case OPERATIONAL:
                return "GREEN";
            case UNDER_MAINTENANCE:
                return "YELLOW";
            case CLOSED:
                return "RED";
            default:
                return "GRAY";
        }
    }
    
    // Method with logic
    public String getRecommendedAction() {
        switch (this) {
            case OPERATIONAL:
                return "Continue normal operations";
            case CLOSED:
                return "Review closure reasons and plan reopening";
            default:
                return "Review building status";
        }
    }
}
```

---

## 3. ABSTRACT CLASS

### Concept
A class that cannot be instantiated. Used as a base for other classes. Can have both abstract and concrete methods.

### Basic Structure

```java
public abstract class AbstractBuilding {
    // Can have regular attributes
    protected String name;
    protected int floors;
    
    // Can have constructor (for subclasses to call)
    public AbstractBuilding(String name, BuildingType type) {
        this.name = name;
        this.buildingType = type;
    }
    
    // ABSTRACT METHODS - subclasses MUST implement
    public abstract double calculateMaintenanceCost();
    public abstract void displaySpecificInfo();
    
    // CONCRETE METHODS - subclasses inherit as-is
    public void displayBasicInfo() {
        System.out.println("Name: " + name);
        System.out.println("Floors: " + floors);
    }
}
```

### Cannot Instantiate Abstract Class

```java
AbstractBuilding building = new AbstractBuilding();  // ✗ ERROR!
// Cannot instantiate abstract class
```

### But Can Create Subclass Objects

```java
AbstractBuilding hospital = new ModernHospital();  // ✓ OK!
// ModernHospital is concrete, AbstractBuilding is reference type
```

### Abstract Methods
Methods declared without implementation. Subclasses MUST provide implementation.

```java
// In AbstractBuilding:
public abstract double calculateMaintenanceCost();
// No body! Just declaration

// In ModernHospital (must implement):
@Override
public double calculateMaintenanceCost() {
    double baseCost = calculateTotalArea() * 0.5;
    double emergencyCost = emergencyBeds * 50;
    return baseCost + emergencyCost;
}

// In ModernSchool (must implement differently):
@Override
public double calculateMaintenanceCost() {
    double baseCost = calculateTotalArea() * 0.5;
    double classroomCost = numberOfClassrooms * 30;
    return baseCost + classroomCost;
}
```

### Why Abstract Methods?
1. **Force implementation**: All subclasses MUST provide the method
2. **Define contract**: "All buildings MUST calculate maintenance, but HOW depends on type"
3. **Enable polymorphism**: Can call method on parent reference

### Concrete Methods in Abstract Class
Regular methods with implementation. Subclasses inherit them.

```java
public abstract class AbstractBuilding {
    // CONCRETE METHOD - has implementation
    public void displayBasicInfo() {
        System.out.println("Name: " + name);
        System.out.println("Floors: " + floors);
    }
    
    // CONCRETE METHOD
    public void changeStatus(BuildingStatus newStatus) {
        this.status = newStatus;
        System.out.println("Status changed to: " + newStatus.getDisplayName());
    }
    
    // ABSTRACT METHOD - no implementation
    public abstract void displaySpecificInfo();
}

// Subclass automatically gets concrete methods
ModernHospital hospital = new ModernHospital();
hospital.displayBasicInfo();  // Inherited, works immediately
hospital.changeStatus(BuildingStatus.OPERATIONAL);  // Inherited
```

### Abstract Class vs Regular Class

| Feature | Abstract Class | Regular Class |
|---------|---------------|---------------|
| Can be instantiated | ✗ | ✓ |
| Can have abstract methods | ✓ | ✗ |
| Can have concrete methods | ✓ | ✓ |
| Can have constructors | ✓ | ✓ |
| Can have attributes | ✓ | ✓ |
| Must implement abstract methods | If extends abstract class | - |

### When to Use Abstract Class?
1. **Share code** among related classes
2. **Define template** for subclasses
3. **Force implementation** of certain methods
4. **Partial implementation**: Some methods implemented, some abstract

---

## 4. INTERFACE

### Concept
Contract that defines what a class must do, not how. A class can implement multiple interfaces.

### Basic Interface

```java
public interface Serviceable {
    // Constants (automatically public static final)
    int MAX_SERVICE_REQUESTS = 100;
    double SERVICE_FEE_MULTIPLIER = 1.5;
    
    // Abstract methods (automatically public abstract)
    void provideService(String serviceType);
    double calculateServiceCost();
    boolean isServiceAvailable(String serviceType);
    String[] getAvailableServices();
}
```

### Default Methods in Interface
Methods with implementation (Java 8+).

```java
public interface Serviceable {
    // Abstract methods
    void provideService(String serviceType);
    double calculateServiceCost();
    
    // Default method (has implementation)
    default void displayServiceInfo() {
        System.out.println("=== Service Information ===");
        System.out.println("Available Services:");
        String[] services = getAvailableServices();
        for (String service : services) {
            System.out.println("- " + service);
        }
    }
}
```

### Static Methods in Interface

```java
public interface Serviceable {
    // Static method
    static void displayInterfaceInfo() {
        System.out.println("Serviceable interface");
        System.out.println("Max requests: " + MAX_SERVICE_REQUESTS);
    }
}

// Call static method
Serviceable.displayInterfaceInfo();
```

### Implementing Interface

```java
public class ModernHospital extends AbstractBuilding 
                            implements Serviceable, Accessible {
    
    // Must implement ALL abstract methods from both interfaces
    
    // From Serviceable interface
    @Override
    public void provideService(String serviceType) {
        System.out.println("Providing service: " + serviceType);
    }
    
    @Override
    public double calculateServiceCost() {
        return 100.0 + (numberOfDoctors * 50.0);
    }
    
    @Override
    public boolean isServiceAvailable(String serviceType) {
        // Implementation
        return true;
    }
    
    @Override
    public String[] getAvailableServices() {
        return new String[]{"Emergency Care", "Surgery", "Consultation"};
    }
    
    // From Accessible interface
    @Override
    public boolean hasWheelchairAccess() {
        return true;
    }
    
    @Override
    public boolean hasElevator() {
        return true;
    }
    
    @Override
    public int getAccessibleParkingSpots() {
        return 10;
    }
}
```

### Multiple Interface Implementation

```java
// A class can extend ONE class but implement MULTIPLE interfaces
public class ModernHospital extends AbstractBuilding 
                            implements Serviceable, Accessible {
    // Implements methods from:
    // - AbstractBuilding (parent class) - abstract methods
    // - Serviceable (interface 1) - abstract methods
    // - Accessible (interface 2) - abstract methods
}
```

### Abstract Class vs Interface

| Feature | Abstract Class | Interface |
|---------|---------------|-----------|
| Methods | Abstract + Concrete | Abstract + Default + Static |
| Attributes | All types | Only constants (static final) |
| Constructor | Yes | No |
| Multiple inheritance | No (single) | Yes (multiple) |
| Access modifiers | All | Public only |
| When to use | IS-A relationship | CAN-DO relationship |

### When to Use Interface?

1. **Multiple "inheritance"**: Need behavior from multiple sources
2. **Contract**: Guarantee class has certain methods
3. **Loose coupling**: Define behavior without implementation
4. **Polymorphism**: Treat different classes uniformly

**Examples:**
```java
// IS-A relationship - use inheritance/abstract class
Hospital IS-A Building  → Hospital extends Building

// CAN-DO relationship - use interface
Hospital CAN provide services  → Hospital implements Serviceable
School CAN provide services    → School implements Serviceable
Hospital CAN be accessible     → Hospital implements Accessible
```

---

## 5. POLYMORPHISM

### Concept
"Many forms" - One reference type can refer to objects of different types. Core principle of OOP.

### Reference Polymorphism

```java
// AbstractBuilding reference can hold any subclass object
AbstractBuilding building1 = new ModernHospital();
AbstractBuilding building2 = new ModernSchool();
AbstractBuilding building3 = new ModernOffice();
AbstractBuilding building4 = new PublicPark();

// Array of AbstractBuilding can hold different types
AbstractBuilding[] buildings = new AbstractBuilding[4];
buildings[0] = new ModernHospital();   // Hospital object
buildings[1] = new ModernSchool();     // School object
buildings[2] = new ModernOffice();     // Office object
buildings[3] = new PublicPark();       // Park object
```

### Method Polymorphism
Same method call, different behavior based on actual type.

```java
// Loop through different building types
for (AbstractBuilding building : buildings) {
    building.displaySpecificInfo();  // Calls appropriate version
    building.calculateMaintenanceCost();  // Different calculation each time
}

// When building is ModernHospital → Hospital version runs
// When building is ModernSchool → School version runs
// When building is ModernOffice → Office version runs
// When building is PublicPark → Park version runs
```

### Why Polymorphism?

**WITHOUT Polymorphism - Messy!**
```java
ModernHospital[] hospitals = new ModernHospital[10];
ModernSchool[] schools = new ModernSchool[10];
ModernOffice[] offices = new ModernOffice[10];
PublicPark[] parks = new PublicPark[10];

// Need separate code for each type
for (ModernHospital h : hospitals) {
    h.displaySpecificInfo();
}
for (ModernSchool s : schools) {
    s.displaySpecificInfo();
}
for (ModernOffice o : offices) {
    o.displaySpecificInfo();
}
for (PublicPark p : parks) {
    p.displaySpecificInfo();
}
```

**WITH Polymorphism - Clean!**
```java
AbstractBuilding[] buildings = new AbstractBuilding[40];
// Can hold ALL types in ONE array

// Single loop handles all types
for (AbstractBuilding building : buildings) {
    building.displaySpecificInfo();  // Works for all!
    building.calculateMaintenanceCost();  // Each calculates differently
}
```

### Interface Polymorphism

```java
// Reference to interface type
Serviceable service = new ModernHospital();
service.provideService("Emergency Care");

service = new ModernSchool();  // Change to different type
service.provideService("Education");

service = new ModernOffice();  // Change again
service.provideService("Business Consulting");

// Same reference variable, different objects!
```

### Real-World Example

```java
public void provideServiceToAll(Serviceable[] serviceProviders) {
    for (Serviceable provider : serviceProviders) {
        provider.displayServiceInfo();  // Polymorphic call
        System.out.println("Cost: $" + provider.calculateServiceCost());
    }
}

// Can pass array of ANY classes that implement Serviceable
Serviceable[] providers = new Serviceable[3];
providers[0] = new ModernHospital();  // Hospital is Serviceable
providers[1] = new ModernSchool();    // School is Serviceable
providers[2] = new ModernOffice();    // Office is Serviceable

provideServiceToAll(providers);  // Works with all types!
```

### Benefits of Polymorphism

1. **Flexibility**: Write code that works with multiple types
2. **Extensibility**: Add new types without changing existing code
3. **Maintainability**: Change implementation without affecting users
4. **Simplicity**: Treat different objects uniformly

---

## 6. UPCASTING and DOWNCASTING

### UPCASTING
Automatically converting subclass reference to superclass reference. Always safe.

```java
// Creating ModernHospital object
ModernHospital hospital = new ModernHospital("City Hospital", 5, 1000, 200);

// UPCASTING - ModernHospital → AbstractBuilding (automatic)
AbstractBuilding building = hospital;

// OR directly:
AbstractBuilding building = new ModernHospital(...);  // Upcasting happens here
```

### What Can You Access After Upcasting?

```java
ModernHospital hospital = new ModernHospital();
AbstractBuilding building = hospital;  // UPCAST

// Can access AbstractBuilding methods ✓
building.displayBasicInfo();
building.calculateMaintenanceCost();
building.changeStatus(BuildingStatus.OPERATIONAL);

// Cannot access ModernHospital-specific methods ✗
building.admitPatient("John");  // ✗ ERROR! 
// AbstractBuilding doesn't have this method
// Even though the actual object IS a ModernHospital!
```

### Memory Reality

```
AbstractBuilding building = new ModernHospital();

Memory: [ModernHospital object with all hospital data]
        name, floors, emergencyBeds, numberOfDoctors, etc.

Reference type: AbstractBuilding 
                (can only see AbstractBuilding methods)

Actual type: ModernHospital 
             (the real object in memory)
```

### Interface Upcasting

```java
ModernHospital hospital = new ModernHospital();

// Upcast to interface
Serviceable service = hospital;  // ModernHospital → Serviceable
Accessible access = hospital;    // ModernHospital → Accessible
```

### DOWNCASTING
Explicitly converting superclass reference back to subclass reference. Requires type checking.

```java
AbstractBuilding building = buildings[0];  // Might be any type

// Check type first (IMPORTANT!)
if (building instanceof ModernHospital) {
    // DOWNCAST - AbstractBuilding → ModernHospital (explicit)
    ModernHospital hospital = (ModernHospital) building;
    
    // Now can access hospital-specific methods
    hospital.admitPatient("John", true);
    hospital.assignDoctor("John", "Dr. Smith");
    System.out.println("Doctors: " + hospital.getNumberOfDoctors());
}
```

### Why Check with instanceof?

```java
AbstractBuilding building = new ModernSchool();  // Actually a school

// BAD - No check, will crash!
ModernHospital hospital = (ModernHospital) building;  
// ✗ ClassCastException at runtime!

// GOOD - Check first
if (building instanceof ModernHospital) {
    ModernHospital hospital = (ModernHospital) building;
    // Safe to use hospital-specific methods
} else if (building instanceof ModernSchool) {
    ModernSchool school = (ModernSchool) building;  // ✓ Safe
    // Safe to use school-specific methods
}
```

### Complete Downcasting Example

```java
public void performTypeSpecificOperation(AbstractBuilding building) {
    // DOWNCASTING based on actual type
    
    if (building instanceof ModernHospital) {
        ModernHospital hospital = (ModernHospital) building;  // Downcast
        System.out.println("=== Hospital Operations ===");
        System.out.println("Emergency beds: " + hospital.getEmergencyBeds());
        System.out.println("License: " + hospital.getLicenseNumber());
        hospital.admitPatient("Patient", true);
        
    } else if (building instanceof ModernSchool) {
        ModernSchool school = (ModernSchool) building;  // Downcast
        System.out.println("=== School Operations ===");
        System.out.println("Classrooms: " + school.getNumberOfClassrooms());
        System.out.println("Accreditation: " + school.getAccreditationId());
        school.enrollStudent("Student", "Grade 1");
        
    } else if (building instanceof PublicPark) {
        PublicPark park = (PublicPark) building;  // Downcast
        System.out.println("=== Park Operations ===");
        park.organizeEvent("Festival", 500);
        park.openToPublic();  // Final method specific to PublicPark
        
    } else {
        System.out.println("Unknown building type");
    }
}
```

### Upcasting vs Downcasting Summary

```
UPCASTING (Automatic):
┌──────────────┐
│ModernHospital│ (Specific)
└──────┬───────┘
       ↓ Automatic
┌──────────────┐
│AbstractBuild.│ (General)
└──────────────┘

- Always safe ✓
- Automatic (no cast needed)
- Loses access to specific methods
- Syntax: AbstractBuilding b = new ModernHospital();

DOWNCASTING (Manual):
┌──────────────┐
│AbstractBuild.│ (General)
└──────┬───────┘
       ↓ Manual (Type Cast)
┌──────────────┐
│ModernHospital│ (Specific)
└──────────────┘

- Needs type checking (instanceof)
- Manual (explicit cast required)
- Gains access to specific methods
- Can fail if wrong type ✗
- Syntax: ModernHospital h = (ModernHospital) building;
```

### Real-World Analogy

```
Upcasting:
"This German Shepherd is a Dog" ✓ (always true)
GermanShepherd gs = new GermanShepherd();
Dog dog = gs;  // Automatic upcasting

Downcasting:
"This Dog is a German Shepherd" (maybe true, need to check!)
Dog dog = getRandomDog();
if (dog instanceof GermanShepherd) {
    GermanShepherd gs = (GermanShepherd) dog;  // Safe downcasting
}
```


## Common Mistakes to Avoid

### 1. Trying to instantiate abstract class or interface
```java
AbstractBuilding b = new AbstractBuilding();  // ✗ ERROR!
Serviceable s = new Serviceable();  // ✗ ERROR!
```

### 2. Not implementing all interface methods
```java
public class Hospital implements Serviceable {
    // ✗ ERROR! Must implement ALL methods from Serviceable
    @Override
    public void provideService(String type) { }
    // Missing other required methods!
}
```

### 3. Downcasting without instanceof check
```java
AbstractBuilding building = getBuilding();
ModernHospital hospital = (ModernHospital) building;  // ✗ Dangerous!
// What if building is actually a School?
```

### 4. Confusing abstract class and interface
```java
// ✗ Wrong - trying to use interface like abstract class
public interface MyInterface {
    private String name;  // ✗ ERROR! Interfaces can't have instance variables
    public MyInterface() { }  // ✗ ERROR! Interfaces can't have constructors
}
```

---
