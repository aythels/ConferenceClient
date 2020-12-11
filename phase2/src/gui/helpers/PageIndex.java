package gui.helpers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;

public class PageIndex {
    private final Stage primaryStage;
    private final HashMap<String, Scene> allPages;
    private final ArrayList<PageUpdateEvent> pageUpdateObservers;

    public PageIndex(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.allPages = new HashMap<String, Scene>();
        this.pageUpdateObservers = new ArrayList<>();
    }

    public void addPage (String pageName, Scene scene) {
        this.allPages.put(pageName, scene);
    }

    public void addPageUpdateObserver(PageUpdateEvent observer){
        this.pageUpdateObservers.add(observer);
    }

    public void setPage(String pageName) {
        this.primaryStage.setTitle("Phase 2");
        this.primaryStage.sizeToScene();

        this.primaryStage.setScene(allPages.get(pageName));
        notifyPageUpdateObservers();

        this.primaryStage.show();

        this.primaryStage.setMinWidth(this.primaryStage.getWidth());
        this.primaryStage.setMinHeight(this.primaryStage.getHeight());
    }

    public void notifyPageUpdateObservers() {
        for (PageUpdateEvent observer: pageUpdateObservers) {
            observer.update();
        }
    }

}
