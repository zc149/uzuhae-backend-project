package project.local.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class CardBenefitsDTO {

    private String type;
    private List<String> benefits;

}
