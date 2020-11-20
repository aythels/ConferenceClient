package ui.views.attendee;

import api.controllers.eventcontrollers.AttendeeEventController;
import ui.UIContext;
import ui.views.View;

import java.util.List;

public class AttendeeAllEvents extends View {
    public AttendeeAllEvents(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        AttendeeEventController eventController =
                (AttendeeEventController) this.context.server.getAPI().getEventAPI(this.context.getState("accessCode"));

        List<Integer> eventIds = eventController.getAllEventIDs();
        StringBuilder sb = new StringBuilder();
        sb
                .append("============================\n")
                .append("|       All events         |\n")
                .append("|--------------------------|\n");

        for (int i = 0; i < eventIds.size(); i++) {
            String name = eventController.getEventName(eventIds.get(i));
            sb.append("| ").append(i). append(". ").append(name).append(" \n");
        }
        sb.append("| q - go back\n");
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        AttendeeEventController eventController =
                (AttendeeEventController) this.context.server.getAPI().getEventAPI(this.context.getState("accessCode"));

        List<Integer> eventIds = eventController.getAllEventIDs();

        if (input.equals("q")) {
            this.context.navigate("events_attendee");
        } else {
            try {
                int inputNumber = Integer.parseInt(input);
                if (inputNumber < eventIds.size()) {
                    AttendeeEventController attendeeEventController =
                            (AttendeeEventController) this.context.server.getAPI().getEventAPI(this.context.getState("accessCode"));
                    attendeeEventController.registerInEvent(this.context.getState("accessCode"), eventIds.get(inputNumber));
                    this.context.navigate("my_events_attendee");
                }
            } catch (Exception ignored){}
        }
    }

    @Override
    public String getRoute() {
        return "all_events_attendee";
    }
}