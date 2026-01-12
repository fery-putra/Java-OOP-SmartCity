package smartcity;

/**
 * MODULE 4: Accessible Interface
 *
 * Interface for buildings that provide public accessibility
 */
public interface Accessible {

    // STATIC FINAL constants
    String ACCESSIBILITY_STANDARD = "ADA Compliant";
    int MIN_ENTRANCE_WIDTH = 90; // cm

    // ABSTRACT METHODS
    boolean hasWheelchairAccess();
    boolean hasElevator();
    int getAccessibleParkingSpots();

    // Default method
    default void displayAccessibilityInfo() {
        System.out.println("\n=== Accessibility Features ===");
        System.out.println("Standard: " + ACCESSIBILITY_STANDARD);
        System.out.println("Wheelchair Access: " + (hasWheelchairAccess() ? "Yes" : "No"));
        System.out.println("Elevator: " + (hasElevator() ? "Yes" : "No"));
        System.out.println("Accessible Parking: " + getAccessibleParkingSpots() + " spots");
    }

    // Default method to check compliance
    default boolean isFullyAccessible() {
        return hasWheelchairAccess() && hasElevator() && getAccessibleParkingSpots() > 0;
    }
}