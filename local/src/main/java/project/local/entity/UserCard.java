package project.local.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.sql.Date;

//회원카드
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "user_card")
public class UserCard {

    @Id
    @Column(name = "USERCARD_ID",nullable = false)
    private int userCardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARDINFO_ID")
    private CardInfo cardInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERINFO_ID")
    private UserInfo userInfo;

    @Column(name = "USERCARD_ISSUEDATE",nullable = false)
    private Date issuanceDate;

    @Column(name = "USERCARD_EXPIRATIONDATE",nullable = false)
    private Date expirationPeriod;

    @Builder
    public UserCard(int userCardId, CardInfo cardInfo, UserInfo userInfo, Date issuanceDate, Date expirationPeriod) {
        this.userCardId = userCardId;
        this.cardInfo = cardInfo;
        this.userInfo = userInfo;
        this.issuanceDate = issuanceDate;
        this.expirationPeriod = expirationPeriod;
    }
}
