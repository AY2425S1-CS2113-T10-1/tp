package seedu.commands;

import seedu.duke.Deadline;
import seedu.duke.Internship;
import seedu.exceptions.InvalidDeadline;
import seedu.exceptions.InvalidIndex;
import seedu.exceptions.InvalidStatus;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

//@@author Ridiculouswifi
/**
 * Subclass of <code>Command</code> to handle updating <code>Internship</code> attributes
 */
public class UpdateCommand extends Command {
    @Override
    public void execute(ArrayList<String> args) {
        try {
            int internshipId = Integer.parseInt(args.get(0));
            int internshipIndex = internshipId - 1;
            if (!internships.isWithinBounds(internshipIndex)) {
                throw new InvalidIndex();
            }
            args.remove(0);


            uiCommand.clearInvalidFlags();
            uiCommand.clearUpdatedFields();
            uiCommand.clearInvalidFields();

            /*
            if (args.get(0).startsWith("deadline")) {
                String trimmedDescription = args.get(0).substring(args.get(0).indexOf(" ") + 1).trim();
                String trimmedDate = args.size() > 1 ? args.get(1).substring(args.get(1).indexOf(" ") + 1) : "";
                if (isValidDeadline(trimmedDescription,trimmedDate)) {
                    updateDeadline(internshipIndex, trimmedDescription,trimmedDate);
                }

            } else {
                for (String arg : args) {
                    String[] words = arg.split(" ", 2);
                    updateOneField(words, internshipIndex);
                }
            }
            */
            for (String arg : args) {
                String[] words = arg.split(" ", 2);
                updateOneField(words, internshipIndex);
            }

            uiCommand.showEditedInternship(internships.getInternship(internshipIndex), "update");
        } catch (NumberFormatException e) {
            uiCommand.showOutput("Invalid integer, please provide a valid internship ID");
        } catch (InvalidIndex e) {
            // Exception message is already handled in InternshipList class
        }
    }

    protected boolean isValidValue(String[] words) {
        try {
            String value = words[INDEX_DATA].trim();
            if (value.isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            return true;
        } catch (IndexOutOfBoundsException e) {
            uiCommand.addInvalidField(words[INDEX_FIELD], "Field cannot be empty");
            return false;
        }
    }

    protected void updateOneField(String[] words, int internshipIndex) throws InvalidIndex {
        String field = words[INDEX_FIELD];
        try {
            switch (field) {
            case "status":
            case "skills":
            case "role":
            case "company":
            case "from":
            case "to":
            case "deadline":
                if (!isValidValue(words)) {
                    return;
                }
                String value = words[INDEX_DATA].trim();
                internships.updateField(internshipIndex, field, value);
                uiCommand.addUpdatedField(field, value, "update");
                break;
            default:
                uiCommand.addInvalidFlag(field);
                break;
            }
        } catch (DateTimeParseException e) {
            uiCommand.addInvalidField(field, "Invalid date format");
        } catch (InvalidDeadline e) {
            uiCommand.addInvalidField(field, "Either description or date is missing.");
        } catch (InvalidStatus e) {
            String message = """
                    Status provided is not recognised:
                    Please provide one of the following:i
                    - Application Pending
                    - Application Completed
                    - Accepted
                    - Rejected""";
            uiCommand.addInvalidField(field, message);
        }
    }
    //@@author jadenlimjc

    private boolean isValidDeadline(String description, String date) throws DateTimeParseException {
        if (description.isEmpty()) {
            uiCommand.addInvalidFlag("deadline");
            return false;
        }
        if (date.isEmpty()) {
            uiCommand.addInvalidFlag("date");
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            formatter.parse(date);
        } catch (DateTimeParseException e) {
            uiCommand.addInvalidField("date", "Invalid date format. use dd/MM/yy");
            return false;
        }
        return true;
    }

    private void updateDeadline(int internshipIndex, String description, String date) throws InvalidIndex {
        Internship internship = internships.getInternship(internshipIndex);

        boolean deadlineFound = false;

        for (Deadline deadline : internship.getDeadlines()) {
            if (deadline.getDescription().equalsIgnoreCase(description)) {
                deadline.setDate(date);
                deadlineFound = true;
                //uiCommand.addUpdatedField(deadline.getDescription(), deadline.getDate());
                break;
            }
        }

        if (!deadlineFound) {
            internship.addDeadline(description, date);
            uiCommand.addCreatedField("Deadline", description);
        }
    }



    public String getUsage() {
        return """
                update
                Usage: update {ID} -{field} {new value}
                
                List of fields:
                - status (refer to below for valid statuses)
                - skills
                - role
                - company
                - start (in MM/yy format)
                - end (in MM/yy format)
                
                Choose from the following statuses:
                - Application Pending
                - Application Completed
                - Accepted
                - Rejected""";
    }
}
