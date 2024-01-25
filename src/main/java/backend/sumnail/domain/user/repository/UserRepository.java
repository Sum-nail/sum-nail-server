package backend.sumnail.domain.user.repository;

import backend.sumnail.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository {
    User getById(Long id);

    void save(User user);

    Optional<User> findByEmail(String email);

}
