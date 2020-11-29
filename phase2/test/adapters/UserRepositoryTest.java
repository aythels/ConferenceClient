package adapters;

import core.entities.User;
import core.entities.UserType;
import org.junit.*;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.io.File;
import java.io.IOException;

public class UserRepositoryTest {

    private String folder = "./teststore";
    private String usersFile = "./teststore/users.ser";
    private String usersByTypeFile = "./teststore/usersByType.ser";

    @Before
    private void deletePreviousData() {
        File f1 = new File(usersFile);
        File f2 = new File(usersByTypeFile);
        f1.delete();
        f2.delete();
    }

    @Test
    public void testPut() {
        try {
            User user = new User("test", "testuser", UserType.ATTENDEE);
            {
                UserRepository ur = new UserRepository(folder);
                ur.addUser(user);
            }
            UserRepository ur = new UserRepository(folder);
            Assert.assertTrue(ur.contains(user));
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testGet() {
        try {
            User user1 = new User("test", "testuser", UserType.ATTENDEE);
            User user2 = new User("test1", "testuser1", UserType.SPEAKER);
            {
                UserRepository ur = new UserRepository(folder);
                ur.addUser(user1);
                ur.addUser(user2);
            }
            UserRepository ur = new UserRepository(folder);
            User result = ur.getUser("testuser");
            Assert.assertEquals(user1, result);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testGetAllOfType() {
    }
}
