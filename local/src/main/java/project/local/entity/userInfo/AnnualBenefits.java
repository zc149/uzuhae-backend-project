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

    @Column(name = "two", nullable = true)
    private int two;

    @Column(name = "four", nullable = true)
    private int four;

    @Column(name = "six", nullable = true)
    private int six;

    @Column(name = "eight", nullable = true)
    private int eight;

    @Column(name = "ten", nullable = true)
    private int ten;

    @Column(name = "twelve", nullable = true)
    private int twelve;
}
