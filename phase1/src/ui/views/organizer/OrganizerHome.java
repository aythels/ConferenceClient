package ui.views.organizer;

import ui.UIContext;
import ui.views.View;

public class OrganizerHome extends View {
    public OrganizerHome(UIContext context) {
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
                .append("| 1. Schedule an event     |\n")
                .append("| 2. Create speaker account|\n")
                .append("| 3. View events           |\n")
                .append("| 4. Log out               |\n")
                .append("============================\n");
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        switch (input) {
            case "1":
                this.context.navigate("new_event_organizer");
                break;
            case "2":
                this.context.navigate("new_speaker_organizer");
                break;
            case "3":
                this.context.navigate("events_organizer");
                break;
            case "4":
                this.context.putState("accessCode", null);
                this.context.navigate("login");
                break;
        }
    }

    @Override
    public String getRoute() {
        return "home_organizer";
    }
}
