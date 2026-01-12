package smartcity;

/**
 * MODULE 4: BuildingStatus Enum
 *
 * This demonstrates:
 * - ENUM definition
 * - Enum with attributes
 * - Enum methods
 * - Using enum in switch-case
 */
public enum BuildingStatus {
    // ENUM constants with attributes
    OPERATIONAL("Operational", "Building is fully functional", 1.0),
    UNDER_MAINTENANCE("Under Maintenance", "Building is being maintained", 0.5),
    UNDER_CONSTRUCTION("Under Construction", "Building is being built", 0.0),
    CLOSED("Closed", "Building is closed", 0.0),
    EMERGENCY("Emergency", "Building is in emergency mode", 0.8);

    // FINAL attributes for each enum constant
    private final String displayName;
    private final String description;
    private final double operationalLevel; // 0.0 to 1.0

    // Enum constructor (always private)
    BuildingStatus(String displayName, String description, double operationalLevel) {
        this.displayName = displayName;
        this.description = description;
        this.operationalLevel = operationalLevel;
    }

    // Getters for enum attributes
    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public double getOperationalLevel() {
        return operationalLevel;
    }

    // Enum method to check if building is usable
    public boolean isUsable() {
        return operationalLevel > 0.0;
    }

    // Enum method to get color code for UI display
    public String getColorCode() {
        switch (this) {  // ENUM in SWITCH-CASE
            case OPERATIONAL:
                return "GREEN";
            case UNDER_MAINTENANCE:
                return "YELLOW";
            case UNDER_CONSTRUCTION:
                return "BLUE";
            case CLOSED:
                return "RED";
            case EMERGENCY:
                return "ORANGE";
            default:
                return "GRAY";
        }
    }

    // Enum method to get recommended action
    public String getRecommendedAction() {
        switch (this) {  // ENUM in SWITCH-CASE
            case OPERATIONAL:
                return "Continue normal operations";
            case UNDER_MAINTENANCE:
                return "Complete maintenance and resume operations";
            case UNDER_CONSTRUCTION:
                return "Complete construction work";
            case CLOSED:
                return "Review closure reasons and plan reopening";
            case EMERGENCY:
                return "Address emergency and restore normal status";
            default:
                return "Review building status";
        }
    }

    // Static method to get status from string
    public static BuildingStatus fromString(String statusStr) {
        for (BuildingStatus status : BuildingStatus.values()) {
            if (status.displayName.equalsIgnoreCase(statusStr)) {
                return status;
            }
        }
        return OPERATIONAL; // Default
    }
}