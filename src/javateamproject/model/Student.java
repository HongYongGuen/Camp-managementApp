package javateamproject.model;

import javateamproject.type.ConditionType;
import javateamproject.type.SubjectType;

import java.util.ArrayList;
import java.util.List;

public class Student {


    // 고유번호
    private final String studentId;
    // 이름
    private String studentName;
    // 과목
    private final List<String> subjectids;
    // 상태
    private ConditionType condition;
    // 필수 과목 목록
    private List<String> mustSubjectIds;
    private List<String> choiceSubjectIds;

    //생성자
    public Student(String studentId, String studentName, List<String> selectSubjectIds) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjectids = selectSubjectIds;
        this.condition = ConditionType.GREEN;
        mustSubjectIds=new ArrayList<>();
        choiceSubjectIds = new ArrayList<>();
        for(String subjectid : selectSubjectIds){
            if(subjectid.compareTo("SJ5")<0) this.mustSubjectIds.add(subjectid);
            else this.choiceSubjectIds.add(subjectid);
        }

    }
    public List<String> getMustSubjectIds(){
        return mustSubjectIds;
    }

    public List<String> getChoiceSubjectIds() {
        return choiceSubjectIds;
    }

    //getter
    public String getStudentId() {
        return studentId;
    }

    public List<String> getSubjectids() {
        return subjectids;
    }

    public ConditionType getCondition() {
        return condition;
    }

    public String getStudentName() {
        return studentName;
    }
    //setter
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setCondition(ConditionType condition) {
        this.condition = condition;
    }
}
    // 수강과목 리스트 띄우기







