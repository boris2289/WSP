package organizations;

import abstractt.User;
import notifications.*;

import java.io.*;
import java.util.*;

public class NewsOffice implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String NEWS_FILE = "news.txt";
    private HashMap<String, News> newsPosts;  // newsId -> News object

    public NewsOffice() {
        this.newsPosts = new HashMap<>();
    }

    // Method to create a news post
    public void createNewsPost(User sender, String newsContent) {
        String senderId = sender.getUserId();
        String newsId = UUID.randomUUID().toString(); // Generate unique newsId

        News news = new News(newsId, newsContent, sender, new Date());

        // Deserialize existing news posts
        deserializeNewsPosts(NEWS_FILE);

        // Add the news post
        newsPosts.put(newsId, news);
        System.out.println("News posted by " + senderId + ": " + newsContent);

        // Serialize the news posts back to the file
        serializeNewsPosts(NEWS_FILE);
    }

    public static void leaveComment(Scanner scanner, User publisher, NewsOffice newsOffice) {
        newsOffice.deserializeNewsPosts("news.txt");

        // Display available news
        System.out.println("Available News:");
        for (Map.Entry<String, News> entry : newsOffice.getNewsPosts().entrySet()) {
            News news = entry.getValue();
            System.out.println("ID: " + news.getNewsId());
            System.out.println("Content: " + news.getContent());
            System.out.println("Posted by: " + news.getSender().getUserId());
            System.out.println("Date: " + news.getDatePosted());
            System.out.println("Comments: " + news.getComments());
            System.out.println();
        }

        // Prompt user to select a news item
        System.out.println("Enter the ID of the news you want to comment on:");
        String newsId = scanner.next();

        // Prompt user to enter a comment
        System.out.println("Enter your comment:");
        scanner.nextLine(); // Consume the newline
        String comment = scanner.nextLine();

        // Add the comment
        newsOffice.addCommentToNews(newsId,publisher, comment);

        // Save the updated news posts
        newsOffice.serializeNewsPosts("news.txt");
    }

    public void addCommentToNews(String newsId,User commentFrom, String comment) {
        if (newsPosts.containsKey(newsId)) {
            News news = newsPosts.get(newsId);
            news.addComment(commentFrom, comment);
            System.out.println("Comment added successfully!");
        } else {
            System.out.println("News with ID " + newsId + " not found.");
        }
    }


    // Serialize the news posts to a file
    public void serializeNewsPosts(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(newsPosts);
            System.out.println("News and comments serialized to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error serializing news and comments: " + e.getMessage());
        }
    }


    // Deserialize the news posts and display their contents
    @SuppressWarnings("unchecked")
    public void deserializeNewsPosts(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            newsPosts = (HashMap<String, News>) ois.readObject();

            // Iterate through the news posts and print their content
            for (Map.Entry<String, News> entry : newsPosts.entrySet()) {
                News news = entry.getValue();
                System.out.println("News ID: " + news.getNewsId());
                System.out.println("Content: " + news.getContent());
                System.out.println("Posted by: " + news.getSender().getUserId());
                System.out.println("Date: " + news.getDatePosted());
                System.out.println("Comments:");
                for (Map.Entry<String, List<Comment>> entryComment : news.getComments().entrySet()) {
                    String authorId = entryComment.getKey();
                    List<Comment> comments = entryComment.getValue();
                    System.out.println("Author: " + authorId);
                    for (Comment comment : comments) {
                        System.out.println("    Content: " + comment.getContent());
                    }
                    System.out.println();
                }
                System.out.println(" ");
            }
        } catch (IOException | ClassNotFoundException e) {
            // In case of deserialization error, initialize an empty structure
            newsPosts = new HashMap<>();
            System.err.println("Error deserializing news: " + e.getMessage());
        }
    }

    public HashMap<String, News> getNewsPosts() {
        return newsPosts;
    }
}
