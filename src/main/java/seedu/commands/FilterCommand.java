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
            ui.showInsufficientArguments();
            return;
        }

        String[] words = args.get(0).split(" ", 2);
        String flag = words[0];
        String searchTerm = words[1];

        // Map flags to getter methods using lambdas
        Map<String, InternshipFieldGetter> fieldGetters = new HashMap<>();
        fieldGetters.put("name", Internship::getRole);
        fieldGetters.put("company", Internship::getCompany);
        fieldGetters.put("from", Internship::getStartDate);
        fieldGetters.put("to", Internship::getEndDate);

        // Retrieve the corresponding getter method based on the flag
        InternshipFieldGetter getter = fieldGetters.get(flag);

        if (getter == null) {
            ui.clearInvalidFlags();
            ui.addInvalidFlag(flag);
            ui.printInvalidFlags();
            return;
        }

        // Iterate over the internships and apply the getter for comparison
        for (Internship internship : internships.getAllInternships()) {
            String fieldValue = getter.getField(internship); // Dynamically calls getRole(), getCompany(), etc.
            if (fieldValue.equals(searchTerm)) {
                filteredInternships.addInternship(internship);
            }
        }

        filteredInternships.listAllInternships();
    }

    @Override
    public String getUsage() {
        return "Usage: filter -name {Role name}";
    }
}
