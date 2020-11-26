import org.junit.*;
import core.entities.Event;

import java.time.Duration;
import java.time.LocalTime;

public class TemplateTest {

    @Test
    public void testEventDuration() {
        Event e = new Event("test", LocalTime.of(1, 0), LocalTime.of(2, 0));
        Duration duration = Duration.ofHours(1);
        Assert.assertEquals(duration, e.getDuration());
    }
}
