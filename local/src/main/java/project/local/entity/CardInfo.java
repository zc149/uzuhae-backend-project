package project.local.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.File;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "card_info")
//카드정보
public class CardInfo {

    @Id
    @Column(name = "CARDINFO_ID",nullable = false)
    private Long id;

    @Column(name = "CARD_NAME", nullable = false)
    private String cardName;

    @Column(name = "CARDINFO_CARDTYPE",nullable = false)
    private String cardType;

    @Column(name = "CARDINFO_ANNUALFEE",nullable = false)
    private String annualFee;

    @Column(name = "CARDINFO_PREVIOUSAMOUNT",nullable = false)
    private String previousAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANYINFO_ID")
    private CompanyInfo companyInfo;

    @Column(name = "CARDINFO_IMAGEURL",nullable = false)
    private String imageURL; //정확히 맞는지 확인이 필요하다.

    @Builder

    public CardInfo(Long id, String cardName, String cardType, String annualFee, String previousAmount, CompanyInfo companyInfo, String imageURL) {
        this.id = id;
        this.cardName = cardName;
        this.cardType = cardType;
        this.annualFee = annualFee;
        this.previousAmount = previousAmount;
        this.companyInfo = companyInfo;
        this.imageURL = imageURL;
    }
}
