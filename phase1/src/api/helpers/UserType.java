package api.helpers;

/**
 * A list of all available user types.
 *
 * Note: certain controller methods allow you to create users. User type parameters for those methods must be one
 * of the constants in this class.
 *
 * Note: since the system architecture relies on controllers for handling client permissions, it is more appropriate to
 * keep the list of user types in this layer rather than in a deeper layer. Use case classes and entity class do not
 * rely on this class at all!
 */

public enum UserType {
    ATTENDEE,
    SPEAKER,
    ORGANIZER;

    /**
     * Check if the specified string is a valid UserType.
     * @param test the string to test (etc "ATTENDEE").
     * @return true if there is a matching enum constant, false otherwise.
     */

    public static boolean contains(String test) {

        for (UserType type : UserType.values()) {
            if (type.name().equals(test)) {
                return true;
            }
        }

        return false;
    }

}
