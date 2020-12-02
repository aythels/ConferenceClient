package gateways;

import core.usecases.Message;
import core.usecases.ports.IMessageRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository extends AbstractRepository implements IMessageRepository {

    private final File store;
    private final List<Message> messages;

    @SuppressWarnings("unchecked")
    public MessageRepository(String path) throws IOException {
        super(path);
        store = new File(path, "messages");
        if (store.createNewFile()) {
            messages = new ArrayList<>();
            put(store, messages);
        } else {
            messages = (List<Message>) get(store);
        }
    }

    @Override
    public void storeMessage(Message message) {
        messages.add(message);
        put(store, message);
    }

    @Override
    public List<Message> getAllMessages() {
        return messages;
    }
}
