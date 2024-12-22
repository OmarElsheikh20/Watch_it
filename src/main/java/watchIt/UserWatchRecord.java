package watchIt;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class UserWatchRecord implements Serializable {

    private int userId;
    private Movie movie;
    private LocalDate watchDate;
    private Integer rating;

    public UserWatchRecord(int userId, Movie movie, LocalDate watchDate, Integer rating) {
        this.userId = userId;
        this.movie = movie;
        this.watchDate = watchDate;
        this.rating = rating;
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

//    public static List<Movie> getRecommendedMoviesForUser(int userID) {
//
//        Map<String, Integer> favoriteGenres = getFavoriteGenres(userID);
//        Map<String, Integer> mostWatchedActors = getMostWatchedActors(userID);
//        Set<Movie> recommendedMovies = findRecommendedMovies(favoriteGenres, mostWatchedActors);
//
//        // Convert to list and sort by release date (most recent first)
//        List<Movie> sortedRecommendations = new ArrayList<>(recommendedMovies);
//        sortMoviesByReleaseDate(sortedRecommendations);
//
//        return sortedRecommendations;
//    }
    private static Map<String, Integer> getFavoriteGenres(int userId) {
        List<UserWatchRecord> watchRecords = loadWatchRecordsFromFile();
        Map<String, Integer> genreCount = new HashMap<>();

        for (UserWatchRecord record : watchRecords) {
            if (record.getUserId() == userId) {
                String genre = record.getMovie().getGenre();
                genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
            }
        }

        return genreCount;
    }
//    private static Map<String, Integer> getMostWatchedActors(int userId) {
//        List<UserWatchRecord> watchRecords = loadWatchRecordsFromFile();
//        Map<Cast, Integer> actorCount = new HashMap<>();
//
//        for (UserWatchRecord record : watchRecords) {
//            if (record.getUserId() == userId) {
//                List<Cast> cast = record.getMovie().getActors();
//                if (cast != null) {
//                    for (Cast actor : cast) {
//                        actorCount.put(actor, actorCount.getOrDefault(actor, 0) + 1);
//                    }
//                }
//            }
//        }
//
//        return actorCount;
//    }
//    private static Set<Movie> findRecommendedMovies(Map<String, Integer> favoriteGenres, Map<String, Integer> mostWatchedActors) {
//        List<Movie> allMovies = Movie.LoadMovieFromFile();
//        Set<Movie> recommendedMovies = new HashSet<>();
//
//        for (Movie movie : allMovies) {
//            if (favoriteGenres.containsKey(movie.getGenre())) {
//                recommendedMovies.add(movie);
//            }
//
//            List<String> cast = movie.getCast();
//            if (cast != null) {
//                for (String actor : cast) {
//                    if (mostWatchedActors.containsKey(actor)) {
//                        recommendedMovies.add(movie);
//                        break; // Avoid adding the same movie multiple times
//                    }
//                }
//            }
//        }
//
//        return recommendedMovies;
//    }
//    private static void sortMoviesByReleaseDate(List<Movie> movies) {
//        movies.sort(new Comparator<Movie>() {
//            @Override
//            public int compare(Movie m1, Movie m2) {
//                return m2.getReleaseDate().compareTo(m1.getReleaseDate()); // Compare in reverse order
//            }
//        });
//    }


}

