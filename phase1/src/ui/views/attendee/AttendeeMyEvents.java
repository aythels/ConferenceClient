package ui.views.attendee;

import controllers.eventcontrollers.AttendeeEventController;
import controllers.eventcontrollers.PublicEventController;
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
                (AttendeeEventController) this.context.server.getAPI().getEventAPI(this.context.getState("accessKey"));
        PublicEventController publicEventController = null; // TODO

        List<Integer> eventIds = attendeeEventController.getAllRegisteredEventIDs(this.context.getState("accessKey"));

        sb
                .append("============================\n")
                .append("|    Registered events     |\n")
                .append("|--------------------------|\n");

        for (Integer id: eventIds) {
            sb.append("| ").append(publicEventController.getEventName(id)).append("\n");
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
