package project.local.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.sql.Date;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "answering")
//문의답변
public class Answering{

    @Id // id 필드를 기본키로 지정
    @Column(name = "ANSWER_ID",nullable = false)
    private Long id;

    @Column(name = "ANSWER_TITLE",nullable = false)
    private String title;

    @Column(name = "ANSWER_CONTENT",nullable = false)
    private String content;

    @Column(name = "ANSWER_DATE",nullable = false)
    private Date date;

    @OneToOne(mappedBy = "answering", cascade = CascadeType.ALL, orphanRemoval = true)
    private QuestionContent questionContent;


    @Builder //빌더 패턴으로 객체 생성
    public Answering(Long id, String title, String content, Date date, QuestionContent questionContent) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.questionContent = questionContent;
    }
}
