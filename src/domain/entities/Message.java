package domain.entities;

public class Message {

    private String userId;
    private String message;

    /**
     * A userId and message is required to create an instance of Message.
     * @param userId
     * @param message
     */

    public Message(String userId, String message){
        this.userId = userId;
        this.message = message;
    }

    /**
     * Returns the User ID of the user who sent the message.
     * @return userId
     */

    public String getUserId() {

        return this.userId;
    }


    /**
     * This method sets the User ID of the user who sent the message.
     * @param userId
     */

    public void setUserId(String ID) {

        this.userId=ID;
    }


    /**
     * Returns the message.
     * @return message
     */

    public String getMessage() {
        return this.message;
    }

    /**
     * This method sets the message.
     * @param message
     */

    public void setMessage(String message) {
        this.message=message;
    }
}