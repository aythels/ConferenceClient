package core.usecases;

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

    public void userSendMessage(User sender, List<User> recipients, String content) {
        Message message = new Message(sender, recipients, content);
        messages.storeMessage(message);
    }

    public List<Message> getInboxForUser(User user) {
        List<Message> result = new ArrayList<>();
        for (Message msg: messages.getAllMessages()) {
            if (msg.getReceivers().contains(user)) {
                result.add(msg);
            }
        }
        return result;
    }

    public List<Event> getEventsForSpeaker(User speaker) {
        return events.getSpeakerEvents(speaker);
    }

    public List<User> getRecipientsForAttendee() {
        List<User> result = new ArrayList<>();
        result.addAll(users.getAllUsersOfType(UserType.ATTENDEE));
        result.addAll(users.getAllUsersOfType(UserType.SPEAKER));
        return result;
    }

    public List<User> getRecipientsForOrganizer() {
        //TODO: Rewrite this
        return getRecipientsForAttendee();
    }
}
