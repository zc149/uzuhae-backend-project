package project.local.dto.mypage;

import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BillsDTO {

    private Long chargeId;
    private Long userId;
    private int chargeMonth;
    private int chargeDay;
    private int chargeAmt;
    private int benefits;

}
