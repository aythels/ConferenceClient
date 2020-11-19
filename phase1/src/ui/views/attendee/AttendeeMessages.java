package ui.views.attendee;

import ui.UIContext;
import ui.views.View;

public class AttendeeMessages extends View {
    public AttendeeMessages(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("============================\n")
                .append("|     Messages page        |\n")
                .append("|--------------------------|\n")
                .append("| 1. View your messages    |\n")
                .append("| 2. Send a message        |\n")
                .append("| 3. Go back               |\n")
                .append("============================\n");
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        switch (input) {
            case "1":
                this.context.navigate("my_messages_attendee");
                break;
            case "2":
                this.context.navigate("send_message_attendee");
                break;
            case "3":
                this.context.navigate("home_attendee");
                break;
        }
    }

    @Override
    public String getRoute() {
        return "messages_attendee";
    }
}
