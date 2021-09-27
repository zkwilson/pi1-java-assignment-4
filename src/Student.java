import java.util.HashMap;
import java.util.Map;

public class Student {
    private String firstName;
    private String lastName;
    private Map<String, Double> assignmentMap = new HashMap<>();

    public Student(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAssignmentMap(String assignmentName, Double assignmentGrade) {
        assignmentMap.put(assignmentName, assignmentGrade);
    }

    public Map<String, Double> getAssignmentMap() {
        return assignmentMap;
    }
}
