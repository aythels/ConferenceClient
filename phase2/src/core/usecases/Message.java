package core.usecases;

import core.entities.Content;
import core.entities.User;

import java.util.List;
import java.util.Optional;

public class Message {

    private final User sender;
    private final List<User> receivers;
    private final Content content;
    private Message previousMessage;

    public Message(User sender, List<User> receivers, Content content, Message previousMessage) {
        this.sender = sender;
        this.receivers = receivers;
        this.content = content;
        this.previousMessage = previousMessage;
    }

    public Message(User sender, List<User> receivers, Content content) {
        this.sender = sender;
        this.receivers = receivers;
        this.content = content;
        previousMessage = null;
    }

    public User getSender() {
        return sender;
    }

    public List<User> getReceivers() {
        return receivers;
    }

    public Content getContent() {
        return content;
    }

    public Optional<Message> getPreviousMessage() {
        if (previousMessage != null)
            return Optional.of(previousMessage);
        else
            return Optional.empty();
    }

    public boolean setPreviousMessage(Message message) {
        if (previousMessage != null) {
            return false;
        } else {
            previousMessage = message;
            return true;
        }
    }
}
