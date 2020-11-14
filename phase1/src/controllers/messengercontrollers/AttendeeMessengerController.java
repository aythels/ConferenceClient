package controllers.messengercontrollers;

import controllers.LoginHelper;
import domain.entities.Message;
import domain.entities.User;
import domain.usecases.MessageManager;
import domain.usecases.UserManager;

import java.util.ArrayList;
import java.util.List;

class AttendeeMessengerController extends MessengerController {
    protected final MessageManager messageManager;
    protected final UserManager userManager;
    protected final LoginHelper loginHelper;

    protected AttendeeMessengerController(MessageManager messageManager, UserManager userManager, LoginHelper loginHelper) {
        this.messageManager = messageManager;
        this.userManager = userManager;
        this.loginHelper = loginHelper;
    }

    /**
     * Get an array of all other userIDs that the client user has talked to.
     * @param accessCode    a code unique to the current client
     * @return  an array of userIDs
     */

    public List<String> getMessagebleUserIDs(String accessCode) {
        User thisUser = loginHelper.getUserByAccessCode(accessCode);

        List<String> allIDs = new ArrayList<String>();
        for (String userID : messageManager.getAllConversationUsers(thisUser)) allIDs.add(userID);

        return allIDs;

    }

    /**
     * Get a conversation between the client user and another user by their ID.
     * @param accessCode    a code unique to the current client
     * @param otherUserID    the login ID of the other user involved in the conversation
     * @return  an array of message strings in the format "userID:message" or null otherwise (such as if otherUserID
    points to non-existing user.
     */

    public List<String> getConvoWithOtherUserByID(String accessCode, String otherUserID) {
        if (!userManager.userExists(otherUserID)) return null;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);
        User otherUser = userManager.getUser(otherUserID);
        List<Message> conversation = messageManager.getConversation(thisUser, otherUser);
        conversation = conversation == null ? new ArrayList<Message>() : conversation;

        List<String> concatenatedConversation = new ArrayList<String>();
        for (Message m : conversation) concatenatedConversation.add(m.getUserId() + ":" + m.getMessage());

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
        if (!userManager.userExists(otherUserID)) return false;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);
        User otherUser = userManager.getUser(otherUserID);

        messageManager.sendMessage(thisUser, otherUser, message);
        return true;
    }

}