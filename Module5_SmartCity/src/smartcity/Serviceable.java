package smartcity;

/**
 * MODULE 4: Serviceable Interface
 *
 * This demonstrates:
 * - INTERFACE definition
 * - Abstract methods (methods without implementation)
 * - Default methods (methods with implementation)
 * - STATIC FINAL constants in interface
 */
public interface Serviceable {

    // STATIC FINAL constant (public static final by default)
    int MAX_SERVICE_REQUESTS = 100;
    double SERVICE_FEE_MULTIPLIER = 1.5;

    // ABSTRACT METHOD (public abstract by default)
    // Classes implementing this interface MUST provide implementation
    void provideService(String serviceType);

    // ABSTRACT METHOD
    double calculateServiceCost();

    // ABSTRACT METHOD
    boolean isServiceAvailable(String serviceType);

    // ABSTRACT METHOD
    String[] getAvailableServices();

    // Default method (has implementation, can be overridden)
    default void displayServiceInfo() {
        System.out.println("\n=== Service Information ===");
        System.out.println("Available Services:");
        String[] services = getAvailableServices();
        for (int i = 0; i < services.length; i++) {
            System.out.println((i + 1) + ". " + services[i]);
        }
        System.out.println("Base Service Cost: $" + calculateServiceCost());
    }

    // Default method
    default void requestService(String serviceType, int quantity) {
        if (isServiceAvailable(serviceType)) {
            System.out.println("Service Request Accepted");
            System.out.println("Service: " + serviceType);
            System.out.println("Quantity: " + quantity);
            double cost = calculateServiceCost() * quantity * SERVICE_FEE_MULTIPLIER;
            System.out.println("Total Cost: $" + String.format("%.2f", cost));
        } else {
            System.out.println("Service '" + serviceType + "' is not available!");
        }
    }

    // Static method in interface
    static void displayInterfaceInfo() {
        System.out.println("This building implements the Serviceable interface");
        System.out.println("Max service requests allowed: " + MAX_SERVICE_REQUESTS);
    }
}