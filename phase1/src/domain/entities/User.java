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

    /**
     * Returns the name of user.
     * @return name
     */

    public String getName() { return this.name; }

    /**
     * Returns the type of user.
     * @return userType
     */

    public String getUserType() {
        return this.userType;
    }

    /**
     * Returns the ID of user.
     * @return ID
     */

    public String getID() {
        return this.ID;
    }

    /**
     * Returns the password of user.
     * @return password
     */


    public String getPassword() {
        return this.password;
    }

    /**
     * This method sets the name of the user.
     * @param name
     */

    public void setName(String name) {
        this.name=name;
    }

    /**
     * This method sets the type of the user.
     * @param userType
     */

    public void setUserType(String type) {
        this.userType=type;
    }

    /**
     * This method sets the username(ID) of the user.
     * @param username
     */

    public void setUsername(String username) {
        this.name=username;
    }

    /**
     * This method sets the password of the user.
     * @param password
     */

    public void setPassword(String password) {
        this.password=password;
    }
}