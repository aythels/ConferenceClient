package usecases;

import adapters.LoginRepository;
import adapters.UserRepository;
import core.entities.User;
import core.entities.UserType;
import core.usecases.LoginInteractor;
import core.usecases.exceptions.UserAlreadyExistsError;
import core.usecases.ports.ILoginRepository;
import core.usecases.ports.IUserRepository;
import org.junit.*;

import java.io.File;
import java.io.IOException;


public class LoginInteractorTest {

    @Before
    public void deleteStore() {
        File file1 = new File("./teststore/users.ser");
        File file2 = new File("./teststore/usersByType.ser");
        File file3 = new File("./teststore/logins.ser");
        file1.delete();
        file2.delete();
        file3.delete();
    }

    @Test
    public void testRegisterUser() throws IOException {
        User user = new User("test", "testuser", UserType.ATTENDEE);
        {
            IUserRepository users = new UserRepository("./teststore");
            ILoginRepository logins = new LoginRepository("./teststore");
            LoginInteractor l = new LoginInteractor(logins, users);
            l.registerUser(user, "password");
        }
        IUserRepository users = new UserRepository("./teststore");
        ILoginRepository logins = new LoginRepository("./teststore");

        Assert.assertTrue(users.contains(user));
        Assert.assertTrue(logins.contains(user.getUserName()));
    }

    @Test(expected = UserAlreadyExistsError.class)
    public void testDuplicateUserRegister() {
        try {
            User user = new User("test", "testuser", UserType.ATTENDEE);
            {
                IUserRepository users = new UserRepository("./teststore");
                ILoginRepository logins = new LoginRepository("./teststore");
                LoginInteractor l = new LoginInteractor(logins, users);
                l.registerUser(user, "test");
            }
            IUserRepository users = new UserRepository("./teststore");
            ILoginRepository logins = new LoginRepository("./teststore");
            LoginInteractor l = new LoginInteractor(logins, users);
            l.registerUser(user, "test");
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
