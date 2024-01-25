package backend.sumnail.domain.user.controller.dto.response;

import backend.sumnail.domain.recentsearch.entity.RecentSearch;

import java.util.List;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class UserFindSearchStationsResponse {
    private List<String> stations;

    public static UserFindSearchStationsResponse from(List<RecentSearch> recentSearchs) {
        List<String> stations = recentSearchs.stream()
                .map(RecentSearch::getStation)
                .toList();

        return UserFindSearchStationsResponse.builder()
                .stations(stations)
                .build();
    }
}
