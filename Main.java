import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<grades> students = new ArrayList<>();

        while (true) {

            System.out.println("\n===== Student Grade Tracker =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Calculate Average");
            System.out.println("4. Find Highest Marks");
            System.out.println("5. Find Lowest Marks");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter student name: ");
                    String name = sc.next();

                    System.out.print("Enter marks: ");
                    int marks = sc.nextInt();

                    students.add(new grades(name, marks));

                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    System.out.println("\n----- Student List -----");

                    for (grades student : students) {
                        System.out.println(
                                "Name: " + student.name +
                                        " | Marks: " + student.marks
                        );
                    }
                    break;

                case 3:
                    int total = 0;

                    for (grades student : students) {
                        total = total + student.marks;
                    }

                    double average = (double) total / students.size();

                    System.out.println("Average Marks: " + average);
                    break;

                case 4:
                    int highest = students.get(0).marks;

                    for (grades student : students) {
                        if (student.marks > highest) {
                            highest = student.marks;
                        }
                    }

                    System.out.println("Highest Marks: " + highest);
                    break;

                case 5:
                    int lowest = students.get(0).marks;

                    for (grades student : students) {
                        if (student.marks < lowest) {
                            lowest = student.marks;
                        }
                    }

                    System.out.println("Lowest Marks: " + lowest);
                    break;

                case 6:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}