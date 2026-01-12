package smartcity;

/**
 * MODULE 4: PublicPark - FINAL CLASS
 *
 * This demonstrates:
 * - FINAL CLASS (cannot be extended/inherited)
 * - FINAL METHODS (cannot be overridden)
 * - Implementing abstract class and interface
 */
public final class PublicPark extends AbstractBuilding implements Accessible {
    // FINAL CLASS - no class can extend PublicPark

    private double greenSpaceArea;
    private boolean hasPlayground;
    private boolean hasFountain;
    private int numberOfBenches;
    private String parkType;

    // Accessibility
    private boolean wheelchairAccess;
    private int accessibleParking;

    // FINAL variable
    private final String parkRegistrationId;

    public PublicPark(String name, double area, int capacity, String parkType) {
        super(name, BuildingType.PARK);
        this.floors = 1; // Parks are single level
        this.area = area;
        this.capacity = capacity;
        this.parkType = parkType;
        this.greenSpaceArea = area * 0.8;
        this.hasPlayground = true;
        this.hasFountain = true;
        this.numberOfBenches = capacity / 10;
        this.wheelchairAccess = true;
        this.accessibleParking = 3;
        this.parkRegistrationId = "PARK-" + (int)(Math.random() * 10000); // FINAL
    }

    // IMPLEMENTING ABSTRACT METHODS

    @Override
    public double calculateMaintenanceCost() {
        double greenSpaceCost = greenSpaceArea * 0.3;
        double benchCost = numberOfBenches * 5;
        double playgroundCost = hasPlayground ? 200 : 0;
        double fountainCost = hasFountain ? 150 : 0;
        return greenSpaceCost + benchCost + playgroundCost + fountainCost;
    }

    @Override
    public void displaySpecificInfo() {
        System.out.println("\n=== Park Specific Information ===");
        System.out.println("Registration ID: " + parkRegistrationId); // FINAL
        System.out.println("Park Type: " + parkType);
        System.out.println("Green Space: " + String.format("%.2f", greenSpaceArea) + " sq meters");
        System.out.println("Playground: " + (hasPlayground ? "Available" : "Not Available"));
        System.out.println("Fountain: " + (hasFountain ? "Available" : "Not Available"));
        System.out.println("Benches: " + numberOfBenches);
        System.out.println("Maintenance Cost: $" + String.format("%.2f", calculateMaintenanceCost()));
    }

    @Override
    public void performSpecialOperation() {
        System.out.println("\n=== Park Special Operation ===");
        System.out.println("Organizing community event...");
        System.out.println("Landscape maintenance in progress...");

        if (hasPlayground) {
            System.out.println("Playground safety inspection: COMPLETED");
        }
        if (hasFountain) {
            System.out.println("Fountain maintenance: COMPLETED");
        }
    }

    // IMPLEMENTING Accessible INTERFACE

    @Override
    public boolean hasWheelchairAccess() {
        return wheelchairAccess;
    }

    @Override
    public boolean hasElevator() {
        return false; // Parks don't need elevators (single level)
    }

    @Override
    public int getAccessibleParkingSpots() {
        return accessibleParking;
    }

    // FINAL METHOD - cannot be overridden by subclasses (if this class wasn't final)
    public final void openToPublic() {
        System.out.println("\n" + name + " is now OPEN to the public!");
        System.out.println("Park Type: " + parkType);
        System.out.println("Visitor Capacity: " + capacity);
        System.out.println("Enjoy the green spaces!");
    }

    // FINAL METHOD
    public final void closeToPublic() {
        System.out.println("\n" + name + " is now CLOSED.");
        System.out.println("Closing time for maintenance and safety.");
    }

    // Regular method specific to park
    public void organizeEvent(String eventName, int expectedVisitors) {
        System.out.println("\n=== Park Event ===");
        System.out.println("Event: " + eventName);
        System.out.println("Park: " + name);

        if (expectedVisitors <= capacity) {
            System.out.println("Event APPROVED");
            System.out.println("Expected Visitors: " + expectedVisitors);
        } else {
            System.out.println("Event DENIED - Exceeds capacity");
            System.out.println("Maximum capacity: " + capacity);
        }
    }

    public String getParkRegistrationId() {
        return parkRegistrationId; // FINAL
    }

    public String getParkType() {
        return parkType;
    }
}