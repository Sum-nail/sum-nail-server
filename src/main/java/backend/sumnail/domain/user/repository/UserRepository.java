package backend.sumnail.domain.user.repository;

import backend.sumnail.domain.user.entity.User;
import org.springframework.stereotype.Repository;

public interface UserRepository {
    User getById(long id);
}
