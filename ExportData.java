import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ExportData {
    public static void exportToTextFile(String[] studentNames, String[] courses, double[][] gpa, double[] totalGPA, double classAverageGPA) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("班级学生成绩汇总.txt"))) {
            // 写入表头
            writer.println("学生姓名\t课程名称\t绩点\t字母分制结果\tGPA");
            
            // 写入学生姓名、课程名称、绩点、字母分制结果、GPA
            for (int i = 0; i < studentNames.length; i++) {
                for (int j = 0; j < courses.length; j++) {
                    writer.println(studentNames[i] + "\t" + courses[j] + "\t" + gpa[i][j] + "\t" + convertToLetterGrade(gpa[i][j]) + "\t\t" + totalGPA[i]);
                }
            }

            // 写入班级平均GPA
            writer.println("班级平均GPA: " + classAverageGPA);
        } catch (IOException e) {
            System.out.println("写入文件时出现错误: " + e.getMessage());
        }
    }
    private static String convertToLetterGrade(double gpa) {
        if (gpa >= 4.0) {
            return "A";
        } else if (gpa >= 3.7) {
            return "A-";
        } else if (gpa >= 3.3) {
            return "B+";
        } else if (gpa >= 3.0) {
            return "B";
        } else if (gpa >= 2.7) {
            return "B-";
        } else if (gpa >= 2.3) {
            return "C+";
        } else if (gpa >= 2.0) {
            return "C";
        } else if (gpa >= 1.7) {
            return "C-";
        } else if (gpa >= 1.3) {
            return "D+";
        } else if (gpa >= 1.0) {
            return "D";
        } else {
            return "F";
        }
    }
    
    
}
