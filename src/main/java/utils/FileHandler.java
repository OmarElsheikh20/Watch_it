package utils;

import java.io.*;
import java.util.List;

public class FileHandler {

    private String fileName;

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    public <T> void writeToFile( List<T> objects) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(objects);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public <T> List<T> readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return null;
    }
}
