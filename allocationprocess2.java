import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class allocationprocess2
 {

    public static void main(String[] args) {
        // Step 1: Prompt builder to specify the type of project
        Scanner scanner = new Scanner(System.in);
        System.out.println("Is the project affordable or luxury? Enter 'affordable' or 'luxury': ");
        String projectType = scanner.nextLine().trim().toLowerCase();

        // Step 2: Define applicants and resources
        List<Applicant> applicants = new ArrayList<>();
        List<Flat> flats = new ArrayList<>();

        // Input applicants and flats until the user is done
        inputApplicants(scanner, applicants);
        inputFlats(scanner, flats);

        // Step 3: Perform fair allocation based on project type
        if (projectType.equals("affordable")) {
            // For affordable projects, use a lottery system for fair allocation
            System.out.println("Performing fair allocation for affordable project...");
            List<Allocation> allocations = fairAllocate(applicants, flats);
            displayAllocationResults(allocations);
        } else if (projectType.equals("luxury")) {
            // For luxury projects, implement sophisticated allocation strategy
            System.out.println("Performing fair allocation for luxury project...");
            List<Allocation> allocations = allocateLuxuryFlats(applicants, flats);
            displayAllocationResults(allocations);
        } else {
            System.out.println("Invalid project type. Please specify 'affordable' or 'luxury'.");
        }

        scanner.close();
    }

    // Method to input details of applicants until the user is done
    public static void inputApplicants(Scanner scanner, List<Applicant> applicants) {
        System.out.println("Enter details of applicants (name, NRI status, age, income level, family size, prefers luxury, current location): ");
        System.out.println("Enter 'done' when finished.");

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            String[] parts = input.split(",");
            if (parts.length != 7) {
                System.out.println("Invalid input. Please enter all details separated by commas.");
                continue;
            }

            String name = parts[0].trim();
            boolean nri = Boolean.parseBoolean(parts[1].trim());
            int age = Integer.parseInt(parts[2].trim());
            int incomeLevel = Integer.parseInt(parts[3].trim());
            int familySize = Integer.parseInt(parts[4].trim());
            boolean prefersLuxury = Boolean.parseBoolean(parts[5].trim());
            String currentLocation = parts[6].trim();

            applicants.add(new Applicant(name, nri, age, incomeLevel, familySize, prefersLuxury, currentLocation));
        }
    }

    // Method to input details of flats until the user is done
    public static void inputFlats(Scanner scanner, List<Flat> flats) {
        System.out.println("Enter details of available flats (name, location, luxury status, price): ");
        System.out.println("Enter 'done' when finished.");

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            String[] parts = input.split(",");
            if (parts.length != 4) {
                System.out.println("Invalid input. Please enter all details separated by commas.");
                continue;
            }

            String name = parts[0].trim();
            String location = parts[1].trim();
            boolean luxury = Boolean.parseBoolean(parts[2].trim());
            int price = Integer.parseInt(parts[3].trim());

            flats.add(new Flat(name, location, luxury, price));
        }
    }

    // Fair allocation method (used for affordable projects)
    public static List<Allocation> fairAllocate(List<Applicant> applicants, List<Flat> flats) {
        List<Allocation> allocations = new ArrayList<>();
        List<Flat> availableFlats = new ArrayList<>(flats); // Make a copy of available flats

        Collections.shuffle(applicants); // Shuffle applicants randomly

        for (Applicant applicant : applicants) {
            if (availableFlats.isEmpty()) {
                // If no more available flats, break the loop
                break;
            }

            // Randomly select an available flat for the applicant
            int randomIndex = (int) (Math.random() * availableFlats.size());
            Flat allocatedFlat = availableFlats.get(randomIndex);

            // Allocate the flat to the applicant
            allocations.add(new Allocation(applicant.getName(), allocatedFlat));

            // Remove the allocated flat from the list of available flats
            availableFlats.remove(randomIndex);
        }

        return allocations;
    }

    // Method to allocate luxury flats based on specified preferences
    public static List<Allocation> allocateLuxuryFlats(List<Applicant> applicants, List<Flat> flats) {
        List<Allocation> allocations = new ArrayList<>();
        List<Flat> availableFlats = new ArrayList<>(flats); // Make a copy of available flats

        // Prioritize applicants based on specified preferences
        Collections.sort(applicants, (a1, a2) -> {
            // Add your prioritization logic here (similar to previous implementation)
            // For simplicity, let's assume all applicants have equal priority
            return 0;
        });

        // Allocate luxury flats based on prioritized applicants
        for (Applicant applicant : applicants) {
            if (availableFlats.isEmpty()) {
                // If no more available flats, break the loop
                break;
            }

            // Randomly select an available flat for the applicant
            int randomIndex = (int) (Math.random() * availableFlats.size());
            Flat allocatedFlat = availableFlats.get(randomIndex);

            // Allocate the flat to the applicant
            allocations.add(new Allocation(applicant.getName(), allocatedFlat));

            // Remove the allocated flat from the list of available flats
            availableFlats.remove(randomIndex);
        }

        return allocations;
    }

    // Method to display allocation results
    public static void displayAllocationResults(List<Allocation> allocations) {
        System.out.println("Allocation Results:");
        for (Allocation allocation : allocations) {
            System.out.println(allocation.getApplicant() + " -> " + allocation.getFlat());
        }
    }
}

// Class to represent an applicant
class Applicant {
    private String name;
    private boolean nri;
    private int age;
    private int incomeLevel;
    private int familySize;
    private boolean prefersLuxury;
    private String currentLocation;

    public Applicant(String name, boolean nri, int age, int incomeLevel, int familySize, boolean prefersLuxury, String currentLocation) {
        this.name = name;
        this.nri = nri;
        this.age = age;
        this.incomeLevel = incomeLevel;
        this.familySize = familySize;
        this.prefersLuxury = prefersLuxury;
        this.currentLocation = currentLocation;
    }

    // Getters for applicant properties
    public String getName() {
        return name;
    }

    public boolean isNri() {
        return nri;
    }

    public int getAge() {
        return age;
    }

    public int getIncomeLevel() {
        return incomeLevel;
    }

    public int getFamilySize() {
        return familySize;
    }

    public boolean prefersLuxury() {
        return prefersLuxury;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", NRI: " + nri + ", Age: " + age + ", Income Level: " + incomeLevel +
                ", Family Size: " + familySize + ", Prefers Luxury: " + prefersLuxury + ", Current Location: " + currentLocation;
    }
}

// Class to represent a flat
class Flat {
    private String name;
    private String location;
    private boolean luxury;
    private int price;

    public Flat(String name, String location, boolean luxury, int price) {
        this.name = name;
        this.location = location;
        this.luxury = luxury;
        this.price = price;
    }

    // Getters for flat properties
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public boolean isLuxury() {
        return luxury;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Location: " + location + ", Luxury: " + luxury + ", Price: " + price;
    }
}

// Class to represent an allocation
class Allocation {
    private String applicant;
    private Flat flat;

    public Allocation(String applicant, Flat flat) {
        this.applicant = applicant;
        this.flat = flat;
    }

    // Getters for applicant and flat
    public String getApplicant() {
        return applicant;
    }

    public Flat getFlat() {
        return flat;
    }

    @Override
    public String toString() {
        return "Applicant: " + applicant + ", Flat: " + flat;
    }
}
