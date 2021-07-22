package api.controllers.messengercontrollers;

import api.helpers.LoginHelper;
import domain.entities.Message;
import domain.entities.User;
import domain.usecases.MessageManager;
import domain.usecases.UserManager;

import java.util.ArrayList;
import java.util.List;

public class AttendeeMessengerController {
    protected final MessageManager messageManager;
    protected final UserManager userManager;
    protected final LoginHelper loginHelper;

    /**
     * @param messageManager Use case class responsible for handing everything related to messaging.
     * @param userManager Use case class responsible for handing everything related to events.
     * @param loginHelper Helper class that keeps track of logged in users.
     */

    public AttendeeMessengerController(MessageManager messageManager, UserManager userManager, LoginHelper loginHelper) {
        this.messageManager = messageManager;
        this.userManager = userManager;
        this.loginHelper = loginHelper;
    }

    /**
     * Get an array of all other userIDs that the client user has talked to.
     * @param accessCode    a code unique to the current client
     * @return  an array of userIDs or null if invalid accessCode
     */

    public List<String> getMessagebleUserIDs(String accessCode) {
        if (!loginHelper.isValidAccessCode(accessCode)) return null;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);

        return new ArrayList<>(messageManager.getAllConversationUsers(thisUser));

    }

    /**
     * Get a conversation between the client user and another user by their ID.
     * @param accessCode    a code unique to the current client
     * @param otherUserID    the login ID of the other user involved in the conversation
     * @return  an array of message strings in the format "userID:message" or null otherwise (such as if otherUserID
    points to non-existing user.
     */

    public List<String> getConvoWithOtherUserByID(String accessCode, String otherUserID) {
        if (!loginHelper.isValidAccessCode(accessCode)) return null;
        if (!userManager.userExists(otherUserID)) return null;

        //this method gets the message string and message writer directed from the message object instead of use case

        User thisUser = loginHelper.getUserByAccessCode(accessCode);
        User otherUser = userManager.getUser(otherUserID);

        List<Message> conversation = messageManager.getConversation(thisUser, otherUser);
        conversation = conversation == null ? new ArrayList<>() : conversation;

        List<String> concatenatedConversation = new ArrayList<>();
        for (Message m : conversation) concatenatedConversation.add(m.getMessage() + ":" + m.getUserId());

        return concatenatedConversation;
    }

    /**
     * Message another user by their userID
     * @param accessCode    a code unique to the current client
     * @param otherUserID    the login ID of the message receiver.
     * @param message    the message string to be sent.
     * @return  true if the message was sent successfully, false otherwise (such as if otherUserID points to
     *          non-existing user).
     */

    public boolean messageUserByID(String accessCode, String otherUserID, String message) {
        if (!loginHelper.isValidAccessCode(accessCode)) return false;
        if (!userManager.userExists(otherUserID)) return false;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);
        User otherUser = userManager.getUser(otherUserID);

        messageManager.sendMessage(thisUser, otherUser, message);
        return true;
    }

}
