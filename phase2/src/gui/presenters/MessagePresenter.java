package gui.presenters;

import api.API;
import gui.helpers.ClientData;

import java.util.*;

public class MessagePresenter {
    private final API api;
    private final ClientData clientData;

    public MessagePresenter(API api, ClientData clientData) {
            this.api = api;
            this.clientData = clientData;
        }

        private ArrayList<HashMap<String, String>> getAttendeeMessageDetails() {
            String[] messages = api.call("Message_controller", this.clientData.getAccessCode(),
                    "getConvoWithOtherUserByID")
                    .replaceAll("[\\[\\]\\s]", "").split(",");

            ArrayList<HashMap<String, String>> data = new ArrayList<>();
            for (String message : messages) {
                HashMap<String, String> map = new HashMap<>();

                map.put("message", message);

                data.add(map);
            }

            return data;
        }

    }

