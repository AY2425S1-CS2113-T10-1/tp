package seedu.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the saving and loading of internships from a file.
 * The tasks are saved in a specified format and can be restored upon loading from the file.
 */
public class Storage {
    //define filepath
    private static final String FILE_PATH = "./data/EasInternship.txt";


    /**
     * Saves all the Internships in the InternshipList to a file.
     * Internships are stored in a specific format, depending on the fields relevant to each internship
     * (ID, Role, Company, Duration, Deadlines, Skills, Status).
     */
    public static void saveToFile(InternshipList internshipList) {
        try {
            File dir = new File("./data");
            //create directory if file does not exist
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            List<Internship> internships = internshipList.getAllInternships();
            for (Internship internship : internships) {
                // Build the deadlines string
                StringBuilder deadlinesBuilder = new StringBuilder();
                List<Deadline> deadlines = internship.getDeadlines();

                if (deadlines.isEmpty()) {
                    deadlinesBuilder.append("No Deadlines set.");
                } else {
                    for (Deadline deadline : deadlines) {
                        deadlinesBuilder.append(deadline.getDescription())
                                .append(" -date ")
                                .append(deadline.getDate())
                                .append(" - ");
                    }
                    // Remove trailing " - "
                    deadlinesBuilder.setLength(deadlinesBuilder.length() - 3);
                }

                // Write the internship details to the file
                writer.write(internship.getId() + " | "
                        + internship.getRole() + " | "
                        + internship.getCompany() + " | "
                        + internship.getStartDate() + " | "
                        + internship.getEndDate() + " | "
                        + internship.getSkills() + " | "
                        + internship.getStatus() + " | "
                        + deadlinesBuilder + "\n");
            }

            // After writing internships, write the favourite IDs
            writer.write("FAVOURITES:");
            for (Internship favInternship : internshipList.favouriteInternships) {
                writer.write(" " + favInternship.getId());
            }
            writer.write("\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads internships from the file into the InternshipList.
     * The method parses each line to recreate the Internship objects and adds them to the InternshipList.
     */
    public static void loadFromFile(InternshipList internshipList) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No data file found.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!(line.startsWith("FAVOURITES:"))) {
                    String[] data = line.split(" \\| ");
                    String role = data[1];
                    String company = data[2];
                    String startDate = data[3];
                    String endDate = data[4];
                    String skills = data[5];
                    String status = data[6];
                    String deadlines = data[7];

                    Internship internship = new Internship(role, company, startDate, endDate);
                    internshipList.addInternship(internship);
                    internship.setSkills(skills);
                    internship.setStatus(status);
                    List<Deadline> loadedDeadlines = parseDeadlines(deadlines, internship.getId());
                    for (Deadline deadline : loadedDeadlines) {
                        internship.addDeadline(deadline.getDescription(), deadline.getDate());
                    }
                    continue;
                }

                // Parse favourite internships
                String[] parts = line.substring("FAVOURITES:".length()).trim().split(" ");
                for (String id : parts) {
                    int favInternshipId = Integer.parseInt(id);
                    int favInternshipIndex = favInternshipId - 1;
                    Internship favInternship = internshipList.internships.get(favInternshipIndex);
                    internshipList.favouriteInternships.add(favInternship);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while loading tasks: " + e.getMessage());
        }
    }


    private static List<Deadline> parseDeadlines(String deadlineString, int internshipId) {
        List<Deadline> deadlines = new ArrayList<>();

        // Skip parsing if the default "No Deadlines Added" is present
        if (deadlineString.equals("No Deadlines set.")) {
            return deadlines;
        }

        String[] parts = deadlineString.split(" - "); // Adjust as per your actual format
        for (String part : parts) {
            // Assume part is formatted like: "description -date MM/dd/yyyy"
            String[] deadlineParts = part.split(" -date ");
            if (deadlineParts.length == 2) {
                String description = deadlineParts[0].trim();
                String date = deadlineParts[1].trim();
                deadlines.add(new Deadline(internshipId, description, date));
            }
        }
        return deadlines;
    }

}
