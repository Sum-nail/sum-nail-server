package backend.sumnail.domain.recentsearch.entity;

import backend.sumnail.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RecentSearch {

    @Id
    @Column(name = "recent_search_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "user_id")
    private User user;

    private String station;

    private LocalDateTime dateTime;

    @Builder
    private RecentSearch(User user, String station, LocalDateTime dateTime){
        this.user = user;
        this.station = station;
        this.dateTime = dateTime;
    }

    public static RecentSearch createRecentSearch(User user, String station, LocalDateTime dateTime){
        return RecentSearch.builder()
                .user(user)
                .station(station)
                .dateTime(dateTime)
                .build();
    }
}
