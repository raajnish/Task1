import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        ArrayList<Integer> grades = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student grades (enter -1 to stop):");
        while (true) {
            int grade = scanner.nextInt();
            if (grade == -1) break;
            grades.add(grade);
        }

        scanner.close();

        if (grades.isEmpty()) {
            System.out.println("No grades were entered.");
            return;
        }

        int highest = grades.stream().max(Integer::compare).orElse(0);
        int lowest = grades.stream().min(Integer::compare).orElse(0);
        double average = grades.stream().mapToInt(Integer::intValue).average().orElse(0);

        System.out.println("Average Score: " + average);
        System.out.println("Highest Score: " + highest);
        System.out.println("Lowest Score: " + lowest);
    }
}