package gateways.stores;

import core.usecases.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageStore implements Serializable {

    private List<Message> messages;

    public MessageStore() {
        messages = new ArrayList<>();
    }

    public List<Message> getMessages() {
        return messages;
    }
}
