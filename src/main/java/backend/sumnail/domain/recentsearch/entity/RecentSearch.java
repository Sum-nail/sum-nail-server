package backend.sumnail.domain.recentsearch.entity;

import backend.sumnail.domain.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
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
    @CreatedDate
    private LocalDateTime dateTime;

    @Builder
    private RecentSearch(User user, String station) {
        this.user = user;
        this.station = station;
    }

    public static RecentSearch createRecentSearch(User user, String station) {
        return RecentSearch.builder()
                .user(user)
                .station(station)
                .build();
    }
}
