import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AgeCalculator2 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide your date of birth as a command-line argument in the format dd-MM-yyyy.");
            return;
        }

        String dobInput = args[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Check for February 29 on a non-leap year before parsing
        if (dobInput.matches("\\d{2}-02-\\d{4}")) { // Matches dates in February
            String[] parts = dobInput.split("-");
            int day = Integer.parseInt(parts[0]);
            int year = Integer.parseInt(parts[2]);

            // If day is 29 and it's not a leap year
            if (day == 29 && !LocalDate.of(year, 1, 1).isLeapYear()) {
                System.out.println("Invalid date: " + dobInput + " is not in a leap year.");
                return;
            }
        }

        try {
            LocalDate dob = LocalDate.parse(dobInput, formatter);
            LocalDate currentDate = LocalDate.now();
            
            // Check if date of birth is in the future
            if (dob.isAfter(currentDate)) {
                System.out.println("The date of birth cannot be in the future.");
                return;
            }

            // Calculate the age
            Period age = Period.between(dob, currentDate);
            System.out.printf("Your age is %d years, %d months, and %d days.%n", 
                              age.getYears(), age.getMonths(), age.getDays());

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format or non-existent date. Please enter the date in the format dd-MM-yyyy.");
        }
    }
}
