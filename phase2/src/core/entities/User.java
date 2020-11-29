package core.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class contains user data as well as methods which operate on user data.
 * A user is distinguished by what kind of user it is.
 */

public class User implements Serializable {

    /** The user's full name. */
    private String fullName;

    /** The user's account user name. */
    private String userName;

    /** The user's type. */
    private UserType userType;

    /** Creates a new User with the given full name, username, and user type.
     * @param fullName the full name of the user.
     * @param userName the username of the user.
     * @param userType the type of the user.
     */
    public User(String fullName, String userName, UserType userType) {
        this.fullName = fullName;
        this.userName = userName;
        this.userType = userType;
    }

    /**
     * @return the user's full name.
     * */
    public String getFullName() {
        return fullName;
    }

    /**
     * @return the user's username.
     * */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the user's user type.
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Implementation for equality between User objects.
     * @param o object being compared to.
     * @return whether they're equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userName.equals(user.userName);
    }

    /**
     * Implementation for hash code generation.
     * @return int representing the hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(fullName, userName, userType);
    }
}
