package studyingProcess;
import users.Student;
import users.Teacher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course implements Serializable{
    private String courseId;
    private String courseName;
    private Teacher instructor;
    private List<Student> enrolledStudents;

    public Course(String courseId, String courseName, Teacher instructor) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.enrolledStudents = new ArrayList<>();
    }
    
    // вакансия
    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.enrolledStudents = new ArrayList<>();
    }

    public void addEnrolledStudent(Student student) {
        enrolledStudents.add(student);
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public Teacher getInstructor() {
        return instructor;
    }
    
    public void setInstructor(Teacher t) {
        this.instructor = t;
    }

    public void removeInstructor() {
        this.instructor = null;
    }
    
    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
        System.out.println("Student " + student.getName() + " removed from the course: " + this.courseName);
    }

    

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    @Override
    public String toString() {
        return "Course: " + this.getCourseName() + " Id: "+ this.getCourseId();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId.equals(course.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }

    public Object getName() {
        return courseName;
    }
}
