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
    public void deletePreviousData() {
        File f1 = new File(usersFile);
        File f2 = new File(usersByTypeFile);
        f1.delete();
        f2.delete();
    }

    @Test
    public void testContains() {
        try {
            User user = new User("test user", "username", UserType.ATTENDEE);
            UserRepository first = new UserRepository(folder);
            first.addUser(user);
            UserRepository second = new UserRepository(folder);
            Assert.assertTrue(second.contains(user));
            Assert.assertEquals(second.getUser(user.getUserName()), user);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testGetSingleUser() {
        try {
            User user = new User("test user", "username", UserType.ATTENDEE);
            UserRepository first = new UserRepository(folder);
            first.addUser(user);
            UserRepository second = new UserRepository(folder);
            Assert.assertEquals(second.getUser(user.getUserName()), user);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testGetAllOfType() {
        try {
            User user = new User("test user", "username", UserType.ATTENDEE);
            User user1 = new User("test user1", "username1", UserType.ATTENDEE);
            User user2 = new User("test user2", "username2", UserType.SPEAKER);
            User user3 = new User("test user3", "username3", UserType.SPEAKER);
            UserRepository first = new UserRepository(folder);
            first.addUser(user);
            first.addUser(user1);
            first.addUser(user2);
            first.addUser(user3);

            UserRepository second = new UserRepository(folder);
            Assert.assertEquals(second.getAllUsersOfType(UserType.ATTENDEE).size(), 2);
            for (User result: second.getAllUsersOfType(UserType.ATTENDEE)) {
                Assert.assertEquals(result.getUserType(), UserType.ATTENDEE);
            }
            Assert.assertEquals(second.getAllUsersOfType(UserType.SPEAKER).size(), 2);
            for (User result: second.getAllUsersOfType(UserType.SPEAKER)) {
                Assert.assertEquals(result.getUserType(), UserType.SPEAKER);
            }
            Assert.assertTrue(second.getAllUsersOfType(UserType.ADMIN).isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
