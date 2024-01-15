package backend.sumnail.mock;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.hashtag.repository.HashtagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeHashtagRepository implements HashtagRepository {

    private final Long id=new AtomicLong().getAndIncrement();
    private final List<Hashtag> data=new ArrayList<>();

    @Override
    public Optional<Hashtag> findByHashtagName(String hashtagName) {
        return data.stream()
                .filter(item->item.getHashtagName().equals(hashtagName))
                .findAny();
    }

    @Override
    public List<Hashtag> findAll() {
        return data;
    }

    @Override
    public Hashtag getById(Long id) {
        return data.stream()
                .filter(item->item.getId().equals(id))
                .findAny()
                .orElseThrow();
    }

    public Hashtag save(Hashtag hashtag){
        if(hashtag.getId()==null||hashtag.getId()==0)
        {
            Hashtag newHashtag=Hashtag.builder()
                    .id(id)
                    .hashtagName(hashtag.getHashtagName())
                    .build();
        }
        else{
            data.removeIf(it->it.getId()==hashtag.getId());
        }
        data.add(hashtag);
        return hashtag;
    }
}
