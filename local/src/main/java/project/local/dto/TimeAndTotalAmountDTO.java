package project.local.dto;

import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TimeAndTotalAmountDTO {

    private int month;
    private int totalAmount;
}
