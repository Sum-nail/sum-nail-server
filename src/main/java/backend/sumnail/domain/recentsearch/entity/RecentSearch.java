package backend.sumnail.domain.recentsearch.entity;

import backend.sumnail.domain.common.service.port.ClockHolder;
import backend.sumnail.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RecentSearch {

    @Id
    @Column(name = "recent_search_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String station;

    @Column(updatable = false)
    private LocalDateTime dateTime;

    @Builder
    private RecentSearch(Long id, User user, String station, LocalDateTime dateTime) {
        this.id = id;
        this.user = user;
        this.station = station;
        this.dateTime = dateTime;
    }

    public static RecentSearch createRecentSearch(User user, String station, ClockHolder clockHolder) {
        return RecentSearch.builder()
                .user(user)
                .station(station)
                .dateTime(clockHolder.millis())
                .build();
    }
}
