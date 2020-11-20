package ui.views.organizer;

import api.controllers.eventcontrollers.OrganizerEventController;
import ui.UIContext;
import ui.views.View;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class OrganizerNewEvent extends View {
    private String name, duration, start;

    public OrganizerNewEvent(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        if (name == null) {
            sb.append("Enter event name - ");
        } else if (duration == null) {
            sb.append("Enter event duration (in hours) - ");
        } else if (start == null) {
            sb.append("Enter event start time (9-5) - ");
        }
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        if (name == null) name = input;
        else if (duration == null) duration = input;
        else {
            start = input;
            OrganizerEventController eventController =
                    (OrganizerEventController) this.context.server.getAPI().getEventAPI(this.context.getState("accessCode"));
            int durationMs = Integer.parseInt(duration) * 60 * 60 * 1000;
            LocalDate d = LocalDate.now();
            LocalDateTime dd = d.atTime(Integer.parseInt(duration), 0);
            int startMs = (int) dd.toEpochSecond(ZoneOffset.UTC);
            eventController.createEvent(name, durationMs, startMs);
            this.context.navigate("events_organizer");
        }
    }

    @Override
    public String getRoute() {
        return "new_event_organizer";
    }
}
