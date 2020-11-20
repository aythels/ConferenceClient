package ui.views.organizer;

import api.controllers.eventcontrollers.OrganizerEventController;
import ui.UIContext;
import ui.views.View;

import java.util.List;

public class OrganizerEvents extends View {
    public OrganizerEvents(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("============================\n")
                .append("|        All events        |\n")
                .append("|--------------------------|\n");
        OrganizerEventController eventController =
                (OrganizerEventController) this.context.server.getAPI().getEventAPI(this.context.getState("accessCode"));
        List<Integer> eventIds = eventController.getAllEventIDs();

        for (int i = 0; i < eventIds.size(); i++) {
            sb.append("| ").append(i).append(". ").append(eventController.getEventName(eventIds.get(i))).append("\n");
        }
        sb.append("| q - go back\n");
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        if (input.equals("q")) {
            this.context.navigate("home_organizer");
        } else {
            try {
                int eventIndex = Integer.parseInt(input);
                OrganizerEventController eventController =
                        (OrganizerEventController) this.context.server.getAPI().getEventAPI(this.context.getState("accessCode"));
                List<Integer> eventIds = eventController.getAllEventIDs();
                int eventId = eventIds.get(eventIndex);
                this.context.putState("event", String.valueOf(eventId));
                this.context.navigate("event_menu_organizer");
            } catch (Exception ignored) {}
        }
    }

    @Override
    public String getRoute() {
        return "events_organizer";
    }
}
