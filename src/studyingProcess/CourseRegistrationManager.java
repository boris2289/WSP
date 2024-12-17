package studyingProcess;

import users.Student;
import users.Teacher;
import users.Manager;
import java.util.HashMap;

public class CourseRegistrationManager {
    private HashMap<String, Course> courses;

    public CourseRegistrationManager() {
        this.courses = new HashMap<>();
    }

    public void registerCourse(Course course) {
        if (!courses.containsKey(course.getCourseId())) {
            courses.put(course.getCourseId(), course);
            System.out.println("Course " + course.getCourseName() + " registered successfully.");
        } else {
            System.out.println("Course " + course.getCourseName() + " is already registered.");
        }
    }

    public void unregisterCourse(String courseID) {
        if (courses.remove(courseID) != null) {
            System.out.println("Course unregistered successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    public void registerStudentToCourse(Student student, Course course) {
        if (courses.containsKey(course.getCourseId())) {
            student.addCourse(course);
            System.out.println("Student " + student.getName() + " registered to course " + course.getCourseName());
        } else {
            System.out.println("Course not found.");
        }
    }

    public void assignTeacherToCourse(Teacher teacher, Course course) {
        if (courses.containsKey(course.getCourseId())) {
            course.setInstructor(teacher);
            System.out.println("Teacher " + teacher.getName() + " assigned to course " + course.getCourseName());
        } else {
            System.out.println("Course not found.");
        }
    }

    public void addManagerToCourse(Manager manager, Course course) {
        if (!manager.getManagedCourses().contains(course)) {
            manager.addManagedCourse(course);
            System.out.println("Course " + course.getCourseName() + " added to manager's list.");
        } else {
            System.out.println("Manager already manages the course.");
        }
    }

    public void listAllCourses() {
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }
}
