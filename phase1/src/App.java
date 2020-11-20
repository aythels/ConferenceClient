import ui.UIContext;
import ui.renderers.ConsoleRenderer;
import ui.views.HomeView;
import ui.views.LoginView;
import ui.views.SplashView;

public class App {
    public static void main(String[] args) {
        UIContext uiContext = new UIContext();
        uiContext.addView(new SplashView(uiContext));
        uiContext.addView(new LoginView(uiContext));
        uiContext.addView(new HomeView(uiContext));
        uiContext.navigate("splash");
        ConsoleRenderer consoleRenderer = new ConsoleRenderer(uiContext);

        while (true) {
            consoleRenderer.blit();
            consoleRenderer.promptInput();
        }
    }
}

