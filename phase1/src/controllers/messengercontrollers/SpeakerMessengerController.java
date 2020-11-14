package controllers.messengercontrollers;

import controllers.LoginHelper;
import domain.usecases.MessageManager;
import domain.usecases.UserManager;

import java.util.List;

class SpeakerMessengerController extends AttendeeMessengerController {

    protected SpeakerMessengerController(MessageManager messageManager, UserManager userManager, LoginHelper loginHelper) {
        super(messageManager, userManager, loginHelper);
    }

    /**
     * Message multiple users at once by an array of userIDs
     * @param accessCode    a code unique to the current client
     * @param otherUserIDArray    a list of login IDs of users who will receive this message.
     * @param message    the message string to be sent.
     * @return  true if the message was sent successfully, false otherwise (such as if otherUserIDArray contains a n
     *          on-existing user).
     */

    public boolean messageUserByIDArray(String accessCode, List<String> otherUserIDArray, String message) {
        for (String userID : otherUserIDArray) if (!messageUserByID(accessCode, userID, message)) return false;

        return true;
    }

}
