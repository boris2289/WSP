package studyingProcess;

import ENUMS.AttendanceStatus;
import users.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttendanceRecord {
    private Student student;
    private Course course;
    private Date date;
    private AttendanceStatus status;

    // Constructor
    public AttendanceRecord(Student student, Course course, Date date, AttendanceStatus status) {
        this.student = student;
        this.course = course;
        this.date = date;
        this.status = status;
    }

    // Methods

    // Mark attendance for a student
    public void markAttendance(Student student, AttendanceStatus status) {
        this.student = student;
        this.status = status;
    }

    // Get a list of attendance records (for example, for a specific course or student)
    public static List<AttendanceRecord> getAttendanceSummary() {
        // In real implementation, this would fetch the attendance records from a database or in-memory list.
        // For now, return an empty list to demonstrate.
        return new ArrayList<>();
    }

    // Getters and Setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
}
