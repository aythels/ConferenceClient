import server.Server;
import ui.UIContext;
import ui.renderers.ConsoleRenderer;
import ui.views.*;
import ui.views.attendee.*;

public class App {
    public static void main(String[] args) {
        Server server = new Server();

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
        uiContext.navigate("splash");
        ConsoleRenderer consoleRenderer = new ConsoleRenderer(uiContext);


        while (true) {
            consoleRenderer.blit();
            consoleRenderer.promptInput();
        }
    }
}
