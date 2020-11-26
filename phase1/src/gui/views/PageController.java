package gui.views;

import gui.helpers.ClientData;
import gui.helpers.PageIndex;

public abstract class PageController {
    protected final PageIndex pageIndex;
    protected final ClientData clientData;

    public PageController(PageIndex pageIndex, ClientData clientData) {
        this.pageIndex = pageIndex;
        this.clientData = clientData;
    }
}
