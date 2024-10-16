package seedu.commands;

import seedu.duke.InternshipList;
import seedu.duke.Internship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FilterCommand extends Command {
    private InternshipList filteredInternships;

    public FilterCommand() {
        this.filteredInternships = new InternshipList();
    }

    @Override
    public void execute(ArrayList<String> args) {
        if (args.isEmpty()) {
            uiCommand.showInsufficientArguments();
            return;
        }

        if (args.size() > 1) {
            uiCommand.showOutput("Too many flags provided. Can only filter by one flag at a time");
            return;
        }

        String[] words = args.get(0).split(" ", 2);
        String flag = words[0];
        try {
            // Map flags to getter methods using lambdas
            Map<String, InternshipFieldGetter> fieldGetters = new HashMap<>();
            fieldGetters.put("role", Internship::getRole);
            fieldGetters.put("company", Internship::getCompany);
            fieldGetters.put("from", Internship::getStartDate);
            fieldGetters.put("to", Internship::getEndDate);

            // Retrieve the corresponding getter method based on the flag
            InternshipFieldGetter getter = fieldGetters.get(flag);

            if (getter == null) {
                uiCommand.clearInvalidFlags();
                uiCommand.addInvalidFlag(flag);
                uiCommand.printInvalidFlags();
                return;
            }

            String searchTerm = words[1];

            // Iterate over the internships and apply the getter for comparison
            for (Internship internship : internships.getAllInternships()) {
                String fieldValue = getter.getField(internship); // Dynamically calls getRole(), getCompany(), etc.
                if (fieldValue.equalsIgnoreCase(searchTerm)) {
                    filteredInternships.addInternship(internship);
                }
            }

            filteredInternships.listAllInternships();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            uiCommand.showOutput(words[INDEX_FIELD] + " field cannot be empty");
        }
    }

    @Override
    public String getUsage() {
        return """
                filter
                Usage: filter -name {Role name}""";
    }
}
