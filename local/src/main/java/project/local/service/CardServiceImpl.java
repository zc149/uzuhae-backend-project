package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.dto.local.LocalCardBenefitsDTO;
import project.local.dto.CardDetailsDTO;
import project.local.dto.SearchDTO;
import project.local.entity.cardInfo.Card;
import project.local.entity.cardInfo.CardBenefits;
import project.local.repository.CardBenefitsRepository;
import project.local.repository.CardRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CardServiceImpl {

    private final CardRepository cardRepository;
    private final CardBenefitsRepository cardBenefitsRepository;


    // 혜택 데이터 전부 불러오기
    public List<LocalCardBenefitsDTO> findAllCardBenefit() {
        List<CardBenefits> cardBenefits = cardBenefitsRepository.findAll();

        List<LocalCardBenefitsDTO> localCardBenefitsDTOS = new ArrayList<>();

        for (CardBenefits benefit : cardBenefits) {
            LocalCardBenefitsDTO localCardBenefitsDTO = LocalCardBenefitsDTO.builder()
                    .id(benefit.getId())
                    .category(benefit.getCategory())
                    .cardType(benefit.getCard().getCardType())
                    .cardId(benefit.getCard().getId()).build();

            localCardBenefitsDTOS.add(localCardBenefitsDTO);
        }


        return localCardBenefitsDTOS;
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
