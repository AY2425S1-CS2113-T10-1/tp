package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Internship;
import seedu.duke.InternshipList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@@ author jadenlimjc
public class AddCommandTest {
    InternshipList internships;
    AddCommand addCommand;

    void createList() {
        internships = new InternshipList();
        addCommand = new AddCommand();
        addCommand.setInternshipList(internships);
    }

    @Test
    void execute_missingRole_abortsAddition() {
        createList();
        ArrayList<String> args = new ArrayList<>();
        args.add("company Google");
        args.add("from 01/24");
        args.add("to 08/24");

        addCommand.execute(args);

        assertEquals(0, internships.getSize());
    }

    @Test
    public void execute_missingCompany_abortsAddition() {
        createList();
        ArrayList<String> args = new ArrayList<>();
        args.add("role Software Engineer");
        args.add("from 01/24");
        args.add("to 08/24");

        addCommand.execute(args);

        assertEquals(0, internships.getSize());
    }

    @Test
    public void execute_validArguments_InternshipAdded() {
        createList();
        ArrayList<String> args = new ArrayList<>();
        args.add("role Software Engineer");
        args.add("company Google");
        args.add("from 01/24");
        args.add("to 08/24");

        addCommand.execute(args);

        assertEquals(1, internships.getSize());
        Internship added = internships.getInternship(0);
        assertEquals("Software Engineer", added.getRole());
        assertEquals("Google", added.getCompany());
    }

}
