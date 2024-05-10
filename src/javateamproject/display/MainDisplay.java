package javateamproject.display;


import javateamproject.management.ScoreManagement;
import javateamproject.management.StudentManagement;
import javateamproject.management.SubjectManagement;
import javateamproject.store.Store;

import java.util.Scanner;

public class MainDisplay extends DisplayView{

    ScoreManagement scoreManagement ;
    StudentManagement studentManagement ;
    SubjectManagement subjectManagement ;
    StudentDisplayView studentDisplayView;
    ScoreDisplayView scoreDisplayView;
    Scanner sc;
    public MainDisplay(){
        Store s = Store.getInstance();
        s.init();
        s.dummy();
        this.subjectManagement = new SubjectManagement();
        this.studentManagement = new StudentManagement(this.subjectManagement);
        this.scoreManagement = new ScoreManagement(this.subjectManagement,this.studentManagement);
        this.studentDisplayView = new StudentDisplayView(this.studentManagement);
        this.scoreDisplayView = new ScoreDisplayView(this.scoreManagement);
        sc = new Scanner(System.in);
    }

    @Override
    public void displayview() throws InterruptedException{
        boolean flag = true;
        do {
            System.out.println("메인페이지입니다.\n숫자를 입력해주세요! \n1.수강생 관리 2.성적관리 3.나가기");
            String choose = sc.next();
            switch (choose) {
                case "1": //학생관리
                    studentDisplayView.displayview();
                    break;
                case "2": //성적관리
                    scoreDisplayView.displayview();
                    break;
                case "3":
                    flag = false;
                    break;
                default:
                    System.out.println("1~3까지의 정수를 입력해주세요.\n");
            }
        } while (flag);
        System.out.println("프로그램을 종료합니다!");
    }
}
