package backend.sumnail.domain.nail_shop.controller;

import backend.sumnail.domain.nail_shop.controller.dto.response.NailShopFindAllResponse;
import backend.sumnail.domain.nail_shop.controller.dto.response.NailShopFindOneResponse;
import backend.sumnail.domain.nail_shop.service.NailShopService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/nail-shops")
@RequiredArgsConstructor
public class NailShopController {
    private final NailShopService nailShopService;

    @GetMapping("")
    public ResponseEntity<NailShopFindAllResponse> getAllShops(@PageableDefault(size = 6) Pageable pageable) {
        NailShopFindAllResponse response = nailShopService.findAllShop(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("search")
    public ResponseEntity<NailShopFindAllResponse> searchShops(@RequestParam List<String> hashtags,
                                                                     @RequestParam String station) {
        NailShopFindAllResponse response = nailShopService.searchNailShop(station, hashtags);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{nailShopId}")
    public ResponseEntity<NailShopFindOneResponse> searchShopById(@PathVariable Long nailShopId) {
        NailShopFindOneResponse response = nailShopService.findNailShopById(nailShopId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
