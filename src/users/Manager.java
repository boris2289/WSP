package users;

import studyingProcess.Course;
import studyingProcess.CourseRegistrationManager;

import java.util.List;

public class Manager extends User {
    private List<Course> managedCourses;

    public Manager(String id, String name, String email, String phone, String password, List<Course> managedCourses) {
        super(id, name, email, phone, password);
        this.managedCourses = managedCourses;
    }

    public List<Course> getManagedCourses() {
        return managedCourses;
    }

    public void addManagedCourse(Course course) {
        if (!managedCourses.contains(course)) {
            managedCourses.add(course);
            System.out.println("Course " + course.getCourseName() + " added to manager's list.");
        } else {
            System.out.println("Manager already manages the course: " + course.getCourseName());
        }
    }

    public void registerStudentToCourse(Student student, Course course, CourseRegistrationManager manager) {
        manager.registerStudentToCourse(student, course);
    }

    public void assignTeacherToCourse(Teacher teacher, Course course, CourseRegistrationManager manager) {
        manager.assignTeacherToCourse(teacher, course);
    }
    public void addCourse(Course course) {
        managedCourses.add(course);
    }



    @Override
    public String toString() {
        return "Manager{" +
                "managedCourses=" + managedCourses +
                ", name='" + getName() + '\'' +
                ", id='" + getUserId() + '\'' +
                '}';
    }
}
