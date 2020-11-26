package core.usecases.ports;

import java.time.LocalTime;

public interface IConferenceRules {

    LocalTime getConferenceStartTime();

    LocalTime getConferenceEndTime();
}
