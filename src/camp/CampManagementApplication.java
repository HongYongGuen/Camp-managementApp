package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    // 과목 타입
    private static final String  SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static final String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex_m;
    private static int subjectIndex_c;
    private static int scoreIndex;
    private static final String INDEX_TYPE_SUBJECT_MANDANTORY =  "MSC";
    private static final String INDEX_TYPE_SUBJECT_CHOICE = "CSC";
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>();
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT_MANDANTORY),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT_MANDANTORY),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT_MANDANTORY),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT_MANDANTORY),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT_MANDANTORY),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT_CHOICE),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT_CHOICE),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT_CHOICE),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT_CHOICE),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        scoreStore = new ArrayList<>();
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT_MANDANTORY-> {
                subjectIndex_m++;
                return INDEX_TYPE_SUBJECT_MANDANTORY + subjectIndex_m;
            }
            case INDEX_TYPE_SUBJECT_CHOICE-> {
                subjectIndex_c++;
                return INDEX_TYPE_SUBJECT_CHOICE + subjectIndex_c;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        // 기능 구현 (필수 과목, 선택 과목)
        int m_idx = 0;
        int c_idx = 0;
        int count=0;
        List<String> subjectIds = new ArrayList<>();
        System.out.println("\n필수과목을 등록합니다...");
        System.out.println("3개 이상 등록해주세요!");

        while(true){
            System.out.println("\n과목 목록 : Java / 1, 객체지향 / 2,  Spring / 3, JAP / 4, Mysql / 5");
            System.out.println("\n해당하는 필수과목 번호를 입력해주세요");
            try{
                c_idx = sc.nextInt();
                switch (c_idx){
                    case 1 ->{
                        subjectIds.add(subjectStore.get(0).getSubjectId());
                    }
                    case 2 ->{
                        subjectIds.add(subjectStore.get(1).getSubjectId());
                    }
                    case 3 ->{
                        subjectIds.add(subjectStore.get(2).getSubjectId());
                    }
                    case 4 ->{
                        subjectIds.add(subjectStore.get(3).getSubjectId());
                    }
                    case 5 ->{
                        subjectIds.add(subjectStore.get(4).getSubjectId());
                    }
                    default -> {
                        System.out.println("잘못 입력하셨습니다.");
                        System.out.println("1~5까지의 정수를 입력해주세요");
                        continue;
                    }
                }count++;
            }catch (Exception ignored){
                System.out.println("잘못 입력하셨습니다.");
                System.out.println("1~5까지의 정수를 입력해주세요");
                continue;
            }
            System.out.println("필수과목 " +m_idx+"개 입력하셨습니다.");
            if(count>=3){
                System.out.println("\n 필수 과목 3개 이상을 입력하셨습니다.");
                System.out.println("추가로 입력하고 싶으시면 'y'를 입력하셍요");
                String sw = sc.next();
                if(sw.equalsIgnoreCase("Y")){
                    break;
                }
            }else if(count>=5){
                System.out.println("\n 모든 과목을 입력하셨습니다.");
                break;
            }
        }
        count=0;
        System.out.println("필수과목이 모두 입력 되었습니다.\n이제 선택과목 입력으로 넘어갑니다.");
        System.out.println("\n선택과목을 등록합니다...");
        System.out.println("2개 이상 등록해주세요!");
        while(true){
            System.out.println("\n과목 목록 : 디자인 패턴 / 1, Spring Security / 2,  Redis / 3, MongoDB / 4");
            System.out.println("\n해당하는 선택과목 번호를 입력해주세요");
            try{
                m_idx = sc.nextInt();
                switch (m_idx){
                    case 1 ->{
                        subjectIds.add(subjectStore.get(5).getSubjectId());
                    }
                    case 2 ->{
                        subjectIds.add(subjectStore.get(6).getSubjectId());
                    }
                    case 3 ->{
                        subjectIds.add(subjectStore.get(7).getSubjectId());
                    }
                    case 4 ->{
                        subjectIds.add(subjectStore.get(8).getSubjectId());
                    }
                    default -> {
                        System.out.println("잘못 입력하셨습니다.");
                        System.out.println("1~4까지의 정수를 입력해주세요");
                        continue;
                    }
                }count++;
            }catch (Exception ignored){
                System.out.println("잘못 입력하셨습니다.");
                System.out.println("1~4까지의 정수를 입력해주세요");
                continue;
            }
            System.out.println("선택과목 " +m_idx+"개 입력하셨습니다.");
            if(count>=2){
                System.out.println("\n 선택 과목 3개 이상을 입력하셨습니다.");
                System.out.println("추가로 입력하고 싶으시면 'y'를 입력하셍요");
                String sw = sc.next();
                if(sw.equalsIgnoreCase("Y")){
                    break;
                }
            }else if(count>=4){
                System.out.println("\n 모든 과목을 입력하셨습니다.");
                break;
            }
        }
        System.out.println("모든 과목이 입력 되었습니다.\n");
        studentStore.add(new Student(sequence(INDEX_TYPE_STUDENT), studentName,subjectIds)); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        System.out.println("수강생 목록:");

        for(int i=0;i<studentStore.size();i++){
            System.out.print(studentStore.get(i).getStudentName()+"("+studentStore.get(i).getStudentId()+")   ");
            if(i%10 == 0) System.out.println();
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        String studentId=sc.next();
        while(searchCheckStudent(studentId).isEmpty()){
            System.out.println("없는 학생 번호입니다.");
            System.out.println("목록에서 다시 확인하세요");
            inquireStudent();
            studentId = sc.next();
        }
        return studentId;
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.println("시험 점수를 등록합니다...");
        boolean flag = false;
        int score=0,round = 0;
        String subjectid = null;
        // 기능 구현
        while(!flag){
            subjectid = getSubjectId();
            System.out.println("\n회차 번호(1~10)를 입력하세요");
            round = sc.nextInt();
            while(round>10 || round <=1){
                System.out.println("\n잘못 입력하셨습니다.");
                round = sc.nextInt();
            }
            System.out.println("\n점수(1~100)를 입력하세요");
            score = sc.nextInt();
            while(score>100 || score <=0){
                System.out.println("\n잘못 입력하셨습니다.");
                score = sc.nextInt();
            }
            flag = searchCheckScore(round,subjectid).isPresent();;
            if(flag){
                System.out.println("이미 입력한 회차 과목 점수 입니다.\n다시 입력해주세요!");
            }
        }

        scoreStore.add(new Score(sequence(INDEX_TYPE_SCORE), studentId, subjectid,round,score));
        System.out.println("\n점수 등록 성공!");
    }

    private static Optional<Score> searchCheckScore(int round,String subjectId){
        Optional<Score> result = scoreStore.stream()
                .filter(score -> score.getSubjectId()==subjectId && score.getRound()==round)
                .findFirst();
        return result;
    }
    private static Optional<Student> searchCheckStudent(String studentId){
        Optional<Student> result = studentStore.stream()
                .filter(score -> score.getStudentId().equals(studentId))
                .findFirst();
        return result;
    }
    private static String getSubjectId() {
        System.out.print("\n관리할 과목 번호를 입력하시오...");
        System.out.print("\n필수과목목록 : ");
        for(int i=0;i<subjectStore.size();i++){
            System.out.print(subjectStore.get(i).getSubjectName()+ "("+subjectStore.get(i).getSubjectId()+") ");
            if(i==4){
                System.out.print("\n선택과목목록 : ");
            }
        }
        return sc.next();
    }
    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        boolean flag=false;
        while(!flag){
            String subjectid = getSubjectId();
            System.out.println("\n회차 번호를 입력하세요");
            int round = sc.nextInt();
            while(round>10 || round <=1){
                System.out.println("\n잘못 입력하셨습니다. \n다시 회차 번호를 입력하세요");
                round = sc.nextInt();
            }
            System.out.println("\n수정할 점수 입력하세요");
            int score = sc.nextInt();
            while(score>100 || score <=0){
                System.out.println("\n잘못 입력하셨습니다. \n다시 회차 번호를 입력하세요");
                score = sc.nextInt();
            }
            Optional<Score> setScore =searchCheckScore(round,subjectid);
            flag=setScore.isPresent();
            if(!flag){
                System.out.println("과목 및 회차 정보를 다시 확인하세요!");
            }
            else{
                setScore.get().setScore(score);
            }
        }
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        String subjectId = getSubjectByStudentId(studentId);
        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현
        List<Score> inquiryScores = scoreStore.stream()
                        .filter(score -> score.getSubjectId().equals(subjectId))
                                .toList();
        Collections.sort(inquiryScores);
        for (Score inquiryScore : inquiryScores) {
            System.out.println(inquiryScore.getRound() + "회차 : " + inquiryScore.getGrade());
        }
        System.out.println("\n등급 조회 성공!");
    }

    private static String getSubjectByStudentId(String studentId){
        Optional<Student> result = studentStore.stream()
                .filter(student -> student.getStudentName().equals(studentId))
                .findFirst();
        System.out.println("현재 학생의 수업 목록 입니다.");
        List<String> nowSubjects = result.map(Student::getSubjectIds).orElse(null);
        for(int i=0;i<subjectStore.size();i++){
            for(int j = 0; j< Objects.requireNonNull(nowSubjects).size(); j++){
                if(subjectStore.get(i).equals(nowSubjects.get(j))) {
                    System.out.print(subjectStore.get(i).getSubjectName()+"("+subjectStore.get(i).getSubjectId()+")  ");
                }
            }
        }
        System.out.println("\n학생의 과목 번호를 입력해주세요!");
        String subjectId = sc.next();
        while(!nowSubjects.contains(subjectId)){
            System.out.println("현 학생의 과목 중에서 입력해주세요!");
            subjectId = sc.next();
        }return subjectId;
    }

}
