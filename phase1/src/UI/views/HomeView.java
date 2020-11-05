package UI.views;

import UI.UIContext;

public class HomeView extends View {
    public HomeView(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        return "Home!";
    }

    @Override
    public void handleInput(String input) {

    }

    @Override
    public String getRoute() {
        return "home";
    }
}
