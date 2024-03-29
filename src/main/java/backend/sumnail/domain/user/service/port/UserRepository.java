package backend.sumnail.domain.user.service.port;

import backend.sumnail.domain.user.entity.User;
import java.util.Optional;

public interface UserRepository {
    User getById(Long id);

    void save(User user);

    Optional<User> findByEmail(String email);

    User getByEmail(String email);
}
