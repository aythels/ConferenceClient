package core.entities;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class Event {

    private String title;
    private String room;
    private int capacity;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String title, String room, int capacity, LocalTime startTime, LocalTime endTime) {
        this.title = title;
        this.room = room;
        this.capacity = capacity;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return title.equals(event.title) &&
                startTime.equals(event.startTime) &&
                endTime.equals(event.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, startTime, endTime);
    }
}