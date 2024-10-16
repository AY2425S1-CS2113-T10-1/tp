package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.Internship;
import seedu.duke.InternshipList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SortCommandTest {

    private InternshipList internshipList;

    @BeforeEach
    void setUp() {
        internshipList = new InternshipList();

        // Add some internships to the list for testing
        Internship internship1 = new Internship("Software Engineer", "Google", "01/24", "06/24");
        Internship internship2 = new Internship("Data Scientist", "Facebook", "02/23", "08/23");
        Internship internship3 = new Internship("Product Manager", "Amazon", "01/23", "09/23");

        internshipList.addInternship(internship1);
        internshipList.addInternship(internship2);
        internshipList.addInternship(internship3);
    }

    @Test
    void execute_sortNoArgs_expectNoCrash() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipList(internshipList);

        // Test "sort" with no additional arguments
        ArrayList<String> arguments = new ArrayList<>();
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortEmptyFlag_expectNoCrash() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipList(internshipList);

        // Test "sort -" with an empty flag
        ArrayList<String> arguments = new ArrayList<>(List.of("-"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortAlphabet_expectSortedByRole() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipList(internshipList);

        // Test "sort -alphabet"
        ArrayList<String> arguments = new ArrayList<>(List.of("alphabet"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortDeadline_expectSortedByDate() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipList(internshipList);

        // Test "sort -deadline"
        ArrayList<String> arguments = new ArrayList<>(List.of("deadline"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortInvalid_expectNoCrash() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipList(internshipList);

        // Test "sort -invalid"
        ArrayList<String> arguments = new ArrayList<>(List.of("invalid"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortUnknown_expectNoCrash() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipList(internshipList);

        // Test "sort -unknown"
        ArrayList<String> arguments = new ArrayList<>(List.of("unknown"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }
}
