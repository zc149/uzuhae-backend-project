package project.local.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "USER")
@Builder
//회원정보
public class User {

    @Id
    @Column(name = "USER_ID",nullable = false)
    private int id;

    @Column(name = "USER_PASSWORD",nullable = false)
    private String password;

    @Column(name = "USER_NICKNAME",nullable = false)
    private String nickName;

    @Column(name = "USER_NAME",nullable = false)
    private String name;

    @Column(name = "JOIN_DATE",nullable = false)
    private Date joinDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Singular // List에 항목을 추가하기 위한 빌더 메소드를 자동 생성
    private List<Inquiry> inquiries;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Singular
    private List<SearchHistory> searchHistories;


}
