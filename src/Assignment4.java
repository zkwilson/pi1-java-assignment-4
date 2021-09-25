import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment4 {
    private static Scanner input = new Scanner(System.in);
    public static void main (String... args) {
        greetings();
        getStudentFromFile();
    }


    public static void greetings(){
        System.out.println("Welcome to the Student Report Card Generator\n" +
                "--------------------------------------------\n");
    }

    public static List<Student> getStudentFromFile(){
        Scanner myReader = null;
        Boolean restart = true;
        List<Student> studentList = new ArrayList<>();
        do {
            try {
                System.out.print("Enter the student input file location: ");
                String fileLocation = input.nextLine();
                myReader = new Scanner(new File(fileLocation));
                while(myReader.hasNextLine()){
                    String line = myReader.nextLine();
                    String[] studentArray = line.split(" ");
                    Student newStudent = new Student(studentArray[0], studentArray[1]);
                    studentList.add(newStudent);
                }
                restart = false;
            }catch (FileNotFoundException e) {
                System.out.println("Input file not found");
            }

        }while(restart);
        return studentList;
    }


}
