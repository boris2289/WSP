package managers;
import ENUMS.Language;
import ENUMS.Role;
import abstractt.User;
import serialization.Loader;
import serialization.SerializationUtil;
import studyingProcess.Course;
import users.Student;
import users.Teacher;
import java.io.*;
import java.util.*;

public class CourseRegistrationManager extends User {

    private List<Course> courses;
    private HashMap<String, Teacher> teachers;
    private HashMap<String, Student> students;

    private static final String TEACHER_FILE = "teachers.txt";
    private static final String STUDENT_FILE = "students.txt";
    private static final String COURSE_FILE = "course.txt";

    public CourseRegistrationManager(String userId, String name, String email, String phoneNumber, String password) {
        super(userId, name, email, phoneNumber, password, Role.COURSE_REGISTRATION_MANAGER);
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

    public void handleTeacherActions(Scanner scanner,HashMap<String, Teacher> teachers, Language language) {
        switch (language) {
            case ENGLISH:
                System.out.println("=== Create New Course ===");
                break;
            case RUSSIAN:
                System.out.println("=== Создать новый курс ===");
                break;
            case KAZAKH:
                System.out.println("=== Жаңа курс жасау ===");
                break;
        }


        switch (language) {
            case ENGLISH:
                System.out.print("Enter course name: ");
                break;
            case RUSSIAN:
                System.out.print("Введите название курса: ");
                break;
            case KAZAKH:
                System.out.print("Курс атауын енгізіңіз: ");
                break;
        }

        String courseName = scanner.nextLine();

        if (teachers == null || teachers.isEmpty()) {
            System.out.println("No teachers available for assignment.");
            return;
        }

        switch (language) {
            case ENGLISH:
                System.out.println("Select a teacher from the list:");
                break;
            case RUSSIAN:
                System.out.println("Выберите преподавателя из списка:");
                break;
            case KAZAKH:
                System.out.println("Тізімнен мұғалімді таңдаңыз:");
                break;
        }

        int index = 1;
        for (Teacher teacher : teachers.values()) {
            System.out.println(index + ". " + teacher.getName());
            index++;
        }

        switch (language) {
            case ENGLISH:
                System.out.print("Enter the number of your choice: ");
                break;
            case RUSSIAN:
                System.out.print("Введите номер вашего выбора: ");
                break;
            case KAZAKH:
                System.out.print("Таңдауыңыздың нөмірін енгізіңіз: ");
                break;
        }

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

        switch (language) {
            case ENGLISH:
                System.out.println("Course " + newCourse.getCourseName() + " added with ID: " + newCourse.getCourseId());
                break;
            case RUSSIAN:
                System.out.println("Курс " + newCourse.getCourseName() + " добавлен с ID: " + newCourse.getCourseId());
                break;
            case KAZAKH:
                System.out.println("Курс " + newCourse.getCourseName() + " қосылды, ID: " + newCourse.getCourseId());
                break;
        }

    }



    public void handleStudentActions(Scanner scanner, HashMap<String, Student> students, Language language) {
        if (this.teachers == null || this.teachers.isEmpty()) {
            System.out.println("No teachers available for course selection.");
            return;
        }

        switch (language) {
            case ENGLISH:
                System.out.println("Select a student from the list:");
                break;
            case RUSSIAN:
                System.out.println("Выберите студента из списка:");
                break;
            case KAZAKH:
                System.out.println("Тізімнен студентті таңдаңыз:");
                break;
        }

        int index_student = 1;
        for (Student student : students.values()) {
            System.out.println(index_student + ". " + student.getName());
            index_student++;
        }

        switch (language) {
            case ENGLISH:
                System.out.print("Enter the number of your choice: ");
                break;
            case RUSSIAN:
                System.out.print("Введите номер вашего выбора: ");
                break;
            case KAZAKH:
                System.out.print("Сіздің таңдауыңыздың нөмірін енгізіңіз: ");
                break;
        }

        int studentChoice = Integer.parseInt(scanner.nextLine());

        if (studentChoice < 1 || studentChoice > students.size()) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }
        Student selectedStudent = (Student) students.values().toArray()[studentChoice - 1];


        switch (language) {
            case ENGLISH:
                System.out.println("=== Select a Teacher ===");
                break;
            case RUSSIAN:
                System.out.println("=== Выберите преподавателя ===");
                break;
            case KAZAKH:
                System.out.println("=== Мұғалімді таңдаңыз ===");
                break;
        }

        int index = 1;
        for (Teacher teacher : teachers.values()) {
            System.out.println(index + ". " + teacher.getName());
            index++;
        }

        switch (language) {
            case ENGLISH:
                System.out.print("Enter the number of your choice: ");
                break;
            case RUSSIAN:
                System.out.print("Введите номер вашего выбора: ");
                break;
            case KAZAKH:
                System.out.print("Сіздің таңдауыңыздың нөмірін енгізіңіз: ");
                break;
        }

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

        switch (language) {
            case ENGLISH:
                System.out.println("Select a course taught by " + selectedTeacher.getName() + ":");
                break;
            case RUSSIAN:
                System.out.println("Выберите курс, преподаваемый " + selectedTeacher.getName() + ":");
                break;
            case KAZAKH:
                System.out.println(selectedTeacher.getName() + " оқытатын курсты таңдаңыз:");
                break;
        }

        for (int i = 0; i < coursesTaught.size(); i++) {
            Course course = coursesTaught.get(i);
            System.out.println(i + ". " + course.getCourseName() + " (ID: " + course.getCourseId() + ")");
        }
        int indexChosen = Integer.parseInt(scanner.nextLine());
        String courseId = coursesTaught.get(indexChosen).getCourseId();


        Course selectedCourse = findCourseById(coursesTaught, courseId);

        if (selectedCourse != null) {
            switch (language) {
                case ENGLISH:
                    System.out.println("You have successfully joined the course: " + selectedCourse.getCourseName());
                    break;
                case RUSSIAN:
                    System.out.println("Вы успешно присоединились к курсу: " + selectedCourse.getCourseName());
                    break;
                case KAZAKH:
                    System.out.println("Сіз курсқа сәтті қосылдыңыз: " + selectedCourse.getCourseName());
                    break;
            }


            selectedCourse.addEnrolledStudent(selectedStudent);
            selectedStudent.enrollInCourse(selectedCourse);

            if (this.courses.contains(selectedCourse)) {
                this.courses.set(indexChosen, selectedCourse);
            }
            else{
                this.courses.add(selectedCourse);
            }
            System.out.println(this.courses);
            saveStudentsToFile(this.students);
            saveCoursesToFile(this.courses);
        } else {
            System.out.println("Invalid course ID.");
        }

    }
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




    // Удаление курса
    public void removeCourse(Scanner scanner, Language language) {
        if (this.courses.isEmpty()) {
            System.out.println(this.students);
        }
        else{
            switch (language) {
                case ENGLISH:
                    System.out.println("Choose the course to remove");
                    break;
                case RUSSIAN:
                    System.out.println("Выберите курс для удаления");
                    break;
                case KAZAKH:
                    System.out.println("Жою үшін курс таңдаңыз");
                    break;
            }

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
            switch (language) {
                case ENGLISH:
                    System.out.println("Course " + toRemoveCourse + " removed successfully.");
                    break;
                case RUSSIAN:
                    System.out.println("Курс " + toRemoveCourse + " успешно удалён.");
                    break;
                case KAZAKH:
                    System.out.println("Курс " + toRemoveCourse + " сәтті жойылды.");
                    break;
            }

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
