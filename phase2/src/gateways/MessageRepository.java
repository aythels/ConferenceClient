package gateways;

import core.usecases.Message;
import core.usecases.ports.IMessageRepository;

import java.io.File;
import java.util.List;

public class MessageRepository implements IMessageRepository {

    public MessageRepository(String path) {

    }

    @Override
    public void storeMessage(Message message) {

    }

    @Override
    public List<Message> getAllMessages() {
        return null;
    }
}
