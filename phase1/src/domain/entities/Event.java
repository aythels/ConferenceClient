package domain.entities;

public class Event {

    private int eventID;
    private String eventName;
    private int eventDuration;
    private int eventTime;

    /**
     * An ID is required to create an instance of Event.
     * @param eventId
     */

    public Event(int eventID, String eventName, int eventDuration, int eventTime){
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDuration = eventDuration;
        this.eventTime = eventTime;
    }

    public int getEventID() {
        return this.eventID;
    }

    public String getEventName() {
        return this.eventName;
    }

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
}