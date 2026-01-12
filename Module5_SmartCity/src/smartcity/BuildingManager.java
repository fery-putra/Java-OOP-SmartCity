package smartcity;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * MODULE 5: BuildingManager with ArrayList
 *
 * This demonstrates:
 * - ARRAYLIST usage (replacing fixed array)
 * - ArrayList methods: add, get, set, remove, size, isEmpty
 * - ITERATOR usage
 * - ENHANCED FOR LOOP (for-each)
 * - EXCEPTION HANDLING with try-catch
 * - Throwing CUSTOM EXCEPTIONS
 */
public class BuildingManager {

    // ARRAYLIST to store buildings (dynamic size)
    private ArrayList<AbstractBuilding> buildings;
    private static final int MAX_BUILDINGS = 50;

    // Constructor
    public BuildingManager() {
        buildings = new ArrayList<>(); // Initialize ArrayList
    }

    /**
     * ADD method - adds building to ArrayList
     * Demonstrates: ArrayList.add(), throwing custom exception
     */
    public void addBuilding(AbstractBuilding building) throws CityCapacityException {
        // TRY-CATCH for exception handling
        try {
            // Check if city is at capacity
            if (buildings.size() >= MAX_BUILDINGS) {
                // THROW custom exception
                throw new CityCapacityException(
                        "Cannot add building - City is at maximum capacity!",
                        buildings.size(),
                        MAX_BUILDINGS
                );
            }

            // ArrayList ADD method
            buildings.add(building);
            System.out.println("✓ Building added successfully!");
            System.out.println("Total buildings in city: " + buildings.size());

        } catch (CityCapacityException e) {
            // Re-throw to be handled by caller
            throw e;
        } catch (Exception e) {
            // Catch any unexpected exceptions
            System.err.println("Unexpected error adding building: " + e.getMessage());
        }
    }

    /**
     * GET method - retrieves building by index
     * Demonstrates: ArrayList.get(), exception handling
     */
    public AbstractBuilding getBuilding(int index) throws BuildingNotFoundException {
        try {
            // ArrayList GET method
            if (index < 0 || index >= buildings.size()) {
                throw new BuildingNotFoundException(
                        "Building not found at index " + index,
                        "Index: " + index
                );
            }

            return buildings.get(index);

        } catch (BuildingNotFoundException e) {
            throw e;
        } catch (IndexOutOfBoundsException e) {
            throw new BuildingNotFoundException(
                    "Invalid building index: " + index,
                    "Index: " + index
            );
        }
    }

    /**
     * REMOVE method - removes building by index
     * Demonstrates: ArrayList.remove(), exception handling
     */
    public void removeBuilding(int index) throws BuildingNotFoundException {
        try {
            if (index < 0 || index >= buildings.size()) {
                throw new BuildingNotFoundException(
                        "Cannot remove - Building not found at index " + index
                );
            }

            AbstractBuilding removed = buildings.get(index);
            buildings.remove(index); // ArrayList REMOVE method

            System.out.println("✓ Building removed successfully!");
            System.out.println("Removed: " + removed.getName());
            System.out.println("Remaining buildings: " + buildings.size());

        } catch (BuildingNotFoundException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("Error removing building: " + e.getMessage());
        }
    }

    /**
     * REMOVE by object
     * Demonstrates: ArrayList.remove(Object)
     */
    public void removeBuilding(AbstractBuilding building) throws BuildingNotFoundException {
        try {
            if (!buildings.contains(building)) {
                throw new BuildingNotFoundException(
                        "Building not found in the city",
                        building.getName()
                );
            }

            buildings.remove(building); // ArrayList REMOVE by object
            System.out.println("✓ Building removed: " + building.getName());

        } catch (BuildingNotFoundException e) {
            throw e;
        }
    }

    /**
     * SET method - updates building at specific index
     * Demonstrates: ArrayList.set()
     */
    public void updateBuilding(int index, AbstractBuilding newBuilding)
            throws BuildingNotFoundException {
        try {
            if (index < 0 || index >= buildings.size()) {
                throw new BuildingNotFoundException(
                        "Cannot update - Building not found at index " + index
                );
            }

            AbstractBuilding oldBuilding = buildings.get(index);
            buildings.set(index, newBuilding); // ArrayList SET method

            System.out.println("✓ Building updated successfully!");
            System.out.println("Old: " + oldBuilding.getName());
            System.out.println("New: " + newBuilding.getName());

        } catch (BuildingNotFoundException e) {
            throw e;
        }
    }

