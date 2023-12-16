package backend.sumnail.domain.hashtag.controller;

import backend.sumnail.domain.hashtag.controller.dto.response.HashtagFindAllResponse;
import backend.sumnail.domain.hashtag.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/hashtags")
@RequiredArgsConstructor
public class HashtagController {

    private final HashtagService hashtagService;
    @GetMapping("")
    public ResponseEntity<HashtagFindAllResponse> findAllHashtags(){
        HashtagFindAllResponse response=hashtagService.findAllHashtag();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
