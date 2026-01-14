# Smart City Management System - Java OOP Learning Project

A comprehensive, modular Java project designed to teach Object-Oriented Programming (OOP) concepts through a practical Smart City Management System. Each module progressively introduces new OOP concepts while remaining independently executable.

## 📚 Project Overview

This project consists of **5 progressive modules**, each building upon the previous one to demonstrate fundamental to advanced Java OOP concepts. Students learn by building a complete Smart City Management System that manages buildings (hospitals, schools, offices, parks) with increasing sophistication.

### 🎯 Learning Objectives

By completing all modules, students will master:
- **Module 1**: Classes, Objects, Methods, Data Types, Conditionals, Loops, I/O
- **Module 2**: Encapsulation, Access Modifiers, Getters/Setters, Static Members, Method Overloading
- **Module 3**: Inheritance, Constructors, Super Keyword, Method Overriding
- **Module 4**: Final Keyword, Enums, Abstract Classes, Interfaces, Polymorphism, Casting
- **Module 5**: Exception Handling, Custom Exceptions, ArrayList, Iterator, Enhanced For Loop

## 🏗️ Project Structure

```
Java-OOP-SmartCity/
├── Module1_SmartCity/
│   ├── src/
│   │   └── smartcity/
│   │       └── SmartCityApp.java
│   └── Module 1 - Explanation Document.md
│
├── Module2_SmartCity/
│   ├── src/
│   │   └── smartcity/
│   │       ├── Building.java
│   │       ├── CityManager.java
│   │       └── SmartCityApp.java
│   └── Module 2 - Explanation Document.md
│
├── Module3_SmartCity/
│   ├── src/
│   │   └── smartcity/
│   │       ├── Building.java
│   │       ├── Hospital.java
│   │       ├── School.java
│   │       ├── Office.java
│   │       ├── Park.java
│   │       ├── CityManager.java
│   │       └── SmartCityApp.java
│   └── Module 3 - Explanation Document.md
│
├── Module4_SmartCity/
│   ├── src/
│   │   └── smartcity/
│   │       ├── BuildingStatus.java (Enum)
│   │       ├── BuildingType.java (Enum)
│   │       ├── Serviceable.java (Interface)
│   │       ├── Accessible.java (Interface)
│   │       ├── AbstractBuilding.java
│   │       ├── ModernHospital.java
│   │       ├── ModernSchool.java
│   │       ├── ModernOffice.java
│   │       ├── PublicPark.java (Final Class)
│   │       ├── CityManager.java
│   │       └── SmartCityApp.java
│   └── Module 4 - Explanation Document.md
│
├── Module5_SmartCity/
│   ├── src/
│   │   └── smartcity/
│   │       ├── BuildingStatus.java (Enum)
│   │       ├── BuildingType.java (Enum)
│   │       ├── Serviceable.java (Interface)
│   │       ├── Accessible.java (Interface)
│   │       ├── AbstractBuilding.java
│   │       ├── ModernHospital.java
│   │       ├── ModernSchool.java
│   │       ├── ModernOffice.java
│   │       ├── PublicPark.java (Final Class)
│   │       ├── CustomExceptions.java (NEW)
│   │       ├── BuildingManager.java (NEW - ArrayList)
│   │       ├── CityManager.java
│   │       └── SmartCityApp.java
│   └── Module 5 - Explanation Document.md
│
└── README.md
```

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Version 11 or higher
- **IDE**: IntelliJ IDEA (recommended), Eclipse, or VS Code with Java extensions
- **Basic knowledge**: Understanding of basic programming concepts

### Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/Java-OOP-SmartCity.git
   cd Java-OOP-SmartCity
   ```

2. **Open a module in IntelliJ IDEA**
   - Open IntelliJ IDEA
   - File → Open
   - Select the module folder (e.g., `Module1_SmartCity`)
   - Wait for IntelliJ to index the project

3. **Run the program**
   - Navigate to `src/smartcity/SmartCityApp.java`
   - Right-click on the file
   - Select "Run 'SmartCityApp.main()'"

### Running from Command Line

```bash
# Navigate to module directory
cd Module1_SmartCity/src

