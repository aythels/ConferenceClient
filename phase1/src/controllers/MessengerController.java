package controllers;

import domain.entities.Message;
import domain.entities.User;
import domain.usecases.MessageManager;
import domain.usecases.UserManager;

import java.util.ArrayList;
import java.util.List;

public class MessengerController {
    private LoginHelper loginHelper;
    private UserTypeChecker userTypeChecker;
    private UserManager userManager;
    private MessageManager messageManager;

    public MessengerController(UserManager userManager, LoginHelper loginHelper, UserTypeChecker userTypeChecker, MessageManager messageManager){
        this.loginHelper = loginHelper;
        this.userTypeChecker = userTypeChecker;
        this.userManager = userManager;
        this.messageManager = messageManager;
    }

    //user level permissions

    /**
     * Get an array of all other userIDs that the client user has talked to.
     * @param accessCode    a code that permits the caller to use controller methods restricted to their user type.
     * @return  an array of userIDs
     */

    public List<String> getMessagebleUserIDs(String accessCode) {
        if (!userTypeChecker.isAuthorizedUser(accessCode)) return null;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);

        List<String> allIDs = new ArrayList<String>();
        for (String userID : messageManager.getAllConversationUsers(thisUser)) allIDs.add(userID);

        return allIDs;

    }

    /**
     * Get a conversation between the client user and another user by their ID.
     * @param accessCode    a code that permits the caller to use controller methods restricted to their user type.
     * @param otherUserID    the login ID of the other user involved in the conversation
     * @return  an array of message strings in the format "userID:message" or null otherwise (such as if otherUserID
                points to non-existing user.
     */

    public List<String> getConvoWithOtherUserByID(String accessCode, String otherUserID) {
        if (!userTypeChecker.isAuthorizedUser(accessCode)) return null;
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
     * @param accessCode    a code that permits the caller to use controller methods restricted to their user type.
     * @param otherUserID    the login ID of the message receiver.
     * @param message    the message string to be sent.
     * @return  true if the message was sent successfully, false otherwise (such as if otherUserID points to
     *          non-existing user).
     */

    public boolean messageUserByID(String accessCode, String otherUserID, String message) {
        if (!userTypeChecker.isAuthorizedUser(accessCode)) return false;
        if (!userManager.userExists(otherUserID)) return false;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);
        User otherUser = userManager.getUser(otherUserID);

        messageManager.sendMessage(thisUser, otherUser, message);
        return true;
    }

    //speaker level permissions

    /**
     * Message multiple users at once by an array of userIDs
     * @param accessCode    a code that permits the caller to use controller methods restricted to their user type.
     * @param otherUserIDArray    a list of login IDs of users who will receive this message.
     * @param message    the message string to be sent.
     * @return  true if the message was sent successfully, false otherwise (such as if otherUserIDArray contains a n
     *          on-existing user).
     */

    public boolean messageUserByIDArray(String accessCode, List<String> otherUserIDArray, String message) {
        if (!userTypeChecker.isAuthorizedSpeaker(accessCode)) return false;

        for (String userID : otherUserIDArray) if (!messageUserByID(accessCode, userID, message)) return false;

        return true;
    }
}
