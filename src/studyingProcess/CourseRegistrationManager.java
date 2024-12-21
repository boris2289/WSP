package studyingProcess;

import serialization.SerializationUtil;
import users.Student;
import users.Teacher;
import java.io.*;
import java.util.*;

public class CourseRegistrationManager {
    private static final String TEACHER_FILE = "teachers.txt";
    private static final String STUDENT_FILE = "students.txt";
    private static final String COURSE_FILE = "course.txt";

    private List<Course> courses;
    private HashMap<String, Teacher> teachers;
    private HashMap<String, Student> students;

    public CourseRegistrationManager() {
        this.courses = loadCoursesFromFile();
        this.teachers = loadTeachersFromFile();
        this.students = loadStudentsFromFile();
    }

    public HashMap<String, Teacher> getTeachers() {
        return teachers;
    }

    public HashMap<String, Student> getStudents() {
        return students;
    }

    public void handleTeacherActions(Scanner scanner,HashMap<String, Teacher> teachers) {
        System.out.println("=== Create New Course ===");

        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();

        if (teachers == null || teachers.isEmpty()) {
            System.out.println("No teachers available for assignment.");
            return;
        }

        System.out.println("Select a teacher from the list:");
        int index = 1;
        for (Teacher teacher : teachers.values()) {
            System.out.println(index + ". " + teacher.getName());
            index++;
        }

        System.out.print("Enter the number of your choice: ");
        int teacherChoice = Integer.parseInt(scanner.nextLine());

        if (teacherChoice < 1 || teacherChoice > teachers.size()) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }

        Teacher selectedTeacher = (Teacher) teachers.values().toArray()[teacherChoice - 1];
        String courseID = generateCourseID();

        Course newCourse = new Course(courseID, courseName);
        newCourse.setInstructor(selectedTeacher);

        selectedTeacher.addCourse(newCourse);

        this.courses.add(newCourse);
        saveCoursesToFile(this.courses);
        saveTeachersToFile(this.teachers);

        System.out.println("Course " + newCourse.getCourseName() + " added with ID: " + newCourse.getCourseId());
    }



    public void handleStudentActions(Scanner scanner, HashMap<String, Student> students) {
        if (this.teachers == null || this.teachers.isEmpty()) {
            System.out.println("No teachers available for course selection.");
            return;
        }

        System.out.println("Select a student from the list:");
        int index_student = 1;
        for (Student student : students.values()) {
            System.out.println(index_student + ". " + student.getName());
            index_student++;
        }

        System.out.print("Enter the number of your choice: ");
        int studentChoice = Integer.parseInt(scanner.nextLine());

        if (studentChoice < 1 || studentChoice > students.size()) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }
        Student selectedStudent = (Student) students.values().toArray()[studentChoice - 1];


        System.out.println("=== Select a Teacher ===");
        int index = 1;
        for (Teacher teacher : teachers.values()) {
            System.out.println(index + ". " + teacher.getName());
            index++;
        }

        System.out.print("Enter the number of your choice: ");
        int teacherChoice = Integer.parseInt(scanner.nextLine());

        if (teacherChoice < 1 || teacherChoice > teachers.size()) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }

        Teacher selectedTeacher = (Teacher) teachers.values().toArray()[teacherChoice - 1];
        List<Course> coursesTaught = selectedTeacher.getCoursesTaught();

        if (coursesTaught.isEmpty()) {
            System.out.println("No courses available for this teacher.");
            return;
        }

        System.out.println("Courses taught by " + selectedTeacher.getName() + ":");
        for (Course course : coursesTaught) {
            System.out.println("- " + course.getCourseName() + " (ID: " + course.getCourseId() + ")");
        }

        System.out.print("Enter the ID of the course you wish to join: ");
        String courseId = scanner.nextLine();

        Course selectedCourse = findCourseById(coursesTaught, courseId);
        if (selectedCourse != null) {
            System.out.println("You have successfully joined the course: " + selectedCourse.getCourseName());
        } else {
            System.out.println("Invalid course ID.");
        }
        selectedStudent.enrollInCourse(selectedCourse);
        saveStudentsToFile(this.students);
    }




    // Удаление курса
    public void removeCourse(Scanner scanner) {
        System.out.println("Choose the course to remove");

        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            System.out.println(i + ". " + course);
        }
        int courseToDelete = Integer.parseInt(scanner.nextLine());
        Course toRemoveCourse = courses.get(courseToDelete);
        courses.remove(courseToDelete);

        for (Teacher teacher : teachers.values()) {
            teacher.removeCourse(toRemoveCourse);
        }
        for (Student student : students.values()) {
            student.removeCourse(toRemoveCourse);
        }

        saveCoursesToFile(courses);
        System.out.println("Course " + toRemoveCourse + " removed successfully.");
    }


    private static Course findCourseById(List<Course> courses, String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    private static String generateCourseID() {
        Random random = new Random();
        return "COURSE-" + random.nextInt(10000);
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
            System.out.println("No teachers found. A new one will be created.");
        } catch (IOException e) {
            System.err.println("Error loading teachers: " + e.getMessage());
        }
        return null;
    }

    private static HashMap<String, Student> loadStudentsFromFile() {
        try (FileInputStream fileIn = new FileInputStream(STUDENT_FILE)) {
            return (HashMap<String, Student>) SerializationUtil.deserializeObject(fileIn);
        } catch (FileNotFoundException e) {
            System.out.println("No students file found. A new one will be created.");
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
        return new HashMap<String, Student>();
    }

    private static List<Course> loadCoursesFromFile() {
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

    private static void saveTeachersToFile(HashMap<String, Teacher> teachers) {
        try (FileOutputStream fileOut = new FileOutputStream(TEACHER_FILE)) {
            SerializationUtil.serializeObject(teachers, fileOut);
        } catch (IOException e) {
            System.err.println("Error saving teachers: " + e.getMessage());
        }
    }

    private static void saveStudentsToFile(HashMap<String, Student> students) {
        try (FileOutputStream fileOut = new FileOutputStream(STUDENT_FILE)) {
            SerializationUtil.serializeObject(students, fileOut);
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }


    private static void saveCoursesToFile(List<Course> courses) {
        try (FileOutputStream fileOut = new FileOutputStream(COURSE_FILE)) {
            SerializationUtil.serializeObject(courses, fileOut);
        } catch (IOException e) {
            System.err.println("Error saving courses: " + e.getMessage());
        }
    }
}
