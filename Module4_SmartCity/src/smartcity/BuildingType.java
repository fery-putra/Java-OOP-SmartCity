package smartcity;

/**
 * MODULE 4: BuildingType Enum
 *
 * ENUM for different building types
 */
public enum BuildingType {
    HOSPITAL("Hospital", "Healthcare Facility", 100),
    SCHOOL("School", "Educational Institution", 80),
    OFFICE("Office", "Commercial Building", 60),
    PARK("Park", "Recreation Area", 50),
    RESIDENTIAL("Residential", "Housing Complex", 70),
    SHOPPING_MALL("Shopping Mall", "Retail Center", 90);

    private final String name;
    private final String category;
    private final int priorityLevel; // Higher = more critical

    BuildingType(String name, String category, int priorityLevel) {
        this.name = name;
        this.category = category;
        this.priorityLevel = priorityLevel;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    // Method to determine if building type is critical infrastructure
    public boolean isCriticalInfrastructure() {
        switch (this) {  // ENUM in SWITCH-CASE
            case HOSPITAL:
            case SCHOOL:
                return true;
            case OFFICE:
            case PARK:
            case RESIDENTIAL:
            case SHOPPING_MALL:
                return false;
            default:
                return false;
        }
    }
}