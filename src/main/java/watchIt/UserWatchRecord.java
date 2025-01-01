package watchIt;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class UserWatchRecord implements Serializable {

    private int userId;
    private Movie movie;
    private LocalDate watchDate;
    private Integer rating;


    private Subscription subscription;

    public UserWatchRecord(int userId, Movie movie, LocalDate watchDate, Integer rating, Subscription subscription) {
        this.userId = userId;
        this.movie = movie;
        this.watchDate = watchDate;
        this.rating = rating;
        this.subscription = subscription;
    }

    public int getUserId() {
        return userId;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDate getWatchDate() {
        return watchDate;
    }
    public void setWatchDate(LocalDate watchDate) {
        this.watchDate = watchDate;
    }

    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        if (rating == null || (rating >= 1 && rating <= 5)) {
            this.rating = rating; // Accept null or valid ratings only
        } else {
            throw new IllegalArgumentException("Rating must be between 1 and 5 or null.");
        }
    }

    public Subscription getSubscription() {
        return subscription;
    }
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public static void saveWatchRecordsToFile(List<UserWatchRecord> watchRecords) {
        File file = new File("UserWatchRecords.txt");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(watchRecords);
        } catch (IOException e) {
            System.err.println("An error occurred while saving watch records: " + e.getMessage());
        }
    }
    public static List<UserWatchRecord> loadWatchRecordsFromFile() {
        File file = new File("UserWatchRecords.txt");
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (List<UserWatchRecord>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("An error occurred while loading watch records: " + e.getMessage());
        }
        return new ArrayList<>();
    }

}
