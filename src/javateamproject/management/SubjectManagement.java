package javateamproject.management;

import javateamproject.model.Student;
import javateamproject.model.Subject;
import javateamproject.store.Store;
import javateamproject.type.SubjectType;

import java.util.List;
import java.util.Scanner;

public class SubjectManagement {
    Scanner sc = new Scanner(System.in);
    Store store;
    StudentManagement studentManagement;
    ScoreManagement scoreManagement;
    public SubjectManagement (){
        this.store = Store.getInstance();
    }


    //필수, 선택 과목별 목록 보여주기
    public void viewSubjects(SubjectType subjectType) {
        List<Subject> subjects = store.getSubjectStore();
        if (subjectType.equals(SubjectType.MUST)) {
            System.out.println("필수 과목 목록 입니다.");
            for (Subject s : subjects) {
                if (s.getSubjectType().equals(SubjectType.MUST))
                    System.out.print(s.getSubjectName() + "(" + s.getSubjectId() + ")  ");
            }
        } else {
            System.out.println("선택 과목 목록 입니다.");
            for (Subject s : subjects) {
                if (s.getSubjectType().equals(SubjectType.CHOICE))
                    System.out.print(s.getSubjectName() + "(" + s.getSubjectId() + ")  ");
            }
        }
        System.out.println();
    }

    //subjectIds에 해당하는 과목이름(과목코드) 출력
    public void viewSubjectSelected(List<String> subjectIds) {
        for (Subject s : store.getSubjectStore()) {
            for (String subjectId : subjectIds) {
                if (subjectId.equals(s.getSubjectId()))
                    System.out.print(s.getSubjectName() + "(" + s.getSubjectId() + ")  ");
            }
        }
        System.out.println();
    }

    public String getSubjectIdFromUserAtAdd(Student student) {
        while(true) {
            List<String> selectedSubjects = student.getSubjectids();

            viewSubjectSelected(selectedSubjects);
            System.out.print("과목번호를 입력하세요 : ");
            String subjectId = sc.nextLine();

            // 입력된 과목이 유효한지 확인
            if (selectedSubjects.contains(subjectId)) {
                return subjectId;
            } else {
                System.out.println();
                System.out.println("잘못입력되었습니다.");
            }
        }
    }

    // 과목명을 입력받는 메소드
    public String getSubjectNameFromUser(Student student) {
        inquirySelectSubjectIds(student);
        String subjectId;
        while(true) {
            System.out.print("과목을 입력하세요 : ");
            subjectId = sc.nextLine();

            // 입력된 과목이 유효한지 확인
            if (scoreManagement.isScoreExistBySubjectId(student.getStudentId(), subjectId)) {
                return subjectId;
            } else {
                System.out.println();
                System.out.println("점수가 입력되지 않은 과목입니다.");
            }
        }
    }

    public void inquirySelectSubjectIds(Student student){
        List<String> selectSubjectIds = student.getSubjectids();
        System.out.println(student.getStudentName()+"학생이 선택한 과목 목록입니다.");
        System.out.println("===========================================");
        for(String s : selectSubjectIds){
            System.out.println();
        }

    }


}