package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.dto.local.LocalCardBenefitsDTO;
import project.local.dto.map.MapBenefitDTO;
import project.local.dto.mydata.CardsDTO;
import project.local.entity.Category;
import project.local.entity.cardInfo.Card;
import project.local.entity.cardInfo.CardBenefits;
import project.local.repository.CardBenefitsRepository;
import project.local.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MapServiceImpl {

    private final MyDataServiceImpl myDataService;
    private final CardBenefitsRepository cardBenefitsRepository;
    private final CardRepository cardRepository;

    public String getCategoryByCode(String code) {
        for (Category category : Category.values()) {
            if (category.name().equals(code)) {
                return category.getCategory();
            }
        }
        return null; // 일치하는 코드가 없는 경우 null 반환
    }

    public MapBenefitDTO findCardByCategory(String category, Long id) throws Exception {
        List<CardsDTO> cardsDTOS = myDataService.requestCards(id);

        for (CardsDTO cardsDTO : cardsDTOS) {
            Card card = cardRepository.findById(cardsDTO.getCardId()).orElse(null);
            List<CardBenefits> byCardId = cardBenefitsRepository.findByCard_Id(cardsDTO.getCardId());
            for (CardBenefits cardBenefit : byCardId) {
                if (cardBenefit.getCategory().contains(category)) {
                    return MapBenefitDTO.builder()
                            .image(card.getCardImage())
                            .cardName(card.getCardName())
                            .benefitTitle(cardBenefit.getBenefitTitle())
                            .benefitSummary(cardBenefit.getBenefitSummary())
                            .build();
                } else continue;
            }
        }
        return null;
    }

}
