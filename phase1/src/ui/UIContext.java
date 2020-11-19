package ui;

import server.Server;
import ui.views.View;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class UIContext {
    private View currentView;
    private List<View> views = new LinkedList<View>();
    private HashMap<String, String> state = new HashMap<>();
    public Server server;

    public UIContext(Server server) {
        this.server = server;
    }

    public void addView(View view) {
        this.views.add(view);
    }

    public View getCurrentView() {
        return this.currentView;
    }

    public void navigate(String route) {
        for (View view: this.views) {
            if (view.getRoute().equals(route)) {
                this.currentView = view;
                return;
            }
        }
    }

    public void putState(String key, String value) {
        this.state.put(key, value);
    }

    public String getState(String key) {
        return this.state.get(key);
    }
}
