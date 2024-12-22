package forResearcher;

import abstractt.User;
import ENUMS.FormatType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    public String getTitle() {
        return title;
    }

    public List<User> getAuthors() {
        return authors;
    }

    // Method to format the publication date
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  // Customize the format as needed
        return sdf.format(date);
    }

    // Method to generate citation based on the format
    public String getCitation(FormatType f) {
        switch (f) {
            case FormatType.PLAIN_TEXT:
                return getPlainTextCitation();
            case FormatType.BIBTEX:
                return getBibtexCitation();
            default:
                return "";
        }
    }

    // Method to generate citation in plain text
    private String getPlainTextCitation() {
        StringBuilder authorsList = new StringBuilder();
        for (User author : authors) {
            if (authorsList.length() > 0) {
                authorsList.append(", ");
            }
            authorsList.append(author.getName());  // Assuming User has a method getName()
        }

        return String.format("Title: %s\nAuthors: %s\nJournal: %s\nDOI: %s\nPublished: %s\nCitations: %d\nPages: %d",
                title, authorsList.toString(), journal, doi, formatDate(publicationDate), citations, pages);
    }

    // Method to generate citation in Bibtex format
    private String getBibtexCitation() {
        StringBuilder authorsList = new StringBuilder();
        for (User author : authors) {
            if (authorsList.length() > 0) {
                authorsList.append(", ");
            }
            authorsList.append(author.getName());  // Assuming User has a method getName()
        }

        return String.format(
                "@article{%s,\n  author = {%s},\n  title = {%s},\n  journal = {%s},\n  doi = {%s},\n  year = {%d},\n  citations = {%d}\n}",
                doi, authorsList.toString(), title, journal, doi, publicationDate.getYear() + 1900, citations);
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Published: %s, Citations: %d, Pages: %d", title, formatDate(publicationDate), citations, pages);
    }

    public String getJournal() {
        return journal;
    }

    public String getDoi() {
        return doi;
    }
}
