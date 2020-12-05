package ui.views;

import ui.UIContext;

/**
 * This abstract class defines a View
 */
public abstract class View {
    protected final UIContext context;

    /**
     * Default constructor
     * @param context the UI context
     */
    public View(UIContext context) {
        this.context = context;
    }

    /**
     * This function renders the UI
     * @return a string representation of a text UI
     */
    public abstract String render();

    /**
     * This function handles user input
     * @param input the string inputted by user
     */
    public abstract void handleInput(String input);

    /**
     * @return the route key for this view. used for navigation
     */
    public abstract String getRoute();
}
