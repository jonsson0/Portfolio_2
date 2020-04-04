import java.io.ObjectInputStream;
import java.sql.*; //from jdbc
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.sql.DriverManager.*;

public class Main {

    public static void main(String[] args) {
        DoSomethingWithStudentDatabase doSomethingWithStudentDatabase = new DoSomethingWithStudentDatabase();

        while (true) {
            System.out.println("what would u like to do?");
            System.out.println("1. If you wish to have all the students presented: enter 1");
            System.out.println("2. If you wish to have the avg grade for a student presented: enter 2");
            System.out.println("3. If you wish to have the avg grade for a course presented: enter 3");
            System.out.println("4. If you with to end the program: enter 4");
            int num = scanANumber();

            if (num == 1) {
                try {
                    System.out.println("Here are all the students in the database:");
                    doSomethingWithStudentDatabase.presentStudents();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (num == 2) {
                try {
                    System.out.println(doSomethingWithStudentDatabase.getAvgGradeFromSpecStudent());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if(num == 3) {
                try {
                    System.out.println(doSomethingWithStudentDatabase.getAvgGradeForACourse());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                return;
            }

        }
    }

    public static int scanANumber() {
        Scanner scanner = new Scanner(System.in);

        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("sorry you need to enter an int which matches one of the options");
            scanner.next();
        }

        return scanANumber();
    }
}
