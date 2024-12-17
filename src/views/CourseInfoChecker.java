package views;
import users.Manager;
import users.Teacher;
import users.Student;
import studyingProcess.Course;
import studyingProcess.CourseRegistrationManager;
import serialization.SerializationUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseInfoChecker {
    private static final String TEACHER_FILE = "teachers.txt";
    private static final String STUDENT_FILE = "students.txt";
    private static final String COURSE_FILE = "course.txt";
    private static HashMap<String, Teacher> teachers;
    private static HashMap<String, Student> students;
    private static CourseRegistrationManager courseManager = new CourseRegistrationManager();

    public static void main(String[] args) {
        // Десериализация учителей и студентов
        teachers = loadTeachersFromFile();
        students = loadStudentsFromFile();

        // Проверка и вывод информации о курсах у каждого учителя
        if (teachers != null) {
            System.out.println("=== Teacher Courses ===");
            for (Teacher teacher : teachers.values()) {
                System.out.println("Teacher ID: " + teacher.getUserId());
                System.out.println("Name: " + teacher.getName());
                System.out.println("Courses:");
                for (Course course : teacher.getCourses()) {
                    System.out.println("  - " + course.getCourseName() + " (ID: " + course.getCourseId() + ")");
                }
                System.out.println();
            }
        }

        // Проверка и вывод информации о курсах у каждого студента
        if (students != null) {
            System.out.println("=== Student Courses ===");
            for (Student student : students.values()) {
                System.out.println("Student ID: " + student.getUserId());
                System.out.println("Name: " + student.getName());
                System.out.println("Courses:");
                for (Course course : student.getEnrolledCourses()) {
                    System.out.println("  - " + course.getCourseName() + " (ID: " + course.getCourseId() + ")");
                }
                System.out.println();
            }
        }
    }

    private static HashMap<String, Teacher> loadTeachersFromFile() {
        try (FileInputStream fileIn = new FileInputStream(TEACHER_FILE)) {
            Object obj = SerializationUtil.deserializeObject(fileIn);
            if (obj instanceof HashMap<?, ?>) {
                return (HashMap<String, Teacher>) obj;
            } else {
                System.err.println("Unexpected data format in teachers file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No teachers found. Starting with an empty list.");
        } catch (IOException e) {
            System.err.println("Error loading teachers: " + e.getMessage());
        }
        return null;
    }

    private static HashMap<String, Student> loadStudentsFromFile() {
        try (FileInputStream fileIn = new FileInputStream(STUDENT_FILE)) {
            Object obj = SerializationUtil.deserializeObject(fileIn);
            if (obj instanceof HashMap<?, ?>) {
                return (HashMap<String, Student>) obj;
            } else {
                System.err.println("Unexpected data format in students file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No students found. Starting with an empty list.");
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
        return null;
    }
}
