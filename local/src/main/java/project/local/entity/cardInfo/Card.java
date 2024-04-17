package project.local.entity.cardInfo;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "CARD")
//카드정보
public class Card {

    @Id
    @Column(name = "CARD_ID")
    private Long id;

    @Column(name = "CARD_NAME")
    private String cardName;

    @Column(name = "CARD_TYPE")
    private String cardType;

    @Column(name = "ANNUAL_FEE")
    private String annualFee;

    @Column(name = "PREVIOUS_AMOUNT")
    private String previousAmount;

    @Column(name = "COMPANY_NAME")
    private String cardCompany;

    @Column(name = "CARD_IMAGE")
    private String cardImage;

//    @Column(name = "CARD_URL", nullable = false)
//    private String cardURL;

//    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Singular
//    private List<CardBenefits> cardBenefits;

}
