package Demo;

import forResearcher.ResearchPaper;
import users.*;
import ENUMS.*;

import java.util.Date;
import java.util.List;

public class GraduateStudentDemo {
    public static void main(String[] args) {
        // Создание магистранта
        GraduateStudent gradStudent = new GraduateStudent(
                "GS001",
                "Alice Smith",
                "alice.smith@example.com",
                "555-1234",
                "password",
                DegreeType.MASTER,
                Language.RUSSIAN,
                "math",
                "ads",
                2.0
        );

        // Создание исследователя-наставника
        Researcher researcher = new Researcher(
                "RS001",
                "Dr. John Doe",
                "john.doe@example.com",
                "555-5678",
                "password",
                Language.ENGLISH
        );

        // Назначение наставника
        gradStudent.addResearchSupervisor(researcher);

        // Проверка текущего наставника
        System.out.println("Текущий исследователь-наставник: " + gradStudent.viewResearchSupervisor().getName());

        // Создание и добавление исследовательских работ
        ResearchPaper paper1 = new ResearchPaper(
                "AI in Education",
                List.of(researcher),
                "Journal of AI",
                "10.1234/ai.education",
                new Date(2024 - 1900, 0, 1),
                50,
                10
        );

        gradStudent.submitResearchPaper(paper1);

        // Вывод всех исследовательских работ
        System.out.println("\nСписок исследовательских работ:");
        gradStudent.getResearchPapers().forEach(System.out::println);
    }
}
