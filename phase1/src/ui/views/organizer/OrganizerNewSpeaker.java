package ui.views.organizer;

import api.controllers.usercontrollers.OrganizerUserController;
import ui.UIContext;
import ui.views.View;

public class OrganizerNewSpeaker extends View {
    private String name, id, password;
    public OrganizerNewSpeaker(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        if (name == null) sb.append("Enter name: ");
        else if (id == null) sb.append("Enter username: ");
        else sb.append("Enter password: ");
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        if (name == null) this.name = input;
        else if (id == null) this.id = input;
        else {
            OrganizerUserController userController =
                    (OrganizerUserController) this.context.server.getAPI().getUserAPI(this.context.getState("accessCode"));
            userController.createAnyUser("SPEAKER", name, id, password);
            this.context.navigate("home_organizer");
        }
    }

    @Override
    public String getRoute() {
        return "new_speaker_organizer";
    }
}
