package comparators;
import studyingProcess.*;
import java.util.Comparator;

public class SortByPublicationDate implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper paper1, ResearchPaper paper2) {
        return paper1.getPublicationDate().compareTo(paper2.getPublicationDate());
    }
}
