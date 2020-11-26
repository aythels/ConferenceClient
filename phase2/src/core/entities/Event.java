package core.entities;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class Event {

    private String title;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String title, LocalTime startTime, LocalTime endTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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