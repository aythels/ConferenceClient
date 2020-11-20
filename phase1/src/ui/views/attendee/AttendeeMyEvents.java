package ui.views.attendee;

import api.controllers.eventcontrollers.AttendeeEventController;
import ui.UIContext;
import ui.views.View;

import java.util.List;

public class AttendeeMyEvents extends View {
    public AttendeeMyEvents(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        AttendeeEventController attendeeEventController =
                (AttendeeEventController) this.context.server.getAPI().getEventAPI(this.context.getState("accessCode"));

        List<Integer> eventIds = attendeeEventController.getAllRegisteredEventIDs(this.context.getState("accessCode"));

        sb
                .append("============================\n")
                .append("|    Registered events     |\n")
                .append("|--------------------------|\n");

        for (Integer id: eventIds) {
            sb.append("| ").append(attendeeEventController.getEventName(id)).append("\n");
        }
        sb.append("| q - go back\n");
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        if (input.equals("q")) {
            this.context.navigate("events_attendee");
        }
    }

    @Override
    public String getRoute() {
        return "my_events_attendee";
    }
}
