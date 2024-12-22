package organizations;

import ENUMS.Language;
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
    public void createNewsPost(User sender, String newsContent, Language language) {
        String senderId = sender.getUserId();
        String newsId = UUID.randomUUID().toString(); // Generate unique newsId

        News news = new News(newsId, newsContent, sender, new Date());

        // Deserialize existing news posts
        deserializeNewsPosts(NEWS_FILE, language);

        // Add the news post
        newsPosts.put(newsId, news);
        switch (language) {
            case ENGLISH:
                System.out.println("News posted by " + senderId + ": " + newsContent);
                break;
            case RUSSIAN:
                System.out.println("Новости, опубликованные " + senderId + ": " + newsContent);
                break;
            case KAZAKH:
                System.out.println("Жаңалықтарды жариялаған " + senderId + ": " + newsContent);
                break;
        }


        // Serialize the news posts back to the file
        serializeNewsPosts(NEWS_FILE);
    }

    public static void leaveComment(Scanner scanner, User publisher, NewsOffice newsOffice, Language language) {
        newsOffice.deserializeNewsPosts("news.txt", language);

        // Display available news
        switch (language) {
            case ENGLISH:
                System.out.println("Available News:");
                break;
            case RUSSIAN:
                System.out.println("Доступные новости:");
                break;
            case KAZAKH:
                System.out.println("Қол жетімді жаңалықтар:");
                break;
        }

        for (Map.Entry<String, News> entry : newsOffice.getNewsPosts().entrySet()) {
            News news = entry.getValue();
            switch (language) {
                case ENGLISH:
                    System.out.println("ID: " + news.getNewsId());
                    System.out.println("Content: " + news.getContent());
                    System.out.println("Posted by: " + news.getSender().getUserId());
                    System.out.println("Date: " + news.getDatePosted());
                    System.out.println("Comments: " + news.getComments());
                    System.out.println();
                    break;
                case RUSSIAN:
                    System.out.println("ID: " + news.getNewsId());
                    System.out.println("Содержание: " + news.getContent());
                    System.out.println("Опубликовано: " + news.getSender().getUserId());
                    System.out.println("Дата: " + news.getDatePosted());
                    System.out.println("Комментарии: " + news.getComments());
                    System.out.println();
                    break;
                case KAZAKH:
                    System.out.println("ID: " + news.getNewsId());
                    System.out.println("Мазмұны: " + news.getContent());
                    System.out.println("Жіберген: " + news.getSender().getUserId());
                    System.out.println("Күні: " + news.getDatePosted());
                    System.out.println("Пікірлер: " + news.getComments());
                    System.out.println();
                    break;
            }

        }

        // Prompt user to select a news item
        switch (language) {
            case ENGLISH:
                System.out.println("Enter the ID of the news you want to comment on:");
                break;
            case RUSSIAN:
                System.out.println("Введите ID новости, на которую хотите оставить комментарий:");
                break;
            case KAZAKH:
                System.out.println("Пікір қалдырғыңыз келетін жаңалықтың ID-ын енгізіңіз:");
                break;
        }

        String newsId = scanner.next();

        // Prompt user to enter a comment
        switch (language) {
            case ENGLISH:
                System.out.println("Enter your comment:");
                break;
            case RUSSIAN:
                System.out.println("Введите ваш комментарий:");
                break;
            case KAZAKH:
                System.out.println("Сіздің пікіріңізді енгізіңіз:");
                break;
        }

        scanner.nextLine(); // Consume the newline
        String comment = scanner.nextLine();

        // Add the comment
        newsOffice.addCommentToNews(newsId,publisher, comment, language);

        // Save the updated news posts
        newsOffice.serializeNewsPosts("news.txt");
    }

    public void addCommentToNews(String newsId,User commentFrom, String comment, Language language) {
        if (newsPosts.containsKey(newsId)) {
            News news = newsPosts.get(newsId);
            news.addComment(commentFrom, comment);
            switch (language) {
                case ENGLISH:
                    System.out.println("Comment added successfully!");
                    break;
                case RUSSIAN:
                    System.out.println("Комментарий успешно добавлен!");
                    break;
                case KAZAKH:
                    System.out.println("Пікір сәтті қосылды!");
                    break;
            }

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
    public void deserializeNewsPosts(String filePath, Language language) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            newsPosts = (HashMap<String, News>) ois.readObject();

            // Iterate through the news posts and print their content
            for (Map.Entry<String, News> entry : newsPosts.entrySet()) {
                News news = entry.getValue();
                switch (language) {
                    case ENGLISH:
                        System.out.println("News ID: " + news.getNewsId());
                        System.out.println("Content: " + news.getContent());
                        System.out.println("Posted by: " + news.getSender().getUserId());
                        System.out.println("Date: " + news.getDatePosted());
                        System.out.println("Comments:");
                        break;
                    case RUSSIAN:
                        System.out.println("ID новости: " + news.getNewsId());
                        System.out.println("Содержание: " + news.getContent());
                        System.out.println("Опубликовал: " + news.getSender().getUserId());
                        System.out.println("Дата: " + news.getDatePosted());
                        System.out.println("Комментарии:");
                        break;
                    case KAZAKH:
                        System.out.println("Жаңалық ID: " + news.getNewsId());
                        System.out.println("Мазмұны: " + news.getContent());
                        System.out.println("Жариялаған: " + news.getSender().getUserId());
                        System.out.println("Күні: " + news.getDatePosted());
                        System.out.println("Пікірлер:");
                        break;
                }

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
