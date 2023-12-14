package backend.sumnail.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 공통 예외
    BAD_REQUEST_PARAM(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    NOT_VALID_URI(HttpStatus.BAD_REQUEST, "유효한 경로로 요청해주세요."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),

    // User 예외
    UNAUTHORIZED_ID(HttpStatus.UNAUTHORIZED, "아이디가 틀립니다."),
    UNAUTHORIZED_PASSWORD(HttpStatus.UNAUTHORIZED, "패스워드가 틀립니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
    EXIST_MEMBER_PREFERRED_FEED(HttpStatus.CONFLICT, "이미 좋아요한 피드입니다."),


    // Token 예외
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다."),
    UNAUTHORIZED_TOKEN(HttpStatus.UNAUTHORIZED, "인증되지 않은 토큰입니다."),

    // NailShop 예외
    NOT_FOUND_NAIL_SHOP(HttpStatus.NOT_FOUND, "해당 네일샵 찾을 수 없습니다."),

    // HashTag 예외
    NOT_FOUND_HASHTAG(HttpStatus.NOT_FOUND, "해당 해시태그를 찾을 수 없습니다."),
   ;

    private final HttpStatus status;
    private final String error;
}