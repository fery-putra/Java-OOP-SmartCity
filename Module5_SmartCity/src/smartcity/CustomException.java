package smartcity;

/**
 * MODULE 5: Custom Exception Classes
 *
 * This demonstrates:
 * - Creating CUSTOM EXCEPTIONS by extending Exception class
 * - Exception constructors
 * - Throwing custom exceptions
 */

/**
 * Custom exception for building capacity issues
 */
class BuildingCapacityException extends Exception {
    private int currentOccupancy;
    private int maxCapacity;

    // Constructor with message only
    public BuildingCapacityException(String message) {
        super(message);
    }

    // Constructor with message and details
    public BuildingCapacityException(String message, int currentOccupancy, int maxCapacity) {
        super(message);
        this.currentOccupancy = currentOccupancy;
        this.maxCapacity = maxCapacity;
    }

    // Method to get detailed error information
    public String getDetailedMessage() {
        return super.getMessage() +
                "\nCurrent Occupancy: " + currentOccupancy +
                "\nMaximum Capacity: " + maxCapacity +
                "\nOvercapacity: " + (currentOccupancy - maxCapacity);
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}

/**
 * Custom exception for building status issues
 */
class InvalidBuildingStatusException extends Exception {
    private BuildingStatus currentStatus;
    private String attemptedOperation;

    public InvalidBuildingStatusException(String message, BuildingStatus currentStatus, String operation) {
        super(message);
        this.currentStatus = currentStatus;
        this.attemptedOperation = operation;
    }

    public String getDetailedMessage() {
        return super.getMessage() +
                "\nCurrent Status: " + currentStatus.getDisplayName() +
                "\nAttempted Operation: " + attemptedOperation +
                "\nRecommendation: " + currentStatus.getRecommendedAction();
    }

    public BuildingStatus getCurrentStatus() {
        return currentStatus;
    }
}

/**
 * Custom exception for invalid building data
 */
class InvalidBuildingDataException extends Exception {
    private String fieldName;
    private Object invalidValue;

    public InvalidBuildingDataException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }

    public InvalidBuildingDataException(String message, String fieldName, Object invalidValue) {
        super(message);
        this.fieldName = fieldName;
        this.invalidValue = invalidValue;
    }

    public String getDetailedMessage() {
        String msg = super.getMessage() + "\nField: " + fieldName;
        if (invalidValue != null) {
            msg += "\nInvalid Value: " + invalidValue;
        }
        return msg;
    }

    public String getFieldName() {
        return fieldName;
    }
}

/**
 * Custom exception for service unavailability
 */
class ServiceUnavailableException extends Exception {
    private String serviceName;
    private String buildingName;

    public ServiceUnavailableException(String message, String serviceName, String buildingName) {
        super(message);
        this.serviceName = serviceName;
        this.buildingName = buildingName;
    }

    public String getDetailedMessage() {
        return super.getMessage() +
                "\nService Requested: " + serviceName +
                "\nBuilding: " + buildingName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getBuildingName() {
        return buildingName;
    }
}

/**
 * Custom exception for building not found
 */
class BuildingNotFoundException extends Exception {
    private String searchCriteria;

    public BuildingNotFoundException(String message) {
        super(message);
    }

    public BuildingNotFoundException(String message, String searchCriteria) {
        super(message);
        this.searchCriteria = searchCriteria;
    }

    public String getDetailedMessage() {
        String msg = super.getMessage();
        if (searchCriteria != null) {
            msg += "\nSearch Criteria: " + searchCriteria;
        }
        return msg;
    }
}

/**
 * Custom exception for city capacity issues
 */
class CityCapacityException extends Exception {
    private int currentBuildingCount;
    private int maxBuildings;

    public CityCapacityException(String message, int currentCount, int maxCount) {
        super(message);
        this.currentBuildingCount = currentCount;
        this.maxBuildings = maxCount;
    }

    public String getDetailedMessage() {
        return super.getMessage() +
                "\nCurrent Buildings: " + currentBuildingCount +
                "\nMaximum Allowed: " + maxBuildings;
    }
}