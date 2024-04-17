package project.local.dto.map;

import lombok.*;

@Builder
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class MapBenefitDTO {

    private String image;
    private String cardName;
    private String benefitTitle;
    private String benefitSummary;
}
