import java.sql.*;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class DoSomethingWithStudentDatabase {

    public DoSomethingWithStudentDatabase() {

    }

    Connection connection = null;
    String url = "jdbc:sqlite:C:\\databaser\\Students.db";


    public void presentStudents() throws SQLException {

        try {
            connection = getConnection(url);
            Statement statement = connection.createStatement();

            String sql;
            sql = "SELECT * FROM Students";
            ResultSet rs = statement.executeQuery(sql);

            if (rs == null)
                System.out.println("No records for Students");
            while (rs != null & rs.next()) {
                String studentName = rs.getString("name");
                String studentCity = rs.getString("livesIn");
                String course1 = rs.getString("course1");
                String course1semester = rs.getString("course1");
                //  if(res.getInt("course1Grade") == 0)
                //     res.updateBigDecimal(res.getInt("course1Grade"), null);
                int course1Grade = rs.getInt("course1Grade");
//                course1Grade = "[NO GRADE YET]" // cant set int = string ...
                //      }
                String course2 = rs.getString("course2");
                String course2semester = rs.getString("course2semester");
                int course2Grade = rs.getInt("course2Grade");
                System.out.println(studentName + " from " + studentCity + ", Courses: " + course1 + " in " + course1semester + " got grade " + course1Grade + ", " + course2 + " " + course2semester + " got grade " + course2Grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getAvgGradeFromSpecStudent() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int avgGradeOfStudent;
        try {
            connection = getConnection(url);
            Statement statement = connection.createStatement();

            System.out.println("Which student (name) would you like to get the avg grade of?");
            String selectedStudent = scanner.nextLine();

            String prepAvgStudentGrade;
            prepAvgStudentGrade = "SELECT * FROM Students WHERE name ='" + selectedStudent + "'";

            // PreparedStatement preparedStatement = connection.prepareStatement(prepAvgStudentGrade);
            // preparedStatement.setString(1, selectedStudent);

            ResultSet rs = statement.executeQuery(prepAvgStudentGrade);

            int grade1 = rs.getInt("course1Grade");
            int grade2 = rs.getInt("course2Grade");

            avgGradeOfStudent = (grade1 + grade2) / 2;
            System.out.println("Here is the avg grade for " + selectedStudent);
            return avgGradeOfStudent;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

   public int getAvgGradeForACourse() throws SQLException {
       Scanner scanner = new Scanner(System.in);
       int numberOfStudents = 10;
       int avgGradeForCourse;


       try {
           connection = getConnection(url);
           Statement statement = connection.createStatement();

           System.out.println("Which course would you like to get the avg grades of? SD 2019, SD 2020, or ES1");
           String selectedCourse = scanner.nextLine();

           String prepSumCourseGrade;
           // prepSumCourseGrade = "SELECT SUM(?) AS AVG FROM Students ";//WHERE course1 OR course2 ='" + selectedCourse + "'";
           //  PreparedStatement preparedStatement = connection.prepareStatement(prepSumCourseGrade);
           if (selectedCourse.equals("SD 2019")) {
               //    preparedStatement.setString(1, "course1Grade");
               int numberOfStudentsWithSD2019 = 5;
               prepSumCourseGrade = "SELECT SUM(course1Grade)/'" + numberOfStudentsWithSD2019 + "' AS AVG FROM Students WHERE course1 ='" + selectedCourse + "'";
           } else if (selectedCourse.equals("SD 2020")) {
               //      preparedStatement.setNString(1, "course2Grade");
               int numberOfStudentsWithSD2020 = 5;
               prepSumCourseGrade = "SELECT SUM(course1Grade)/'" + numberOfStudentsWithSD2020 + "' AS AVG FROM Students WHERE course1 ='" + selectedCourse + "'";
           }
           else{
               int numberOfStudentsWithES12019 = 10;
               prepSumCourseGrade = "SELECT SUM(course2Grade)/'" + numberOfStudentsWithES12019 + "' AS AVG FROM Students ";
           }

           ResultSet rs = statement.executeQuery(prepSumCourseGrade);

           avgGradeForCourse = rs.getInt("AVG");

           System.out.println("Here is the avg Grade for " + selectedCourse);
           return avgGradeForCourse;

          // int avgGradeOfCourse = rs.getInt("SUM_AVGCOURSEGRADE");

       } catch (SQLException e) {
           e.printStackTrace();
       }
       return 0;
   }

}
