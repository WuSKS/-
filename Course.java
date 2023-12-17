import java.util.Scanner;


public class Course {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ����γ���Ϣ
        System.out.print("������γ�������");
        int numberOfCourses = scanner.nextInt();
        String[] courses = new String[numberOfCourses];
        int[] credits = new int[numberOfCourses];

        for (int i = 0; i < numberOfCourses; i++) {
            System.out.print("�������" + (i + 1) + "�ſγ����ƣ�");
            courses[i] = scanner.next();
            System.out.print("�������" + (i + 1) + "�ſγ�ѧ�֣�");
            credits[i] = scanner.nextInt();
            
        }

        // ����ѧ������
        System.out.print("������ѧ��������");
        int numberOfStudents = scanner.nextInt();
        String[] studentNames = new String[numberOfStudents];
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.print("�������" + (i + 1) + "��ѧ����������");
            studentNames[i] = scanner.next();
        }
        // ����ѧ���ɼ�����
        double[][] gpa = new double[numberOfStudents][numberOfCourses];

        // ����ÿ��ѧ������GPA��ƽ��GPA
        double[] totalGPA = new double[numberOfStudents];
        

        // ѧ��ѡ�β�����ɼ���������ÿ��ѧ������GPA��ƽ��GPA
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("������ѧ��" + (i + 1) + "��ѡ������ͳɼ���");

            double GPA = 0.0; // ÿ��ѧ����GPA
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

        // ����༶ƽ��GPA
        double sumAverageGPA = 0.0;

        for (int i = 0; i < numberOfStudents; i++) {
            sumAverageGPA += totalGPA[i];
        }

        double classAverageGPA = sumAverageGPA / numberOfStudents;

        // ��ѯѧ���ɼ���ƽ��GPA�Ͱ༶GPA
        System.out.print("������Ҫ��ѯ�ɼ���ѧ��������");
        String queryName = scanner.next();

        int studentIndex = -1; // ��ʼ��Ϊ-1������Ҳ���ƥ����������򱣳�Ϊ-1

        // ��ѧ�������������ҵ�ƥ���ѧ������
        for (int i = 0; i < numberOfStudents; i++) {
            if (studentNames[i].equals(queryName)) {
                studentIndex = i; // �ҵ�ƥ���ѧ������������studentIndex
                break;
            }
        }

        if (studentIndex != -1) {
            System.out.println("ѧ��" + queryName + "�ĳɼ��ͼ��㣺");
            for (int j = 0; j < numberOfCourses; j++) {
                System.out.print(courses[j] + ": " + gpa[studentIndex][j] + " -> ");
                System.out.println(convertToLetterGrade(gpa[studentIndex][j]));
            }
            System.out.println("GPA: " + totalGPA[studentIndex]);
        } else {
            System.out.println("û���ҵ���Ϊ" + queryName + "��ѧ����");
        }
        System.out.print("�밴 Enter ������...");
        scanner.nextLine();  // �ȴ��û��� Enter
        
        
        System.out.println("\n\n����ȫ����ѧ�����㼰��Ӧ��ĸ���ơ�GPA�Ͱ༶GPA���£�");

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("ѧ��" + (i + 1) +studentNames[i]+ "�ĳɼ��ͼ��㣺");

            for (int j = 0; j < numberOfCourses; j++) {
                System.out.print(courses[j] + ": " + gpa[i][j] + " -> ");
                System.out.println(convertToLetterGrade(gpa[i][j]));
            }

            System.out.println("GPA: " + totalGPA[i]);
            System.out.println();
        }
        System.out.println("�༶ƽ��GPA: " + classAverageGPA);
        
        ExportData.exportToTextFile(studentNames, courses, gpa, totalGPA, classAverageGPA);

    }

    // ���ݷ�������GPA
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

