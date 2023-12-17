import java.util.Scanner;


public class Course {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入课程信息
        System.out.print("请输入课程数量：");
        int numberOfCourses = scanner.nextInt();
        String[] courses = new String[numberOfCourses];
        int[] credits = new int[numberOfCourses];

        for (int i = 0; i < numberOfCourses; i++) {
            System.out.print("请输入第" + (i + 1) + "门课程名称：");
            courses[i] = scanner.next();
            System.out.print("请输入第" + (i + 1) + "门课程学分：");
            credits[i] = scanner.nextInt();
            
        }

        // 输入学生人数
        System.out.print("请输入学生人数：");
        int numberOfStudents = scanner.nextInt();
        String[] studentNames = new String[numberOfStudents];
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.print("请输入第" + (i + 1) + "名学生的姓名：");
            studentNames[i] = scanner.next();
        }
        // 定义学生成绩数组
        double[][] gpa = new double[numberOfStudents][numberOfCourses];

        // 计算每个学生的总GPA和平均GPA
        double[] totalGPA = new double[numberOfStudents];
        

        // 学生选课并输入成绩，并计算每个学生的总GPA和平均GPA
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("请输入学生" + (i + 1) + "的选课情况和成绩：");

            double GPA = 0.0; // 每个学生的GPA
            double totalCredits = 0.0;

            for (int j = 0; j < numberOfCourses; j++) {
                System.out.print(courses[j] + ": ");
                int score = scanner.nextInt();
                gpa[i][j] = calculateGP(score);
                GPA = GPA + (gpa[i][j]* credits[j]);
                totalCredits += credits[j];
            }
            GPA=GPA/totalCredits;
            totalGPA[i] = GPA;
            
        }

        // 计算班级平均GPA
        double sumAverageGPA = 0.0;

        for (int i = 0; i < numberOfStudents; i++) {
            sumAverageGPA += totalGPA[i];
        }

        double classAverageGPA = sumAverageGPA / numberOfStudents;

        // 查询学生成绩、平均GPA和班级GPA
        System.out.print("请输入要查询成绩的学生姓名：");
        String queryName = scanner.next();

        int studentIndex = -1; // 初始化为-1，如果找不到匹配的姓名，则保持为-1

        // 在学生姓名数组中找到匹配的学生姓名
        for (int i = 0; i < numberOfStudents; i++) {
            if (studentNames[i].equals(queryName)) {
                studentIndex = i; // 找到匹配的学生姓名，更新studentIndex
                break;
            }
        }

        if (studentIndex != -1) {
            System.out.println("学生" + queryName + "的成绩和绩点：");
            for (int j = 0; j < numberOfCourses; j++) {
                System.out.print(courses[j] + ": " + gpa[studentIndex][j] + " -> ");
                System.out.println(convertToLetterGrade(gpa[studentIndex][j]));
            }
            System.out.println("GPA: " + totalGPA[studentIndex]);
        } else {
            System.out.println("没有找到名为" + queryName + "的学生。");
        }
        System.out.print("请按 Enter 键继续...");
        scanner.nextLine();  // 等待用户按 Enter
        
        
        System.out.println("\n\n班里全部的学生绩点及对应字母分制、GPA和班级GPA如下：");

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("学生" + (i + 1) +studentNames[i]+ "的成绩和绩点：");

            for (int j = 0; j < numberOfCourses; j++) {
                System.out.print(courses[j] + ": " + gpa[i][j] + " -> ");
                System.out.println(convertToLetterGrade(gpa[i][j]));
            }

            System.out.println("GPA: " + totalGPA[i]);
            System.out.println();
        }
        System.out.println("班级平均GPA: " + classAverageGPA);
        
        ExportData.exportToTextFile(studentNames, courses, gpa, totalGPA, classAverageGPA);

    }

    // 根据分数计算GPA
    private static double calculateGP(int score) {
        if (score >= 95) {
            return 4.33;
        } else if (score >= 90) {
            return 4.00;
        } else if (score >= 85) {
            return 3.67;
        } else if (score >= 82) {
            return 3.33;
        } else if (score >= 78) {
            return 3.00;
        } else if (score >= 75) {
            return 2.67;
        } else if (score >= 72) {
            return 2.33;
        } else if (score >= 68) {
            return 2.00;
        } else if (score >= 64) {
            return 1.67;
        } else if (score >= 61) {
            return 1.33;
        } else if (score >= 60) {
            return 1.00;
        } else {
            return 0.00;
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

