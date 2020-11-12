package domain.usecases;

import domain.entities.User;
import domain.entities.Message;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class MessageManager {

    private HashMap<String, List<Message>> conversations;

    public MessageManager() {
        this.conversations = new HashMap<>();
    }

    /**
     * Creates a new Message.
     * @param m content of the message
     * @return  a new Message with content m
     */
    public Message createMessage(String m, String userId) {
        return new Message(userId, m);
    }

    /**
     * Gets and returns a desired conversation.
     * @param sender    the User sending this message
     * @param receiver  the User receiving this message
     * @return          the conversation between sender and receiver
     */
    public List<Message> getConversation(User sender, User receiver) {
        String x = "";
        if (conversationExists(sender, receiver)) {
            for (String key : conversations.keySet()) {
                if (key.contains(sender.getID()) && key.contains(receiver.getID())) {
                    x = key;
                }
            }
            return conversations.get(x);
        }
        return null;
    }

    private boolean conversationExists(User sender, User receiver) {
        for (String key : conversations.keySet()) {
            if (key.contains(sender.getID()) && key.contains(receiver.getID())) {
                return true;
            }
        }
        return false;
    }

    private void createConversation(User sender, User receiver) {
        List<Message> x = new ArrayList<Message>();
        conversations.put((sender.getID() + ", " + receiver.getID()), x);
    }

    /**
     * Sends a message from sender to receiver.
     * If this conversation does not already exist, creates a new conversation.
     * If this conversation does exist, adds the Message to the conversation.
     * @param sender    the User sending this message
     * @param receiver  the User receiving this message
     * @param m         content of the message
     */
    public void sendMessage(User sender, User receiver, String m) {
        List<Message> x;
        if (!conversationExists(sender, receiver)) {
            createConversation(sender, receiver);
        }
        x = getConversation(sender, receiver);
        x.add(createMessage(sender.getID(), m));
    }
}
