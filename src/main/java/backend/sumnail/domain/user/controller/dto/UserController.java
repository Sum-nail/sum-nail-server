package backend.sumnail.domain.user.controller.dto;

import backend.sumnail.domain.recentsearch.service.RecentSearchService;
import backend.sumnail.domain.user.controller.dto.request.RecentSearchSaveRequest;
import backend.sumnail.domain.user.controller.dto.response.UserFindNailShopResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindSearchStationsResponse;
import backend.sumnail.domain.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RecentSearchService recentSearchService;
    // TODO 테스트 용 userId 1 번 사용중

    /**
     * 나의 프로필 조회
     */
    @GetMapping("profile")
    public ResponseEntity<UserFindResponse> findUser() {
        UserFindResponse response = userService.findUser(1);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 저장한 네일샵 전체 조회
     */
    @GetMapping("nail-shops")
    public ResponseEntity<List<UserFindNailShopResponse>> findAllNailShopsUser() {
        List<UserFindNailShopResponse> responses = userService.findAllNailShopsUser(1);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    /**
     * 네일샵 저장하기
     */
    @PostMapping("nail-shops/{nailShopId}")
    public ResponseEntity<Void> saveNailShopUser(@PathVariable("nailShopId") long nailShopId) {
        userService.saveNailShopUser(1, nailShopId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 네일샵 저장 취소하기
     */
    @DeleteMapping("nail-shops/{nailShopId}")
    public ResponseEntity<Void> deleteNailShopUser(@PathVariable("nailShopId") long nailShopId) {
        userService.deleteNailShopUser(1, nailShopId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 지하철 역 검색 내역 조회
     */
    @GetMapping("search-station-history")
    public ResponseEntity<UserFindSearchStationsResponse> findSearchStationsUser() {
        UserFindSearchStationsResponse response = userService.findSearchStationsUser(1);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 지하철 역 검색 기록 전체 삭제
     */
    @DeleteMapping("search-station-history")
    public ResponseEntity<Void> deleteSearchStationsUser() {
        userService.deleteSearchStationsUser(1);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    /**
     * 지하철 역 검색 기록 추가
     */
    @PostMapping("search-station-history")
    public ResponseEntity<Void> saveSearchStationsUser(@RequestBody RecentSearchSaveRequest request) {
        recentSearchService.addRecentSearch(request.getStationName(), 1);
        //jwt 인증 구현 필요
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
