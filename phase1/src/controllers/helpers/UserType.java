package controllers.helpers;

public enum UserType {
    ATTENDEE,
    SPEAKER,
    ORGANIZER;

    public static boolean contains(String test) {

        for (UserType type : UserType.values()) {
            if (type.name().equals(test)) {
                return true;
            }
        }

        return false;
    }

}
