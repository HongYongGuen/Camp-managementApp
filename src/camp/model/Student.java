package camp.model;

import java.util.List;

public class Student {
    private String studentId;
    private String studentName;

    private Score[] scores;
    private final List<String> subjectIds;

    public Student(String seq, String studentName ,List<String> subjectIds) {
        this.studentId = seq;
        this.studentName = studentName;
        this.subjectIds = subjectIds;
    }

    // Getter
    public String getStudentId() {
        return this.studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public List<String> getSubjectIds(){return this.subjectIds;}

}
