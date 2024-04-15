package project.local.dto.CardDetailDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CardDetailRequestDTO {
    private Long id;
    private String category;
    private String cardType;
    private Long cardId;
}
