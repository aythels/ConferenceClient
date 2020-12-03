package gateways;

import core.usecases.Message;
import core.usecases.ports.IMessageRepository;
import gateways.stores.MessageStore;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MessageRepository extends AbstractRepository implements IMessageRepository {

    private final File file;
    private final MessageStore store;

    public MessageRepository(String path) throws IOException {
        super(path);
        file = new File(path, "messages");
        if (file.createNewFile()) {
            store = new MessageStore();
            put(file, store);
        } else {
            store = (MessageStore) get(file);
        }
    }

    @Override
    public void storeMessage(Message message) {
        store.getMessages().add(message);
        put(file, message);
    }

    @Override
    public List<Message> getAllMessages() {
        return store.getMessages();
    }
}
