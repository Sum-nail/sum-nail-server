package backend.sumnail.domain.hashtag.repository;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagJpaRepository extends JpaRepository<Hashtag, Long> {
}
