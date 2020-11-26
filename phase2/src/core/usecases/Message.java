package core.usecases;

import core.entities.User;

import java.util.List;
import java.util.Optional;

public class Message {

    private User sender;
    private List<User> receivers;
    private String content;
    private Message previousMessage;

    public Message(User sender, List<User> receivers, String content, Message previousMessage) {
        this.sender = sender;
        this.receivers = receivers;
        this.content = content;
        this.previousMessage = previousMessage;
    }

    public Message(User sender, List<User> receivers, String content) {
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

    public String getContent() {
        return content;
    }

    public Optional<Message> getPreviousMessage() {
        return Optional.ofNullable(previousMessage);
    }
}
