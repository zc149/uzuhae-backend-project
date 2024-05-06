package project.local.entity.userInfo;

import lombok.*;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "ANNUALBENEFITS")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AnnualBenefits implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "TWO", nullable = true)
    private int two;

    @Column(name = "FOUR", nullable = true)
    private int four;

    @Column(name = "SIX", nullable = true)
    private int six;

    @Column(name = "EIGHT", nullable = true)
    private int eight;

    @Column(name = "TEN", nullable = true)
    private int ten;

    @Column(name = "TWELVE", nullable = true)
    private int twelve;
}
