package seedu.commands;

import java.util.ArrayList;

//@@author Toby-Yu
/**
 * Command to sort internships by different fields: alphabetically by role, by duration, by earliest deadline,
 * by the first skill in the skills list, or by status alphabetically.
 *
 * Usage:
 * - `sort -alphabet`: Sort internships alphabetically by role (case-insensitive).
 * - `sort -duration`: Sort internships by start date (year first), then end date.
 * - `sort -deadline`: Sort internships by earliest deadline.
 * - `sort -skills`: Sort internships by the first skill in the skills list alphabetically.
 * - `sort -status`: Sort internships by status alphabetically.
 *
 * Invalid sort options will display an error message along with the correct usage.
 */
public class SortCommand extends Command {

    // Execute method for the SortCommand
    @Override
    public void execute(ArrayList<String> args) {
        uiCommand.clearInvalidFlags();

        // Check if no arguments are provided after "sort"
        if (args.isEmpty()) {
            internships.listInternshipsNotSorted(); // No valid sort option provided
            return;
        }

        // Get the first argument, which should be the sort option
        String sortOption = args.get(0).toLowerCase();

        // Handle valid sorting options
        switch (sortOption) {
        case "role":
            internships.listInternshipsSortedByRole();  // Sort by role alphabetically (case-insensitive)
            break;
        case "duration":
            internships.listInternshipsSortedByDuration();  // Sort by start date, then end date (year first)
            break;
        case "deadline":
            internships.listInternshipsSortedByDeadline();
            break;
        case "skills":
            internships.listInternshipsSortedByFirstSkill();  // Sort by first skill alphabetically
            break;
        case "status":
            internships.listInternshipsSortedByStatus();  // Sort by status alphabetically
            break;
        case "role in favourite":
            internships.listFavouriteInternshipsSortedByRole();  // Sort by role alphabetically (case-insensitive)
            break;
        case "duration in favourite":
            internships.listFavouriteInternshipsSortedByDuration();  // Sort by start date, then end date (year first)
            break;
        case "deadline in favourite":
            internships.listFavouriteInternshipsSortedByDeadline();
            break;
        case "skills in favourite":
            internships.listFavouriteInternshipsSortedByFirstSkill();  // Sort by first skill alphabetically
            break;
        case "status in favourite":
            internships.listFavouriteInternshipsSortedByStatus();  // Sort by status alphabetically
            break;
        default:
            // Handle invalid sorting options
            internships.listInternshipsInvalidFlag(sortOption);
        }
    }

    @Override
    public String getUsage() {
        return """
                sort
                Usage: sort [role | duration | deadline | skills | status | role in favourite
                            | duration in favourite | deadline in favourite
                            | skills in favourite | status in favourite ]
                
                role: Sort internships alphabetically by role (case-insensitive).
                deadline: Sort internships by start date (year first), then end date.
                duration: Sort internships by internship duration.
                skills: Sort internships by the first skill alphabetically.
                status: Sort internships by status alphabetically.
                role in favourite: Sort internships in favourite alphabetically by role (case-insensitive).
                deadline in favourite: Sort internships in favourite by start date (year first), then end date.
                duration in favourite: Sort internships in favourite by internship duration.
                skills in favourite: Sort internships in favourite by the first skill alphabetically.
                status in favourite: Sort internships in favourite by status alphabetically.
                """;
    }
}
