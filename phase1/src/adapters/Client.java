package adapters;

import ui.UIContext;
import ui.renderers.ConsoleRenderer;
import ui.views.LoginView;
import ui.views.SplashView;
import ui.views.attendee.*;
import ui.views.organizer.*;
import ui.views.speaker.SpeakerEvents;
import ui.views.speaker.SpeakerHome;
import ui.views.speaker.SpeakerNewMessage;

public class Client {

    /**
     * A class enclosing all front-end functionality. It is currently responsible for initiating the UI and calling
     * backend API methods.
     * @param server the Server object to get the controller API from.
     */

    public Client(Server server){
    /*
        UIContext uiContext = new UIContext(server);

        uiContext.addView(new SplashView(uiContext));
        uiContext.addView(new LoginView(uiContext));
        uiContext.addView(new AttendeeAllEvents(uiContext));
        uiContext.addView(new AttendeeChat(uiContext));
        uiContext.addView(new AttendeeEvents(uiContext));
        uiContext.addView(new AttendeeHome(uiContext));
        uiContext.addView(new AttendeeMessages(uiContext));
        uiContext.addView(new AttendeeMyEvents(uiContext));
        uiContext.addView(new AttendeeMyMessages(uiContext));
        uiContext.addView(new AttendeeNewMessage(uiContext));

        uiContext.addView(new OrganizerHome(uiContext));
        uiContext.addView(new OrganizerEventMenu(uiContext));
        uiContext.addView(new OrganizerEvents(uiContext));
        uiContext.addView(new OrganizerNewEvent(uiContext));
        uiContext.addView(new OrganizerNewSpeaker(uiContext));

        uiContext.addView(new SpeakerHome(uiContext));
        uiContext.addView(new SpeakerEvents(uiContext));
        uiContext.addView(new SpeakerNewMessage(uiContext));

        uiContext.navigate("splash");
        ConsoleRenderer consoleRenderer = new ConsoleRenderer(uiContext);

        while (true) {
            consoleRenderer.blit();
            consoleRenderer.promptInput();
        }
    */
    }

}
