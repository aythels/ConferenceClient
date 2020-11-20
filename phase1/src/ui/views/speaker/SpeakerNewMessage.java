package ui.views.speaker;

import api.controllers.eventcontrollers.SpeakerEventController;
import api.controllers.messengercontrollers.SpeakerMessengerController;
import ui.UIContext;
import ui.views.View;

import java.util.List;

public class SpeakerNewMessage extends View {
    private String eventId, message;
    public SpeakerNewMessage(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        if (eventId == null) {
            SpeakerEventController eventController =
                    (SpeakerEventController) this.context.server.getAPI().getEventAPI(this.context.getState("accessCode"));
            List<Integer> eventIds = eventController.getAllEventIDs();
            sb.append("Choose event to message all attendees: \n");
            for (Integer eventId: eventIds) {
                sb.append(eventId).append(" - ").append(eventController.getEventName(eventId)).append("\n");
            }
        } else {
            sb.append("Enter message: ");
        }
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        if (eventId == null) {
            eventId = input;
        } else {
            SpeakerEventController eventController =
                    (SpeakerEventController) this.context.server.getAPI().getEventAPI(this.context.getState("accessCode"));
            List<String> userIds = eventController.getEventRegisteredUserIDs(Integer.parseInt(eventId));
            SpeakerMessengerController messengerController =
                    (SpeakerMessengerController) this.context.server.getAPI().getMessengerAPI(this.context.getState("accessCode"));
            messengerController.messageUserByIDArray(this.context.getState("accessCode"), userIds, input);
            this.context.navigate("home_speaker");
        }
    }

    @Override
    public String getRoute() {
        return "new_message_speaker";
    }
}
