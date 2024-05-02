package camp.model;

public class Score implements Comparable<Score>{
    private String subjectId;
    private String scoreId;
    private String studentId;
    private String grade;
    private int score;
    private int round;

    public Score(String scoreId, String studentId,  String subjectId,int round,int score ) {
        this.scoreId=scoreId;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.score = score;
        this.round = round;
        if(subjectId.charAt(0)=='M'){
            if(score>=95) grade = "A";
            else if(score>=90) grade="B";
            else if(score>=80) grade="C";
            else if(score>=70) grade="D";
            else grade="F";
        }
        else{
            if(score>=90) grade = "C";
            else if(score>=80) grade="B";
            else if(score>=70) grade="C";
            else if(score>=60) grade="D";
            else grade="F";
        }
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }
    public String getSubjectId(){
        return subjectId;
    }
    public String getStudentId(){
        return studentId;
    }
    public int getRound(){
        return round;
    }
    public int getScore(){
        return score;
    }
    public String getGrade(){
        return  grade;
    }
    public void setScore(int score){
        this.score=score;
        if(subjectId.charAt(0)=='M'){
            if(score>=95) this.grade = "A";
            else if(score>=90) this.grade="B";
            else if(score>=80) this.grade="C";
            else if(score>=70) this.grade="D";
            else this.grade="F";
        }
        else{
            if(score>=90) this.grade = "C";
            else if(score>=80) this.grade="B";
            else if(score>=70) this.grade="C";
            else if(score>=60) this.grade="D";
            else this.grade="F";
        }
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(this.round,o.round);
    }
}
