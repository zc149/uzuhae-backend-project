package project.local.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "subscribe_info")
@Builder
//@RequiredArgsConstructor
//@AllArgsConstructor
//구독정보
public class SubscribeInfo {

    @Id
    @Column(name = "SUBSCRIBEINFO_ID", nullable = false)
    private Long id;

    @Column(name = "SUBSCRIBEINFO_TITLE",nullable = false)
    private String title;

    @Column(name = "SUBSCRIBEINFO_FEE",nullable = false)
    private String fee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANYINFO_ID")
    private CompanyInfo companyInfo;

    @Column(name = "SUBSCRIBEINFO_CATEGORY",nullable = false)
    private String category;

    @OneToMany(mappedBy = "subscribeInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Singular
    private List<SubscribeBenefit> subscribeBenefits;

    public SubscribeInfo(Long id, String title, String fee, CompanyInfo companyInfo, String category, List<SubscribeBenefit> subscribeBenefits) {
        this.id = id;
        this.title = title;
        this.fee = fee;
        this.companyInfo = companyInfo;
        this.category = category;
        this.subscribeBenefits = subscribeBenefits;
    }

}
