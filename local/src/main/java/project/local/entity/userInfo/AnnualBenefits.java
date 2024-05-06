package project.local.entity.userInfo;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ANNUALBENEFITS")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class AnnualBenefits {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "2", nullable = true)
    private int two;

    @Column(name = "4", nullable = true)
    private int four;

    @Column(name = "6", nullable = true)
    private int six;

    @Column(name = "8", nullable = true)
    private int eight;

    @Column(name = "10", nullable = true)
    private int ten;

    @Column(name = "12", nullable = true)
    private int twelve;
}
