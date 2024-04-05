package project.local.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "map_benefits")
//카드혜택(지도상세)
public class MapBenefits {

    @Id
    @Column(name = "MAPBENEFITS_ID",nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARDINFO_ID")
    private CardInfo cardInfo;

    @Column(name = "MAPBENEFITS_BENEFITSDETAIL",nullable = false)
    private String benefitsDetail;

    @Column(name = "MAPBENEFITS_CATEGORY",nullable = false)
    private String category;

    @Builder
    public MapBenefits(Long id, CardInfo cardInfo, String benefitsDetail, String category) {
        this.id = id;
        this.cardInfo = cardInfo;
        this.benefitsDetail = benefitsDetail;
        this.category = category;
    }
}
