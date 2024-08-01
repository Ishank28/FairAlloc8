import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class allocationprocess {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Get input from user
        List<String> applicants = getInputApplicants(scanner);
        List<String> flats = getInputFlats(scanner);

        // Step 2: Perform fair allocation
        List<String> allocations = fairAllocate(applicants, flats);

        // Step 3: Display allocation results
        displayAllocationResults(allocations);
    }

    private static List<String> getInputApplicants(Scanner scanner) {
        List<String> applicants = new ArrayList<>();
        System.out.println("Enter applicant names (one per line, type 'done' when finished):");
        String name;
        while (!(name = scanner.nextLine()).equals("done")) {
            applicants.add(name);
        }
        return applicants;
    }

    private static List<String> getInputFlats(Scanner scanner) {
        List<String> flats = new ArrayList<>();
        System.out.println("Enter flat addresses (one per line, type 'done' when finished):");
        String address;
        while (!(address = scanner.nextLine()).equals("done")) {
            flats.add(address);
        }
        return flats;
    }

    private static List<String> fairAllocate(List<String> applicants, List<String> flats) {
        List<String> allocations = new ArrayList<>();
        List<String> availableFlats = new ArrayList<>(flats); // Make a copy of available flats

        Collections.shuffle(applicants, new Random()); // Shuffle applicants randomly

        for (String applicant : applicants) {
            if (availableFlats.isEmpty()) {
                // If no more available flats, break the loop
                break;
            }

            // Randomly select an available flat for the applicant
            int randomIndex = new Random().nextInt(availableFlats.size());
            String allocatedFlat = availableFlats.get(randomIndex);

            // Allocate the flat to the applicant
            allocations.add(applicant + " has been allocated " + allocatedFlat);

            // Remove the allocated flat from the list of available flats
            availableFlats.remove(randomIndex);
        }

        return allocations;
    }

    private static void displayAllocationResults(List<String> allocations) {
        System.out.println("Allocation Results:");
        for (String allocation : allocations) {
            System.out.println(allocation);
        }
    }
}