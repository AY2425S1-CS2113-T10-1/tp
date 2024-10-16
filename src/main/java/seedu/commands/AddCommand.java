package seedu.commands;

import seedu.duke.Internship;

import java.util.ArrayList;

//@@author jadenlimjc
public class AddCommand extends Command {
    @Override
    public void execute(ArrayList<String> args) {
        String role = "";
        String company = "";
        String startDate = "01/01";
        String endDate = "01/01";
        boolean hasRole = false;
        boolean hasCompany = false;

        uiCommand.clearInvalidFlags();
        for (String arg : args) {
            String[] words = arg.split(" ", 2);
            String flag = words[0];
            switch (flag) {
            case "role":
                hasRole = true;
                if (words.length > 1) {
                    role = words[INDEX_DATA];
                } else {
                    uiCommand.addInvalidFlag(flag);
                }
                break;
            case "company":
                hasCompany = true;
                if (words.length > 1) {
                    company = words[INDEX_DATA];
                } else {
                    uiCommand.addInvalidFlag(flag);
                }
                break;
            case "from":
                if (words.length > 1) {
                    startDate = words[INDEX_DATA];
                } else {
                    uiCommand.addInvalidFlag(flag);
                }
                break;
            case "to":
                if (words.length > 1) {
                    endDate = words[INDEX_DATA];
                } else {
                    uiCommand.addInvalidFlag(flag);
                }
                break;
            default:
                uiCommand.addInvalidFlag(flag);
                break;
            }

        }

        if (!hasRole) {
            uiCommand.addInvalidFlag("role");
        }

        if (!hasCompany) {
            uiCommand.addInvalidFlag("company");
        }
        if (!uiCommand.getInvalidFlags().isEmpty()) {
            uiCommand.printInvalidFlags();
            return;
        }

        Internship newInternship = new Internship(role, company, startDate, endDate);
        internships.addInternship(newInternship);
        uiCommand.showEditedInternship(newInternship, "add");
    }

    @Override
    public String getUsage() {
        return """
                add
                Usage: add -role {Role name} -company {Company name} -from {start date} -to {end date}""";
    }
}
