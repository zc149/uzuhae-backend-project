package project.local.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "benefit_summary")
//카드혜택요약(카드검색)
public class BenefitSummary {

    @Id // 기본키로 설정
    @Column(name = "BENEFITSUMMARY_ID",nullable = false)
    private Long id;

    @Column(name = "BENEFITSUMMARY_BENEFITTITLE",nullable = false)
    private String benefitTitle;

    @Column(name = "BENEFITSUMMARY_BENEFITSUMMARY",nullable = false)
    private String benefitSummary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARDINFO_ID")
    private CardInfo cardInfo;


    @Builder
    public BenefitSummary(Long id, String benefitTitle, String benefitSummary, CardInfo cardInfo) {
        this.id = id;
        this.benefitTitle = benefitTitle;
        this.benefitSummary = benefitSummary;
        this.cardInfo = cardInfo;
    }
}
