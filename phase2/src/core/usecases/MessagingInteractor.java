package core.usecases;

import core.entities.Content;
import core.entities.Event;
import core.entities.User;
import core.entities.UserType;
import core.usecases.ports.IEventRepository;
import core.usecases.ports.IMessageRepository;
import core.usecases.ports.IUserRepository;

import java.util.ArrayList;
import java.util.List;

public class MessagingInteractor {

    private IMessageRepository messages;
    private IUserRepository users;
    private IEventRepository events;

    public MessagingInteractor(IMessageRepository messages, IUserRepository users, IEventRepository events) {
        this.messages = messages;
        this.users = users;
        this.events = events;
    }

    public List<Message> getInbox(User user) {
        List<Message> result = new ArrayList<>();
        for (Message msg: messages.getAllMessages()) {
            if (msg.getReceivers().contains(user)) {
                result.add(msg);
            }
        }
        return result;
    }

    public void userSendMessage(User sender, List<User> recipients, Content content) {
        Message message = new Message(sender, recipients, content);
        messages.storeMessage(message);
    }

    public List<Event> getEventsForSpeaker(User speaker) {
        return events.getSpeakerEvents(speaker);
    }

    public void speakerSendToEvent(User speaker, Event event, Content content) {
        userSendMessage(speaker, events.getAttendeesForEvent(event), content);
    }

    public List<User> getUsersByType(UserType type) {
        return users.getAllUsersOfType(type);
    }

    public void organizerMessageUsers(User organizer, UserType type, Content content) {
        if (type == UserType.ATTENDEE || type == UserType.SPEAKER)
            userSendMessage(organizer, users.getAllUsersOfType(type), content);
    }
}
