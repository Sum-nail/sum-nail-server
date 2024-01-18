package backend.sumnail.mock;

import backend.sumnail.domain.common.service.port.ClockHolder;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestClockHolder implements ClockHolder {
    private final LocalDateTime mills;
    @Override
    public LocalDateTime millis() {
        return mills;
    }
}
