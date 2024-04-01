package project.local.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "userinfo")
@Builder
//회원정보
public class UserInfo {

    @Id
    @Column(name = "USERINFO_ID",nullable = false)
    private int id;

    @Column(name = "USERINFO_PASSWORD",nullable = false)
    private String password;

    @Column(name = "USERINFO_NICKNAME",nullable = false)
    private String nickName;

    @Column(name = "USERINFO_NAME",nullable = false)
    private String name;

    @Column(name = "USERINFO_JOINDATE",nullable = false)
    private Date joinDate;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Singular // List에 항목을 추가하기 위한 빌더 메소드를 자동 생성
    private List<QuestionContent> questionContents;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Singular
    private List<MapSearch> mapSearches;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Singular
    private List<UserCard> userCards;



    @Builder
    public UserInfo(int id, String password, String nickName, String name, Date joinDate, List<QuestionContent> questionContents, List<MapSearch> mapSearches, List<UserCard> userCards) {
        this.id = id;
        this.password = password;
        this.nickName = nickName;
        this.name = name;
        this.joinDate = joinDate;
        this.questionContents = questionContents;
        this.mapSearches = mapSearches;
        this.userCards = userCards;
    }
}
