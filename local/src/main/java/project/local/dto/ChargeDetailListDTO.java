package project.local.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@RequiredArgsConstructor
@Getter
@ToString
@NoArgsConstructor
public class ChargeDetailListDTO {

    private Long id;
    private String cardIdenty;
    private String useDate;
    private String dealNum;
    private String usePay;
    private String callCode;
    private String storeName;
    private String storeBusinessNum;
    private String classiBusinessBig;
    private String classiBusinessSmall;

}
