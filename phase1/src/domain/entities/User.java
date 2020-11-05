package domain.entities;

public class User{

    private String name;
    private String userType;
    private String ID;
    private String password;

    /**
     * A name, userType, ID and password are required to create an instance of User.
     * @param name
     * @param userType
     * @param ID
     * @param password
     */

    public User(String name, String userType, String ID, String password) {
        this.name = name;
        this.userType = userType;
        this.ID = ID;
        this.password = password;
    }

    public String getName() { return this.name; }

    public String getUserType() {
        return this.userType;
    }

    public String getID() {
        return this.ID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setUserType(String type) {
        this.userType=type;
    }

    public void setUsername(String username) {
        this.name=username;
    }

    public void setPassword(String password) {
        this.password=password;
    }
}