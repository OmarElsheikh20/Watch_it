package watchIt;

import com.example.loginpagedemo.FilterController;
import com.example.loginpagedemo.Filters;
import com.example.loginpagedemo.Filters;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Movie implements Serializable {

    private static final String FILENAME = "movies.ser";
    private static ArrayList<Movie> Movies; // Singleton list for movies


    private int Id;
    private String Title;
    private LocalDate ReleaseDate;
    private int RunningTime;
    private String Genre;
    private String Language;
    private String Country;
    private float Budget;
    private float Revenue;
    private float Rating;
    private int Views;
    private String PosterSrc;

    private ArrayList<Actor> Actors;
    private Director Director;

    private float OldRatings;
    private int noofwatched;


    public ArrayList<Actor> getActors() {
        return Actors;
    }
    public void setActors(ArrayList<Actor> cast) {
        Actors = cast;
    }
    public void addActor(Actor actor) {
        if (!Actors.contains(actor)) {
            Actors.add(actor);
        }
    }

    public Director getDirector() {
        return Director;
    }
    public void setDirector(Director director) {
        Director = director;
    }

    public String getPosterSrc() {
        return PosterSrc;
    }
    public void setPosterSrc(String posterSrc) {
        PosterSrc = posterSrc;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }

    public LocalDate getReleaseDate() {
        return ReleaseDate;
    }
    public void setReleaseDate(LocalDate releaseDate) {
        ReleaseDate = releaseDate;
    }

    public int getRunningTime() {
        return RunningTime;
    }
    public void setRunningTime(int runningTime) {
        RunningTime = runningTime;
    }

    public String getGenre() {
        return Genre;
    }
    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getLanguage() {
        return Language;
    }
    public void setLanguage(String languages) {
        Language = languages;
    }

    public String getCountry() {
        return Country;
    }
    public void setCountry(String country) {
        Country = country;
    }

    public float getBudget() {
        return Budget;
    }
    public void setBudget(float budget) {
        Budget = budget;
    }

    public float getRevenue() {
        return Revenue;
    }
    public void setRevenue(float revenue) {
        Revenue = revenue;
    }

    public float getRating() {
        return Rating;
    }
    public void setRating(float rating) {
        Rating = rating;
    }

    public int getViews() {
        return Views;
    }
    public void setViews(int views) {
        Views = views;
    }

    private static int count = 1;
    public Movie(String title, String genre, int runningTime, float budget, String country, String language, LocalDate releaseDate, float revenue, int views , Director director , ArrayList<Actor> actors , String posterSrc ) {
        Id = count++;
        Title = title;
        Genre = genre;
        RunningTime = runningTime;
        Budget = budget;
        Country = country;
        Language = language;
        ReleaseDate = releaseDate;
        Revenue = revenue;
        Views = views;
        Director = director;
        Actors = actors;
        PosterSrc = posterSrc;
    }
    public Movie() {
        Id = count++;
    }
    public Movie(String title, LocalDate releaseDate, int runningTime, String languages, String genre, String country, float budget) {
        Id = count++;
        Title = title;
        ReleaseDate = releaseDate;
        RunningTime = runningTime;
        Language = languages;
        Genre = genre;
        Country = country;
        Budget = budget;
        Revenue = 0;
        Rating = 0;
        Views = 0;
    }
    public Movie(int id, String title, String genre, int runningTime, float budget, String country, String language, LocalDate releaseDate, float revenue, int views) {
        Id = id;
        Title = title;
        Genre = genre;
        RunningTime = runningTime;
        Budget = budget;
        Country = country;
        Language = language;
        ReleaseDate = releaseDate;
        Revenue = revenue;
        Views = views;
    }
        public Movie(String title, String genre, int runningTime, float budget, String country, String language, LocalDate releaseDate ,String posterSrc, Director director , ArrayList<Actor> actors ) {
        Id = count++;
        Title = title;
        Genre = genre;
        RunningTime = runningTime;
        Budget = budget;
        Country = country;
        Language = language;
        ReleaseDate = releaseDate;
        Director = director;
        Actors = actors;
        PosterSrc = posterSrc;
    }
    public Movie(String title, String genre, int runningTime, float budget, String country, String language, LocalDate releaseDate ,String posterSrc, Director director) {
        Id = count++;
        Title = title;
        Genre = genre;
        RunningTime = runningTime;
        Budget = budget;
        Country = country;
        Language = language;
        ReleaseDate = releaseDate;
        Director = director;
        Actors = new ArrayList<>();
        PosterSrc = posterSrc;
    }

    public static ArrayList<Movie> getAllMovies() {
        if (Movies == null) {
            Movies = LoadMovieFromFile();
        }
        return Movies;
    }
    private static ArrayList<Movie> LoadMovieFromFile() {
        ArrayList<Movie> movies = new ArrayList<>();
        File file = new File(FILENAME);

        if (!file.exists() || !(file.length() > 0)) {
            System.out.println("file is empty or doesn't exist");
            return movies; // Return empty list
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            movies = (ArrayList<Movie>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error while loading movies: " + e.getMessage());
        }
        return movies;
    }

    public static void SaveMoviesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(Movies);
        } catch (IOException e) {
            System.err.println("Error saving movies: " + e.getMessage());
        }
    }

    public static Movie Find(String movieTitle) {
        ArrayList<Movie> Movies = getAllMovies();

        for (Movie movie : Movies) {
            if (movie.getTitle().equalsIgnoreCase(movieTitle)) {
                return movie;
            }
        }
        return null;
    }
    public static Boolean IsMovieExist(String MovieTitle) {
        Movie movie = Movie.Find(MovieTitle);
        return (movie != null);

    }
    public static void  AssignMovieToDirector(Movie movie,Director director){

        if(!director.IsMovieAssigned(movie)) {
            director.getListOfMovies().add(movie);
        }

    }
    public static void  AssignMovieToActor(Movie movie,Actor actor){

        if(!actor.IsMovieAssigned(movie)) {
            actor.getListOfMovies().add(movie);
        }

    }
    public static void  AssignMovieToActors(Movie movie,ArrayList<Actor> actors){

        for(Actor actor:actors){

            AssignMovieToActor(movie,actor);

        }

    }
    public static ArrayList<Movie> FindByCast(String castName)
    {
        ArrayList<Movie> Movies = getAllMovies();
        ArrayList<Movie> CastMovies = new ArrayList<>();

        for (Movie movie : Movies)
        {

            if(movie.getDirector()._FirstName.equalsIgnoreCase(castName))
            {
                CastMovies.add(movie);
            }

            for (Actor actor : movie.getActors())
            {
                if(actor._FirstName.equalsIgnoreCase(castName))
                {
                    CastMovies.add(movie);
                    break;
                }
            }
        }
        return CastMovies;
    }
    public static boolean AddNewMovie(Movie movie) {

        ArrayList<Movie> Movies = getAllMovies();
        if(IsMovieExist(movie.getTitle())) {
            return false;
        }

        AssignMovieToDirector(movie,movie.Director);
        AssignMovieToActors(movie,movie.Actors);
        Movies.add(movie);
        SaveMoviesToFile();
        return true;
    }
    public static void AddNewMovies(List<Movie> moviesToAdd) {
        ArrayList<Movie> AllMovies = getAllMovies();
        AllMovies.addAll(moviesToAdd);
        SaveMoviesToFile();
    }
    public static Boolean DeleteMovie(Movie movie) {
        ArrayList<Movie> Movies = getAllMovies();
        if (IsMovieExist(movie.getTitle())) {
            Movies.remove(movie);
            Movie.SaveMoviesToFile();
            return true;
        } else
            return false;
    }

    public static ArrayList<Movie> getMostViewedMovie(int limit) {

        ArrayList<Movie> Movies = getAllMovies();
        ArrayList<Movie> TopFiveMovies = new ArrayList<>();

        if (Movies == null || Movies.isEmpty()) {
            System.out.println("No movies available to process.");
            return TopFiveMovies;
        }

        List<Movie> sortedMovies = Movies.stream()
                .sorted(Comparator.comparingInt(Movie::getViews).reversed())
                .toList();

        for (int i = 0; i < Math.min(limit, sortedMovies.size()); i++) {
            TopFiveMovies.add(sortedMovies.get(i));
        }

        return TopFiveMovies;
    }
    public static ArrayList<Movie> getRecentMovies() {
        ArrayList<Movie> movies = getAllMovies();

//        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
//
//        for (Movie movie : movies) {
//            if (movie.getReleaseDate().isAfter(oneMonthAgo)) {
//                recentMovies.add(movie);
//            }
//        }

        movies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return m2.getReleaseDate().compareTo(m1.getReleaseDate());
            }
        });

        return movies;

    }
    public static List<Movie> getTopRatedMovies(int limit) {
        List<Movie> movies = getAllMovies();
        List<Movie> topRatedMovies = new ArrayList<>(movies);

        // Sort movies by rating in descending order
        topRatedMovies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return Float.compare(m2.getRating(), m1.getRating());
            }
        });

        if (topRatedMovies.size() > limit) {
            return topRatedMovies.subList(0, limit);
        }

        return topRatedMovies;
    }
    public static ArrayList<Movie> SameGenreMovies(String genre) {
        ArrayList<Movie> Movies = getAllMovies();
        ArrayList<Movie> SameGenre = new ArrayList<>();

        for (Movie movie : Movies) {
            if (movie.getGenre().contains(genre)) {
                SameGenre.add(movie);
            }
        }
        return SameGenre;
    }

    public void CalcRating(int rating) {
        noofwatched++;
        OldRatings += rating;
        this.Rating = OldRatings / noofwatched;

        SaveMoviesToFile();
    }
    public void IncrementViewsByOne() {
        this.Views ++;
        SaveMoviesToFile();
    }

    public static HashSet<Movie> Filter(String word) { // word : movie name or actor name or genre

        ArrayList<Movie> AllMovies = LoadMovieFromFile();
        HashSet<Movie> FilteredMovies = new HashSet<>();
        for (Movie movie : AllMovies)
        {
            if (movie.getTitle().toLowerCase().contains(word.toLowerCase()) || movie.getGenre().toLowerCase().contains(word.toLowerCase()))
                FilteredMovies.add(movie);
            if(movie.getActors() != null)
            {
                for (Actor actor : movie.getActors()) {
                    if (actor.getFullName().contains(word))
                        FilteredMovies.add(movie);
                }
            }
        }
        return FilteredMovies;
    }

    public static ArrayList<Movie> CopyMovies() {
        ArrayList<Movie> FileMovies = Movie.LoadMovieFromFile();
        ArrayList<Movie> guiMovies = new ArrayList<>();

        for (Movie movie : FileMovies) {
            guiMovies.add(new Movie(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getGenre(),
                    movie.getRunningTime(),
                    movie.getBudget(),
                    movie.getCountry(),
                    movie.getLanguage(),
                    movie.getReleaseDate(),
                    movie.getRevenue(),
                    movie.getViews()
            ));


        }
        return guiMovies;
    }

    public static boolean UpdateMovie(Movie updatedMovie) {

        ArrayList<Movie> movies = getAllMovies();

        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if (movie.getTitle().equalsIgnoreCase(updatedMovie.getTitle())) {
                movies.set(i, updatedMovie);
                SaveMoviesToFile();
                return true;
            }
        }
        return false;
    }

    public static void clearMoviesFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            // Write an empty list to the file
            oos.writeObject(new ArrayList<Movie>());
            System.out.println("Movies file has been cleared.");
        } catch (IOException e) {
            System.err.println("Error while clearing movies file: " + e.getMessage());
        }
    }

    public static List<Movie> FilterWithCriteria(Filters filters) {
        List<Movie> AllMovies = new ArrayList<>(getAllMovies());
        HashSet<Movie> filteredMovies = new HashSet<>();

        for (Movie movie : AllMovies) {
            boolean matches = true;


            if (filters.getDuration() != null) { // Apply Duration Filter
                switch (filters.getDuration()) {
                    case LESS_THAN_HOUR -> {
                        if (!(movie.getRunningTime() < 60)) {
                            matches = false;
                        }
                    }
                    case LESS_THAN_TWO_HOURS -> {
                        if (!(movie.getRunningTime() >= 60 && movie.getRunningTime() < 120)) {
                            matches = false;
                        }
                    }
                    case MORE_THAN_TWO_HOURS -> {
                        if (!(movie.getRunningTime() >= 120)) {
                            matches = false;
                        }
                    }
                }
            }


            if (filters.getRating() != null) { // Apply Rating Filter
                switch (filters.getRating()) {
                    case ABOVE_3 -> {
                        if (!(movie.getRating() > 3.0f)) {
                            matches = false;
                        }
                    }
                    case ABOVE_4 -> {
                        if (!(movie.getRating() > 4.0f)) {
                            matches = false;
                        }
                    }
                }
            }

            if (filters.getLanguage() != null) { // Apply Language Filter
                switch (filters.getLanguage()) {
                    case ENGLISH -> {
                        if (!movie.getLanguage().equalsIgnoreCase("English")) {
                            matches = false;
                        }
                    }
                    case ARABIC -> {
                        if (!movie.getLanguage().equalsIgnoreCase("Arabic")) {  // If language is not Arabic, exclude it
                            matches = false;
                        }
                    }
                    case OTHERS -> {
                        if (movie.getLanguage().equalsIgnoreCase("English") || movie.getLanguage().equalsIgnoreCase("Arabic")) {
                            matches = false;
                        }
                    }
                }
            }
            if (matches) {
                filteredMovies.add(movie);
            }
        }

        return new ArrayList<>(filteredMovies);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie movie = (Movie) obj;
        return Id == movie.Id;
    }
}
