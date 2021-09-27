import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Assignment4 {
    private static Scanner input = new Scanner(System.in);

    public static void main (String... args) {
        greetings();
        List<Student> studentList = getStudentFromFile();
        getAssignmentGrades(studentList);
        generateReportCards(studentList);
        successfulReportMessage();
    }

    public static void greetings(){
        System.out.println("Welcome to the Student Report Card Generator\n" +
                            "--------------------------------------------\n");
    }

    public static List<Student> getStudentFromFile(){
        Scanner myReader = null;
        boolean restart = true;
        List<Student> studentList = new ArrayList<>();
        do {
            try {
                System.out.print("Enter the student input file location: ");
                String fileLocation = input.nextLine();
                myReader = new Scanner(new File(fileLocation));
                System.out.println();
                while(myReader.hasNextLine()){
                    String line = myReader.nextLine();
                    System.out.println("Creating Student " + line);
                    String[] studentArray = line.split(" ");
                    Student newStudent = new Student(studentArray[0], studentArray[1]);
                    studentList.add(newStudent);
                }
                restart = false;
            }catch (FileNotFoundException e) {
                System.out.println("\nInput file not found\n");
            }finally {
                    if (myReader != null) {
                        myReader.close();
                    }
            }
        }while(restart);
        return studentList;
    }

    private static void getAssignmentGrades(List<Student> studentList) {
        String nextAssignment;
        do {
            System.out.print("\nEnter the name of an Assignment: ");
            String assignmentName = input.nextLine();
            for(Student student : studentList){
                System.out.print("Enter the grade for " + student.getFirstName() + " " + student.getLastName() + ": ");
                Double grade = Double.parseDouble(input.nextLine());
                student.setAssignmentMap(assignmentName,grade);
            }
            System.out.print("\nAnother Assignment? (y/n): ");
            nextAssignment = input.nextLine();
            while(!nextAssignment.equalsIgnoreCase("y") && !nextAssignment.equalsIgnoreCase("n")) {
                System.out.println("\nEnter a valid entry");
                System.out.print("\nAnother Assignment? (y/n): ");
                nextAssignment = input.nextLine();
            }
        }while(nextAssignment.equalsIgnoreCase("y"));
    }

    private static void generateReportCards(List<Student> studentList) {
        BufferedWriter writer;
        System.out.print("\nEnter output Directory: ");
        String outputDirectory = input.nextLine();

            try {
                for (Student student : studentList) {
                    double percentGrade = computeAverageGrade(student);
                    char letterGrade = assignLetterGrade(percentGrade);
                    Map<String, Double> studentAssignmentMap = student.getAssignmentMap();
                    writer = new BufferedWriter(new FileWriter(outputDirectory +
                            student.getFirstName() + "_" + student.getLastName() + ".txt"));
                    writer.write(student.getFirstName() + " " + student.getLastName() + "\n\n" +
                            "Average: " + percentGrade + "\n" +
                            "Letter Grade: " + letterGrade + "\n\n");
                    for (Map.Entry<String, Double> entry : studentAssignmentMap.entrySet()) {
                        writer.write(entry.getKey() + ": " + entry.getValue() + "%\n");
                    }
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Invalid Directory");
            }
        }

    private static Double computeAverageGrade(Student student) {
        double totalGradePercent = 0.0;
        int numberOfAssignments = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        Map<String, Double> assignment = student.getAssignmentMap();
        for(Double value : assignment.values()) {
            totalGradePercent += value;
            numberOfAssignments++;
        }
        totalGradePercent = totalGradePercent/numberOfAssignments;
        totalGradePercent = Double.parseDouble(df.format(totalGradePercent));
        return totalGradePercent;
    }


    private static Character assignLetterGrade (double averageGrade) {
        char letterGrade;
        if (averageGrade >= 90.0) {
            letterGrade = 'A';
        } else if (averageGrade >= 80.0) {
            letterGrade = 'B';
        } else if (averageGrade >= 70.0) {
            letterGrade = 'C';
        } else if (averageGrade >= 60.0) {
            letterGrade = 'D';
        } else {
            letterGrade = 'F';
        }
        return letterGrade;
    }

    private static void successfulReportMessage() {
        System.out.println("\nSuccessfully Generated Report Cards!");
    }
}
