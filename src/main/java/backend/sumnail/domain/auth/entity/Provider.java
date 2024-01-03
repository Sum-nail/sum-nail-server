package backend.sumnail.domain.auth.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Provider {
    KAKAO("kakao"),
    GOOGLE("google");

    private final String providerName;
}
