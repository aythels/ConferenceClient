package core.usecases.ports;

import core.usecases.Message;

import java.util.List;

public interface IMessageRepository {

    void storeMessage(Message message);

    List<Message> getAllMessages();
}
