package seedu.ui;

import java.util.Scanner;

//@@author Ridiculouswifi
/**
 * Class to facilitate program interactions with the user.
 */
public class Ui {
    protected static final String DIVIDER = "__________________________________________________\n";
    private static Scanner scanner = new Scanner(System.in);

    private static final String MESSAGE_WELCOME = """
                Hello! Welcome to EasInternship!
                Ready to rock some internships?? :)""";
    private static final String MESSAGE_GOODBYE = "Peace out! Have fun at the internship.";

    /**
     * Reads and returns the user's input.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Prints the welcome message of the chatbot.
     */
    public void showWelcome() {
        showOutput(MESSAGE_WELCOME);
    }

    /**
     * Prints the goodbye message of the chatbot.
     */
    public void showGoodbye() {
        showOutput(MESSAGE_GOODBYE);
    }

    /**
     * Prints the specified output <code>String</code>.
     * Includes a line break at the end.
     */
    public void showOutput(String output) {
        printHeadDivider();
        System.out.println(output);
        printTailDivider();
    }

    public void showEmptyFlags() {
        showOutput("No flag provided");
    }

    /**
     * Prints when unknown command is given.
     *
     * @param input Command by user.
     */
    public void showUnknownCommand(String input) {
        showOutput("Unknown command: " + input);
    }

    /**
     * Prints when there is an error executing the commands.
     *
     * @param errorMessage  Message given by exception.
     */
    public void showErrorCommand(String errorMessage) {
        showOutput("Error executing command: " + errorMessage);
    }

    /**
     * Prints double horizontal line to indicate the start of a chatbot response.
     */
    public void printHeadDivider() {
        System.out.print(DIVIDER + DIVIDER);
    }

    /**
     * Prints double horizontal line to indicate the end of a chatbot response.
     */
    public void printTailDivider() {
        System.out.println(DIVIDER + DIVIDER + "\n");
    }

    /**
     * Prints horizontal line to indicate separation.
     */
    public void printDivider() {
        System.out.print(DIVIDER);
    }
}
