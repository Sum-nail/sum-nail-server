package backend.sumnail.domain.user.controller.dto;

import backend.sumnail.domain.user.controller.dto.response.UserFindNailShopResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindSearchStationsResponse;
import backend.sumnail.domain.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // TODO 테스트 용 userId 1 번 사용중
    @GetMapping("profile")
    public ResponseEntity<UserFindResponse> findUser() {
        UserFindResponse response = userService.findUser(1);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("nail-shops")
    public ResponseEntity<List<UserFindNailShopResponse>> findAllNailShopsUser() {
        List<UserFindNailShopResponse> responses = userService.findAllNailShopsUser(1);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PostMapping("nail-shops/{nailShopId}")
    public ResponseEntity<Void> saveNailShopUser(@PathVariable("nailShopId") long nailShopId) {
        userService.saveNailShopUser((long) 1, nailShopId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("nail-shops/{nailShopId}")
    public ResponseEntity<Void> deleteNailShopUser(@PathVariable("nailShopId") long nailShopId) {
        userService.deleteNailShopUser(1, nailShopId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("search-station-history")
    public ResponseEntity<UserFindSearchStationsResponse> findSearchStationsUser(long userId) {
        UserFindSearchStationsResponse response = userService.findSearchStationsUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("search-station-history")
    public ResponseEntity<Void> deleteSearchStationsUser(long userId) {
        userService.deleteSearchStationsUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
