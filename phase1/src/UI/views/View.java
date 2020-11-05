package UI.views;

import UI.UIContext;

public abstract class View {
    protected final UIContext context;

    public View(UIContext context) {
        this.context = context;
    }
    public abstract String render();
    public abstract void handleInput(String input);
    public abstract String getRoute();
}
