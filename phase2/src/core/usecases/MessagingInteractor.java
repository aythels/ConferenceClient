package core.usecases;

import core.usecases.ports.IEventRepository;
import core.usecases.ports.IMessageRepository;
import core.usecases.ports.IUserRepository;

public class MessagingInteractor {

    private IMessageRepository messages;
    private IUserRepository users;
    private IEventRepository events;

    public MessagingInteractor(IMessageRepository messages, IUserRepository users, IEventRepository events) {
        this.messages = messages;
        this.users = users;
        this.events = events;
    }

}
