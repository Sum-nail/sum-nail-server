package backend.sumnail.domain.nail_shop.controller;

import backend.sumnail.domain.nail_shop.controller.dto.response.NailShopFindAllResponse;
import backend.sumnail.domain.nail_shop.controller.dto.response.NailShopFindOneResponse;
import backend.sumnail.domain.nail_shop.service.NailShopService;
import backend.sumnail.domain.recentsearch.service.RecentSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/nail-shops")
@RequiredArgsConstructor
public class NailShopController {
    private final NailShopService nailShopService;

    private final RecentSearchService recentSearchService;

    @GetMapping("")
    public ResponseEntity<List<NailShopFindAllResponse>> getAllShops() {
        List<NailShopFindAllResponse> response = nailShopService.findAllShop();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("search")
    public ResponseEntity<List<NailShopFindAllResponse>> searchShops(@RequestParam String hashtags, @RequestParam String station) {
        List<NailShopFindAllResponse> response = nailShopService.searchNailShop(station, hashtags);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{nailShopId}")
    public ResponseEntity<NailShopFindOneResponse> searchShopById(@PathVariable Long nailShopId) {
        NailShopFindOneResponse response = nailShopService.findNailShopById(nailShopId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
