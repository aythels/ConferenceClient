package domain.entities;

public class Event {

    private String eventId;
    private int eventDuration;
    private int eventTime;

    /**
     * An ID is required to create an instance of Event.
     * @param eventId
     */

    public Event(String eventId){
        this.eventId = eventId;
    }

    public String getEventId() {
        return this.eventId;
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