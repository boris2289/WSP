package notifications;
import abstractt.User;
import java.util.Date;

public class Comment {
    private User author;
    private String content;
    private Date timeStamp;

    public Comment(User author, String content, Date timeStamp) {
        this.author = author;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "author=" + author +
                ", content='" + content + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }

    public String getContent() {
        return content;
    }
}