# Compile
javac smartcity/*.java

# Run
java smartcity.SmartCityApp
```

## 📖 Module Details

### Module 1: Foundation - Basic OOP
**Concepts Covered:**
- Class and Object creation
- Attributes (instance variables)
- Methods (with/without parameters and return values)
- Data types (int, double, String, boolean)
- Conditional statements (if-else)
- Switch-case statements
- Loops (for, while, do-while)
- Input/Output with Scanner

**Features:**
- Basic building management
- Display building information
- Calculate building efficiency
- Perform maintenance checks
- City statistics calculation

**Run the project:**
```bash
cd Module1_SmartCity
```

---

### Module 2: Encapsulation and Static Members
**Concepts Covered:**
- Encapsulation (private, protected, public)
- Getter and Setter methods with validation
- Method overloading
- Static attributes and methods
- Static final constants

**New Features:**
- Data validation through setters
- Multiple display options (method overloading)
- Building counter using static variables
- Enhanced building information display

**Improvements from Module 1:**
- Better data protection
- Flexible method interfaces
- Shared city-wide statistics

---

### Module 3: Inheritance and Constructors
**Concepts Covered:**
- Inheritance (extends keyword)
- Constructor overloading
- Super keyword (constructor chaining)
- Method overriding
- Protected members

**New Features:**
- Specialized building types: Hospital, School, Office, Park
- Type-specific attributes and methods
- Customized maintenance procedures
- Building-specific operations (admit patients, enroll students, etc.)

**Building Types:**
- **Hospital**: Emergency beds, doctors, specialization
- **School**: Classrooms, teachers, education level
- **Office**: Company info, employees, parking
- **Park**: Green space, playground, fountain

---

### Module 4: Advanced OOP Concepts
**Concepts Covered:**
- Final variables, methods, and classes
- Static final constants
- Enums with attributes and methods
- Enum in switch-case
- Abstract classes and methods
- Interfaces (multiple implementation)
- Polymorphism
- Upcasting and Downcasting

**New Features:**
- Building status system (enum)
- Building type classification (enum)
- Service provision (interface)
- Accessibility features (interface)
- Polymorphic building management
- Type-safe status handling

**Key Enhancements:**
- Type-safe building states
- Contract-based design with interfaces
- Flexible building collections
- Advanced filtering and categorization

---

### Module 5: Exception Handling and ArrayList
**Concepts Covered:**
- Try-catch blocks
- Multiple catch blocks
- Custom exceptions
- Throwing exceptions
- Finally blocks
- ArrayList (add, get, set, remove, size, isEmpty)
- Iterator
- Enhanced for loop (for-each)

**New Features:**
- Robust error handling
- Custom exceptions with detailed messages
- Dynamic building collection (ArrayList)
- Advanced search and filtering
- Iterator-based operations
- Comprehensive error reporting

**Custom Exceptions:**
- `BuildingCapacityException`
- `InvalidBuildingDataException`
- `ServiceUnavailableException`
- `BuildingNotFoundException`
- `CityCapacityException`
- `InvalidBuildingStatusException`

---

## 💡 Key Features Across All Modules

### Common Functionality
- ✅ Add buildings to the city
- ✅ Display building information
- ✅ Calculate maintenance costs
- ✅ Check building efficiency/occupancy
- ✅ Perform maintenance operations
- ✅ View city statistics

### Progressive Complexity
Each module adds new capabilities:

| Feature | M1 | M2 | M3 | M4 | M5 |
|---------|----|----|----|----|-----|
| Basic CRUD | ✓ | ✓ | ✓ | ✓ | ✓ |
| Data Validation | | ✓ | ✓ | ✓ | ✓ |
| Specialized Types | | | ✓ | ✓ | ✓ |
| Polymorphism | | | | ✓ | ✓ |
| Error Handling | | | | | ✓ |
| Dynamic Collections | | | | | ✓ |

## 🎓 Learning Path

### For Students

1. **Start with Module 1**
   - Read the explanation document
   - Study the code with comments
   - Run the program
   - Complete the practice exercises
   - Modify the code to experiment

2. **Progress through modules sequentially**
   - Each module builds on previous concepts
   - Don't skip modules
   - Practice exercises reinforce learning

3. **Experiment and Extend**
   - Add new building types
   - Create additional features
   - Modify existing functionality
   - Break things and fix them (great learning!)

### For Instructors

- Each module is a complete lesson with explanation document
- Code is heavily commented for self-study
- Practice exercises included
- Common mistakes highlighted
- Can be taught over 5-10 weeks (one module per 1-2 weeks)

## 📝 Example Usage

### Module 1 - Basic Operations
```
====== SMART CITY MANAGEMENT SYSTEM ======
1. Add New Building
2. Display All Buildings
3. Check Building Efficiency
4. Perform Maintenance
5. Calculate City Statistics
6. Exit
Enter your choice: 1

Enter building name: City Hospital
Enter building type: Hospital
Enter number of floors: 5
Enter area per floor: 1000.0
Enter capacity: 500
Is building operational? true

Building added successfully!
```

### Module 5 - Advanced Operations with Error Handling
```
====== SMART CITY MANAGEMENT SYSTEM - v5.0 ======
1. Add Hospital
2. Add School
...
9. Remove Building
10. Update Status
...

Enter your choice: 1

Enter hospital name: Central Hospital
Enter floors: -5

❌ INVALID DATA:
Floors must be greater than 0
Field: floors
Invalid Value: -5
```

## 🔧 Customization Ideas

Students can extend the project by:

1. **Add new building types**
   - Library, Museum, Stadium, Shopping Mall
   
2. **Add new features**
   - Building ratings/reviews
   - Energy consumption tracking
   - Visitor management
   - Booking system

3. **Enhance existing features**
   - Advanced search filters
   - Sorting capabilities
   - Data persistence (save/load)
   - Graphical user interface

4. **Add new modules**
   - Module 6: File I/O and Serialization
   - Module 7: Collections Framework
   - Module 8: Generics and Lambda Expressions

## 🤝 Contributing

Contributions are welcome! If you'd like to improve this educational project:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Contribution Guidelines
- Maintain the educational focus
- Keep code well-commented
- Update documentation
- Ensure each module remains independently runnable
- Add explanation for new concepts

## 🙏 Acknowledgments

- Designed for teaching Java OOP concepts progressively
- Inspired by real-world smart city management needs
- Built with education-first approach
- Comments and explanations prioritized for learning

## 📚 Additional Resources

- [Oracle Java Documentation](https://docs.oracle.com/en/java/)
- [Java Programming Tutorial](https://docs.oracle.com/javase/tutorial/)

## 🐛 Known Issues

None currently. If you find any bugs or issues, please report them in the [Issues](https://github.com/yourusername/Java-OOP-SmartCity/issues) section.

## 📊 Project Statistics

- **Lines of Code**: ~3,500+ (across all modules)
- **Classes**: 20+
- **Modules**: 5
- **OOP Concepts Covered**: 40+
- **Time to Complete**: 30-50 hours (for students)

## 🎯 Learning Outcomes

After completing all modules, students will be able to:

✅ Design and implement class hierarchies  
✅ Apply encapsulation principles  
✅ Use inheritance effectively  
✅ Understand and apply polymorphism  
✅ Create and use interfaces  
✅ Handle exceptions properly  
✅ Work with collections (ArrayList)  
✅ Write clean, maintainable OOP code  
✅ Build real-world applications using OOP principles  

---

**Happy Coding! 🚀**

*If this project helped you learn Java OOP, please give it a ⭐ star!*
