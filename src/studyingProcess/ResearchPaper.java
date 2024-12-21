package studyingProcess;

import java.util.Date;
import java.util.List;
import users.*;
import ENUMS.Format;  // Assuming Format is an enum for citation formats

import java.util.Date;

public class ResearchPaper {
    private String title;
    private List<User> authors;
    private String journal;
    private String doi;
    private Date publicationDate;
    private int citations;
    private int pages;

    public ResearchPaper(String title, List<User> authors, String journal, String doi, Date publicationDate, int citations, int pages) {
        this.title = title;
        this.authors = authors;
        this.journal = journal;
        this.doi = doi;
        this.publicationDate = publicationDate;
        this.citations = citations;
        this.pages = pages;
    }

    // Getters and setters

    public int getCitations() {
        return citations;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Published: %s, Citations: %d, Pages: %d", title, publicationDate, citations, pages);
    }
}

