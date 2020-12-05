package ui.views.attendee;

import ui.UIContext;
import ui.views.View;


public class AttendeeNewMessage extends View {
    public AttendeeNewMessage(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("============================\n")
                .append("|        New message       |\n")
                .append("|--------------------------|\n")
                .append("| Enter user id to message |\n")
                .append("| Or q to go back          |\n")
                .append("============================\n");
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        if (input.equals("q")) {
            this.context.navigate("messages_attendee");
            return;
        }
        this.context.putState("chat", input);
        this.context.navigate("chat_attendee");
    }

    @Override
    public String getRoute() {
        return "new_message_attendee";
    }
}
