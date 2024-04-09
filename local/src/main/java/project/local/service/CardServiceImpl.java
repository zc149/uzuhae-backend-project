package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.dto.CardDetailsDTO;
import project.local.dto.SearchDTO;
import project.local.entity.cardInfo.Card;
import project.local.entity.cardInfo.CardBenefits;
import project.local.repository.mypage.CardBenefitsRepository;
import project.local.repository.mypage.CardRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CardServiceImpl {

    private final CardRepository cardRepository;
    private final CardBenefitsRepository cardBenefitsRepository;

    public List<Card> countAll() {
        return cardRepository.findAll();
    }

    public List<Card> countByTypes(String cardType) {
        return cardRepository.findByCardType(cardType);
    }

    public List<Card> countByTypesAndBenefits(String cardType, List<String> benefitList) {
        List<Card> byCardTypes = cardRepository.findByCardType(cardType);

        for (int i = 0; i < benefitList.size(); i++) {
            List<Card> forDetails = new ArrayList<>();
            List<CardBenefits> byCategories = cardBenefitsRepository.findByCategory(benefitList.get(i));
            for (CardBenefits byCategory : byCategories) {
                for (Card byCardType : byCardTypes) {
                    if (byCategory.getCard().getId() == byCardType.getId()) {
                        forDetails.add(byCardType);
                    }
                }
            }
            byCardTypes = forDetails;
        }

        return byCardTypes;
    }

    public List<CardDetailsDTO> findCardDetails(SearchDTO searchDTO) {
        List<Long> cardIds = searchDTO.getCardId();
        List<CardDetailsDTO> cards = new ArrayList<>();

        for (Long cardId : cardIds) {
            Card card = cardRepository.findById(cardId).orElse(null);
            List<CardBenefits> byCardIds = cardBenefitsRepository.findByCard_Id(cardId);
            List<String> benefitTitle = new ArrayList<>();
            List<String> benefitSummary = new ArrayList<>();

            for (CardBenefits byCardId : byCardIds) {
                benefitTitle.add(byCardId.getBenefitTitle());
                benefitSummary.add(byCardId.getBenefitSummary());
            }
            cards.add(CardDetailsDTO.builder()
                    .cardId(card.getId())
                    .imageURL(card.getCardImage())
                    .cardName(card.getCardName())
                    .cardCompany(card.getCardCompany().getId())
                    .benefitTitle(benefitTitle)
                    .benefitSummary(benefitSummary)
                    .annualFee(card.getAnnualFee())
                    .previousAmount(card.getPreviousAmount())
//                    .cardURL(card.getCardURL())
                    .build());
        }

        return cards;
    }
}
