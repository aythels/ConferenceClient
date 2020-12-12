package gui.helpers;

import gui.presenters.EventsPresenter;
import gui.presenters.LoginPresenter;
import gui.presenters.MessagePresenter;
import gui.presenters.SettingsPresenter;

public class Presenters {
    public EventsPresenter eventsPresenter;
    public LoginPresenter loginPresenter;
    public MessagePresenter messagePresenter;
    public SettingsPresenter settingsPresenter;

    public void setLoginPresenter(LoginPresenter presenter) {
        this.loginPresenter = presenter;
    }

    public void setSettingPresenter(SettingsPresenter presenter) {
        this.settingsPresenter = presenter;
    }

    public void setMessagePresenter(MessagePresenter presenter) {
        this.messagePresenter = presenter;
    }

    public void setEventsPresenter(EventsPresenter presenter) {
        this.eventsPresenter = presenter;
    }
}
