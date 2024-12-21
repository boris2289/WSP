package users;
import java.util.HashSet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import ENUMS.Language;
import ENUMS.Role;
import studyingProcess.Course;
import studyingProcess.Grade;


public class Student extends User implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private String major;
    private String minor;
    private double GPA;
    private List<Course> enrolledCourses;
    private Map<Course, Grade> grades;
    private HashSet<Course> courses;

    public Student(String userId, String name, String email, String phoneNumber, String password, Language preferredLanguage,
                   String major, String minor, double GPA) 
    {
        super(userId, name, email, phoneNumber,password, Role.STUDENT, preferredLanguage);
        this.major = major;
        this.minor = minor;
        this.GPA = GPA;
        this.enrolledCourses = new ArrayList<>();
        this.setRole(Role.STUDENT);
        this.courses = new HashSet<>();
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }
    

    public void setGrade(Course course, Grade grade) {
        grades.put(course, grade);
    }

    public Map<Course, Grade> getGrades() {
        return grades;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public double calculateGPA() {
        return GPA;
    }
    
    public void addCourse(Course course) {
        if (course != null) {
            courses.add(course);
            System.out.println("Course " + course.getCourseName() + " added successfully.");
        } else {
            System.out.println("Invalid course.");
        }
    }

    public void removeCourse(Course course) {
        courses.remove(course);
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
