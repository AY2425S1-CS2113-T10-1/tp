package seedu.commands;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    @Override
    public void execute (ArrayList<String> args) {
        int id = Integer.parseInt(args.get(0));
        int index = id - 1;
        internships.removeInternship(index);
        ui.showDeletedInternship(id);
    }

    @Override
    public String getUsage() {
        return "Usage: del {ID}";
    }
}
