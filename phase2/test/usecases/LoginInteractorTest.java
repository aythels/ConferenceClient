package usecases;

import adapters.LoginRepository;
import adapters.UserRepository;
import core.entities.User;
import core.entities.UserType;
import core.usecases.LoginInteractor;
import core.usecases.exceptions.LoginNotFoundError;
import core.usecases.exceptions.UserAlreadyExistsError;
import core.usecases.ports.ILoginRepository;
import core.usecases.ports.IUserRepository;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;


public class LoginInteractorTest {

    private String folder = "./teststore";

    @Before
    public void deleteStore() {
        File file1 = new File("./teststore/users.ser");
        File file2 = new File("./teststore/usersByType.ser");
        File file3 = new File("./teststore/logins.ser");
        file1.delete();
        file2.delete();
        file3.delete();
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testRegisterUser() throws IOException {
        User user = new User("test user", "username", UserType.ATTENDEE);
        ILoginRepository logins = new LoginRepository(folder);
        IUserRepository users = new UserRepository(folder);
        LoginInteractor l = new LoginInteractor(logins, users);
        l.registerUser(user, "password");

        ILoginRepository accessLogins = new LoginRepository("./teststore");
        IUserRepository accessUsers = new UserRepository("./teststore");

        Assert.assertEquals(accessLogins.getPassword(user.getUserName()), "password");
        Assert.assertEquals(accessUsers.getUser(user.getUserName()), user);
        Assert.assertTrue(accessUsers.getAllUsersOfType(UserType.ATTENDEE).contains(user));
    }

    @Test
    public void testDuplicateUserRegister() {
        try {
            User user = new User("test", "testuser", UserType.ATTENDEE);
            IUserRepository users = new UserRepository("./teststore");
            ILoginRepository logins = new LoginRepository("./teststore");
            LoginInteractor l = new LoginInteractor(logins, users);
            l.registerUser(user, "test");

            IUserRepository accessUsers = new UserRepository("./teststore");
            ILoginRepository accessLogins = new LoginRepository("./teststore");
            l = new LoginInteractor(accessLogins, accessUsers);
            exception.expect(UserAlreadyExistsError.class);
            l.registerUser(user, "test");
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testLogin() {
        try {
            User user = new User("test", "testuser", UserType.ATTENDEE);
            IUserRepository users = new UserRepository("./teststore");
            ILoginRepository logins = new LoginRepository("./teststore");
            LoginInteractor l = new LoginInteractor(logins, users);
            l.registerUser(user, "test");

            IUserRepository accessUsers = new UserRepository("./teststore");
            ILoginRepository accessLogins = new LoginRepository("./teststore");
            l = new LoginInteractor(accessLogins, accessUsers);

            exception.expect(LoginNotFoundError.class);
            l.login("testuse", "password");
            l.login("testuser", "passwor");

            Assert.assertEquals(l.login("testuser", "password"), user);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
