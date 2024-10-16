package seedu.commands;

import java.util.ArrayList;

public class SortCommand extends Command {

    // Execute method for the SortCommand
    @Override
    public void execute(ArrayList<String> args) {
        // Check if no arguments are provided after "sort"
        if (args.isEmpty()) {
            uiCommand.showSortedInternships("none");  // No valid sort option provided
            internships.listAllInternships();  // Default to listing by ID
            return;
        }

        // Get the first argument, which should be the sort option
        String sortOption = args.get(0).toLowerCase();

        // Handle valid sorting options
        switch (sortOption) {
        case "alphabet":
            uiCommand.showSortedInternships(sortOption);  // Show sorting message for alphabet
            internships.listInternshipsSortedByRole();  // Sort by role alphabetically (case-insensitive)
            break;
        case "deadline":
            uiCommand.showSortedInternships(sortOption);  // Show sorting message for deadline
            internships.listInternshipsSortedByDeadline();  // Sort by start date, then end date (year first)
            break;
        default:
            // Handle invalid sorting options
            uiCommand.showOutput("Invalid field");  // Show error message for invalid option
            System.out.println(uiCommand.getSortUsageMessage());  // Show correct usage message
            internships.listAllInternships();  // Default to listing by ID
        }
    }

    @Override
    public String getUsage() {
        return """
                sort
                Usage: sort [alphabet | deadline]""";
    }
}
