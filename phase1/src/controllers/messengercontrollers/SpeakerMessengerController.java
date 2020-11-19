package controllers.messengercontrollers;

import controllers.helpers.LoginHelper;
import domain.usecases.MessageManager;
import domain.usecases.UserManager;

import java.util.List;

public class SpeakerMessengerController extends AttendeeMessengerController {

    public SpeakerMessengerController(MessageManager messageManager, UserManager userManager, LoginHelper loginHelper) {
        super(messageManager, userManager, loginHelper);
    }

    /**
     * Message multiple users at once by an array of userIDs
     * @param accessCode    a code unique to the current client
     * @param otherUserIDArray    a list of login IDs of users who will receive this message.
     * @param message    the message string to be sent.
     */

    public void messageUserByIDArray(String accessCode, List<String> otherUserIDArray, String message) {
        for (String userID : otherUserIDArray) messageUserByID(accessCode, userID, message);
    }

}
