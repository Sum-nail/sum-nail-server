package backend.sumnail.domain.user.repository;

import backend.sumnail.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User getById(long id) {
        return userJpaRepository.findById(id)
                // TODO 커스텀 에러 만든 후 수정
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));
    }
}
