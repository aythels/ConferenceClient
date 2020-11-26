package core.entities;

import java.time.LocalTime;
import java.util.Objects;

public class TimeSlot implements Comparable<TimeSlot> {

    private LocalTime startTime;
    private LocalTime endTime;

    public TimeSlot(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return startTime.equals(timeSlot.startTime) &&
                endTime.equals(timeSlot.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }

    @Override
    public int compareTo(TimeSlot o) {
        if (startTime.equals(o.startTime) && endTime.equals(o.endTime)) {
            return 0;
        } else if (startTime.isBefore(o.startTime) && endTime.isBefore(o.endTime)) {
            return -1;
        } else {
            return 1;
        }
    }
}
