# MODULE 3: Inheritance and Constructors

## Table of Contents
1. [Inheritance](#1-inheritance)
2. [Constructors](#2-constructors)
3. [Super Keyword](#3-super-keyword)
4. [Method Overriding](#4-method-overriding)
5. [Overloading vs Overriding](#5-overloading-vs-overriding)

---

## 1. INHERITANCE

### Concept
A class (child/subclass) can inherit attributes and methods from another class (parent/superclass). This promotes code reuse and establishes relationships between classes.

### Syntax
```java
public class Hospital extends Building {
    // Hospital inherits everything from Building
}
```

### In the Code

**PARENT CLASS (Base/Super class)**
```java
public class Building {
    protected String name;    // Available to child classes
    protected int floors;
    protected double area;
    
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Floors: " + floors);
    }
}
```

**CHILD CLASS (Derived/Sub class)**
```java
public class Hospital extends Building {
    // Inherits: name, floors, area, displayInfo()
    // Plus adds its own attributes:
    private int emergencyBeds;
    private int numberOfDoctors;
    
    // Plus adds its own methods:
    public void admitPatient(String patientName) {
        // Hospital-specific method
    }
}
```

### What Gets Inherited

```java
Hospital hospital = new Hospital();

// Can use inherited attributes (if accessible)
hospital.name = "City Hospital";      // From Building
hospital.floors = 5;                  // From Building
hospital.area = 1000.0;               // From Building

// Can use inherited methods
hospital.displayInfo();               // From Building

// Can use its own attributes
hospital.emergencyBeds = 20;          // Hospital-specific
hospital.numberOfDoctors = 15;        // Hospital-specific

// Can use its own methods
hospital.admitPatient("John Doe");    // Hospital-specific
```

### Inheritance Hierarchy
```
        Building (Parent)
           /  |  \
          /   |   \
    Hospital School Office Park
     (Child) (Child) (Child) (Child)
```

### Why Use Inheritance?

#### 1. Code Reuse
**WITHOUT Inheritance - REPETITION!**
```java
class Hospital {
    String name;      // Repeated
    int floors;       // Repeated
    double area;      // Repeated
    // ... plus hospital-specific fields
    int emergencyBeds;
    int numberOfDoctors;
}

class School {
    String name;      // SAME as Hospital!
    int floors;       // SAME as Hospital!
    double area;      // SAME as Hospital!
    // ... plus school-specific fields
    int numberOfClassrooms;
    int numberOfTeachers;
}
```

**WITH Inheritance - NO REPETITION!**
```java
class Building {
    String name;      // Define once
    int floors;       // Define once
    double area;      // Define once
}

class Hospital extends Building {
    // Inherits name, floors, area
    // Only add hospital-specific fields
    int emergencyBeds;
    int numberOfDoctors;
}

class School extends Building {
    // Inherits name, floors, area
    // Only add school-specific fields
    int numberOfClassrooms;
    int numberOfTeachers;
}
```

#### 2. Organization
Group related classes in logical hierarchy

#### 3. Polymorphism
Treat different types uniformly (covered in Module 4)

### Access Modifiers in Inheritance

| Modifier | Inherited? | Accessible in Subclass? |
|----------|-----------|------------------------|
| private | Yes (but not directly accessible) | No |
| protected | Yes | Yes |
| public | Yes | Yes |

```java
class Building {
    private String buildingId;      // Not accessible in Hospital
    protected String name;          // Accessible in Hospital
    public int floors;              // Accessible in Hospital
}

class Hospital extends Building {
    public void someMethod() {
        // System.out.println(buildingId);  // ✗ ERROR - private
        System.out.println(name);           // ✓ OK - protected
        System.out.println(floors);         // ✓ OK - public
    }
}
```

### Types of Inheritance in Java

**Single Inheritance** (Supported)
```java
class A { }
class B extends A { }  // B inherits from A
```

**Multilevel Inheritance** (Supported)
```java
class A { }
class B extends A { }
class C extends B { }  // C inherits from B, which inherits from A
```

**Multiple Inheritance** (NOT Supported with classes, but with interfaces)
```java
class A { }
class B { }
class C extends A, B { }  // ✗ ERROR! Java doesn't allow this

// But this is OK:
interface I1 { }
interface I2 { }
class C implements I1, I2 { }  // ✓ OK with interfaces
```

---

## 2. CONSTRUCTORS

### Concept
Special methods that initialize objects when created. Same name as class, no return type.

### Why Constructors?
```java
// WITHOUT constructor - manual initialization (tedious)
Hospital h = new Hospital();
h.name = "City Hospital";
h.floors = 5;
h.area = 1000.0;
h.emergencyBeds = 20;
h.numberOfDoctors = 15;

// WITH constructor - automatic initialization (easy)
Hospital h = new Hospital("City Hospital", 5, 1000.0, 20, 15);
```

### Types of Constructors

#### a) Default Constructor (No Parameters)
```java
public Hospital() {
    // Called when: new Hospital()
    this.type = "Hospital";
    this.emergencyBeds = 0;
    this.numberOfDoctors = 0;
    this.hasEmergencyRoom = false;
}

// Usage:
Hospital h = new Hospital();  // Uses default constructor
```

#### b) Parameterized Constructor
```java
public Hospital(String name, int floors, double area) {
    // Called when: new Hospital("City Hospital", 5, 1000.0)
    this.name = name;
    this.floors = floors;
    this.area = area;
}

// Usage:
Hospital h = new Hospital("City Hospital", 5, 1000.0);
```

#### c) Constructor with All Parameters
```java
public Hospital(String name, int floors, double area, int capacity,
                int emergencyBeds, int numberOfDoctors, String specialization) {
    this.name = name;
    this.floors = floors;
    this.area = area;
    this.capacity = capacity;
    this.emergencyBeds = emergencyBeds;
    this.numberOfDoctors = numberOfDoctors;
    this.specialization = specialization;
}

// Usage:
Hospital h = new Hospital("City Hospital", 5, 1000.0, 200, 20, 15, "General");
```

### Constructor Overloading
Multiple constructors with different parameters:

```java
public class Hospital extends Building {
    // Constructor 1
    public Hospital() {
        // No parameters
    }
    
    // Constructor 2
    public Hospital(String name, int floors, double area) {
        // Some parameters
    }
    
    // Constructor 3
    public Hospital(String name, int floors, double area, 
                    int capacity, int beds, int doctors, String spec) {
        // All parameters
    }
}

// Usage:
Hospital h1 = new Hospital();                          // Calls Constructor 1
Hospital h2 = new Hospital("CH", 5, 1000);             // Calls Constructor 2
Hospital h3 = new Hospital("CH", 5, 1000, 200, 
                          20, 15, "General");          // Calls Constructor 3
```

### Default Constructor Note
```java
// If you don't write ANY constructor, Java provides default:
public class Building {
    // Java automatically adds:
    // public Building() { }
}

// But if you write ANY constructor, Java doesn't provide default:
public class Building {
    public Building(String name) {
        this.name = name;
    }
    // Java does NOT add default constructor
}

// This would be an error:
Building b = new Building();  // ✗ ERROR! No default constructor
Building b = new Building("Hospital");  // ✓ OK
```

### The 'this' Keyword in Constructors
```java
public Hospital(String name, int floors) {
    this.name = name;      // 'this.name' = instance variable
    this.floors = floors;  // 'name' and 'floors' = parameters
}
```

---

## 3. SUPER Keyword

### Concept
Refers to the parent class. Used to:
1. Call parent class constructor
2. Access parent class methods/attributes

### a) Calling Parent Constructor

```java
public class Building {
    protected String name;
    protected String type;
    
    public Building() {
        System.out.println("Building default constructor");
    }
    
    public Building(String name, String type) {
        this.name = name;
        this.type = type;
        System.out.println("Building parameterized constructor");
    }
}

public class Hospital extends Building {
    private int emergencyBeds;
    
    public Hospital(String name, int floors, double area) {
        super(name, "Hospital");  // Call parent constructor FIRST
        // After parent is initialized, initialize hospital-specific
        this.emergencyBeds = 20;
        System.out.println("Hospital constructor");
    }
}
```

### Constructor Chaining
```java
public class Building {
    public Building() {
        System.out.println("1. Building default constructor");
    }
    
    public Building(String name, String type) {
        this();  // Call Building's default constructor first
        System.out.println("2. Building(name, type) constructor");
        this.name = name;
        this.type = type;
    }
}

public class Hospital extends Building {
    public Hospital(String name, int floors, double area) {
        super(name, "Hospital");  // Call Building's constructor
        System.out.println("3. Hospital constructor");
    }
}

// Creating object:
Hospital h = new Hospital("City Hospital", 5, 1000);

// Output shows chain:
// 1. Building default constructor
// 2. Building(name, type) constructor
// 3. Hospital constructor
```

### Order of Execution
**Rule:** Parent constructor ALWAYS runs before child constructor

```java
class Parent {
    public Parent() {
        System.out.println("Parent constructor");
    }
}

class Child extends Parent {
    public Child() {
        // super() is called automatically if not written
        System.out.println("Child constructor");
    }
}

Child c = new Child();
// Output:
// Parent constructor
// Child constructor
```

### Important Rules for super()
1. Must be FIRST statement in constructor
2. Can only call one parent constructor
3. If not written, Java automatically calls `super()` (no-arg constructor)

```java
public Hospital(String name) {
    System.out.println("Starting");
    super(name, "Hospital");  // ✗ ERROR! Must be first statement
}

public Hospital(String name) {
    super(name, "Hospital");  // ✓ OK - First statement
    System.out.println("Hospital created");
}
```

### b) Calling Parent Methods

```java
public class Building {
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Floors: " + floors);
    }
}

public class Hospital extends Building {
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call parent's version first
        // Then add hospital-specific info
        System.out.println("=== Hospital Specific Info ===");
        System.out.println("Emergency Beds: " + emergencyBeds);
        System.out.println("Doctors: " + numberOfDoctors);
    }
}
```

### c) Accessing Parent Attributes

```java
public class Building {
    protected String name;
}

public class Hospital extends Building {
    public void printName() {
        System.out.println(super.name);  // Access parent's name
        System.out.println(this.name);   // Same thing (inherited)
        System.out.println(name);        // Same thing
    }
}
```

---

## 4. METHOD OVERRIDING

### Concept
Child class provides its own implementation of a parent's method.

### Basic Example

```java
// PARENT CLASS
public class Building {
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Floors: " + floors);
    }
}

// CHILD CLASS
public class Hospital extends Building {
    @Override  // Annotation (good practice)
    public void displayInfo() {
        // Completely different implementation
        System.out.println("=== HOSPITAL INFORMATION ===");
        System.out.println("Hospital Name: " + name);
        System.out.println("Number of Floors: " + floors);
        System.out.println("Emergency Beds: " + emergencyBeds);
        System.out.println("Medical Staff: " + numberOfDoctors);
    }
}
```

### @Override Annotation
```java
@Override
public void displayInfo() {
    // Implementation
}
```

**Benefits:**
- Tells compiler "I'm overriding a parent method"
- Compiler checks if parent method actually exists
- Prevents typos

```java
class Building {
    public void displayInfo() { }
}

class Hospital extends Building {
    @Override
    public void displayInf() { }  // ✗ ERROR! Typo detected by compiler
}
```

### Calling Parent Method from Override
```java
@Override
public void displayInfo() {
    super.displayInfo();  // Call parent version
    // Add additional information
    System.out.println("Emergency Beds: " + emergencyBeds);
}
```

### Rules for Overriding

1. **Same method signature**
```java
// Parent
public void displayInfo() { }

// Child - Must match exactly
@Override
public void displayInfo() { }  // ✓ OK
```

2. **Same or broader access modifier**
```java
// Parent
protected void method() { }

// Child - Can be same or broader
@Override
protected void method() { }  // ✓ OK - same
@Override
public void method() { }     // ✓ OK - broader

@Override
private void method() { }    // ✗ ERROR - more restrictive
```

3. **Same or compatible return type**
```java
// Parent
public Building getBuilding() { }

// Child
@Override
public Hospital getBuilding() { }  // ✓ OK - Hospital is a Building (covariant)
```

4. **Cannot override final methods**
```java
// Parent
public final void method() { }

// Child
@Override
public void method() { }  // ✗ ERROR - cannot override final
```

5. **Cannot override static methods** (but can hide them)
```java
// Parent
public static void staticMethod() { }

// Child
public static void staticMethod() { }  // Not overriding, hiding
```

### Example - Different Implementations

```java
public class Building {
    public void performMaintenance() {
        System.out.println("General building maintenance");
    }
}

public class Hospital extends Building {
    @Override
    public void performMaintenance() {
        System.out.println("=== Hospital Maintenance ===");
        System.out.println("- Checking medical equipment");
        System.out.println("- Sanitizing areas");
        System.out.println("- Testing emergency systems");
    }
}

public class School extends Building {
    @Override
    public void performMaintenance() {
        System.out.println("=== School Maintenance ===");
        System.out.println("- Inspecting classrooms");
        System.out.println("- Checking playground");
        System.out.println("- Testing fire alarms");
    }
}

// Usage:
Hospital h = new Hospital();
h.performMaintenance();  // Calls Hospital's version

School s = new School();
s.performMaintenance();  // Calls School's version
```

---

## 5. OVERLOADING vs OVERRIDING

### Quick Comparison Table

| Feature | Overloading | Overriding |
|---------|------------|------------|
| Location | Same class | Parent and child class |
| Method signature | Different parameters | Same parameters |
| Decision time | Compile time | Runtime |
| Purpose | Multiple versions of method | Change inherited behavior |
| Access modifier | Any | Same or broader |
| Keyword | - | @Override |

### OVERLOADING
Same class, same method name, **different parameters**

```java
class Calculator {
    // Different number of parameters
    int add(int a, int b) { 
        return a + b; 
    }
    
    int add(int a, int b, int c) { 
        return a + b + c; 
    }
    
    // Different type of parameters
    double add(double a, double b) { 
        return a + b; 
    }
}

Calculator calc = new Calculator();
calc.add(5, 10);        // Calls first method
calc.add(5, 10, 15);    // Calls second method
calc.add(5.5, 10.5);    // Calls third method
```

### OVERRIDING
Parent and child class, same method signature, **different implementation**

```java
class Building {
    void displayInfo() { 
        System.out.println("Basic building info");
    }
}

class Hospital extends Building {
    @Override
    void displayInfo() { 
        System.out.println("Hospital specific info");
    }
}

Building b = new Building();
b.displayInfo();  // "Basic building info"

Hospital h = new Hospital();
h.displayInfo();  // "Hospital specific info"
```

### Visual Comparison

**OVERLOADING:**
```
     Calculator
   /     |      \
add(int,int) add(int,int,int) add(double,double)
```

**OVERRIDING:**
```
Building → displayInfo() [parent implementation]
    ↓
Hospital → displayInfo() [overridden implementation]
```

### Can You Have Both?

**Yes!** A child class can override AND overload:

```java
class Building {
    // Parent method
    void displayInfo() {
        System.out.println("Basic info");
    }
}

class Hospital extends Building {
    // OVERRIDE parent method
    @Override
    void displayInfo() {
        System.out.println("Hospital basic info");
    }
    
    // OVERLOAD the overridden method
    void displayInfo(boolean detailed) {
        displayInfo();  // Call overridden version
        if (detailed) {
            System.out.println("Detailed hospital info");
        }
    }
    
    // OVERLOAD again
    void displayInfo(String section) {
        System.out.println(section + " information");
    }
}
```

## Common Mistakes to Avoid

### 1. Forgetting to call super()
```java
public class Hospital extends Building {
    public Hospital(String name) {
        // If Building has no default constructor, this will error
        this.name = name;  // ✗ Parent not initialized!
    }
    
    // CORRECT:
    public Hospital(String name) {
        super(name, "Hospital");  // ✓ Initialize parent first
        this.emergencyBeds = 20;
    }
}
```

### 2. Calling super() not as first statement
```java
public Hospital(String name) {
    this.emergencyBeds = 20;
    super(name, "Hospital");  // ✗ ERROR! Must be first
}
```

### 3. Trying to override private methods
```java
class Building {
    private void calculate() { }
}

class Hospital extends Building {
    @Override
    private void calculate() { }  // ✗ ERROR! Can't override private
}
```

### 4. Making overridden method more restrictive
```java
class Building {
    public void method() { }
}

class Hospital extends Building {
    @Override
    private void method() { }  // ✗ ERROR! Can't be more restrictive
}
```

### 5. Confusing overloading and overriding
```java
class Building {
    void method(int a) { }
}

class Hospital extends Building {
    void method(double a) { }  // This is OVERLOADING, not overriding!
}
```

---

