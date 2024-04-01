package project.local.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "subscribebenefits")
//구독혜택
public class SubscribeBenefit {

    @Id
    @Column(name = "SUBSCRIBEBENEFITS_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBSCRIBEINFO_ID")
    private SubscribeInfo subscribeInfo;

    @Column(name = "SUBSCRIBEBENEFITS_TITLE",nullable = false)
    private String title;

    @Column(name = "SUBSCRIBEBENEFITS_CONTENT",nullable = false)
    private String content;

    @Builder
    public SubscribeBenefit(Long id, SubscribeInfo subscribeInfo, String title, String content) {
        this.id = id;
        this.subscribeInfo = subscribeInfo;
        this.title = title;
        this.content = content;
    }
}
