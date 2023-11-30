package backend.sumnail.domain.nail_shop.controller;

import backend.sumnail.domain.nail_shop.controller.dto.response.NailShopFindAllResponse;
import backend.sumnail.domain.nail_shop.service.NailShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nail-shops")
@RequiredArgsConstructor
public class NailShopController {
    private final NailShopService nailShopService;

    @GetMapping("")
    public ResponseEntity<List<NailShopFindAllResponse>> getAllShops(){
        List<NailShopFindAllResponse> response=nailShopService.findAllShop();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
