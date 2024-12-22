package studyingProcess;

public class Mark {
    private final double mark;
    private final char letterMark;

    // Static instances for the grades A, B, C, D, and F
    public static final Mark A = new Mark(90.0, 'A');
    public static final Mark B = new Mark(80.0, 'B');
    public static final Mark C = new Mark(70.0, 'C');
    public static final Mark D = new Mark(60.0, 'D');
    public static final Mark F = new Mark(0.0, 'F');

    // Private constructor to restrict instantiation
    private Mark(double mark, char letterMark) {
        this.mark = mark;
        this.letterMark = letterMark;
    }

    // Getters
    public double getMark() {
        return mark;
    }

    public char getLetterMark() {
        return letterMark;
    }

    // Static method to get a Mark by grade letter
    public static Mark getMarkByLetter(char letter) {
        switch (letter) {
            case 'A':
                return A;
            case 'B':
                return B;
            case 'C':
                return C;
            case 'D':
                return D;
            case 'F':
                return F;
            default:
                throw new IllegalArgumentException("Invalid grade letter: " + letter);
        }
    }

    // Method to display the grade information
    public void write() {
        System.out.println("Mark: " + mark + ", Letter grade: " + letterMark);
    }

    @Override
    public String toString() {
        return "Mark{" + "mark=" + mark + ", letterMark=" + letterMark + '}';
    }
}

