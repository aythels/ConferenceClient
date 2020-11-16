package domain.usecases;

import java.io.*;

public class Serializer {

    public void serializeUserManager(UserManager object) {
        String filename = "UserManager.ser";
        try
        {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(object);
            out.close();
            file.close();
        }
        catch(IOException ex)
        {
            System.out.println("IOException");
        }
    }

    public void serializeMessageManager(UserManager object) {
        String filename = "MessageManager.ser";
        try
        {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(object);
            out.close();
            file.close();
        }
        catch(IOException ex)
        {
            System.out.println("IOException");
        }
    }

    public void serializeEventManager(UserManager object) {
        String filename = "EventManager.ser";
        try
        {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(object);
            out.close();
            file.close();
        }
        catch(IOException ex)
        {
            System.out.println("IOException");
        }
    }

    public void serializeHashMapManager(UserManager object) {
        String filename = "HashMapManager.ser";
        try
        {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(object);
            out.close();
            file.close();
        }
        catch(IOException ex)
        {
            System.out.println("IOException");
        }
    }

    public UserManager deserializeUserManager() {
        UserManager object = null;
        String filename = "UserManager.ser";
        try
        {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            object = (UserManager)in.readObject();
            in.close();
            file.close();
        }
        catch(IOException ex)
        {
            System.out.println("IOException");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException");
        }
        return object;
    }

    public MessageManager deserializeMessageManager() {
        MessageManager object = null;
        String filename = "MessageManager.ser";
        try
        {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            object = (MessageManager)in.readObject();
            in.close();
            file.close();
        }
        catch(IOException ex)
        {
            System.out.println("IOException");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException");
        }
        return object;
    }

    public EventManager deserializeEventManager() {
        EventManager object = null;
        String filename = "EventManager.ser";
        try
        {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            object = (EventManager)in.readObject();
            in.close();
            file.close();
        }
        catch(IOException ex)
        {
            System.out.println("IOException");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException");
        }
        return object;
    }

    public HashMapManager deserializeHashMapManager() {
        HashMapManager object = null;
        String filename = "UserManager.ser";
        try
        {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            object = (HashMapManager)in.readObject();
            in.close();
            file.close();
        }
        catch(IOException ex)
        {
            System.out.println("IOException");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException");
        }
        return object;
    }


}
