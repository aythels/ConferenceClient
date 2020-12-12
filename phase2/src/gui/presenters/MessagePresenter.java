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

            String[] messages = api.call("Message_controller", this.clientData.accessCode,
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

        public String[] getAllConvoUsers(){
            String[] userIDs = api.call("messenger_controller", clientData.accessCode,
                    "getMessagebleUserIDs", clientData.accessCode).
                    replaceAll("[\\[\\]\\s]", "").split(",");
            return userIDs;
        }

        public String[] getAllConvoMessages(String userID){
            String[] messages = api.call("messenger_controller", clientData.accessCode,
                    "getConvoWithOtherUserByID", clientData.accessCode, userID).
                    replaceAll("[\\[\\]\\s]", "").split(",");

            return messages;
        }

        public String getUserDisplayName(String userID) {
            return api.call("user_controller", null,
                    "getUserName",
                    userID);
        }

    }

