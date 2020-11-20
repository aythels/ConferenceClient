package ui.views.attendee;

import api.controllers.messengercontrollers.AttendeeMessengerController;
import ui.UIContext;
import ui.views.View;

import java.util.List;

public class AttendeeChat extends View {
    public AttendeeChat(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        String peerId = this.context.getState("chat");
        AttendeeMessengerController messengerController =
                (AttendeeMessengerController) this.context.server.getAPI().getMessengerAPI(this.context.getState("accessCode"));
        List<String> messages = messengerController.getConvoWithOtherUserByID(this.context.getState("accessCode"), peerId);

        StringBuilder sb = new StringBuilder();
        sb
                .append("============================\n")
                .append("|           Chat           |\n")
                .append("|--------------------------|\n");

        for (String message: messages) {
            sb.append("| ").append(message).append("\n");
        }
        sb.append("|--------------------------|\n");
        sb.append("| Enter your message       |\n");
        sb.append("| Or \"q\" to go back      |\n");
        sb.append("============================\n");
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        if (input.equals("q")) this.context.navigate("my_messages_attendee");

        String peerId = this.context.getState("chat");
        AttendeeMessengerController messengerController =
                (AttendeeMessengerController) this.context.server.getAPI().getMessengerAPI(this.context.getState("accessCode"));
        messengerController.messageUserByID(this.context.getState("accessCode"), peerId, input);
    }

    @Override
    public String getRoute() {
        return "chat_attendee";
    }
}
