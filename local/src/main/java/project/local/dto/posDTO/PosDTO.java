package project.local.dto.posDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PosDTO {
    private Long id;
    private int realPay;
    private int resultPay;
}
