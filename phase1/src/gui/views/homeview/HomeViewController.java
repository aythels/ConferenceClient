package gui.views.homeview;

import gui.helpers.PageIndex;
import javafx.event.ActionEvent;

import java.io.IOException;

public class HomeViewController {
    private final PageIndex pageIndex;

    public HomeViewController(PageIndex pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void logoutButtonOnClick(ActionEvent event) throws IOException {
        pageIndex.setPage("loginview");
    }

    public void settingsButtonOnClick(ActionEvent event) throws IOException {
        pageIndex.setPage("loginview");
    }

    public void eventsButtonOnClick(ActionEvent event) throws IOException {
        pageIndex.setPage("eventsview");
    }

    public void messengerButtonOnClick(ActionEvent event) throws IOException {
        pageIndex.setPage("loginview");
    }

}
