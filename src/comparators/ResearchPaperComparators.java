package comparators;

import studyingProcess.ResearchPaper;

import java.util.Comparator;

public class ResearchPaperComparators {

    // Comparator to sort by publication date
    public static Comparator<ResearchPaper> byDatePublished = new Comparator<ResearchPaper>() {
        @Override
        public int compare(ResearchPaper p1, ResearchPaper p2) {
            return p1.getPublicationDate().compareTo(p2.getPublicationDate());
        }
    };

    // Comparator to sort by citations (descending order)
    public static Comparator<ResearchPaper> byCitations = new Comparator<ResearchPaper>() {
        @Override
        public int compare(ResearchPaper p1, ResearchPaper p2) {
            return Integer.compare(p2.getCitations(), p1.getCitations());  // Descending order
        }
    };

    // Comparator to sort by pages (article length)
    public static Comparator<ResearchPaper> byPages = new Comparator<ResearchPaper>() {
        @Override
        public int compare(ResearchPaper p1, ResearchPaper p2) {
            return Integer.compare(p2.getPages(), p1.getPages());  // Descending order
        }
    };
}

