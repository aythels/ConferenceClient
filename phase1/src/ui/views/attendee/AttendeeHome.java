package ui.views.attendee;

import ui.UIContext;
import ui.views.View;

public class AttendeeHome extends View {
    public AttendeeHome(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("============================\n")
                .append("|        Home Page         |\n")
                .append("|--------------------------|\n")
                .append("| Make a selection:        |\n")
                .append("| 1. View events           |\n")
                .append("| 2. View messages         |\n")
                .append("| 3. Log out               |\n")
                .append("============================\n");
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        switch (input) {
            case "1":
                this.context.navigate("events_attendee");
                break;
            case "2":
                this.context.navigate("messages_attendee");
                break;
            case "3":
                this.context.putState("accessCode", null);
                this.context.navigate("login");
                break;
        }
    }

    @Override
    public String getRoute() {
        return "home_attendee";
    }
}
