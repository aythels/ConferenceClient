package domain.entities;

public class Event {

    private final int eventID;
    private String eventName;
    private int eventDuration;
    private int eventTime;
    private boolean vip = false;

    /**
     * An ID is required to create an instance of Event.
     * @param eventID
     */

    public Event(int eventID, String eventName, int eventDuration, int eventTime){
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDuration = eventDuration;
        this.eventTime = eventTime;
    }

    /**
     * This method gets the Event's identifier.
     * @return the event's identifier
     */

    public int getEventID() {
        return this.eventID;
    }

    /**
     * This method gets the display name of this Event.
     * @return the event's display name
     */

    public String getEventName() {
        return this.eventName;
    }

    /**
     * This method sets the display name of this Event.
     * @param eventName
     */

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Returns the duration of this Event.
     * @return eventDuration
     */

    public int getEventDuration() {

        return eventDuration;
    }

    /**
     * This method sets the duration of this Event.
     * @param eventDuration
     */

    public void setEventDuration(int eventDuration) {

        this.eventDuration = eventDuration;
    }

    /**
     * Returns the time of this Event.
     * @return eventTime
     */

    public int getEventTime() {
        return eventTime;
    }

    /**
     * This method sets the time of this Event.
     * @param eventTime
     */

    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }

    public boolean getVip(){return this.vip;}

    public void setVip(boolean vip){this.vip = vip; }
}