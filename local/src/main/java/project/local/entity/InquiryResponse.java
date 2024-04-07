package project.local.entity;

import lombok.*;


import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
@Table(name = "INQUIRY_RESPONSE")
//문의답변
public class InquiryResponse {

    @Id // id 필드를 기본키로 지정
    @Column(name = "RESPONSE_ID",nullable = false)
    private Long id;

    @Column(name = "RESPONSE_TITLE",nullable = false)
    private String title;

    @Column(name = "RESPONSE_CONTENT",nullable = false)
    private String content;

    @Column(name = "RESPONSE_DATE",nullable = false)
    private Date date;

    @OneToOne
    @MapsId
    @JoinColumn(name = "INQUIRY_ID")
    private Inquiry inquiry;


}
