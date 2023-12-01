package backend.sumnail.domain.user.service;

import backend.sumnail.domain.user.entity.User;
import backend.sumnail.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findUser(final Long userId) {
        return userRepository.getById(userId);
    }

}
