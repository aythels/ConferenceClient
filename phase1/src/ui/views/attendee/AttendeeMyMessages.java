package ui.views.attendee;

import api.controllers.messengercontrollers.AttendeeMessengerController;
import api.controllers.usercontrollers.AttendeeUserController;
import ui.UIContext;
import ui.views.View;

import java.util.List;
import java.util.stream.Collectors;

public class AttendeeMyMessages extends View {
    public AttendeeMyMessages(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        AttendeeMessengerController messengerController =
                (AttendeeMessengerController) this.context.server.getAPI().getMessengerAPI(this.context.getState("accessCode"));
        List<String> userIds = messengerController.getMessagebleUserIDs(this.context.getState("accessCode"));
        AttendeeUserController userController =
                (AttendeeUserController) this.context.server.getAPI().getUserAPI(this.context.getState("accessCode"));
        List<String> userNames = userIds.stream().map(userController::getUserName).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb
                .append("============================\n")
                .append("|          Chats           |\n")
                .append("|--------------------------|\n");

        for (int i = 0; i < userNames.size(); i++) {
            sb.append("| ").append(i).append(" - ").append(userNames.get(i)).append(" \n");
        }
        sb.append("| q - go back\n");
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        AttendeeMessengerController messengerController =
                (AttendeeMessengerController) this.context.server.getAPI().getMessengerAPI(this.context.getState("accessCode"));
        List<String> userIds = messengerController.getMessagebleUserIDs(this.context.getState("accessCode"));

        if (input.equals("q")) {
            this.context.navigate("messages_attendee");
            return;
        }

        try {
            int inputNumber = Integer.parseInt(input);
            if (inputNumber < userIds.size()) {
                this.context.putState("chat", userIds.get(inputNumber));
                this.context.navigate("chat_attendee");
            }
        } catch (Exception ignored) {}
    }

    @Override
    public String getRoute() {
        return "my_messages_attendee";
    }
}
