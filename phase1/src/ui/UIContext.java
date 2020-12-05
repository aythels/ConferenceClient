package ui;

import adapters.Server;
import ui.views.View;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * This class is responsible for persisting UI state between views
 * It contains all views for the app, keeps track of navigation and state
 */
public class UIContext {
    private View currentView;
    private List<View> views = new LinkedList<View>();
    private HashMap<String, String> state = new HashMap<>();
    public Server server;

    /**
     * @param server The api server
     */
    public UIContext(Server server) {
        this.server = server;
    }

    /**
     * Add view to list of views
     * @param view View
     */
    public void addView(View view) {
        this.views.add(view);
    }

    /**
     * @return current view
     */
    public View getCurrentView() {
        return this.currentView;
    }

    /**
     * Navigate to another view
     * @param route route key to navigate
     */
    public void navigate(String route) {
        for (View view: this.views) {
            if (view.getRoute().equals(route)) {
                this.currentView = view;
                return;
            }
        }
    }

    /**
     * Save something in state, which is a key-value data structure
     * @param key key
     * @param value value to save
     */
    public void putState(String key, String value) {
        this.state.put(key, value);
    }

    /**
     * Get something from state
     * @param key key
     * @return the value
     */
    public String getState(String key) {
        return this.state.get(key);
    }
}
