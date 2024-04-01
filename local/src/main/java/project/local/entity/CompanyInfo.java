package project.local.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Entity
@Table(name = "companyinfo")
//카드사정보
public class CompanyInfo {

    @Id
    @Column(name= "COMPANYINFO_ID",nullable = false)
    private Long id;

    @Column(name= "COMPANYINFO_COMPANYNAME",nullable = false)
    private String companyName;

    @OneToMany(mappedBy = "companyInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Singular
    private List<CardInfo> cardInfos;


    @OneToMany(mappedBy = "companyInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Singular
    private List<SubscribeInfo> subscribeInfos;


    public CompanyInfo(Long id, String companyName, List<CardInfo> cardInfos, List<SubscribeInfo> subscribeInfos) {
        this.id = id;
        this.companyName = companyName;
        this.cardInfos = cardInfos;
        this.subscribeInfos = subscribeInfos;
    }
}
