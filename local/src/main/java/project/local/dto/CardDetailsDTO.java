package project.local.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardDetailsDTO {

    private Long cardId;
    private String imageURL;
    private String cardName;
    private String cardCompany;
    private List<String> benefitTitle;
    private List<String> benefitSummary;
    private String annualFee;
    private String previousAmount;
//    private String cardURL;
}
