package gui.views.homeview;

import api.API;
import gui.helpers.ClientData;
import gui.helpers.PageIndex;
import gui.helpers.PageInitializeEvent;
import gui.helpers.PageUpdateEvent;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController implements PageInitializeEvent, PageUpdateEvent {
    private final ClientData clientData;
    private final PageIndex pageIndex;
    private final API api;

    public HomeViewController(PageIndex pageIndex, ClientData clientData, API api) {
        this.clientData = clientData;
        this.pageIndex = pageIndex;
        this.api = api;

        pageIndex.addPageUpdateObserver(this);
    }

    public void logoutButtonOnClick(ActionEvent event) throws IOException {
        pageIndex.setPage("loginview");
    }

    public void settingsButtonOnClick(ActionEvent event) throws IOException {
        pageIndex.setPage("loginview");
    }

    public void eventsButtonOnClick(ActionEvent event) throws IOException {
        pageIndex.setPage("loginview");
    }

    public void messengerButtonOnClick(ActionEvent event) throws IOException {
        pageIndex.setPage("loginview");
    }

    @Override
    public void update() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
