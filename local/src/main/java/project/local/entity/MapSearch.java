package project.local.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "mapsearch")
//지도검색
public class MapSearch {

    @Id
    @Column(name = "MAPSEARCH_ID",nullable = false)
    private Long id;

    @Column(name = "MAPSEARCH_SEARCHCONTENTS",nullable = false)
    private String searchContents;

    @Column(name = "MAPSEARCH_SEARCHDATE", nullable = false)
    private Date searchDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERINFO_ID")
    private UserInfo userInfo;

    @Builder
    public MapSearch(Long id, String searchContents, Date searchDate, UserInfo userInfo) {
        this.id = id;
        this.searchContents = searchContents;
        this.searchDate = searchDate;
        this.userInfo = userInfo;
    }
}
