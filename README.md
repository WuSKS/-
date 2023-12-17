# 学生学分管理系统
## 一、项目目的
1. 分析学生学分管理系统
2. 分析系统角色、实体，定义相应的属性和方法
3. 在系统中完成相应的实例化操作，支持运行过程中新录入学生成绩，计算学生或班级 GPA
4. 读取数据保存文件
5. 处理异常
## 二、项目要求
1. 系统角色分析及类设计
   <br>系统角色分析：
   <br>$\cdot$用户角色：输入课程信息、学生人数、学生成绩；查询学生成绩、班级平均GPA
   <br>$\cdot$系统角色：计算每个学生的总GPA和平均GPA；计算班级平均GPA；输出学生成绩、平均GPA和班级GPA；将数据导出到文本文件

   <br>类设计：
   <br>$\cdot$ Course类：包含main方法，用于输入课程信息、学生人数和成绩，并计算每个学生的总GPA和平均GPA，计算班级平均GPA，输出学生成绩、平均GPA和班级GPA，以及将数据导出到文本文件。
   <br>$\cdot$ ExportData类：用于将数据导出到文本文件，提供exportToTextFile方法。
   <br>$\cdot$ calculateGP方法：用于根据分数获得绩点已便计算GPA。
   <br>$\cdot$ convertToLetterGrade方法：用于将GPA转换为对应的字母分制。
2. 基本需求
   <br>学生选课并且考核后，获得成绩分数(按百分制)，根据我校学习管理规定，对应至字母分制。因此可得某学生的 GPA，可得班级的 GPA。
## 三、解题思路
1. 首先，使用Scanner类从控制台输入课程数量、课程名称、学分、学生人数和学生姓名。
2. 创建课程和学生姓名的数组，并创建学生成绩的二维数组。
3. 根据输入的课程数量和学生人数，使用循环分别输入课程名称、学分和学生姓名，并初始化学生成绩的二维数组。
4. 创建calculateGP方法，用于根据分数获得绩点已便计算GPA，以及convertToLetterGrade方法，用于将GPA转换为对应的字母分制。
5. 使用循环让每位学生输入选课情况和成绩，同时计算每个学生的总GPA和平均GPA，并将其存储到数组中。
6. 计算班级平均GPA，并根据用户输入的学生姓名查询并输出该学生的成绩、GPA和班级GPA。
7. 最后，使用ExportData类将学生成绩、课程和班级平均GPA导出到文本文件中。
## 四、流程图
<img width="327" alt="9d2f4b07eb1519fdaf0c5abdbf72a01" src="https://github.com/WuSKS/xue-sheng-xue-fen-guan-li-xi-tong/assets/145275222/57d951cb-1b21-414c-9bf2-5fdc4fbceacf">

## 五、关键代码
1. 根据学生数量循环输入学生的选课情况和成绩，根据课程数量循环输入每门课程成绩，计算课程学分*绩点并加和存入GPA中，等待进一步处理，再将课程学分加和存入totalCredits中，最后通过GPA/totalCredits计算每个学生的GPA存储在数组totalGPA中。
   ```java
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
   ```
2. 用于查找学生的成绩和绩点，通过循环遍历学生的姓名数组，查找是否有与查询姓名匹配的学生，如果找到匹配的学生，则更新studentIndex并结束循环。然后，通过判断studentIndex是否不等于-1来确定是否找到了匹配的学生。如果找到了匹配的学生，将打印出该学生的每门课程的成绩和绩点，以及总GPA。如果没有找到匹配的学生，则打印出相应的消息。最后，等待用户按Enter键继续执行。
   ```java
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
    ```

3.  使用PrintWriter和FileWriter打开文件进行写入操作，进入循环后外层循环遍历所有学生，而内层循环遍历每个学生所修的课程。在每次内层循环迭代中，它将通过writer.println方法将学生的姓名、课程名称、绩点、字母分制结果和总GPA写入到文件中。如果在写入文件时出现错误，它将捕获IOException并打印错误消息。
   ```java
  import java.io.FileWriter;
  import java.io.PrintWriter;
  import java.io.IOException;

  public class ExportData {
      public static void exportToTextFile(String[] studentNames, String[] courses, double[][] gpa, double[] totalGPA,         double classAverageGPA) {
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
  ```

  
<br>补充说明：
<br>catch (IOException e)：这是一个异常处理块。它用于捕获输入输出异常。
<br>static的作用: 在这里，静态关键字表示exportToTextFile方法是一个类级别的方法，可以直接通过类名来调用，而无需创建ExportData类的实例。
## 六、系统运行截图
1. 控制台输出所有学生绩点及对应字母分制、GPA和班级GPA
   <br><img width="218" alt="032819fe74b63647dd2428fec4d0f1c" src="https://github.com/WuSKS/xue-sheng-xue-fen-guan-li-xi-tong/assets/145275222/1e709c50-75b0-4a52-9616-48e0a60b3694">
2. 查询学生的各成绩信息（例为不存在的学生）
   <br><img width="135" alt="a1b1dac918a4447fada0e2bce76aeac" src="https://github.com/WuSKS/xue-sheng-xue-fen-guan-li-xi-tong/assets/145275222/27f2801e-713a-41e3-a943-aef5c07784bd">
3. 存储到文本文件里的学生各项信息
   <br><img width="951" alt="d384f8403e49cff200ad87b452dd259" src="https://github.com/WuSKS/xue-sheng-xue-fen-guan-li-xi-tong/assets/145275222/78dda358-8ebb-4783-9c25-b7a798ce4165">
## 七、感想与体会
  在小组合作完成这个学生学分管理系统的过程中，我们首先进行了需求分析，明确了系统的主要功能和要求。然后我们对系统角色和实体进行了分析，定义了相应的属性和方法，并根据需求进行了实例化操作。
<br>  在实现过程中，我们遇到了一些挑战和问题。其中一个挑战是如何设计成绩的计算规则，将百分制成绩转换为字母分制，并计算GPA。我们需要仔细研究学校的学习管理规定，并根据规定来确定具体的计算方式。
<br>  另一个挑战是如何支持运行过程中的成绩录入和导出成绩到文件中。我们需要设计相应的功能，使用户能够方便地录入成绩。这需要考虑数据格式的解析和处理，以及数据的存储和更新。
<br>  在界面设计方面，我们采用了控制台输入交互的形式，因为这种方式简单易用，适合快速开发和测试。当然，也有同学也尝试了GUI或Web页面的设计，提升了系统的用户体验。
<br>  在完成这个学生学分管理系统的过程中，我们不仅学到了如何分析需求、设计系统架构和实现功能，还锻炼了团队合作和沟通能力。通过相互协作、交流思想和解决问题，我们顺利地完成了这个项目，并收获了很多经验和成长。
