package seedu.commands;

import java.util.ArrayList;

/**
 * Command to list all internships in the system.
 */
public class ListCommand extends Command {
    @Override
    public void execute(ArrayList<String> args) {
        internships.listAllInternships();
    }

    @Override
    public String getUsage() {
        return """
                list
                Usage: list""";
    }
}
