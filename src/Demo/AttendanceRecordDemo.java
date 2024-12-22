package Demo;

import ENUMS.AttendanceStatus;
import studyingProcess.AttendanceRecord;
import studyingProcess.Course;
import users.Student;

import java.util.List;
import java.util.ArrayList;

class AttendanceRecordDemo {

    public static void main(String[] args) {
        testCreateAttendanceRecord();
        testMarkAttendance();
        testGetAttendanceSummary();
    }

    // Test creating an AttendanceRecord
    static void testCreateAttendanceRecord() {
        System.out.println("Test 1: Test Create Attendance Record");

        Student student = new Student("ST001", "Alice Johnson", "alice@example.com", "555-1234", "alicePass123");
        Course course = new Course("CSE101", "Intro to Computer Science");
        AttendanceStatus status = AttendanceStatus.PRESENT;

        AttendanceRecord record = new AttendanceRecord(student, course, new java.util.Date(), status);

        assert record.getStudent().getName().equals("Alice Johnson") : "Test failed for student name";
        assert record.getCourse().getName().equals("Intro to Computer Science") : "Test failed for course name";
        assert record.getStatus() == AttendanceStatus.PRESENT : "Test failed for attendance status";

        System.out.println("Test passed for Create Attendance Record\n");
    }

    // Test markAttendance method
    static void testMarkAttendance() {
        System.out.println("Test 2: Test Mark Attendance");

        Student student = new Student("ST001", "Alice Johnson", "alice@example.com", "555-1234", "alicePass123");
        Course course = new Course("CSE101", "Intro to Computer Science");

        AttendanceRecord record = new AttendanceRecord(student, course, new java.util.Date(), AttendanceStatus.ABSENT);

        // Mark attendance as present
        record.markAttendance(student, AttendanceStatus.PRESENT);

        assert record.getStatus() == AttendanceStatus.PRESENT : "Test failed for updating attendance status to PRESENT";

        System.out.println("Test passed for Mark Attendance\n");
    }

    // Test getAttendanceSummary method
    static void testGetAttendanceSummary() {
        System.out.println("Test 3: Test Get Attendance Summary");

        Student student1 = new Student("ST001", "Alice Johnson", "alice@example.com", "555-1234", "alicePass123");
        Student student2 = new Student("ST002", "Bob Smith", "bob@example.com", "555-5678", "bobPass456");

        Course course = new Course("CSE101", "Intro to Computer Science");

        AttendanceRecord record1 = new AttendanceRecord(student1, course, new java.util.Date(), AttendanceStatus.PRESENT);
        AttendanceRecord record2 = new AttendanceRecord(student2, course, new java.util.Date(), AttendanceStatus.ABSENT);

        List<AttendanceRecord> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);

        // Verify that the attendance summary returns correct records
        List<AttendanceRecord> summary = AttendanceRecord.getAttendanceSummary();
        assert summary.size() == 2 : "Test failed for attendance summary size";
        assert summary.contains(record1) : "Test failed for attendance record 1";
        assert summary.contains(record2) : "Test failed for attendance record 2";

        System.out.println("Test passed for Get Attendance Summary\n");
    }
}
