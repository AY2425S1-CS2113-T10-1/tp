package seedu.commands;

import java.util.ArrayList;
//@@author jadenlimjc
public class DeleteCommand extends Command {
    @Override
    public void execute(ArrayList<String> args) {
        int id = Integer.parseInt(args.get(0));
        int index = id - 1;
        internships.removeInternship(index);
    }

    @Override
    public String getUsage() {
        return "Usage: del {ID}";
    }
}
