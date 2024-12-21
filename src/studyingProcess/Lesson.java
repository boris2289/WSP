package studyingProcess;

import users.Teacher;
import java.io.Serializable;

public class Lesson implements Serializable {
    private static final long serialVersionUID = 1L;

    private String lessonId;
    private String lessonType; // lecture or practice
    private String topic;
    private Teacher teacher;

    public Lesson(String lessonId, String lessonType, String topic, Teacher teacher) {
        if (!lessonType.equalsIgnoreCase("lecture") && !lessonType.equalsIgnoreCase("practice")) {
            throw new IllegalArgumentException("Invalid lesson type. Must be 'lecture' or 'practice'.");
        }
        this.lessonId = lessonId;
        this.lessonType = lessonType;
        this.topic = topic;
        this.teacher = teacher;
    }

    public String getLessonId() {
        return lessonId;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        if (!lessonType.equalsIgnoreCase("lecture") && !lessonType.equalsIgnoreCase("practice")) {
            throw new IllegalArgumentException("Invalid lesson type. Must be 'lecture' or 'practice'.");
        }
        this.lessonType = lessonType;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId='" + lessonId + '\'' +
                ", lessonType='" + lessonType + '\'' +
                ", topic='" + topic + '\'' +
                ", teacher=" + teacher.getName() +
                '}';
    }
}
