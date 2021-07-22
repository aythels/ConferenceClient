package domain.entities;

public class Event {

    private final int eventID;
    private String eventName;
    private int eventDuration;
    private int eventTime;
    private int capacity;
    private boolean vip = false;

    /**
     *
     * @param eventID id of the event
     * @param eventName name of the event
     * @param eventDuration duration of the event
     * @param eventTime time the event starts at
     * @param capacity capacity for the event
     */
    public Event(int eventID, String eventName, int eventDuration, int eventTime, int capacity){
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDuration = eventDuration;
        this.eventTime = eventTime;
        this.capacity = capacity;
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
     * @param eventName name of the event to be set
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
     * @param eventDuration duration of the event to be set
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
     * @param eventTime start time of the event to be set
     */

    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * Returns the capacity of this Event
     * @return capacity
     */
    public int getCapacity() { return capacity; }

    /**
     * Sets the capacity for the Event
     * @param capacity capacity of the event to be set
     */
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public boolean getVip(){return this.vip;}

    public void setVip(boolean vip){this.vip = vip; }
}