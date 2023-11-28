package backend.sumnail.domain.user.controller.dto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String checkCd() {
        return "checkCd";
    }
}
