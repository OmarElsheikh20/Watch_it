package watchIt;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

public class Director extends Cast implements Serializable {

    public Director(String firstName, String lastName, LocalDate dateOfBirth, enGender gender, String nationality ) {
        super(firstName, lastName, dateOfBirth,  nationality, gender);
    }

    public  Movie FindAssignedMovie(Movie movie )
    {
        if(this.getListOfMovies()==null){

            return null;

        }else {
            for (Movie movie1 : this.getListOfMovies()) {
                if (movie1.getTitle().equalsIgnoreCase(movie.getTitle())) {
                    return movie;
                }
            }
        }
        return null;
    }

    public  boolean IsMovieAssigned(Movie movie){
        return ( this.FindAssignedMovie(movie) !=null);
    }
    public Director() {
    }

    public static ArrayList<Director> LoadDirectorsFromFile()
    {
        ArrayList<Director> Directors = new ArrayList<>();
        File file = new File("Directors.txt");

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file)))
        {
            Directors = (ArrayList<Director>) objectInputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        return Directors;
    }

    public static void SaveDirctorsToFile(ArrayList<Director> Directors)
    {
        File file = new File("Directors.txt");

        try (ObjectOutputStream objectOutputStream =new ObjectOutputStream(new FileOutputStream(file)))
        {
            objectOutputStream.writeObject(Directors);
        }
        catch (IOException e)
        {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }


    public static Director Find(String fullname )
    {
        ArrayList<Director> Directors = LoadDirectorsFromFile();

        for(Director director : Directors)
        {
            if(director.getFullName().equalsIgnoreCase(fullname))
            {
                return director;
            }
        }
        return null;
    }


    public static Boolean IsDirectorExist(String fullname)
    {
        Director director = Director.Find(fullname);
        return (director != null);

    }

    public static Boolean AddNewDirector(Director director)
    {
        ArrayList<Director> Directors = LoadDirectorsFromFile();

        if(IsDirectorExist(director.getFullName()))
        {
            return false;
        }
        else
        {

            Directors.add(director);
            Director.SaveDirctorsToFile(Directors);
            return true;
        }
    }

    public static Boolean DeleteDirector(Director director)
    {
        ArrayList<Director> Directors = LoadDirectorsFromFile();

        if (IsDirectorExist(director.getFullName()))
        {
            Directors.remove(director);
            Director.SaveDirctorsToFile(Directors);
            return true;
        }
        else
            return false;
    }


    @Override
    public HashSet<Movie> getListOfMovies() {
        ArrayList<Movie> allMovies = Movie.getAllMovies();
        HashSet<Movie> movies = new HashSet<>();
        for (Movie movie : allMovies) {
            if (this.getFullName().equalsIgnoreCase(movie.getDirector().getFullName())) {
                movies.add(movie);
            }
        }
        return movies;
    }
}