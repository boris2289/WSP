package studyingProcess;

public class Grade {
    private double score;
    private String letterGrade;

    public Grade(double score) {
        this.score = score;
        this.letterGrade = calculateLetterGrade(score);
    }

    private String calculateLetterGrade(double score) {
        if (score >= 90) return "A";
        else if (score >= 80) return "B";
        else if (score >= 70) return "C";
        else if (score >= 60) return "D";
        else return "F";
    }

    public double getScore() {
        return score;
    }

    public String getLetterGrade() {
        return letterGrade;
    }
}
