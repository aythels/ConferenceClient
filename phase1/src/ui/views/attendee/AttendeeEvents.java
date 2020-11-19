package ui.views.attendee;

import ui.UIContext;
import ui.views.View;

public class AttendeeEvents extends View {
    public AttendeeEvents(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("============================\n")
                .append("|       Events page        |\n")
                .append("|--------------------------|\n")
                .append("| 1. View all events       |\n")
                .append("| 2. View registered events|\n")
                .append("| 3. Go back               |\n")
                .append("============================\n");
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        switch (input) {
            case "1":
                this.context.navigate("all_events_attendee");
                break;
            case "2":
                this.context.navigate("my_events_attendee");
                break;
            case "3":
                this.context.navigate("home_attendee");
                break;
        }
    }

    @Override
    public String getRoute() {
        return "events_attendee";
    }
}
