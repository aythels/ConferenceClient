package UI.views;

import UI.UIContext;

public class SplashView extends View {
    public SplashView(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        return  "==================================\n" +
                "|        CSC207 - Phase 1        |\n" +
                "|--------------------------------|\n" +
                "|    Press enter to continue     |\n" +
                "==================================\n";
    }

    @Override
    public void handleInput(String input) {
        this.context.navigate("login");
    }

    @Override
    public String getRoute() {
        return "splash";
    }
}
