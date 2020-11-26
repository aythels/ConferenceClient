package gui.views.homeview;

import gui.helpers.ClientData;
import gui.helpers.PageIndex;
import gui.helpers.PageInitializeEvent;
import gui.helpers.PageUpdateEvent;
import gui.views.PageController;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController extends PageController implements PageInitializeEvent, PageUpdateEvent {
    public HomeViewController(PageIndex pageIndex, ClientData clientData) {
        super(pageIndex, clientData);
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
