import java.util.ArrayList;
import java.util.Scanner;

public class studentgradetaker {
    public static void main(String[] args) {
        // Create an ArrayList to store student grades
        ArrayList<Double> grades = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter student grades (type -1 to stop):");

        // Read grades from the teacher
        while (true) {
            System.out.print("Enter grade: ");
            double grade = sc.nextDouble();

            if (grade == -1) {
                break;
            }

            // Validate the grade (e.g., between 0 and 100)
            if (grade >= 0 && grade <= 100) {
                grades.add(grade);
            } else {
                System.out.println("Invalid grade. Please enter a value between 0 and 100.");
            }
        }

        // Check if any grades were entered
        if (grades.isEmpty()) {
            System.out.println("No grades entered.");
        } else {
            // Calculate average, highest, and lowest grades
            double total = 0;
            double highest = grades.get(0);
            double lowest = grades.get(0);

            for (double grade : grades) {
                total += grade;

                if (grade > highest) {
                    highest = grade;
                }

                if (grade < lowest) {
                    lowest = grade;
                }
            }

            double average = total / grades.size();

            // Display results
            System.out.println("\nGrade Summary:");
            System.out.println("Number of students: " + grades.size());
            System.out.printf("Average grade: %.2f\n", average);
            System.out.println("Highest grade: " + highest);
            System.out.println("Lowest grade: " + lowest);
        }

        sc.close();
    }
}