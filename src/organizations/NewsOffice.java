package organizations;

import abstractt.User;
import notifications.*;

import java.io.*;
import java.util.*;

public class NewsOffice implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String NEWS_FILE = "news.txt";
    private HashMap<String, HashMap<String, List<Comment>>> newsPosts;  // newsId -> userId -> comments

    public NewsOffice() {
        this.newsPosts = new HashMap<>();
    }

    // Method to create a news post
    public void createNewsPost(User sender, String newsContent) {
        String senderId = sender.getUserId();

        News news = new News(newsContent, sender, new Date());

        // Deserialize existing news posts
        deserializeNews(NEWS_FILE);

        // Add the news post
        newsPosts.putIfAbsent(news.getNewsId(), new HashMap<>());
        System.out.println("News posted by " + senderId + ": " + news.getContent());

        // Serialize the news posts back to the file
        serializeNews(NEWS_FILE);
    }

    // Method to leave a comment under a news post
    public void leaveComment(User sender, String newsId, String commentContent) {
        String senderId = sender.getUserId();

        Comment comment = new Comment(sender, commentContent, new Date());

        // Deserialize existing news posts
        deserializeNews(NEWS_FILE);

        // Add the comment to the specific news post
        newsPosts.putIfAbsent(newsId, new HashMap<>());
        HashMap<String, List<Comment>> userComments = newsPosts.get(newsId);
        userComments.putIfAbsent(senderId, new ArrayList<>());
        userComments.get(senderId).add(comment);

        System.out.println("Comment posted on news " + newsId + " by " + senderId + ": " + comment.getContent());

        // Serialize the news posts with comments back to the file
        serializeNews(NEWS_FILE);
    }

    // Serialize the news posts to a file
    public void serializeNews(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(newsPosts);
            System.out.println("News and comments serialized to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error serializing news and comments: " + e.getMessage());
        }
    }

    // Deserialize the news posts and comments from a file
    @SuppressWarnings("unchecked")
    public void deserializeNews(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            newsPosts = (HashMap<String, HashMap<String, List<Comment>>>) ois.readObject();

            // Iterate through the news posts and comments and print them
            for (Map.Entry<String, HashMap<String, List<Comment>>> entry : newsPosts.entrySet()) {
                String newsId = entry.getKey();
                HashMap<String, List<Comment>> userComments = entry.getValue();

                System.out.println("News ID: " + newsId);
                System.out.println("Content: " + newsPosts.get(newsId));
                for (Map.Entry<String, List<Comment>> userEntry : userComments.entrySet()) {
                    String userId = userEntry.getKey();
                    List<Comment> comments = userEntry.getValue();

                    System.out.println("\tComments by user: " + userId);
                    for (Comment comment : comments) {
                        System.out.println("\t\t" + comment.getContent());
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // In case of deserialization error, initialize an empty structure
            newsPosts = new HashMap<>();
            System.err.println("Error deserializing news and comments: " + e.getMessage());
        }
    }

    public HashMap<String, HashMap<String, List<Comment>>> getNewsPosts() {
        return newsPosts;
    }
}
