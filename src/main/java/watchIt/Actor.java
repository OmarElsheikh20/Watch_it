package watchIt;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Actor extends Cast implements Serializable {

    public Actor() {
    }

    public Actor(String firstName, String lastName, LocalDate dateOfBirth, enGender gender, String nationality) {
        super(firstName, lastName, dateOfBirth,  nationality,gender);
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

    public static ArrayList<Actor> LoadActorsFromFile()
    {
        ArrayList<Actor> Actors = new ArrayList<>();
        File file = new File("Actors.txt");

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file)))
        {
            Actors = (ArrayList<Actor>) objectInputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        return Actors;
    }
    public static void SaveActorsToFile(ArrayList<Actor> Actors)
    {
        File file = new File("Actors.txt");

        try (ObjectOutputStream objectOutputStream =new ObjectOutputStream(new FileOutputStream(file)))
        {
            objectOutputStream.writeObject(Actors);
        }
        catch (IOException e)
        {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static Actor Find(String fullname )
    {
        ArrayList<Actor> Actors = LoadActorsFromFile();

        for(Actor actor : Actors)
        {
            if(actor.getFullName().equalsIgnoreCase(fullname))
            {
                return actor;
            }
        }
        return null;
    }


    public static Boolean IsActorExist(String fullname)
    {
        Actor actor = Actor.Find(fullname);
        return (actor != null);

    }

    public static Boolean AddNewActor(Actor actor)
    {
        ArrayList<Actor> Actors = LoadActorsFromFile();

        if(IsActorExist(actor.getFullName()))
        {
            return false;
        }
        else
        {
            Actors.add(actor);
            Actor.SaveActorsToFile(Actors);
            return true;
        }
    }

    public static Boolean DeleteActor(Actor actor)
    {
        ArrayList<Actor> Actors = LoadActorsFromFile();

        if (IsActorExist(actor.getFullName()))
        {
            Actors.remove(actor);
            Actor.SaveActorsToFile(Actors);
            return true;
        }
        else
            return false;
    }


}