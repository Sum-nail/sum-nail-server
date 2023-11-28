package backend.sumnail.domain.user_nail_shop.entity;

import backend.sumnail.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne(fetch =  FetchType.LAZY)
//    @JoinColumn(name = "nail_shop_id")
//    private NailShop nailShop;


//    @Builder
//    public UserNailShop(User user, NailShop nailShop){
//        this.user = user;
//        this.nailShop = nailShop;
//    }
//
//    public UserNailShop createUserNailShop(User user, NailShop nailShop){
//        return UserNailShop.builder()
//                .user(user)
//                .nailShop(nailShop)
//                .build();
//    }

}
