package ui.views.speaker;

import ui.UIContext;
import ui.views.View;

public class SpeakerEvents extends View {
    public SpeakerEvents(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append("This function is not currently needed, if you want to register as speakers to a Event, please go through the organizer");
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        this.context.navigate("home_speaker");
    }

    @Override
    public String getRoute() {
        return "events_speaker";
    }
}
