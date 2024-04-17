package project.local.dto.CardDetailDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class CardDetailDTO {

    private Long benefits_Id;
    private String card_benefit_title;
    private String card_benefit_summary;
    private String card_company;
    private String card_img;

}
