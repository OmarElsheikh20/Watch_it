package watchIt;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class User implements Serializable {

    private static final String FILENAME = "Users.txt";
    private static ArrayList<User> Users; // Singleton list for users

    private int ID;
    private String Username;
    private String Password;
    private String FirstName;
    private String LastName;
    private String Email;

    private HashSet<Movie> WatchedMovies;
    public ArrayList<Movie> WatchLaterMovies;
    private ArrayList<UserWatchRecord> userWatchRecord;

    private ArrayList<Subscription> subscriptionHistory;
    private Subscription currentSubscription;
    private int limitMovies;

    public Subscription getCurrentSubscription() {
        return currentSubscription;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFullName() {
        return FirstName + " " + LastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Subscription getSubscription() {
        return currentSubscription;
    }

    public void setSubscription(Subscription subscription) {
        this.currentSubscription = subscription;
        this.limitMovies = currentSubscription.getAllowedWatches();
    }
    public Subscription.enPlan getGuiSubscription() {return currentSubscription.getPlan();}

    public int getLimitMovies() {
        return limitMovies;
    }

    private static int counter = 1;

    public User(String username, String email, String lastName, String firstName, String password) {
        Username = username;
        this.ID = counter++;
        Email = email;
        LastName = lastName;
        FirstName = firstName;
        Password = password;

        WatchedMovies = new HashSet<>();
        WatchLaterMovies = new ArrayList<>();
        userWatchRecord = new ArrayList<>();
        subscriptionHistory = new ArrayList<>();

    }

    public static ArrayList<User> getAllUsers() {
        if (Users == null) {
            Users = LoadUsersFromFile();
        }
        return Users;
    }

    private static ArrayList<User> LoadUsersFromFile() {

        ArrayList<User> users = new ArrayList<>();
        File file = new File(FILENAME);

        if (!file.exists() || !(file.length() > 0)) {
            System.out.println("file is empty or doesn't exist");
            return users; // Return empty list
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            users = (ArrayList<User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        return users;
    }

    public static void saveUsersDataToFile() {
        File file = new File(FILENAME);

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(Users);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static User Find(String Username) throws IOException {
        ArrayList<User> users = getAllUsers();

        for (User user : users) {
            if (user.Username.equals(Username))
                return user;
        }
        return null;
    }

    public static User Find(String Username, String Password) throws IOException {
        ArrayList<User> users = getAllUsers();

        for (User user : users) {
            if (user.Username.equals(Username) && user.Password.equals(Password))
                return user;
        }
        return null;
    }

    public static User Find(int ID) throws IOException {
        ArrayList<User> users = getAllUsers();

        for (User user : users) {
            if (user.ID == ID)
                return user;
        }
        return null;
    }

    public static boolean isUserExist(String Username) throws IOException {
        User user = Find(Username);
        return (user != null);
    }

    public static boolean isUserExist(int ID) throws IOException {
        User user = Find(ID);
        return (user != null);
    }

    public static boolean isUserExist(String Username, String Password) throws IOException {
        User user = Find(Username, Password);
        return (user != null);
    }

    public static boolean AddNewUser(User newUser) throws IOException {

        ArrayList<User> users = getAllUsers();

        if (isUserExist(newUser.getUsername())) {
            return false;
        }

        users.add(newUser);
        saveUsersDataToFile();
        return true;
    }

    public static boolean Delete(int ID) throws IOException {

        ArrayList<User> users = getAllUsers();

        User user = Find(ID);

        if (user == null)
            return false;

        users.remove(user);
        saveUsersDataToFile();
        return true;
    }

    public static boolean Delete(String username) throws IOException {

        ArrayList<User> users = getAllUsers();

        User user = Find(username);

        if (user == null)
            return false;

        users.remove(user);
        saveUsersDataToFile();
        return true;
    }

    public void DecrementLimitByOne(Movie movie) {
        if (!isMovieWatchedInCurrentSubscription(movie)) {
            if (limitMovies >= 0) {
                this.limitMovies--;
            } else {
                createNewSups(null);
            }
            saveUsersDataToFile();
        }
    }

    public Boolean hasValidSups() {
        if (currentSubscription == null) return false;

        long days = Math.abs(ChronoUnit.DAYS.between(currentSubscription.getStartDate(), LocalDate.now()));
        return limitMovies > 0 && days <= 30;
    }

    public void createNewSups(Subscription subscription) {
        if (currentSubscription != null) {
            subscriptionHistory.add(currentSubscription);
        }
        currentSubscription = subscription;
        if (subscription != null)
            this.limitMovies = subscription.getAllowedWatches();
        else
            this.limitMovies = 0;
        saveUsersDataToFile();

    }

    public void WatchMovie(UserWatchRecord record) {

        for (UserWatchRecord rec : userWatchRecord) {
            if (rec.getMovie().getTitle().equalsIgnoreCase(record.getMovie().getTitle())) {
                userWatchRecord.remove(rec);
                break;
            }
        }
        userWatchRecord.add(record);
        saveUsersDataToFile();
    }

    public boolean AddToWatchLater(Movie movie) {
        if (!isExistInWatchLater(movie)) {
            this.WatchLaterMovies.add(movie);
            saveUsersDataToFile();
            return true;
        }
        return false;
    }

    private boolean isExistInWatchLater(Movie movie) {
        ArrayList<Movie> watchLater = this.WatchLaterMovies;
        for (Movie m : watchLater) {
            if (m.getTitle().equalsIgnoreCase(movie.getTitle()))
                return true;
        }
        return false;
    }

    private boolean isMovieWatchedInCurrentSubscription(Movie movie) {
        ArrayList<UserWatchRecord> history = userWatchRecord;
        for (UserWatchRecord record : history) {
            if (record.getMovie().getTitle().equalsIgnoreCase(movie.getTitle())) {
                if (record.getSubscription().equals(this.currentSubscription))
                    return true;
            }
        }
        return false;
    }

    public HashSet<Movie> getHistory() {
        HashSet<Movie> set = new HashSet<>();
        for (UserWatchRecord rec : userWatchRecord) {
            set.add(rec.getMovie());
        }
        return set;
    }

    public ArrayList<Movie> getWatchLater() {
        return WatchLaterMovies;
    }

    public HashSet<Movie> getRecommended() {
        List<Movie> allMovies = Movie.getAllMovies();
        HashSet<Movie> history = getHistory();
        HashSet<Movie> recommended = new HashSet<>();

        HashSet<String> preferredGenres = new HashSet<>();
        HashSet<Actor> preferredActors = new HashSet<>();

        for (Movie watchedMovie : history) {
            preferredGenres.add(watchedMovie.getGenre());
            preferredActors.addAll(watchedMovie.getActors());
        }

        for (Movie movie : allMovies) {

            // Skip movies already in the user's history
            if (history.contains(movie)) {
                continue;
            }

            boolean matchesGenre = preferredGenres.contains(movie.getGenre());
            boolean matchesActor = movie.getActors().stream().anyMatch(preferredActors::contains);

            if (matchesGenre || matchesActor) {
                recommended.add(movie);
            }
        }
        return recommended;
    }


    public static void clearFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            // Write an empty list to the file
            oos.writeObject(new ArrayList<Movie>());
            System.out.println("Movies file has been cleared.");
        } catch (IOException e) {
            System.err.println("Error while clearing movies file: " + e.getMessage());
        }
    }

    public ArrayList<Subscription> getSubscriptionHistory() {
        return subscriptionHistory;
    }
}