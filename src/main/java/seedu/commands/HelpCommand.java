package seedu.commands;

import java.util.ArrayList;

//@@ Ridiculouswifi

/**
 * Subclass of <code>Command</code> to handle showing the usages for all commands available.
 */
public class HelpCommand extends Command {
    @Override
    public void execute(ArrayList<String> args) {
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(new AddCommand());
        commands.add(new DeleteCommand());
        commands.add(new ListCommand());
        commands.add(new FilterCommand());
        commands.add(new SortCommand());
        commands.add(new UpdateCommand());

        uiCommand.showCommands(commands);
    }

    @Override
    public String getUsage() {
        return "Usage: help";
    }
}
