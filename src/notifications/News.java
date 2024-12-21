package notifications;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class News {
    private String title;
    private String content;
    private Date publishedDate;
    private List<Comment> comments;

    public News(String title, String content, Date publishedDate) {
        this.title = title;
        this.content = content;
        this.publishedDate = publishedDate;
        this.comments = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishedDate=" + publishedDate +
                '}';
    }
}
