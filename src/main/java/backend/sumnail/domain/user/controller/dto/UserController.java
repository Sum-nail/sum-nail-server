package backend.sumnail.domain.user.controller.dto;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.user.controller.dto.response.UserFindNailShopResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindSearchStationsResponse;
import backend.sumnail.domain.user.entity.User;
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
@RequestMapping("/user/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("profile")
    public ResponseEntity<UserFindResponse> findUser() {
        UserFindResponse response = userService.findUser(1); // TODO 테스트 용 userId
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("nail-shops")
    public ResponseEntity<List<UserFindNailShopResponse>> findAllNailShopsUser() {
        List<UserFindNailShopResponse> responses = userService.findAllNailShopsUser(1);// TODO 테스트 용 userId
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PostMapping("nail-shops/{nailShopId}")
    public ResponseEntity<Void> saveNailShopUser(@PathVariable("nailShopId") long nailShopId){
        userService.saveNailShopUser(1, nailShopId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("nail-shops/{nailShopId}")
    public ResponseEntity<Void> deleteNailShopUser(@PathVariable("nailShopId") long nailShopId){
        userService.deleteNailShopUser(1,nailShopId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }







}
