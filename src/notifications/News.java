package notifications;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import users.*;

public class News implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 1;
    private final String newsId;
    private final String content;
    private final User sender;
    private final Date timestamp;

    // HashMap to store comments: key is userId, value is list of comments by that user
    private final HashMap<String, List<Comment>> comments;

    public News(String content, User sender, Date timestamp) {
        this.newsId = "news_" + idCounter++;
        this.content = content;
        this.sender = sender;
        this.timestamp = timestamp;
        this.comments = new HashMap<>();
    }

    public String getNewsId() {
        return newsId;
    }

    public String getContent() {
        return content;
    }

    public User getSender() {
        return sender;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    // Getter for comments
    public HashMap<String, List<Comment>> getComments() {
        return comments;
    }

    // Method to add a comment
    public void addComment(User user, String commentContent) {
        Comment comment = new Comment(user, commentContent, new Date());
        comments.putIfAbsent(user.getUserId(), new ArrayList<>());
        comments.get(user.getUserId()).add(comment);
    }
}
