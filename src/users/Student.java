package users;

import exceptions.LowHIndexException;
import studyingProcess.Course;
import studyingProcess.Grade;
import ENUMS.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Student extends User implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private String major;
    private String minor;
    private double GPA;
    private List<Course> enrolledCourses;
    private Map<Course, Grade> grades;
    private Researcher supervisor; // Researcher who supervises the student

    public Student(String userId, String name, String email, String phoneNumber, String password, Language preferredLanguage,
                   String major, String minor, double GPA) {
        super(userId, name, email, phoneNumber, password, Role.STUDENT, preferredLanguage);
        this.major = major;
        this.minor = minor;
        this.GPA = GPA;
        this.enrolledCourses = new ArrayList<>();
        this.setRole(Role.STUDENT);
    }

    // Method to assign a supervisor
    public void assignSupervisor(Researcher researcher) throws LowHIndexException {
        if (researcher.getHIndex() < 3) {
            throw new LowHIndexException("Researcher with h-index less than 3 cannot be a supervisor.");
        }
        this.supervisor = researcher;
    }

    // Other methods
    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }

    public void finishCourse(Course course) {
        enrolledCourses.remove(course);
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setGrade(Course course, Grade grade) {
        grades.put(course, grade);
    }

    public Map<Course, Grade> getGrades() {
        return grades;
    }

    public double calculateGPA() {
        return GPA;
    }

    @Override
    public String toString() {
        return String.format("Student{id='%s', major='%s', GPA=%.2f, enrolledCourses=%s}",
                userId, major, GPA, enrolledCourses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Double.compare(student.GPA, GPA) == 0 &&
                Objects.equals(major, student.major);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), major, GPA);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student cloned = (Student) super.clone();
        cloned.enrolledCourses = new ArrayList<>(enrolledCourses);
        return cloned;
    }
}
