package managers;
import ENUMS.Language;
import customInterfaces.markUpdater;
import serialization.SerializationUtil;
import studyingProcess.Course;
import users.Student;
import users.Teacher;
import java.io.*;
import java.util.*;

public class MarkJournal implements markUpdater {

    private List<Course> courses;

    private static final String COURSE_FILE = "course.txt";
    private static final String ATTESTATION_MARKS_FILE = "marks.txt";

    public MarkJournal() {
        loadCourses();
    }

    public void loadCourses(){
        this.courses = loadCoursesFromFile();
        System.out.println(this.courses);

    }

    public void chooseCourse(Scanner scanner, Language language) {
        switch (language) {
            case ENGLISH:
                System.out.println("=== Choose course to put marks ===");
                break;
            case RUSSIAN:
                System.out.println("=== Выберите курс для выставления оценок ===");
                break;
            case KAZAKH:
                System.out.println("=== Бағаларды қою үшін курсты таңдаңыз ===");
                break;
        }

        int index = 0;

        for (Course course : courses) {
            System.out.println(index + ". " + course.getName());
            index++;
        }

        int courseIndex = scanner.nextInt();
        Course course = courses.get(courseIndex);
        scanner.nextLine();

        switch (language) {
            case ENGLISH:
                System.out.println("Current instructor for this course: " + course.getInstructor().getName());
                break;
            case RUSSIAN:
                System.out.println("Текущий преподаватель этого курса: " + course.getInstructor().getName());
                break;
            case KAZAKH:
                System.out.println("Осы курстың ағымдағы оқытушысы: " + course.getInstructor().getName());
                break;
        }


        List<Student> students = course.getEnrolledStudents();

        switch (language) {
            case ENGLISH:
                System.out.println("=== Choose student to put marks ===");
                break;
            case RUSSIAN:
                System.out.println("=== Выберите студента для выставления оценок ===");
                break;
            case KAZAKH:
                System.out.println("=== Баға қою үшін студентті таңдаңыз ===");
                break;
        }

        int indexStudent = 0;
        for (Student student: students){
            System.out.println(indexStudent + ". " + student.getName());
            indexStudent++;
        }
        int studentIndex = scanner.nextInt();
        scanner.nextLine();
        Student chosenStudent = students.get(studentIndex);
        String chosenStudentId = chosenStudent.getUserId();
        switch (language) {
            case ENGLISH:
                System.out.println("What attestation do you want to evaluate?");
                break;
            case RUSSIAN:
                System.out.println("Какую аттестацию вы хотите оценить?");
                break;
            case KAZAKH:
                System.out.println("Қай аттестацияны бағалағыңыз келеді?");
                break;
        }


        int attestation1 = 0;
        int attestation2 = 0;
        int finalMark = 0;
        boolean attestationBool1 = true;
        boolean attestationBool2 = true;
        boolean finalAtt = true;
        Map<String, Map<String, Integer>> attestationMarks = new HashMap<>();


        while (attestationBool1 && attestationBool2 && attestationBool1) {
            if (attestationBool1) {
                System.out.println("1. Attestation 1");
            }
            if (attestationBool2) {
                System.out.println("2. Attestation 2");
            }
            if (finalAtt) {
                System.out.println("3. Final");
            }
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter attestation 1: ");
                    attestation1 = scanner.nextInt();
                    attestationMarks.computeIfAbsent(chosenStudentId, k -> new HashMap<>())
                            .put("attestation1", attestation1);
                    attestationBool1 = false;
                case 2:
                    System.out.println("Enter attestation 2: ");
                    attestation2 = scanner.nextInt();
                    attestationMarks.computeIfAbsent(chosenStudentId, k -> new HashMap<>())
                            .put("attestation2", attestation2);
                    attestationBool2 = false;
                case 3:
                    System.out.println("Enter final");
                    finalMark = scanner.nextInt();
                    attestationMarks.computeIfAbsent(chosenStudentId, k -> new HashMap<>())
                            .put("finalPoints", finalMark);
                    finalAtt = false;
            }
        }
        for (Map.Entry<String, Map<String, Integer>> entry : attestationMarks.entrySet()) {
            String studentId = entry.getKey();
            System.out.println("Student: " + studentId);
            Map<String, Integer> marks = entry.getValue();
            for (Map.Entry<String, Integer> mark : marks.entrySet()) {
                System.out.println(mark.getKey() + ": " + mark.getValue());
            }
        }
        saveMarksToFile(attestationMarks);
    }

    public void saveMarksToFile(Map<String, Map<String, Integer>> attestationMarks) {
        try (FileOutputStream fileOut = new FileOutputStream(ATTESTATION_MARKS_FILE)) {
            SerializationUtil.serializeObject(attestationMarks, fileOut);
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }
    public Map<String, Map<String, Integer>> loadAllMarks() {
        try (FileInputStream fileIn = new FileInputStream(ATTESTATION_MARKS_FILE)) {
            Map<String, Map<String, Integer>> attestationMarks = (Map<String, Map<String, Integer>>) SerializationUtil.deserializeObject(fileIn);
            return attestationMarks;
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
            return null;
        }
    }

    public void viewAllMarks(Language language) {
        switch (language) {
            case ENGLISH:
                System.out.println("What attestation do you want to evaluate?");
                break;
            case RUSSIAN:
                System.out.println("Какую аттестацию вы хотите оценить?");
                break;
            case KAZAKH:
                System.out.println("Қай аттестацияны бағалағыңыз келеді?");
                break;
        }

        Map<String, Map<String, Integer>> attestationMarks = loadAllMarks();
        if (attestationMarks == null) {
            switch (language) {
                case ENGLISH:
                    System.out.println("No marks loaded");
                    break;
                case RUSSIAN:
                    System.out.println("Оценки не загружены");
                    break;
                case KAZAKH:
                    System.out.println("Бағалар жүктелмеді");
                    break;
            }

        }
        else{
            for(Map.Entry<String, Map<String, Integer>> entry : attestationMarks.entrySet()) {
                System.out.println("ID: " + entry.getKey());
                System.out.println("Attestation 1: " + entry.getValue().get("attestation1"));
                System.out.println("Attestation 2: " + entry.getValue().get("attestation2"));
                System.out.println("Final points: " + entry.getValue().get("finalPoints"));
            }
        }
    }
    public void generateSimpleReport(Language language) {
        System.out.println("=== Generate simple report ===");
        Map<String, Integer> markSum = new HashMap<>();
        Map<String, Map<String, Integer>> attestationMarks = loadAllMarks();
        for (Map.Entry<String, Map<String, Integer>> entry : attestationMarks.entrySet()) {
            markSum.put(entry.getKey(),
                    entry.getValue().get("attestation1") +
                            entry.getValue().get("attestation2") +
                            entry.getValue().get("finalPoints"));
        }

        // Find the best and worst students
        String bestStudent = null;
        String worstStudent = null;
        int maxMarks = Integer.MIN_VALUE;
        int minMarks = Integer.MAX_VALUE;
        int totalMarks = 0;

        for (Map.Entry<String, Integer> entry : markSum.entrySet()) {
            int marks = entry.getValue();
            totalMarks += marks;

            if (marks > maxMarks) {
                maxMarks = marks;
                bestStudent = entry.getKey();
            }

            if (marks < minMarks) {
                minMarks = marks;
                worstStudent = entry.getKey();
            }
        }

        // Calculate average mark
        double averageMark = totalMarks / (double) markSum.size();

        switch (language) {
            case ENGLISH:
                System.out.println("The best student on course: " + bestStudent);
                System.out.println("Highest marks: " + maxMarks);
                System.out.println("The worst student on course: " + worstStudent);
                System.out.println("Lowest marks: " + minMarks);
                System.out.println("Average marks: " + averageMark);
                break;
            case RUSSIAN:
                System.out.println("Лучший студент на курсе: " + bestStudent);
                System.out.println("Максимальные оценки: " + maxMarks);
                System.out.println("Худший студент на курсе: " + worstStudent);
                System.out.println("Минимальные оценки: " + minMarks);
                System.out.println("Средний балл: " + averageMark);
                break;
            case KAZAKH:
                System.out.println("Курс бойынша ең жақсы студент: " + bestStudent);
                System.out.println("Ең жоғары балл: " + maxMarks);
                System.out.println("Курс бойынша ең нашар студент: " + worstStudent);
                System.out.println("Ең төмен балл: " + minMarks);
                System.out.println("Орташа балл: " + averageMark);
                break;
        }

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
