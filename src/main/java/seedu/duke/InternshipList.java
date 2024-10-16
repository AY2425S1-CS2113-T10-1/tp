package seedu.duke;

import seedu.exceptions.InvalidIndex;
import seedu.exceptions.InvalidStatus;
import seedu.ui.UiInternshipList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

//@@author jadenlimjc
public class InternshipList {
    private static final UiInternshipList ui = new UiInternshipList();
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

    //@@ Ridiculouswifi
    /**
     * Returns whether the index given is within the boundaries of the list.
     */
    public boolean isWithinBounds(int index) {
        if (index >= 0 && index < internships.size()) {
            return true;
        }
        ui.showInvalidIndex();
        return false;
    }

    // Method to remove an internship by index (0-based)
    public void removeInternship(int index) {
        if (isWithinBounds(index)) {
            internships.remove(index);
            ui.showDeletedInternship(index + 1);
            updateIds(); // Reassign IDs after removal
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
        if (isWithinBounds(index)) {
            return internships.get(index);
        }
        return null;
    }

    //@@author Ridiculouswifi
    /**
     * Updates the specified field with new values.
     *
     * @param index <code>Internship</code> index in <code>InternshipList</code>.
     * @param field Specific attribute to update.
     * @param value Updated value
     */
    public void updateField(int index, String field, String value) throws InvalidIndex, InvalidStatus {
        try {
            switch (field) {
            case "status":
                internships.get(index).updateStatus(value);
                break;
            case "skills":
                internships.get(index).setSkills(value);
                break;
            case "role":
                internships.get(index).setRole(value);
                break;
            case "company":
                internships.get(index).setCompany(value);
                break;
            case "from":
                internships.get(index).setStartDate(value);
                break;
            case "to":
                internships.get(index).setEndDate(value);
                break;
            default:
                break;
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showInvalidIndex();
            throw new InvalidIndex();
        }
    }

    // Method to list all internships
    public void listAllInternships() {
        if (internships.isEmpty()) {
            ui.showEmptyInternshipList();
        } else {
            ui.showInternships(internships);
        }
    }

    public List<Internship> getAllInternships() {
        return Collections.unmodifiableList(internships);
    }

    public int getSize() {
        return internships.size();
    }

    // Method to list all internships in sorted order by role (case-insensitive)
    public void listInternshipsSortedByRole() {
        ArrayList<Internship> sortedList = new ArrayList<>(internships);

        // Sort roles alphabetically, ignoring case sensitivity
        Collections.sort(sortedList, Comparator.comparing(internship -> internship.getRole().toLowerCase()));

        // Display the sorted list without changing IDs
        ui.showInternships(sortedList);
    }

    // Method to list all internships sorted by start date (year first), then end date
    public void listInternshipsSortedByDeadline() {
        ArrayList<Internship> sortedInternships = new ArrayList<>(internships);

        Collections.sort(sortedInternships, (i1, i2) -> {
            // First compare start dates (year then month)
            int startComparison = compareYearMonth(i1.getStartDate(), i2.getStartDate());
            if (startComparison != 0) {
                return startComparison;
            }
            // If start dates are equal, compare end dates (year then month)
            return compareYearMonth(i1.getEndDate(), i2.getEndDate());
        });

        // Display the sorted list without changing IDs
        ui.showInternships(sortedInternships);
    }

    // Helper method to compare YearMonth strings in "MM/yy" format (year first, then month)
    private int compareYearMonth(String date1, String date2) {
        String[] parts1 = date1.split("/");
        String[] parts2 = date2.split("/");

        int year1 = Integer.parseInt(parts1[1]);
        int year2 = Integer.parseInt(parts2[1]);
        int month1 = Integer.parseInt(parts1[0]);
        int month2 = Integer.parseInt(parts2[0]);

        if (year1 != year2) {
            return Integer.compare(year1, year2);
        }
        return Integer.compare(month1, month2);
    }
}
