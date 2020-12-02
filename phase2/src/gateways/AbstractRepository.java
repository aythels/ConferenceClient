package gateways;

import java.io.*;
import java.nio.file.InvalidPathException;

public class AbstractRepository {

    public AbstractRepository(String path) throws InvalidPathException {
        File dir = new File(path);
        if (!dir.isDirectory())
            throw new InvalidPathException(path, "Given path is not a valid directory");
    }

    public Object get(File store) {
        try {
            FileInputStream fileIn = new FileInputStream(store);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            Object obj = objIn.readObject();
            objIn.close();
            fileIn.close();
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void put(File store, Object data) {
        try {
            FileOutputStream fileOut = new FileOutputStream(store);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(data);
            objOut.close();
            fileOut.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
