package project.local.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class CardsDTO {

    private Long id;
    private String cardNum;
    private String cardGood;
    private String cardKind;
    private String cardCompany;
    private String yearFee;
}
