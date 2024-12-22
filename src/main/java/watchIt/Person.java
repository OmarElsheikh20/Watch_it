package watchIt;

import java.io.Serializable;
import java.time.LocalDate;

abstract public class Person implements Serializable {

    public enum enGender {Male, Female}

    protected String _FirstName, _LastName, _Nationality;
    protected LocalDate _DateOfBirth;
    protected enGender _Gender;

    public Person(String FirstName, String LastName, LocalDate DateOfBirth, String Nationality, enGender Gender) {
        this._FirstName = FirstName;
        this._DateOfBirth = DateOfBirth;
        this._Nationality = Nationality;
        this._Gender = Gender;
        this._LastName = LastName;
    }

    public Person() {
    }

    public String getFullName() {
        return _FirstName + " " + _LastName;
    }

    public void set_LastName(String _LastName) {
        this._LastName = _LastName;
    }
    public void set_FirstName(String _FirstName) {
        this._FirstName = _FirstName;
    }

    public void set_Gender(enGender _Gender) {
        this._Gender = _Gender;
    }
    public void set_Nationality(String _Nationality) {
        this._Nationality = _Nationality;
    }
    public void set_DateOfBirth(LocalDate _DateOfBirth) {
        this._DateOfBirth = _DateOfBirth;
    }

    public String get_FirstName() {
        return _FirstName;
    }
    public String get_LastName() {
        return _LastName;
    }

    public enGender get_Gender() {
        return _Gender;
    }
    public LocalDate get_DateOfBirth() {
        return _DateOfBirth;
    }
    public String get_Nationality() {
        return _Nationality;
    }

}