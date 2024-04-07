package project.local.entity.cardInfo;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "CARD_COMPANY")
//카드사정보
public class CardCompany {

    @Id
    @Column(name = "COMPANY_NAME", nullable = false)
    private String id;

    @Column(name = "CARD_QUANTITY", nullable = false)
    private int cardQuantity;

    @Column(name = "SUBSCRIPTION_QUANTITY", nullable = false)
    private int subscriptionQuantity;

//    @OneToMany(mappedBy = "cardCompany", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Singular
//    private List<Card> cards;
//
//    @OneToMany(mappedBy = "cardCompany", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Singular
//    private List<SubscriptionBenefits> subscriptionBenefits;

}