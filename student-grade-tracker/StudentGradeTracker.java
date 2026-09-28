import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        // Input student details
        for (int i = 0; i < n; i++) {

            System.out.println("\nEnter details of Student " + (i + 1));

            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            double[] marks = new double[3];

            System.out.print("Enter Kannada marks: ");
            marks[0] = sc.nextDouble();

            System.out.print("Enter Maths marks: ");
            marks[1] = sc.nextDouble();

            System.out.print("Enter English marks: ");
            marks[2] = sc.nextDouble();

            sc.nextLine();

            students.add(new Student(name, marks));
        }

        System.out.println("\n========== STUDENT REPORT ==========");

        double highestAverage = -1;
        String toppers = "";

        for (Student s : students) {

            double total = 0;
            double highest = s.marks[0];
            double lowest = s.marks[0];

            for (double mark : s.marks) {

                total = total + mark;

                if (mark > highest) {
                    highest = mark;
                }

                if (mark < lowest) {
                    lowest = mark;
                }
            }

            double average = total / 3;

            System.out.println("\nStudent Name: " + s.name);
            System.out.println("Kannada Marks: " + s.marks[0]);
            System.out.println("Maths Marks: " + s.marks[1]);
            System.out.println("English Marks: " + s.marks[2]);
            System.out.println("Average Marks: " + average);
            System.out.println("Highest Mark: " + highest);
            System.out.println("Lowest Mark: " + lowest);

            // Check pass or fail
            boolean failed = false;
            String failedSubjects = "";

            if (s.marks[0] < 35) {
                failed = true;
                failedSubjects = "Kannada";
            }

            if (s.marks[1] < 35) {
                failed = true;

                if (!failedSubjects.isEmpty()) {
                    failedSubjects += ", ";
                }

                failedSubjects += "Maths";
            }

            if (s.marks[2] < 35) {
                failed = true;

                if (!failedSubjects.isEmpty()) {
                    failedSubjects += ", ";
                }

                failedSubjects += "English";
            }

            if (failed) {

                System.out.println("Status: FAIL");
                System.out.println("Failed Subjects: " + failedSubjects);

            } else {

                System.out.println("Status: PASS");

                // Find topper only among passed students
                if (average > highestAverage) {

                    highestAverage = average;
                    toppers = s.name;

                } else if (average == highestAverage) {

                    toppers = toppers + ", " + s.name;
                }
            }
        }

        // Display topper
        System.out.println("\n========== CLASS TOPPER ==========");

        if (toppers.contains(",")) {
            System.out.println("Toppers: " + toppers);
        } else {
            System.out.println("Topper: " + toppers);
        }

        System.out.println("Highest Average: " + highestAverage);

        sc.close();
    }
}