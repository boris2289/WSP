package studyingProcess;
import serialization.Loader;
import serialization.SerializationUtil;
import users.Student;
import users.Teacher;
import java.io.*;
import java.util.*;

public class CourseRegistrationManager extends Loader {

    private List<Course> courses;
    private HashMap<String, Teacher> teachers;
    private HashMap<String, Student> students;

    private static final String TEACHER_FILE = "teachers.txt";
    private static final String STUDENT_FILE = "students.txt";
    private static final String COURSE_FILE = "course.txt";

    public CourseRegistrationManager() {
        loadAllInfo();
    }

    public void loadAllInfo(){
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

        System.out.println("Select a course taught by " + selectedTeacher.getName() + ":");
        for (int i = 0; i < coursesTaught.size(); i++) {
            Course course = coursesTaught.get(i);
            System.out.println(i + ". " + course.getCourseName() + " (ID: " + course.getCourseId() + ")");
        }
        int indexChosen = Integer.parseInt(scanner.nextLine());
        String courseId = coursesTaught.get(indexChosen).getCourseId();


        Course selectedCourse = findCourseById(coursesTaught, courseId);
        this.courses.remove(selectedCourse);
        if (selectedCourse != null) {
            System.out.println("You have successfully joined the course: " + selectedCourse.getCourseName());
            selectedCourse.addEnrolledStudent(selectedStudent);
            selectedStudent.enrollInCourse(selectedCourse);
            this.courses.add(selectedCourse);
            saveStudentsToFile(this.students);
            saveCoursesToFile(this.courses);
        } else {
            System.out.println("Invalid course ID.");
        }

    }




    // Удаление курса
    public void removeCourse(Scanner scanner) {
        if (this.courses.isEmpty()) {
            System.out.println(this.students);
        }
        else{
            System.out.println("Choose the course to remove");
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                System.out.println(i + ". " + course.getCourseName() + " (ID: " + course.getCourseId() + ")");
            }


            int courseToDeleteIndex = Integer.parseInt(scanner.nextLine());
            Course toRemoveCourse = courses.get(courseToDeleteIndex);
            Teacher instructorTeacher = toRemoveCourse.getInstructor();
            String instructorId = instructorTeacher.getUserId();
            List<Student> studentList = toRemoveCourse.getEnrolledStudents();



            toRemoveCourse.removeInstructor();
            instructorTeacher.removeCourse(toRemoveCourse);
            this.teachers.put(instructorId, instructorTeacher);

            System.out.println(studentList);
            for (Student student : this.students.values()) {
                if (studentList.contains(student)) {

                    student.finishCourse(toRemoveCourse);
                    toRemoveCourse.removeStudent(student);
                    this.students.put(student.getUserId(), student);

                }
            }

            courses.remove(courseToDeleteIndex);

            saveCoursesToFile(courses);
            saveTeachersToFile(teachers);
            saveStudentsToFile(students);

            loadAllInfo();
            System.out.println("Course " + toRemoveCourse + " removed successfully.");
        }


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


    public void printAllInfo(){

        // Проверка и вывод информации о курсах у каждого учителя
        if (teachers != null) {
            System.out.println("=== Teachers ===");
            for (Teacher teacher : teachers.values()) {
                System.out.println("Teacher ID: " + teacher.getUserId());
                System.out.println("Name: " + teacher.getName());
                System.out.println("Courses:");
                for (Course course : teacher.getCoursesTaught()) {
                    System.out.println("  - " + course.getCourseName() + " (ID: " + course.getCourseId() + ")");
                }
                System.out.println();
            }
        }

        // Проверка и вывод информации о курсах у каждого студента
        if (students != null) {
            System.out.println("=== Students ===");
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
