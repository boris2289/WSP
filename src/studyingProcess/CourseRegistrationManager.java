package studyingProcess;

import users.Student;
import users.Teacher;
import java.io.*;
import java.util.*;

public class CourseRegistrationManager {
    private static final String LESSONS_FILE = "lessons.ser";
    private HashMap<String, Course> courses;
    private HashMap<String, List<Lesson>> courseLessons;
    private HashMap<String, Teacher> teachers;
    private HashMap<String, Student> students;

    public CourseRegistrationManager() {
        this.courses = new HashMap<>();
        this.courseLessons = new HashMap<>();
        this.teachers = new HashMap<>();
        this.students = new HashMap<>();
        loadLessonsFromFile();
    }

    // Добавление курса
    public void registerCourse(Course course) {
        if (!courses.containsKey(course.getCourseId())) {
            courses.put(course.getCourseId(), course);
            courseLessons.put(course.getCourseId(), new ArrayList<>());
            System.out.println("Course " + course.getCourseName() + " registered successfully.");
        } else {
            System.out.println("Course " + course.getCourseName() + " is already registered.");
        }
    }

    // Добавление преподавателя
    public void addTeacher(Teacher teacher) {
        if (!teachers.containsKey(teacher.getUserId())) {
            teachers.put(teacher.getUserId(), teacher);
            System.out.println("Teacher " + teacher.getName() + " added successfully.");
        } else {
            System.out.println("Teacher " + teacher.getName() + " already exists.");
        }
    }

    // Добавление студента
    public void addStudent(Student student) {
        if (!students.containsKey(student.getUserId())) {
            students.put(student.getUserId(), student);
            System.out.println("Student " + student.getName() + " added successfully.");
        } else {
            System.out.println("Student " + student.getName() + " already exists.");
        }
    }

    // Добавление урока к курсу через ввод
    public void addLessonToCourseFromInput(Scanner scanner) {
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        Course course = courses.get(courseId);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.print("Enter Lesson ID: ");
        String lessonId = scanner.nextLine();

        System.out.print("Enter Lesson Type (lecture/practice): ");
        String lessonType = scanner.nextLine();

        System.out.print("Enter Lesson Topic: ");
        String topic = scanner.nextLine();

        System.out.print("Enter Teacher ID: ");
        String teacherId = scanner.nextLine();
        Teacher teacher = teachers.get(teacherId);

        if (teacher == null) {
            System.out.println("Teacher not found.");
            return;
        }

        Lesson lesson = new Lesson(lessonId, lessonType, topic, teacher);
        courseLessons.get(courseId).add(lesson);
        saveLessonsToFile();
        System.out.println("Lesson added successfully.");
    }

    // Вывод уроков по курсу
    public void listLessonsForCourse(String courseId) {
        if (courseLessons.containsKey(courseId)) {
            System.out.println("Lessons for course " + courses.get(courseId).getCourseName() + ":");
            for (Lesson lesson : courseLessons.get(courseId)) {
                System.out.println(lesson);
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    private void saveLessonsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LESSONS_FILE))) {
            oos.writeObject(courseLessons);
            System.out.println("Lessons saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving lessons: " + e.getMessage());
        }
    }

    private void loadLessonsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(LESSONS_FILE))) {
            courseLessons = (HashMap<String, List<Lesson>>) ois.readObject();
            System.out.println("Lessons loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved lessons found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading lessons: " + e.getMessage());
            courseLessons = new HashMap<>();
        }
    }

    public void listAllCourses() {
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }
}
