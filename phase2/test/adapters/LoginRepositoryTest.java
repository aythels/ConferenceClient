package adapters;

import org.junit.*;

import java.io.File;
import java.io.IOException;

public class LoginRepositoryTest {

    private String store = "./teststore";
    private String storeFile = "./teststore/logins.ser";

    @Test
    public void testPut() {
        try {
            File file = new File(storeFile);
            file.delete();
            {
                LoginRepository repo = new LoginRepository(store);
                repo.addLogin("test", "123");
            }
            LoginRepository repo = new LoginRepository(store);
            Assert.assertTrue(repo.contains("test"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGet() {
        try {
            File file = new File(storeFile);
            file.delete();
            {
                LoginRepository repo = new LoginRepository(store);
                repo.addLogin("test", "123");
            }
            LoginRepository repo = new LoginRepository(store);
            Assert.assertEquals(repo.getPassword("test"), "123");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
