package seedu.duke;

import seedu.exceptions.InvalidStatus;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Class to store the relevant information for an internship.
 */
public class Internship {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

    private int id = -1;
    private String role;
    private String company;
    private YearMonth startDate;
    private YearMonth endDate;
    private String skills;
    private String status;


    /**
     * Constructs an <code>Internship</code> instance with information provided.
     * Skills and Status fields are initialised with default values.
     *
     * @param role    description of role.
     * @param company company name.
     * @param start   start year/month.
     * @param end     end year/month.
     */
    public Internship(String role, String company, String start, String end) {
        this.role = role;
        this.company = company;
        setStartDate(start);
        setEndDate(end);
        this.skills = "No Skills Entered";
        this.status = "Application Pending";
    }

    //@@author Ridiculouswifi
    /**
     * Updates the internship field with the new value.
     *
     * @param field field to be updated.
     * @param value new value to update field with.
     */
    public void updateField(String field, String value) {
        switch (field) {
        case "id":
            setId(Integer.parseInt(value));
            break;
        case "status":
            updateStatus(value);
            break;
        case "skills":
            setSkills(value);
            break;
        case "role":
            setRole(value);
            break;
        case "company":
            setCompany(value);
            break;
        case "from":
            setStartDate(value);
            break;
        case "to":
            setEndDate(value);
            break;
        default:
            break;
        }
    }

    //@@author Ridiculouswifi
    /**
     * Updates the status of the Internship with a valid status.
     * Does not update with invalid statuses and will prompt for a valid status.
     *
     * @param userStatus user-inputted status.
     */
    public void updateStatus(String userStatus) {
        List<String> statuses = Arrays.asList("Application Pending", "Application Completed", "Accepted", "Rejected");
        try {
            for (String status : statuses) {
                if (status.equalsIgnoreCase(userStatus)) {
                    this.status = status;
                    return;
                }
            }
            throw new InvalidStatus();
        } catch (InvalidStatus e) {
            String message = """
                    Status provided is not recognised:
                    Please provide one of the following:
                    - Application Pending
                    - Application Completed
                    - Accepted
                    - Rejected
                    """;
            System.out.println(message);
        }
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    /**
     * Sets ID based on the index of the internship in the list.
     */
    public void setId(int index) {
        this.id = index + 1;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStartDate() {
        return startDate.format(formatter); // Format as MM/yy
    }

    public void setStartDate(String start) {
        this.startDate = YearMonth.parse(start, formatter);
    }

    public String getEndDate() {
        return endDate.format(formatter); // Format as MM/yy
    }

    public void setEndDate(String end) {
        this.endDate = YearMonth.parse(end, formatter);
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills.isEmpty() ? "Not Stated" : skills;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status.isEmpty() ? "Pending" : status;
    }

    // toString method for displaying the details
    @Override
    public String toString() {
        return "ID: " + id + "\tStatus: " + status + "\n" + "Role: " + role + "\n" + "Company: " + company + "\n" +
                "Duration: " + getStartDate() + " to " + getEndDate() + "\n" + "Skills: " + skills;
    }
}
