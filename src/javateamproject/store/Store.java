package javateamproject.store;

import javateamproject.model.Score;
import javateamproject.model.Student;
import javateamproject.model.Subject;
import javateamproject.type.ClassType;
import javateamproject.type.ConditionType;
import javateamproject.type.SubjectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//저장소 기능 추가, 검색, 접근, 삭제 오직!//여기올때 오레 체크 완료
public class Store {
    private int studentIndex;
    private int scoreIndex;
    private int subjectIndex;
    private List<Student> studentStore;
    private List<Subject> subjectStore;
    private List<Score> scoreStore;


    private static class StoreHelper{
        private static final Store INSTANCE = new Store();

    }

    public static Store getInstance(){
        return StoreHelper.INSTANCE;
    }

    public void init() {
        this.studentStore = new ArrayList<>();
        this.scoreStore = new ArrayList<>();
        this.subjectStore = List.of(
                new Subject(
                        sequence(ClassType.SUBJECT),
                         "Java",
                        SubjectType.MUST
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "객체지향",
                        SubjectType.MUST
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "Spring",
                        SubjectType.MUST
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "JPA",
                        SubjectType.MUST
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "MySQL",
                        SubjectType.MUST
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "디자인 패턴",
                        SubjectType.CHOICE
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "Spring Security",
                        SubjectType.CHOICE
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "Redis",
                        SubjectType.CHOICE
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "MongoDB",
                        SubjectType.CHOICE
                )
        );
        dummy();
    }

    private String sequence(ClassType classtype) {
        switch (classtype) {
            case STUDENT -> {
                return classtype.getIdxName() + this.studentIndex++;
            }
            case SCORE -> {
                return classtype.getIdxName() + this.scoreIndex++;
            }
            case SUBJECT -> {
                return classtype.getIdxName() + this.subjectIndex++;
            }
            default -> {
                return null;
            }
        }
    }

    public List<Score> getScoreStore() {
        return this.scoreStore;
    }

    public List<Student> getStudentStore() {
        return this.studentStore;
    }

    public List<Subject> getSubjectStore() {
        return this.subjectStore;
    }

    public int getScoreIndex() {
        return this.scoreIndex - 1;
    }

    public int getStudentIndex() {
        return this.studentIndex - 1;
    }

    public int getSubjectIndex() {
        return this.subjectIndex - 1;
    }

    //학생 추가
    public void addStudent(String studentName, List<String> selectSubjects) {
        Student student = new Student(sequence(ClassType.STUDENT), studentName, selectSubjects);
        studentStore.add(student);
    }

    //성적 추가
    public void addScore(String studentId, String subjectId, int round, int score, SubjectType subjectType) {
        Score examScore = new Score(sequence(ClassType.SCORE), studentId, subjectId, round, score, subjectType);
        scoreStore.add(examScore);
    }

    //삭제하기
    public void deleteStudent(String studentId) {
        int idx = findStudentIndex(studentId);
        studentStore.remove(idx);
    }

    //학번 맞는 학생 인덱스 찾기
    public int findStudentIndex(String targetStudentId) {
        for (int i = 0; i < studentStore.size(); i++) {
            if (studentStore.get(i).getStudentId().equals(targetStudentId)) {
                return i; // 일치하는 학번을 가진 학생을 찾으면 해당 인덱스 반환
            }
        }
        return -1; // 일치하는 학번을 가진 학생을 찾지 못한 경우 -1 반환
    }

    //필수/선택 별 과목 이름 가져오기
    public List<String> getSubjectNamesBySubjectType(SubjectType subjectType) {
        List<String> names = new ArrayList<>();
        for (Subject s : subjectStore) {
            if (s.getSubjectType().equals(subjectType)) names.add(s.getSubjectId());
        }
        return names;
    }

    //해당 과복번호로 타입 가져오기
    public SubjectType getSubjectTypeBySubjectId(String subjectId) {
        for (Subject s : subjectStore) {
            if (s.getSubjectId().equals(subjectId)) return s.getSubjectType();
        }
        return null;
    }

    public String getSubjectNameBySubjectId(String subjectId) {
        for (Subject s : subjectStore) {
            if (s.getSubjectId().equals(subjectId)) return s.getSubjectName();
        }
        return null;
    }

