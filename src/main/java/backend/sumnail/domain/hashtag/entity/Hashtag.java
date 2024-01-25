package backend.sumnail.domain.hashtag.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hashtag {
    public static final int MAX_HASHTAG_COUNT = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    private String hashtagName;

    @Builder
    public Hashtag(Long id, String hashtagName) {
        this.id = id;
        this.hashtagName = hashtagName;
    }
}
