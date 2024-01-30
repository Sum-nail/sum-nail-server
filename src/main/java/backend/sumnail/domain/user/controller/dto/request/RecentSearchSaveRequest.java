package backend.sumnail.domain.user.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class RecentSearchSaveRequest {
    private String stationName;
}