    //학생번호, 과목 번호, 회차 번호 받아서 스코어 객체 접근하기
    public Optional<Score> optionalScore(String studentId, String subjectId, int round) {
        return this.scoreStore.stream()
                .filter(score -> score.getStudentId().equals(studentId) && score.getSubjectId().equals(subjectId) && score.getRound() == round)
                .findFirst();

    }
    //과목번호에 해당하는 과목 반환
    public Subject getSubjectBySubjectId(String subjectId) {
        for (Subject s : this.subjectStore) {
            if (s.getSubjectId().equals(subjectId)) return s;
        }
        return null;
    }

    public void dummy() {
        List<String> subjectList =Arrays.asList("ST1","ST2","ST3","ST4","ST6","ST7","ST8");
        addStudent("강호동", subjectList);
        addStudent("유재석", subjectList);
        addStudent("장원영", subjectList);
        addStudent("김지민", subjectList);
        addStudent("함날두", subjectList);
        addStudent("홍길동", subjectList);
        addStudent("문수정", subjectList);
        addScore("ST1", "SJ0", 1, 90, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST1", "SJ0", 2, 70, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST1", "SJ0", 3, 40, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST1", "SJ1", 1, 90, getSubjectTypeBySubjectId("SJ2"));
        addScore("ST1", "SJ2", 1, 60, getSubjectTypeBySubjectId("SJ3"));
        addScore("ST1", "SJ3", 1, 60, getSubjectTypeBySubjectId("SJ5"));
        addScore("ST1", "SJ5", 1, 80, getSubjectTypeBySubjectId("SJ6"));
        addScore("ST2", "SJ0", 1, 90, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST2", "SJ0", 2, 80, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST2", "SJ0", 3, 70, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST2", "SJ0", 4, 60, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST2", "SJ0", 5, 50, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST2", "SJ0", 6, 40, getSubjectTypeBySubjectId("SJ1"));
    }

    public List<Score> getScoreByStudentId(String studentId) {
        return this.scoreStore.stream()
                .filter(score -> score.getStudentId().equals(studentId))
                .toList();
    }

    // 해당 학생과 과목에 대한 점수 목록을 반환하는 메서드
    // 주어진 학생 ID와 과목 ID에 해당하는 점수를 찾아서 리스트로 반환
    // 리스트에서 각 점수를 확인해 서로가 일치하는 경우에만 List에 추가!
    public List<Score> getScoresByStudentAndSubject(String studentId, String subjectId) {
        List<Score> scores = new ArrayList<>();
        for (Score score : this.scoreStore) {
            if (score.getStudentId().equals(studentId) && score.getSubjectId().equals(subjectId)) {
                scores.add(score);
            }
        }
        return scores;
    }

    // 해당 과목의 총 회차 수를 반환하는 메서드
    // 주어진 과목 ID에 해당하는 회차 수를 계산하여 반한!
    // scoreStore 리스트에서 과목 id와 일치하는 점수를 찾고, 찾을때마다 회차 수를 증가 => 총 회차 수 반복
    public int getSubjectRoundsById(String subjectId) {
        int totalRounds = 0;
        for (Score score : this.scoreStore) {
            if (score.getSubjectId().equals(subjectId)) {
                totalRounds++;
            }
        }
        return totalRounds;
    }

    // 특정 상태의 수강생을 필터링하여 반환하는 메서드
    public List<Student> getStudentsByCondition(ConditionType condition) {
        List<Student> filteredStudents = new ArrayList<>(); // 필터링된 수강생을 저장할 List
        for (Student student : this.studentStore) { // 모든 수강생을 반복하여 주어진 상태와 일치하는 수강생을 찾아냄
            if (student.getCondition().equals(condition)) { // 수강생의 상태가 주어진 상태와 일치시 리스트에 추가!
                filteredStudents.add(student);
            }
        }
        return filteredStudents; // 필터링된 수강생 리스트를 return
    }

    public Optional<Score> optionalScoreByStudentId(String studentId) {
        return scoreStore.stream()
                .filter(score -> score.getStudentId().equals(studentId))
                .findFirst();
    }
}
