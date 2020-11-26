package core.entities;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

/**
 * A class representing an event in the conference. It has a title,
 * room identifier, capacity limit, and a time slot.
 */
public class Event {

    /** The title of the event. */
    private String title;

    /** The room identifier. */
    private String room;

    /** The capacity of the event. */
    private int capacity;

    /** The start time in hours, minutes. */
    private LocalTime startTime;

    /** The end time in hours, minutes. */
    private LocalTime endTime;

    /**
     * Construct an Event given parameters.
     * @param title The title of the event.
     * @param room The room identifier.
     * @param capacity The capacity of the event.
     * @param startTime The start time.
     * @param endTime The end time.
     */
    public Event(String title, String room, int capacity, LocalTime startTime, LocalTime endTime) {
        this.title = title;
        this.room = room;
        this.capacity = capacity;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Get the title of the event.
     * @return The title of the event.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the event.
     * @param title New title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the room identifier.
     * @return The room identifier.
     */
    public String getRoom() {
        return room;
    }

    /**
     * Set the room identifier.
     * @param room The new room identifier.
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * Get the capacity of the room.
     * @return The capacity of the room.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Set the capacity of the room.
     * @param capacity The new capacity.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Get the start time.
     * @return The start time.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Set a new start time.
     * @param startTime New start time.
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Get the end time.
     * @return The end time.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Set the end time.
     * @param endTime The new end time.
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Get the duration of the event.
     * @return The duration as a Duration object.
     */
    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }

    /**
     * Implementation for boolean equality.
     * @param o The object being compared to.
     * @return True if equals. False otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return title.equals(event.title) &&
                startTime.equals(event.startTime) &&
                endTime.equals(event.endTime);
    }

    /**
     * Implementation for hash code.
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, startTime, endTime);
    }
}