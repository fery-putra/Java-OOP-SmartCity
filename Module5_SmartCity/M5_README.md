# MODULE 5: Exception Handling and ArrayList

## Table of Contents
1. [Exception Handling Overview](#1-exception-handling-overview)
2. [Try-Catch Block](#2-try-catch-block)
3. [Multiple Catch Blocks](#3-multiple-catch-blocks)
4. [Custom Exceptions](#4-custom-exceptions)
5. [Throwing Exceptions](#5-throwing-exceptions)
6. [Finally Block](#6-finally-block)
7. [ArrayList](#7-arraylist)
8. [ArrayList Methods](#8-arraylist-methods)
9. [Iterator](#9-iterator)
10. [Enhanced For Loop](#10-enhanced-for-loop)

---

## 1. EXCEPTION HANDLING Overview

### Concept
Handle errors gracefully instead of crashing the program. Exceptions are events that disrupt normal program flow.

### Without Exception Handling - Program Crashes
```java
public static void main(String[] args) {
    int result = 10 / 0;  // ArithmeticException - CRASH!
    System.out.println("This never executes");  // Never reached
}

// Output:
// Exception in thread "main" java.lang.ArithmeticException: / by zero
// Program terminates
```

### With Exception Handling - Program Continues
```java
public static void main(String[] args) {
    try {
        int result = 10 / 0;  // Error occurs
    } catch (ArithmeticException e) {
        System.out.println("Cannot divide by zero!");  // Handle error
    }
    System.out.println("Program continues");  // ✓ This executes
}

// Output:
// Cannot divide by zero!
// Program continues
```

### Why Exception Handling?
1. **Prevent crashes**: Program keeps running
2. **User-friendly**: Show meaningful error messages
3. **Debugging**: Easier to find and fix problems
4. **Recovery**: Attempt alternative actions
5. **Resource cleanup**: Ensure resources are closed properly

### Exception Hierarchy
```
Throwable
├── Error (Serious problems, usually can't handle)
│   ├── OutOfMemoryError
│   └── StackOverflowError
└── Exception (Problems we can handle)
    ├── IOException
    │   └── FileNotFoundException
    ├── SQLException
    └── RuntimeException (Unchecked)
        ├── NullPointerException
        ├── ArrayIndexOutOfBoundsException
        ├── ArithmeticException
        └── InputMismatchException
```

---

## 2. TRY-CATCH Block

### Syntax
```java
try {
    // Code that might throw an exception
} catch (ExceptionType e) {
    // Code to handle the exception
}
```

### Basic Example
```java
try {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter a number: ");
    int number = scanner.nextInt();  // Might throw InputMismatchException
    System.out.println("You entered: " + number);
} catch (InputMismatchException e) {
    System.out.println("That's not a valid number!");
}
```

### In the Code
```java
private static void addHospital() throws CityCapacityException {
    try {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        // Validation that might throw exception
        if (name.isEmpty()) {
            throw new InvalidBuildingDataException("Name cannot be empty", "name");
        }
        
        System.out.print("Enter floors: ");
        int floors = scanner.nextInt();  // Might throw InputMismatchException
        
        ModernHospital hospital = new ModernHospital(name, floors, area, capacity);
        buildingManager.addBuilding(hospital);  // Might throw CityCapacityException
        
    } catch (InvalidBuildingDataException e) {
        System.err.println("Invalid data: " + e.getMessage());
    } catch (InputMismatchException e) {
        scanner.nextLine();  // Clear buffer
        System.err.println("Please enter valid numbers!");
    } catch (CityCapacityException e) {
        throw e;  // Re-throw to be handled by caller
    }
}
```

### How It Works

**Flow Diagram:**
```
┌─────────────┐
│  try block  │
│ line 1 ✓    │ Executes
│ line 2 ✗    │ Exception occurs → Jump to catch
│ line 3      │ Skipped
└─────────────┘
       ↓
┌─────────────┐
│ catch block │
│ Handle error│ Executes
└─────────────┘
       ↓
┌─────────────┐
│ After try   │
│ Continue... │ Executes
└─────────────┘
```

**Code Example:**
```java
try {
    System.out.println("Line 1");  // ✓ Executes
    int x = 10 / 0;                // ✗ Exception! Jump to catch
    System.out.println("Line 2");  // Skipped
} catch (ArithmeticException e) {
    System.out.println("Error!");  // ✓ Executes
}
System.out.println("After");       // ✓ Executes

// Output:
// Line 1
// Error!
// After
```

### Exception Object Methods
```java
catch (Exception e) {
    e.getMessage();       // Get error message
    e.toString();         // Get exception class and message
    e.printStackTrace();  // Print full stack trace (for debugging)
}
```

---

## 3. MULTIPLE CATCH Blocks

### Concept
Handle different types of exceptions differently.

### Syntax
```java
try {
    // Code that might throw different exceptions
} catch (SpecificException1 e) {
    // Handle specific exception 1
} catch (SpecificException2 e) {
    // Handle specific exception 2
} catch (Exception e) {
    // Handle any other exception
}
```

### In the Code
```java
try {
    buildingManager.addBuilding(hospital);
    
} catch (CityCapacityException e) {
    // Handle city full error
    System.err.println("❌ CITY CAPACITY ERROR:");
    System.err.println(e.getDetailedMessage());
    
} catch (BuildingNotFoundException e) {
    // Handle building not found
    System.err.println("❌ BUILDING NOT FOUND:");
    System.err.println(e.getDetailedMessage());
    
} catch (InvalidBuildingStatusException e) {
    // Handle status error
    System.err.println("❌ INVALID STATUS ERROR:");
    System.err.println(e.getDetailedMessage());
    
} catch (BuildingCapacityException e) {
    // Handle capacity error
    System.err.println("❌ CAPACITY ERROR:");
    System.err.println(e.getDetailedMessage());
    
} catch (Exception e) {
    // Catch any other exception
    System.err.println("❌ UNEXPECTED ERROR: " + e.getMessage());
}
```

### Order Matters - Specific to General
```java
// ✗ WRONG - More general exception before more specific
try {
    // code
} catch (Exception e) {          // Catches everything
    // This catch will handle ALL exceptions
} catch (CityCapacityException e) {  // ✗ Never reached!
    // This code is unreachable
}

// ✓ CORRECT - More specific first
try {
    // code
} catch (CityCapacityException e) {   // Specific first
    // Handle specific exception
} catch (BuildingNotFoundException e) {  // Specific
    // Handle specific exception
} catch (Exception e) {                  // General last
    // Handle any other exception
}
```

### Why Multiple Catch Blocks?
```java
try {
    String name = scanner.nextLine();
    int age = scanner.nextInt();
    int result = 100 / age;
    
} catch (InputMismatchException e) {
    System.out.println("Age must be a number!");
} catch (ArithmeticException e) {
    System.out.println("Age cannot be zero!");
} catch (Exception e) {
    System.out.println("Something went wrong!");
}
```

### Multi-Catch (Java 7+)
```java
// Catch multiple exceptions in one block (if handling same way)
try {
    // code
} catch (IOException | SQLException e) {
    System.out.println("Database or file error: " + e.getMessage());
}
```

---

## 4. CUSTOM EXCEPTIONS

### Concept
Create your own exception types for specific error conditions in your application.

### Creating Custom Exception

**Step 1: Extend Exception class**
```java
public class BuildingCapacityException extends Exception {
    // Your exception class
}
```

**Step 2: Add constructors and attributes**
```java
public class BuildingCapacityException extends Exception {
    private int currentOccupancy;
    private int maxCapacity;
    
    // Constructor with message only
    public BuildingCapacityException(String message) {
        super(message);  // Call parent constructor
    }
    
    // Constructor with message and details
    public BuildingCapacityException(String message, int current, int max) {
        super(message);
        this.currentOccupancy = current;
        this.maxCapacity = max;
    }
    
    // Method to get detailed error information
    public String getDetailedMessage() {
        return super.getMessage() + 
               "\nCurrent Occupancy: " + currentOccupancy + 
               "\nMaximum Capacity: " + maxCapacity +
               "\nOvercapacity: " + (currentOccupancy - maxCapacity);
    }
    
    // Getters
    public int getCurrentOccupancy() { return currentOccupancy; }
    public int getMaxCapacity() { return maxCapacity; }
}
```

### In the Code - Multiple Custom Exceptions

**1. BuildingCapacityException**
```java
class BuildingCapacityException extends Exception {
    private int currentOccupancy;
    private int maxCapacity;
    
    public BuildingCapacityException(String message, int current, int max) {
        super(message);
        this.currentOccupancy = current;
        this.maxCapacity = max;
    }
    
    public String getDetailedMessage() {
        return super.getMessage() + 
               "\nCurrent: " + currentOccupancy + 
               "\nMaximum: " + maxCapacity;
    }
}
```

**2. InvalidBuildingDataException**
```java
class InvalidBuildingDataException extends Exception {
    private String fieldName;
    private Object invalidValue;
    
    public InvalidBuildingDataException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }
    
    public InvalidBuildingDataException(String message, String field, Object value) {
        super(message);
        this.fieldName = field;
        this.invalidValue = value;
    }
    
    public String getDetailedMessage() {
        String msg = super.getMessage() + "\nField: " + fieldName;
        if (invalidValue != null) {
            msg += "\nInvalid Value: " + invalidValue;
        }
        return msg;
    }
}
```

**3. ServiceUnavailableException**
```java
class ServiceUnavailableException extends Exception {
    private String serviceName;
    private String buildingName;
    
    public ServiceUnavailableException(String msg, String service, String building) {
        super(msg);
        this.serviceName = service;
        this.buildingName = building;
    }
    
    public String getDetailedMessage() {
        return super.getMessage() +
               "\nService: " + serviceName +
               "\nBuilding: " + buildingName;
    }
}
```

### Using Custom Exceptions
```java
public void validateOccupancy(Building building, int occupancy) 
        throws BuildingCapacityException {
    
    if (occupancy > building.getCapacity()) {
        // THROW custom exception
        throw new BuildingCapacityException(
            "Building is overcrowded!",
            occupancy,
            building.getCapacity()
        );
    }
}

// Calling code:
try {
    validateOccupancy(hospital, 300);
} catch (BuildingCapacityException e) {
    System.err.println(e.getDetailedMessage());
    // Output:
    // Building is overcrowded!
    // Current Occupancy: 300
    // Maximum Capacity: 200
    // Overcapacity: 100
}
```

### Why Create Custom Exceptions?
1. **Specific error information**: Store relevant data (occupancy, capacity, etc.)
2. **Clear error handling**: Catch specific problems
3. **Better debugging**: Know exactly what went wrong
4. **Meaningful names**: `BuildingCapacityException` vs generic `Exception`
5. **Domain-specific**: Matches your application's logic

---

## 5. THROWING EXCEPTIONS

### Concept
Manually create and throw an exception when an error condition is detected.

### Syntax
```java
throw new ExceptionType("Error message");
```

### In the Code
```java
public void addBuilding(AbstractBuilding building) 
        throws CityCapacityException {
    
    // Check condition
    if (buildings.size() >= MAX_BUILDINGS) {
        // THROW exception
        throw new CityCapacityException(
            "Cannot add building - City is full!",
            buildings.size(),
            MAX_BUILDINGS
        );
    }
    
    // If no exception, continue normally
    buildings.add(building);
}
```

### throws vs throw

**"throws" - in method signature (declares)**
```java
public void method() throws IOException {
    // This method MIGHT throw IOException
}
```

**"throw" - in code (actually throws)**
```java
public void method() throws IOException {
    if (error) {
        throw new IOException("Error!");  // Actually throw
    }
}
```

### Complete Example
```java
public void setFloors(int floors) throws InvalidBuildingDataException {
    if (floors <= 0) {
        // THROW exception
        throw new InvalidBuildingDataException(
            "Floors must be positive",
            "floors",
            floors
        );
    }
    this.floors = floors;
}

// Usage:
try {
    building.setFloors(-5);  // Will throw exception
} catch (InvalidBuildingDataException e) {
    System.err.println(e.getDetailedMessage());
    // Output:
    // Floors must be positive
    // Field: floors
    // Invalid Value: -5
}
```

### Checked vs Unchecked Exceptions

**CHECKED (Must handle or declare)**
```java
// Must either:
// 1. Catch it
public void method1() {
    try {
        method2();  // Might throw checked exception
    } catch (IOException e) {
        // Handle
    }
}

// 2. Or declare it
public void method2() throws IOException {
    throw new IOException("Error");
}
```

**UNCHECKED (Don't have to handle)**
```java
// Runtime exceptions - don't need try-catch or throws
public void method() {
    int x = 10 / 0;  // ArithmeticException - no try-catch required
    // But program will crash if not handled
}
```

### Re-throwing Exceptions
```java
public void outerMethod() throws CityCapacityException {
    try {
        innerMethod();
    } catch (CityCapacityException e) {
        System.err.println("Error occurred!");
        throw e;  // Re-throw to caller
    }
}
```

---

## 6. FINALLY Block

### Concept
Code that ALWAYS executes, whether exception occurs or not. Used for cleanup (closing files, releasing resources).

### Syntax
```java
try {
    // Might throw exception
} catch (Exception e) {
    // Handle exception
} finally {
    // ALWAYS executes
    // Close resources, cleanup, etc.
}
```

### Example
```java
Scanner scanner = new Scanner(System.in);
try {
    int number = scanner.nextInt();
    System.out.println("Number: " + number);
} catch (InputMismatchException e) {
    System.err.println("Invalid input!");
} finally {
    scanner.close();  // ALWAYS close, even if error
    System.out.println("Scanner closed");
}
```

### When Finally Executes

**Case 1: No exception**
```java
try {
    System.out.println("A");  // ✓ Executes
} finally {
    System.out.println("B");  // ✓ Executes
}
// Output: A B
```

**Case 2: Exception caught**
```java
try {
    System.out.println("A");      // ✓ Executes
    throw new Exception();
    System.out.println("C");      // Skipped
} catch (Exception e) {
    System.out.println("D");      // ✓ Executes
} finally {
    System.out.println("E");      // ✓ Executes
}
// Output: A D E
```

**Case 3: Exception not caught**
```java
try {
    throw new RuntimeException();
} finally {
    System.out.println("F");  // ✓ Still executes!
}
// Output: F (then program crashes)
```

### Try-With-Resources (Java 7+)
Auto-closes resources without finally block:
```java
// Old way - with finally
Scanner scanner = null;
try {
    scanner = new Scanner(System.in);
    // use scanner
} catch (Exception e) {
    // handle
} finally {
    if (scanner != null) {
        scanner.close();
    }
}

// New way - try-with-resources (auto-closes)
try (Scanner scanner = new Scanner(System.in)) {
    // use scanner
} catch (Exception e) {
    // handle
}
// scanner automatically closed
```

---

## 7. ARRAYLIST

### Concept
Dynamic array that can grow/shrink automatically. Part of Java Collections Framework.

### vs Regular Array

**Regular Array - Fixed size**
```java
Building[] buildings = new Building[10];  // Fixed at 10
buildings[0] = new Building();
buildings[1] = new Building();
// Can't change size
// buildings[10] = ...  // ✗ ERROR! Out of bounds
```

**ArrayList - Dynamic size**
```java
ArrayList<Building> buildings = new ArrayList<>();  // Starts empty
buildings.add(new Building());  // Size = 1
buildings.add(new Building());  // Size = 2
buildings.add(new Building());  // Size = 3
buildings.add(new Building());  // Size = 4
// Can keep adding - no size limit!
```

### Creating ArrayList

```java
import java.util.ArrayList;

// Create empty ArrayList
ArrayList<AbstractBuilding> buildings = new ArrayList<>();

// Create with initial capacity (optimization)
ArrayList<AbstractBuilding> buildings = new ArrayList<>(100);

// Create from another collection
ArrayList<AbstractBuilding> copy = new ArrayList<>(existingList);
```

### Generic Type
```java
ArrayList<AbstractBuilding> buildings = new ArrayList<>();
//       ^^^^^^^^^^^^^^^^
//       Type of objects to store

// Can only add AbstractBuilding (or subclass) objects
buildings.add(new ModernHospital());  // ✓ OK
buildings.add(new ModernSchool());    // ✓ OK
buildings.add("String");              // ✗ ERROR! Wrong type
buildings.add(123);                   // ✗ ERROR! Wrong type
```

### In the Code
```java
import java.util.ArrayList;

public class BuildingManager {
    // ARRAYLIST to store buildings (dynamic size)
    private ArrayList<AbstractBuilding> buildings;
    
    public BuildingManager() {
        buildings = new ArrayList<>();  // Create empty ArrayList
    }
}
```

---

## 8. ARRAYLIST Methods

### a) ADD Method
Add element to the list.

**Add at end:**
```java
ArrayList<String> list = new ArrayList<>();

list.add("A");    // ["A"]
list.add("B");    // ["A", "B"]
list.add("C");    // ["A", "B", "C"]
```

**Add at specific index:**
```java
list.add(1, "X");  // ["A", "X", "B", "C"]
// Inserts at index 1, shifts others right
```

**In the Code:**
```java
public void addBuilding(AbstractBuilding building) 
        throws CityCapacityException {
    if (buildings.size() >= MAX_BUILDINGS) {
        throw new CityCapacityException(...);
    }
    buildings.add(building);  // ADD method - adds at end
}
```

### b) GET Method
Retrieve element by index.

```java
ArrayList<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add("C");

String first = list.get(0);   // "A"
String second = list.get(1);  // "B"
String third = list.get(2);   // "C"
```

**In the Code:**
```java
public AbstractBuilding getBuilding(int index) 
        throws BuildingNotFoundException {
    if (index < 0 || index >= buildings.size()) {
        throw new BuildingNotFoundException(...);
    }
    return buildings.get(index);  // GET method
}
```

### c) SET Method
Replace element at index.

```java
ArrayList<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add("C");
// ["A", "B", "C"]

list.set(1, "X");  // Replace "B" with "X"
// ["A", "X", "C"]
```

**In the Code:**
```java
public void updateBuilding(int index, AbstractBuilding newBuilding) 
        throws BuildingNotFoundException {
    if (index < 0 || index >= buildings.size()) {
        throw new BuildingNotFoundException(...);
    }
    AbstractBuilding old = buildings.set(index, newBuilding);  // SET method
    System.out.println("Updated: " + old.getName() + " → " + newBuilding.getName());
}
```

### d) REMOVE Method

**Remove by index:**
```java
ArrayList<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add("C");
// ["A", "B", "C"]

list.remove(1);  // Remove at index 1
// ["A", "C"]  (size decreased by 1)
```

**Remove by object:**
```java
ArrayList<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add("C");

list.remove("B");  // Remove specific object
// ["A", "C"]
```

**In the Code:**
```java
public void removeBuilding(int index) 
        throws BuildingNotFoundException {
    if (index < 0 || index >= buildings.size()) {
        throw new BuildingNotFoundException(...);
    }
    AbstractBuilding removed = buildings.remove(index);  // REMOVE method
    System.out.println("Removed: " + removed.getName());
}
```

### e) SIZE Method
Get number of elements.

```java
ArrayList<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add("C");

int count = list.size();  // 3
```

**In the Code:**
```java
public int getBuildingCount() {
    return buildings.size();  // SIZE method
}

// Use in loops
for (int i = 0; i < buildings.size(); i++) {
    AbstractBuilding b = buildings.get(i);
    // Process building
}
```

### f) ISEMPTY Method
Check if ArrayList is empty.

```java
ArrayList<String> list = new ArrayList<>();

if (list.isEmpty()) {  // true
    System.out.println("List is empty!");
}

list.add("A");

if (list.isEmpty()) {  // false
    System.out.println("List is empty!");
}
```

**In the Code:**
```java
public void displayAllBuildings() {
    if (buildings.isEmpty()) {  // ISEMPTY method
        System.out.println("No buildings!");
        return;
    }
    // Display buildings...
}
```

### ArrayList Methods Summary Table

| Method | Description | Example |
|--------|-------------|---------|
| add(E) | Add at end | list.add("A") |
| add(index, E) | Add at index | list.add(1, "B") |
| get(index) | Get element | list.get(0) |
| set(index, E) | Replace element | list.set(0, "X") |
| remove(index) | Remove by index | list.remove(1) |
| remove(Object) | Remove by object | list.remove("A") |
| size() | Get size | list.size() |
| isEmpty() | Check if empty | list.isEmpty() |
| clear() | Remove all | list.clear() |
| contains(Object) | Check if exists | list.contains("A") |

---

## 9. ITERATOR

### Concept
Object that allows traversing through a collection sequentially.

### Getting Iterator
```java
ArrayList<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add("C");

Iterator<String> iterator = list.iterator();  // Get iterator
```

### Using Iterator
```java
while (iterator.hasNext()) {     // Check if more elements
    String element = iterator.next();  // Get next element
    System.out.println(element);
}

// Output:
// A
// B
// C
```

### In the Code
```java
public void displayBuildingsWithIterator() {
    if (buildings.isEmpty()) {
        return;
    }
    
    // Get ITERATOR from ArrayList
    Iterator<AbstractBuilding> iterator = buildings.iterator();
    
    int count = 1;
    // Use ITERATOR to traverse
    while (iterator.hasNext()) {  // Check if more elements exist
        AbstractBuilding building = iterator.next();  // Get next element
        System.out.println(count + ". " + building.getName());
        count++;
    }
}
```

### Iterator Methods

**hasNext()** - Returns true if more elements exist
```java
if (iterator.hasNext()) {
    // More elements available
}
```

**next()** - Returns next element and moves forward
```java
String element = iterator.next();
```

**remove()** - Removes last element returned by next()
```java
while (iterator.hasNext()) {
    String element = iterator.next();
    if (element.equals("B")) {
        iterator.remove();  // Remove "B"
    }
}
```

### Complete Iterator Example
```java
ArrayList<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add("C");

Iterator<String> it = list.iterator();

// First iteration
it.hasNext();          // true (has "A")
String s1 = it.next(); // Returns "A", moves to next

// Second iteration
it.hasNext();          // true (has "B")
String s2 = it.next(); // Returns "B", moves to next

// Third iteration
it.hasNext();          // true (has "C")
String s3 = it.next(); // Returns "C", moves to end

// No more elements
it.hasNext();  // false
it.next();     // ✗ ERROR! NoSuchElementException
```

### Why Use Iterator?
1. **Safe removal**: Can remove while iterating
2. **Standard way**: Works with all collections
3. **Abstraction**: Don't need to know internal structure
4. **Forward traversal**: Consistent way to go through elements

---

## 10. ENHANCED FOR LOOP (For-Each)

### Concept
Simplified loop syntax for traversing collections/arrays. Cleaner and more readable.

### Syntax
```java
for (Type variable : collection) {
    // Use variable
}
```

### Basic Example
```java
ArrayList<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
names.add("Charlie");

// Enhanced for loop
for (String name : names) {
    System.out.println(name);
}

// Output:
// Alice
// Bob
// Charlie
```

### In the Code
```java
public void displayAllBuildings() {
    // ENHANCED FOR LOOP
    for (AbstractBuilding building : buildings) {
        building.displayBasicInfo();
    }
}

public ArrayList<AbstractBuilding> findBuildingsByType(BuildingType type) {
    ArrayList<AbstractBuilding> result = new ArrayList<>();
    
    // ENHANCED FOR LOOP to filter
    for (AbstractBuilding building : buildings) {
        if (building.getBuildingType() == type) {
            result.add(building);
        }
    }
    
    return result;
}
```

### Equivalent Traditional Loop
```java
// Enhanced for loop:
for (String name : names) {
    System.out.println(name);
}

// Is equivalent to:
for (int i = 0; i < names.size(); i++) {
    String name = names.get(i);
    System.out.println(name);
}
```

### Works with Arrays Too
```java
String[] array = {"A", "B", "C"};

// Enhanced for loop with array
for (String s : array) {
    System.out.println(s);
}
```

### Filtering Example
```java
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(10);
numbers.add(25);
numbers.add(30);
numbers.add(15);

// Find all numbers greater than 20
for (int num : numbers) {
    if (num > 20) {
        System.out.println(num);
    }
}
// Output: 25, 30
```

### Advantages
1. **Cleaner syntax**: Less code to write
2. **No index errors**: Can't go out of bounds
3. **Readable**: Clear what you're iterating over
4. **Type-safe**: Compiler knows element type

### Limitations
1. **Can't modify collection**: Can't add/remove while iterating
2. **No index**: Don't know current position
3. **Forward only**: Can't go backwards
4. **No modification of element**: Can't change the element itself in the collection

### When to Use What

**Enhanced For Loop:**
```java
// When: Just need to read each element
for (AbstractBuilding building : buildings) {
    System.out.println(building.getName());  // Just reading
}
```

**Traditional For Loop:**
```java
// When: Need index or want to modify collection
for (int i = 0; i < buildings.size(); i++) {
    System.out.println("Building " + i + ": " + buildings.get(i).getName());
}
```

**Iterator:**
```java
// When: Need to remove while iterating
Iterator<AbstractBuilding> it = buildings.iterator();
while (it.hasNext()) {
    AbstractBuilding b = it.next();
    if (b.shouldRemove()) {
        it.remove();  // Safe removal with iterator
    }
}
```

## Common Mistakes to Avoid

### 1. Not handling exceptions at all
```java
// BAD - Program crashes
int result = 10 / 0;  // ArithmeticException!

// GOOD - Program continues
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero!");
}
```

### 2. Catching Exception too broadly
```java
// BAD - Catches everything, can't handle specifically
try {
    // code
} catch (Exception e) {
    System.out.println("Error!");  // What error?
}

// GOOD - Specific exceptions first
try {
    // code
} catch (FileNotFoundException e) {
    System.out.println("File not found!");
} catch (IOException e) {
    System.out.println("IO error!");
}
```

### 3. Empty catch block
```java
// VERY BAD - Silently swallows errors
try {
    // code
} catch (Exception e) {
    // Do nothing - error is hidden!
}

// GOOD - At least log the error
try {
    // code
} catch (Exception e) {
    System.err.println("Error: " + e.getMessage());
    e.printStackTrace();
}
```

### 4. Modifying ArrayList while using enhanced for loop
```java
// BAD - Will throw ConcurrentModificationException
for (Building b : buildings) {
    if (b.isOld()) {
        buildings.remove(b);  // ✗ ERROR!
    }
}

// GOOD - Use iterator
Iterator<Building> it = buildings.iterator();
while (it.hasNext()) {
    Building b = it.next();
    if (b.isOld()) {
        it.remove();  // ✓ OK
    }
}
```

### 5. Using get() with wrong index
```java
ArrayList<String> list = new ArrayList<>();
list.add("A");
list.add("B");

String s = list.get(5);  // ✗ ERROR! IndexOutOfBoundsException

// GOOD - Check size first
if (index >= 0 && index < list.size()) {
    String s = list.get(index);
}
```

### 6. Forgetting to clear scanner buffer
```java
// BAD
int number = scanner.nextInt();
String text = scanner.nextLine();  // Gets empty string!

// GOOD
int number = scanner.nextInt();
scanner.nextLine();  // Clear buffer
String text = scanner.nextLine();  // Now works correctly
```

---
