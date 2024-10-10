package seedu.ui;

import seedu.duke.Internship;

import java.util.ArrayList;

//@@author Ridiculouswifi
/**
 * Subclass of <code>Ui</code> to print outputs from <code>InternshipList</code>.
 */
public class UiInternshipList extends Ui {
    /**
     * Prints message when <code>InternshipList</code> is empty.
     */
    public void showEmptyInternshipList() {
        showOutput("No internships found.");
    }

    /**
     * Prints out all internships in a specified <code>ArrayList</code>.
     */
    public void showInternships(ArrayList<Internship> internships) {
        printHeadDivider();
        for (Internship internship : internships) {
            System.out.println(internship.toString());
            printDivider();
        }
        printDivider();
        System.out.println("\n");
    }

    /**
     * Prints message when index provided is out of bounds.
     */
    public void showInvalidIndex() {
        showOutput("Invalid index!\nPlease use list to find internship ID");
    }
}
