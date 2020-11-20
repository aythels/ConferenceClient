package ui.views.speaker;

import ui.UIContext;
import ui.views.View;

public class SpeakerHome extends View {
    public SpeakerHome(UIContext context) {
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
                .append("| 1. View my events        |\n")
                .append("| 2. Send a message        |\n")
                .append("============================\n");
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        switch (input) {
            case "1":
                this.context.navigate("events_speaker");
                break;
            case "2":
                this.context.navigate("new_message_speaker");
                break;
        }
    }

    @Override
    public String getRoute() {
        return "home_speaker";
    }
}
