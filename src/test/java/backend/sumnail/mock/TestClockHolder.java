package backend.sumnail.mock;

import backend.sumnail.domain.common.service.port.ClockHolder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestClockHolder implements ClockHolder {
    private final LocalDateTime mills;

    public TestClockHolder(String dateTimeString) {
        this.mills = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
    }

    @Override
    public LocalDateTime millis() {
        return mills;
    }
}
