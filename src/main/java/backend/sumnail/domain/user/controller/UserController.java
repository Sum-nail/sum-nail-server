package backend.sumnail.domain.user.controller;

import backend.sumnail.domain.recentsearch.service.RecentSearchService;
import backend.sumnail.domain.user.controller.dto.request.RecentSearchSaveRequest;
import backend.sumnail.domain.user.controller.dto.response.UserFindNailShopResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindSearchStationsResponse;
import backend.sumnail.domain.user.service.UserService;
import backend.sumnail.global.config.jwt.PrincipalDetails;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RecentSearchService recentSearchService;

    /**
     * 나의 프로필 조회
     */
    @GetMapping("profile")
    public ResponseEntity<UserFindResponse> findUser(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        UserFindResponse response = userService.findUser(principalDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 저장한 네일샵 전체 조회
     */
    @GetMapping("nail-shops")
    public ResponseEntity<List<UserFindNailShopResponse>> findAllNailShopsUser(
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<UserFindNailShopResponse> responses = userService.findAllNailShopsUser(principalDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    /**
     * 네일샵 저장하기
     */
    @PostMapping("nail-shops/{nailShopId}")
    public ResponseEntity<Void> saveNailShopUser(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                 @PathVariable("nailShopId") long nailShopId) {
        userService.saveNailShopUser(principalDetails.getUser().getId(), nailShopId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 네일샵 저장 취소하기
     */
    @DeleteMapping("nail-shops/{nailShopId}")
    public ResponseEntity<Void> deleteNailShopUser(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                   @PathVariable("nailShopId") long nailShopId) {
        userService.deleteNailShopUser(principalDetails.getUser().getId(), nailShopId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 지하철 역 검색 내역 조회
     */
    @GetMapping("search-station-history")
    public ResponseEntity<UserFindSearchStationsResponse> findSearchStationsUser(
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        UserFindSearchStationsResponse response = userService.findSearchStationsUser(
                principalDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 지하철 역 검색 기록 전체 삭제
     */
    @DeleteMapping("search-station-history")
    public ResponseEntity<Void> deleteSearchStationsUser(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        userService.deleteSearchStationsUser(principalDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 지하철 역 검색 기록 추가
     */
    //TODO station name -> request param으로 이동?
    @PostMapping("search-station-history")
    public ResponseEntity<Void> saveSearchStationsUser(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                       @RequestBody RecentSearchSaveRequest request) {
        recentSearchService.addRecentSearch(principalDetails.getUser().getId(), request.getStationName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
