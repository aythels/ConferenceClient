package ui.views.organizer;

import api.controllers.eventcontrollers.OrganizerEventController;
import api.controllers.usercontrollers.OrganizerUserController;
import ui.UIContext;
import ui.views.View;

import java.util.List;

public class OrganizerEventMenu extends View {
    public OrganizerEventMenu(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        int eventId = Integer.parseInt(this.context.getState("event"));
        OrganizerEventController eventController =
                (OrganizerEventController) this.context.server.getAPI().getEventAPI(this.context.getState("accessCode"));
        OrganizerUserController userController =
                (OrganizerUserController) this.context.server.getAPI().getUserAPI(this.context.getState("accessCode"));
        String eventName = eventController.getEventName(eventId);
        List<String> speakerIds = eventController.getEventSpeakerID(eventId);
        List<String> attendeeIds = eventController.getEventRegisteredUserIDs(eventId);

        StringBuilder sb = new StringBuilder();
        sb
                .append("============================\n")
                .append("|      Event details       |\n")
                .append("|--------------------------|\n")
                .append("| Event name: ").append(eventName).append("\n")
                .append("| Registered speakers: \n");

        for (String speakerId: speakerIds) {
            sb.append("| ").append(userController.getUserName(speakerId)).append("\n");
        }
        sb.append("| Registered attendees: \n");
        for (String attendeeId: attendeeIds) {
            sb.append("| ").append(userController.getUserName(attendeeId));
        }
        sb.append("| Enter speaker id to add to event\n");
        sb.append("| or q to go back\n");
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        if (input.equals("q")) this.context.navigate("events_organizer");
        else {
            OrganizerEventController eventController =
                    (OrganizerEventController) this.context.server.getAPI().getEventAPI(this.context.getState("accessCode"));
            eventController.setEventSpeaker(Integer.parseInt(this.context.getState("event")), input);
        }
    }

    @Override
    public String getRoute() {
        return "event_menu_organizer";
    }
}
