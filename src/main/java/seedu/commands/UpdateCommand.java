package seedu.commands;

import java.util.ArrayList;

public class UpdateCommand extends Command {
    @Override
    public void execute(ArrayList<String> args) {
        int internshipId = Integer.parseInt(args.get(0));
        int internshipIndex = internshipId - 1;
        args.remove(0);

        String field = "";
        String value = "";

        for (String arg : args) {
            String[] words = arg.split(" ", 2);
            switch (words[INDEX_FIELD]) {
            case "status":
            case "skills":
            case "role":
            case "company":
            case "end":
                field = words[INDEX_FIELD];
                value = words[INDEX_DATA].replace(field, "").trim();
                internships.getInternship(internshipIndex).updateField(field, value);
                break;
            default:
                System.out.println("Unknown flag: " + words[INDEX_FIELD]);
                break;
            }
        }
        System.out.println(internships.toString());
    }

    public String getUsage() {
        return """
                Usage: update -id {ID} -{field} {status}
                
                Choose from the following statuses:
                - Application Pending
                - Application Completed
                - Accepted
                - Rejected
                """;
    }
}
