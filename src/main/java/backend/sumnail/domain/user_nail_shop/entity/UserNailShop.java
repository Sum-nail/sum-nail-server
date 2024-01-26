package backend.sumnail.domain.user_nail_shop.entity;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserNailShop {
    @Id
    @Column(name = "user_nail_shop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nail_shop_id")
    private NailShop nailShop;


    @Builder
    public UserNailShop(Long id, User user, NailShop nailShop) {
        this.id = id;
        this.user = user;
        this.nailShop = nailShop;
    }

    public static UserNailShop createUserNailShop(User user, NailShop nailShop) {
        return UserNailShop.builder()
                .user(user)
                .nailShop(nailShop)
                .build();
    }

}
