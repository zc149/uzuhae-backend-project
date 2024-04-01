package project.local.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "faq")
//FAQ
public class Faq {

    @Id
    @Column(name = "FAQ_ID",nullable = false)
    private Long id;

    @Column(name = "FAQ_TITLE",nullable = false)
    private String title;

    @Column(name = "FAQ_CONTENT",nullable = false)
    private String content;

    @Column(name = "FAQ_VIEW",nullable = false)
    private int view;

    @Column(name = "FAQ_CATEGORY",nullable = false)
    private String category;

    @Builder
    public Faq(Long id, String title, String content, int view, String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.view = view;
        this.category = category;
    }
}
