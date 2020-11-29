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
    public void testContains() {
        try {
            LoginRepository first = new LoginRepository(store);
            Assert.assertFalse(first.contains("user"));
            first.addLogin("user", "password");
            LoginRepository second = new LoginRepository(store);
            Assert.assertTrue(second.contains("user"));
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testRetrieve() {
        try {
            LoginRepository first = new LoginRepository(store);
            first.addLogin("user", "password");
            LoginRepository second = new LoginRepository(store);
            Assert.assertEquals(second.getPassword("user"), "password");

        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
