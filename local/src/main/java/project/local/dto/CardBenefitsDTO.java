package project.local.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardBenefitsDTO {

    private String type;
    private List<String> benefits;

}
