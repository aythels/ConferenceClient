package adapters;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.util.HashMap;
import java.util.Optional;

public class AbstractRepository {

    public Optional<Object> get(File store) {
        try {
            FileInputStream fileIn = new FileInputStream(store);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            Object obj = objIn.readObject();
            objIn.close();
            fileIn.close();
            return Optional.of(obj);
        } catch (ClassNotFoundException | IOException e) {
            return Optional.empty();
        }
    }

    public void put(File store, Object data) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(store);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(data);
        objOut.close();
        fileOut.close();
    }
}
