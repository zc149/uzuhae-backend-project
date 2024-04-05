package project.local.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "question_content")
//문의내역
public class QuestionContent {

    @Id
    @Column(name = "QUESTIONCONTENT_ID",nullable = false)
    private Long questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERINFO_ID")
    private UserInfo userInfo;

    @Column(name = "QUESTIONCONTENT_CATEGORY",nullable = false)
    private String category;

    @Column(name = "QUESTIONCONTENT_TITLE",nullable = false)
    private String title;

    @Column(name = "QUESTIONCONTENT_CONTENT",nullable = false)
    private String content;

    @Column(name = "QUESTIONCONTENT_INANSWER",nullable = false)
    private int isAnswer;

    @Column(name = "QUESTIONCONTENT_QUESTIONDATE",nullable = false)
    private Date questionDate;

    @Column(name = "QUESTIONCONTENT_VIEWS",nullable = false)
    private int views;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTIONCONTENT_ID")
    private Answering answering;

    @Builder
    public QuestionContent(Long questionId, UserInfo userInfo, String category, String title, String content, int isAnswer, Date questionDate, int views, Answering answering) {
        this.questionId = questionId;
        this.userInfo = userInfo;
        this.category = category;
        this.title = title;
        this.content = content;
        this.isAnswer = isAnswer;
        this.questionDate = questionDate;
        this.views = views;
        this.answering = answering;
    }
}
