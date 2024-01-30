package backend.sumnail.domain.common.infrastructure;

import backend.sumnail.domain.common.service.port.ClockHolder;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class SystemClockHolder implements ClockHolder {
    @Override
    public LocalDateTime millis() {
        return LocalDateTime.now();
    }
}
