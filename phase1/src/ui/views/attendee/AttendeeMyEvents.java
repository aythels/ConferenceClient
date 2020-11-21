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
                .append("| Input index to unregister|\n")
                .append("|--------------------------|\n");

        for (int i = 0; i < eventIds.size(); i++) {
            sb.append("| ").append(i).append(". ").append(attendeeEventController.getEventName(eventIds.get(i))).append("\n");
        }
        sb.append("| q - go back\n");
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        AttendeeEventController attendeeEventController =
                (AttendeeEventController) this.context.server.getAPI().getEventAPI(this.context.getState("accessCode"));
        List<Integer> eventIds = attendeeEventController.getAllRegisteredEventIDs(this.context.getState("accessCode"));

        if (eventIds != null) {
            for (int i = 0; i < eventIds.size(); i++) {
                int _index = i;
                if (input.equals(String.valueOf(_index))) {

                    System.out.println(eventIds + "aasf" + eventIds.get(i));


                    System.out.println(attendeeEventController.unregisterInEvent(this.context.getState("accessCode"), eventIds.get(i)));
                    this.context.navigate("all_events_attendee");
                }
            }
        }

        if (input.equals("q")) {
            this.context.navigate("events_attendee");
        }
    }

    @Override
    public String getRoute() {
        return "my_events_attendee";
    }
}
