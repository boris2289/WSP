package users;

import ENUMS.Role;
import abstractt.User;
import studyingProcess.Course;

import java.util.ArrayList;
import java.util.List;

public class Manager extends User {
    private List<Course> managedCourses;

    public Manager(String id, String name, String email, String phone, String password, List<Course> managedCourses) {
        super(id, name, email, phone, password, Role.MANAGER);
        this.managedCourses = managedCourses;
    }
    public Manager(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password, Role.MANAGER);
        this.managedCourses = new ArrayList<>();
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
