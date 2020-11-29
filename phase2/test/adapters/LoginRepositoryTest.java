package adapters;

import org.junit.*;

import java.io.File;
import java.io.IOException;

public class LoginRepositoryTest {

    private String store = "./teststore";
    private String storeFile = "./teststore/logins.ser";

    @Before
    public void deleteStore() {
        File file = new File(storeFile);
        file.delete();
    }

    @Test
    public void testPut() {
        try {
            {
                LoginRepository repo = new LoginRepository(store);
                repo.addLogin("test", "123");
            }
            LoginRepository repo = new LoginRepository(store);
            Assert.assertTrue(repo.contains("test"));
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testGet() {
        try {
            {
                LoginRepository repo = new LoginRepository(store);
                repo.addLogin("test", "123");
            }
            LoginRepository repo = new LoginRepository(store);
            Assert.assertEquals(repo.getPassword("test"), "123");
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testContains() {
        try {
            {
               LoginRepository repo = new LoginRepository(store);
               repo.addLogin("test", "password");
                repo.addLogin("test1", "password");
            }
            LoginRepository repo = new LoginRepository(store);
            Assert.assertTrue(repo.contains("test"));
            Assert.assertTrue(repo.contains("test1"));
            Assert.assertFalse(repo.contains("test2"));
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
