package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import studyingProcess.Course;
import ENUMS.Language;
import ENUMS.Role;

public class Teacher extends User implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private List<Course> coursesTaught;
    private String academicTitle;
    private int yearsOfExperience;

    public Teacher(String userId, String name, String email, String phoneNumber,String password, Language preferredLanguage,
                   String academicTitle, int yearsOfExperience) {
        super(userId, name, email, phoneNumber,password, Role.TEACHER, preferredLanguage);
        this.academicTitle = academicTitle;
        this.yearsOfExperience = yearsOfExperience;
        this.coursesTaught = new ArrayList<>();
    }

    public void addCourse(Course course) {
        coursesTaught.add(course);
    }

    public List<Course> getCoursesTaught() {
        return coursesTaught;
    }

    @Override
    public String toString() {
        return String.format("Teacher{id='%s', title='%s', experience=%d years, courses=%s}",
                userId, academicTitle, yearsOfExperience, coursesTaught);
    }
    
    public List<Course> getCourses() { 
        return coursesTaught;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return yearsOfExperience == teacher.yearsOfExperience &&
                Objects.equals(academicTitle, teacher.academicTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), academicTitle, yearsOfExperience);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Teacher cloned = (Teacher) super.clone();
        cloned.coursesTaught = new ArrayList<>(coursesTaught);
        return cloned;
    }
}