    /**
     * SIZE method
     * Demonstrates: ArrayList.size()
     */
    public int getBuildingCount() {
        return buildings.size(); // ArrayList SIZE method
    }

    /**
     * ISEMPTY method
     * Demonstrates: ArrayList.isEmpty()
     */
    public boolean isEmpty() {
        return buildings.isEmpty(); // ArrayList ISEMPTY method
    }

    /**
     * Display all buildings using ENHANCED FOR LOOP
     * Demonstrates: Enhanced for loop (for-each)
     */
    public void displayAllBuildings() {
        if (buildings.isEmpty()) {
            System.out.println("\nNo buildings in the city!");
            return;
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("ALL BUILDINGS IN " + AbstractBuilding.getCityName());
        System.out.println("=".repeat(60));

        int count = 1;
        // ENHANCED FOR LOOP (for-each)
        for (AbstractBuilding building : buildings) {
            System.out.println("\n--- Building " + count + " ---");
            building.displayBasicInfo();
            count++;
        }

        System.out.println("\nTotal Buildings: " + buildings.size());
    }

    /**
     * Display buildings using ITERATOR
     * Demonstrates: ArrayList.iterator(), Iterator usage
     */
    public void displayBuildingsWithIterator() {
        if (buildings.isEmpty()) {
            System.out.println("\nNo buildings in the city!");
            return;
        }

        System.out.println("\n=== Buildings (using Iterator) ===");

        // Get ITERATOR from ArrayList
        Iterator<AbstractBuilding> iterator = buildings.iterator();

        int count = 1;
        // Use ITERATOR to traverse
        while (iterator.hasNext()) {
            AbstractBuilding building = iterator.next();
            System.out.println(count + ". " + building.getName() +
                    " (" + building.getBuildingType().getName() + ")");
            count++;
        }
    }

    /**
     * Find buildings by type using ENHANCED FOR LOOP
     * Demonstrates: Filtering with ArrayList and exception handling
     */
    public ArrayList<AbstractBuilding> findBuildingsByType(BuildingType type)
            throws BuildingNotFoundException {
        ArrayList<AbstractBuilding> result = new ArrayList<>();

        // ENHANCED FOR LOOP to filter
        for (AbstractBuilding building : buildings) {
            if (building.getBuildingType() == type) {
                result.add(building);
            }
        }

        if (result.isEmpty()) {
            throw new BuildingNotFoundException(
                    "No buildings of type " + type.getName() + " found"
            );
        }

        return result;
    }

    /**
     * Find buildings by status
     */
    public ArrayList<AbstractBuilding> findBuildingsByStatus(BuildingStatus status)
            throws BuildingNotFoundException {
        ArrayList<AbstractBuilding> result = new ArrayList<>();

        for (AbstractBuilding building : buildings) {
            if (building.getStatus() == status) {
                result.add(building);
            }
        }

        if (result.isEmpty()) {
            throw new BuildingNotFoundException(
                    "No buildings with status " + status.getDisplayName() + " found"
            );
        }

        return result;
    }

    /**
     * Search building by name
     * Demonstrates: String comparison and exception handling
     */
    public AbstractBuilding findBuildingByName(String name)
            throws BuildingNotFoundException {
        try {
            // ENHANCED FOR LOOP for searching
            for (AbstractBuilding building : buildings) {
                if (building.getName().equalsIgnoreCase(name)) {
                    return building;
                }
            }

            // If not found, throw exception
            throw new BuildingNotFoundException(
                    "Building with name '" + name + "' not found",
                    name
            );

        } catch (BuildingNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BuildingNotFoundException(
                    "Error searching for building: " + e.getMessage()
            );
        }
    }

    /**
     * Get building by ID
     */
    public AbstractBuilding findBuildingById(String buildingId)
            throws BuildingNotFoundException {
        for (AbstractBuilding building : buildings) {
            if (building.getBuildingId().equals(buildingId)) {
                return building;
            }
        }

        throw new BuildingNotFoundException(
                "Building with ID '" + buildingId + "' not found",
                buildingId
        );
    }

    /**
     * Calculate total maintenance cost with exception handling
     */
    public double calculateTotalMaintenance() {
        double total = 0;

        try {
            if (buildings.isEmpty()) {
                throw new BuildingNotFoundException("No buildings to calculate maintenance");
            }

            // ENHANCED FOR LOOP
            for (AbstractBuilding building : buildings) {
                total += building.calculateMaintenanceCost();
            }

        } catch (BuildingNotFoundException e) {
            System.err.println(e.getMessage());
            return 0;
        } catch (Exception e) {
            System.err.println("Error calculating maintenance: " + e.getMessage());
            return 0;
        }

        return total;
    }

    /**
     * Validate building occupancy with exception handling
     * Demonstrates: Throwing custom exception
     */
    public void validateOccupancy(AbstractBuilding building, int occupancy)
            throws BuildingCapacityException, InvalidBuildingStatusException {

        // Check if building is operational
        if (!building.isUsable()) {
            throw new InvalidBuildingStatusException(
                    "Cannot check occupancy - Building is not operational",
                    building.getStatus(),
                    "Occupancy Check"
            );
        }

        // Check capacity
        if (occupancy > building.getCapacity()) {
            throw new BuildingCapacityException(
                    "Building is over capacity!",
                    occupancy,
                    building.getCapacity()
            );
        }

        System.out.println("✓ Occupancy is within limits");
        double utilizationRate = (occupancy * 100.0) / building.getCapacity();
        System.out.println("Utilization: " + String.format("%.1f", utilizationRate) + "%");
    }

    /**
     * Provide service with exception handling
     */
    public void requestService(AbstractBuilding building, String serviceName)
            throws ServiceUnavailableException, InvalidBuildingStatusException {

        // Check if building is operational
        if (!building.isUsable()) {
            throw new InvalidBuildingStatusException(
                    "Cannot provide service - Building is not operational",
                    building.getStatus(),
                    "Service Request"
            );
        }

        // Check if building implements Serviceable
        if (!(building instanceof Serviceable)) {
            throw new ServiceUnavailableException(
                    "This building does not provide services",
                    serviceName,
                    building.getName()
            );
        }

        Serviceable serviceable = (Serviceable) building;

        // Check if service is available
        if (!serviceable.isServiceAvailable(serviceName)) {
            throw new ServiceUnavailableException(
                    "Requested service is not available at this building",
                    serviceName,
                    building.getName()
            );
        }

        // Provide the service
        serviceable.provideService(serviceName);
    }

    /**
     * Get all buildings (returns copy of ArrayList)
     */
    public ArrayList<AbstractBuilding> getAllBuildings() {
        return new ArrayList<>(buildings); // Return copy to prevent external modification
    }

    /**
     * Clear all buildings
     */
    public void clearAllBuildings() {
        int count = buildings.size();
        buildings.clear(); // ArrayList CLEAR method (bonus)
        System.out.println("✓ All buildings cleared. Removed " + count + " buildings.");
    }

    /**
     * Get statistics using ArrayList methods
     */
    public void displayStatistics() {
        if (buildings.isEmpty()) {
            System.out.println("\nNo buildings to display statistics!");
            return;
        }

        System.out.println("\n=== City Statistics ===");
        System.out.println("Total Buildings: " + buildings.size()); // SIZE

        int operational = 0;
        double totalArea = 0;
        int totalCapacity = 0;

        // ENHANCED FOR LOOP for calculations
        for (AbstractBuilding building : buildings) {
            if (building.isUsable()) {
                operational++;
            }
            totalArea += building.calculateTotalArea();
            totalCapacity += building.getCapacity();
        }

        System.out.println("Operational: " + operational);
        System.out.println("Under Maintenance/Closed: " + (buildings.size() - operational));
        System.out.println("Total Area: " + String.format("%.2f", totalArea) + " sq meters");
        System.out.println("Total Capacity: " + totalCapacity + " people");
        System.out.println("Average Building Size: " +
                String.format("%.2f", totalArea / buildings.size()) + " sq meters");
    }
}