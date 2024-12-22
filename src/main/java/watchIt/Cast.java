package watchIt;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Cast extends Person implements Serializable {

    private ArrayList<Movie> ListOfMovies;
    private HashMap<String, String> SocialMediaLinks;

    public Cast() {}

    public Cast(String firstName, String lastName, LocalDate dateOfBirth, Person.enGender gender,String nationality)
    {
        super(firstName,lastName, dateOfBirth,nationality,gender);
        ListOfMovies = new ArrayList<>();
        SocialMediaLinks = new HashMap<>();
    }



    public ArrayList<Movie> getListOfMovies() {
        return ListOfMovies;
    }
    public void setListOfMovies(ArrayList<Movie> listOfMovies) {
        ListOfMovies = listOfMovies;
    }

    public HashMap<String, String> getSocialMediaLinks() {
        return SocialMediaLinks;
    }
    public void setSocialMediaLinks(HashMap<String, String> socialMediaLinks) {
        SocialMediaLinks = socialMediaLinks;
    }

    public void addSocialMedia(String platform, String link) {
        this.SocialMediaLinks.put(platform, link);
    }

    public void removeSocialMedia(String platform) {
        this.SocialMediaLinks.remove(platform);
    }



    public Cast(String firstName, String lastName, LocalDate dateOfBirth, Person.enGender gender, String nationality, ArrayList<Movie> ListOfMovies)
    {
        super(firstName,lastName, dateOfBirth,nationality,gender);
        this.ListOfMovies = ListOfMovies;
        SocialMediaLinks = new HashMap<>();
    }

    public Cast(String FirstName, String LastName, LocalDate DateOfBirth, String Nationality, enGender Gender) {
        super(FirstName, LastName, DateOfBirth, Nationality, Gender);
        this.ListOfMovies = new ArrayList<>();
        SocialMediaLinks = new HashMap<>();
    }

}

