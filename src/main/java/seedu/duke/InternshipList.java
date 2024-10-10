package seedu.duke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class InternshipList {
    public ArrayList<Internship> internships;

    // Constructor
    public InternshipList() {
        internships = new ArrayList<>();
    }

    public void addInternship(Internship internship) {
        if (internship.getId() == -1) {
            internship.setId(internships.size());
        }
        internships.add(internship);
    }

    // Method to remove an internship by index (0-based)
    public void removeInternship(int index) {
        if (index >= 0 && index < internships.size()) {
            internships.remove(index);
            updateIds(); // Reassign IDs after removal
        } else {
            System.out.println("Invalid index");
        }
    }

    // Private method to update the IDs after a removal
    private void updateIds() {
        for (int i = 0; i < internships.size(); i++) {
            internships.get(i).setId(i);
        }
    }

    // Method to get an internship by index
    public Internship getInternship(int index) {
        if (index >= 0 && index < internships.size()) {
            return internships.get(index);
        } else {
            System.out.println("Invalid index");
            return null;
        }
    }

    //@@author Ridiculouswifi

    /**
     * Updates the specified field with new values..
     *
     * @param index <code>Internship</code> index in <code>InternshipList</code>.
     * @param field Specific attribute to update.
     * @param value Updated value
     */
    public void updateField(int index, String field, String value) {
        if (index >= 0 && index < internships.size()) {
            internships.get(index).updateField(field, value);
        } else {
            System.out.println("Invalid index");
        }
    }

    // Method to list all internships
    public void listAllInternships() {
        if (internships.isEmpty()) {
            System.out.println("No internships found.");
        } else {
            for (Internship internship : internships) {
                System.out.println(internship);
                System.out.println("---------------------------------");
            }
        }
    }

    public List<Internship> getAllInternships() {
        return Collections.unmodifiableList(internships);
    }

    // Method to list all internships in sorted order without modifying the IDs
    public void listInternshipsSortedByRole() {
        ArrayList<Internship> sortedList = new ArrayList<>(internships);
        Collections.sort(sortedList, Comparator.comparing(Internship::getRole));

        // Display the sorted list without changing IDs
        for (Internship internship : sortedList) {
            System.out.println(internship);
            System.out.println("---------------------------------");
        }
    }

    // Method to list all internships sorted by start date, then end date
    public void listInternshipsSortedByDeadline() {
        ArrayList<Internship> sortedInternships = new ArrayList<>(internships);
        Collections.sort(sortedInternships, new Comparator<Internship>() {
            @Override
            public int compare(Internship i1, Internship i2) {
                // Compare start dates
                int startComparison = i1.getStartDate().compareTo(i2.getStartDate());
                if (startComparison != 0) {
                    return startComparison;
                }
                // If start dates are equal, compare end dates
                return i1.getEndDate().compareTo(i2.getEndDate());
            }
        });

        // Display the sorted list without changing IDs
        for (Internship internship : sortedInternships) {
            System.out.println(internship);
            System.out.println("---------------------------------");
        }
    }
}
