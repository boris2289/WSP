package serialization;

import studyingProcess.Course;
import users.Student;
import users.Teacher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Loader {
    private static final String TEACHER_FILE = "teachers.txt";
    private static final String STUDENT_FILE = "students.txt";
    private static final String COURSE_FILE = "course.txt";

    public static HashMap<String, Teacher> loadTeachersFromFile() {
        try (FileInputStream fileIn = new FileInputStream(TEACHER_FILE)) {
            Object obj = SerializationUtil.deserializeObject(fileIn);
            if (obj instanceof HashMap<?, ?>) {
                return (HashMap<String, Teacher>) obj;
            } else {
                System.err.println("Unexpected data format in teachers file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No teachers found. A new one will be created.");
        } catch (IOException e) {
            System.err.println("Error loading teachers: " + e.getMessage());
        }
        return null;
    }

    public static HashMap<String, Student> loadStudentsFromFile() {
        try (FileInputStream fileIn = new FileInputStream(STUDENT_FILE)) {
            return (HashMap<String, Student>) SerializationUtil.deserializeObject(fileIn);
        } catch (FileNotFoundException e) {
            System.out.println("No students file found. A new one will be created.");
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
        return new HashMap<String, Student>();
    }

    public static List<Course> loadCoursesFromFile() {
        try (FileInputStream fileIn = new FileInputStream(COURSE_FILE)) {
            Object obj = SerializationUtil.deserializeObject(fileIn);
            if (obj instanceof List<?>) {
                return (List<Course>) obj;
            } else {
                System.err.println("Unexpected data format in courses file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No courses file found. A new one will be created.");
        } catch (IOException e) {
            System.err.println("Error loading courses: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
