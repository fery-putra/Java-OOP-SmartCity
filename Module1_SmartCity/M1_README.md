# MODULE 1: Foundation - Basic Object-Oriented Programming

## Table of Contents
1. [Class and Object](#1-class-and-object)
2. [Attributes](#2-attributes)
3. [Methods](#3-methods)
4. [Data Types](#4-data-types)
5. [Conditional Statements](#5-conditional-statements)
6. [Switch-Case](#6-switch-case)
7. [Loops](#7-loops)
8. [Input/Output](#8-input-output)

---

## 1. CLASS and OBJECT

### Concept
A **class** is a blueprint or template for creating objects. An **object** is an instance of a class with actual data.

### In the Code
```java
// Class definition - blueprint for Building
class Building {
    String name;           // Attributes
    String type;
    int floors;
    // ... methods
}

// Creating objects (instances)
Building hospital = new Building();  // Object 1
Building school = new Building();    // Object 2
```

### Real-World Analogy
- **Class** = House blueprint
- **Object** = Actual houses built from the blueprint
- Each house (object) has its own color, size, etc. (attributes)

### Key Points
- A class is a template
- An object is a concrete instance
- You can create multiple objects from one class
- Each object has its own data

---

## 2. ATTRIBUTES (Instance Variables)

### Concept
Attributes are variables that belong to an object. Each object has its own copy.

### In the Code
```java
class Building {
    String name;           // Each building has its own name
    int floors;            // Each building has its own floor count
    double area;           // Each building has its own area
    boolean isOperational; // Each building has its own status
}
```

### Example
```java
Building hospital = new Building();
hospital.name = "City Hospital";
hospital.floors = 5;

Building school = new Building();
school.name = "Primary School";
school.floors = 3;
// hospital and school have different values for the same attributes
```

### Key Points
- Attributes define the state/properties of an object
- Each object has its own copy of attributes
- Attributes can be different data types

---

## 3. METHODS

### Concept
Methods are functions that belong to a class. They define what objects can do.

### Types of Methods

#### a) Method without parameters and return value
```java
void displayInfo() {
    System.out.println("Name: " + name);
}
```

#### b) Method with parameters
```java
void calculateEfficiency(int currentOccupancy) {
    // Uses the parameter to calculate something
    double efficiency = (currentOccupancy * 100.0) / capacity;
}
```

#### c) Method with return value
```java
double calculateTotalArea() {
    return area * floors;  // Returns a value
}
```

### When to use what
- **No parameters + no return**: Just performing an action (like printing)
- **Parameters + no return**: Performing an action with input data
- **Parameters + return**: Calculating and giving back a result

### Key Points
- Methods define behavior/actions
- Can accept input (parameters)
- Can return output (return value)
- Called using dot notation: `object.methodName()`

---

## 4. DATA TYPES

### In the Code
```java
String name;           // String - text data
int floors;            // int - whole numbers
double area;           // double - decimal numbers
boolean isOperational; // boolean - true/false
```

### Why different types?
- `int`: When you need whole numbers (floors can't be 2.5)
- `double`: When you need decimals (area can be 150.75 sq meters)
- `boolean`: When you need yes/no, true/false
- `String`: When you need text

### Primitive vs Reference Types
**Primitive Types:**
- `int`, `double`, `boolean`, `char`, `byte`, `short`, `long`, `float`
- Store actual values
- Lowercase names

**Reference Types:**
- `String`, `Building`, Arrays, etc.
- Store reference to object
- Uppercase names (by convention)

---

## 5. CONDITIONAL STATEMENTS (if-else)

### Concept
Make decisions in your code based on conditions.

### In the Code
```java
void calculateEfficiency(int currentOccupancy) {
    if (currentOccupancy > capacity) {
        System.out.println("WARNING: Building is overcrowded!");
    } else if (currentOccupancy == 0) {
        System.out.println("Building is empty.");
    } else {
        double efficiency = (currentOccupancy * 100.0) / capacity;
        System.out.println("Building Efficiency: " + efficiency + "%");
    }
}
```

### Flow Diagram
```
Start
  ↓
Is occupancy > capacity? → YES → Print warning → END
  ↓ NO
Is occupancy == 0? → YES → Print "empty" → END
  ↓ NO
Calculate efficiency → Print efficiency → END
```

### Comparison Operators
- `==` equals
- `!=` not equals
- `>` greater than
- `<` less than
- `>=` greater than or equal
- `<=` less than or equal

### Logical Operators
- `&&` AND (both conditions must be true)
- `||` OR (at least one condition must be true)
- `!` NOT (negates condition)

---

## 6. SWITCH-CASE

### Concept
Choose one option from multiple possibilities. Cleaner than many if-else statements.

### In the Code
```java
void performMaintenance() {
    switch (type.toLowerCase()) {
        case "hospital":
            System.out.println("- Checking medical equipment");
            System.out.println("- Sanitizing all areas");
            break;  // Important! Stops execution
        case "school":
            System.out.println("- Inspecting classrooms");
            break;
        case "office":
            System.out.println("- Checking HVAC systems");
            break;
        default:  // If no case matches
            System.out.println("- General building inspection");
            break;
    }
}
```

### Key Points
- Each `case` is one possible value
- `break` prevents fall-through to next case
- `default` is executed if nothing matches
- More readable than multiple if-else for same variable

### When to Use
- **Switch**: Checking one variable against multiple values
- **If-else**: Complex conditions, ranges, multiple variables

---

## 7. LOOPS

### Concept
Repeat code multiple times.

### a) FOR LOOP
**Use when:** You know how many times to repeat

```java
for (int i = 0; i < count; i++) {
    System.out.println("\n--- Building " + (i + 1) + " ---");
    buildings[i].displayInfo();
}
```

**How it works:**
1. `int i = 0` - Initialize (start at 0)
2. `i < count` - Condition (continue while true)
3. `i++` - Increment (add 1 after each iteration)

**Example:** If count = 3:
```
i = 0: Display Building 1
i = 1: Display Building 2
i = 2: Display Building 3
i = 3: Stop (not < 3)
```

### b) WHILE LOOP
**Use when:** You don't know how many times, but have a condition

```java
int index = 0;
while (index < count) {
    totalFloors += buildings[index].floors;
    index++;
}
```

**How it works:**
1. Check condition before each iteration
2. If true → execute body
3. If false → exit loop

### c) DO-WHILE LOOP
**Use when:** Must execute at least once

```java
double averageArea = 0;
int i = 0;
do {
    averageArea += buildings[i].calculateTotalArea();
    i++;
} while (i < count);
```

**Difference from while:**
- `while`: Check → Execute
- `do-while`: Execute → Check (guarantees at least one execution)

### Comparison Table
| Loop Type | When to Use | Checks Condition |
|-----------|-------------|------------------|
| for | Known iterations | Before each iteration |
| while | Unknown iterations | Before each iteration |
| do-while | At least once | After each iteration |

---

## 8. INPUT/OUTPUT

### Input (Reading from user)
```java
Scanner scanner = new Scanner(System.in);

System.out.print("Enter building name: ");
String name = scanner.nextLine();  // Read text

System.out.print("Enter number of floors: ");
int floors = scanner.nextInt();    // Read integer

System.out.print("Enter area: ");
double area = scanner.nextDouble(); // Read decimal

System.out.print("Is operational? (true/false): ");
boolean operational = scanner.nextBoolean(); // Read boolean
```

### Output (Printing to console)
```java
System.out.println("Name: " + name);  // Print with new line
System.out.print("Enter choice: ");    // Print without new line
```

### Important Scanner Methods
- `nextLine()` - Read entire line of text
- `nextInt()` - Read integer
- `nextDouble()` - Read decimal number
- `nextBoolean()` - Read true/false
- `next()` - Read single word

### Common Issue - Buffer Problem
```java
int number = scanner.nextInt();
scanner.nextLine();  // Clear the newline character
String text = scanner.nextLine();  // Now works correctly
```

## Common Mistakes to Avoid

1. **Forgetting to initialize objects**
   ```java
   Building b;  // Not initialized!
   b.name = "Hospital";  // NullPointerException!
   
   // Correct:
   Building b = new Building();
   b.name = "Hospital";
   ```

2. **Infinite loops**
   ```java
   int i = 0;
   while (i < 10) {
       System.out.println(i);
       // Forgot i++; - infinite loop!
   }
   ```

3. **Forgetting break in switch**
   ```java
   switch (type) {
       case "hospital":
           System.out.println("Hospital");
           // No break - falls through!
       case "school":
           System.out.println("School");
   }
   ```

4. **Using == for String comparison**
   ```java
   if (name == "Hospital") { }  // Wrong!
   if (name.equals("Hospital")) { }  // Correct!
   ```

---


