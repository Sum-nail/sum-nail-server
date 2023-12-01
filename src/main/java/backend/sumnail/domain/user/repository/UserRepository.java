package backend.sumnail.domain.user.repository;

import backend.sumnail.domain.user.entity.User;

public interface UserRepository {
    User getById(long id);
}
