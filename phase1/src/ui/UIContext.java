package ui;

import ui.views.View;

import java.util.LinkedList;
import java.util.List;

public class UIContext {
    private View currentView;
    private List<View> views = new LinkedList<View>();

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
}
