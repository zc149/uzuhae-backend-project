package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.entity.cardInfo.CardBenefits;
import project.local.entity.cardInfo.Card;
import project.local.repository.mypage.CardBenefitsRepository;
import project.local.repository.mypage.CardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl {

    private final CardRepository cardRepository;
    private final CardBenefitsRepository cardBenefitsRepository;
//    List<CardInfo> forCount = new ArrayList<>();

    public List<Card> countAll() {
        return cardRepository.findAll();
    }

    public List<Card> countByTypes(String cardType) {
        return cardRepository.findByCardType(cardType);
    }

    public List<Card> countByTypesAndBenefits(String cardType, List<String> benefitList) {
        List<Card> byCardTypes = cardRepository.findByCardType(cardType);
        List<Card> forCount = new ArrayList<>();
        for (int i = 0; i < benefitList.size(); i++) {
            List<CardBenefits> byBenefitTitles = cardBenefitsRepository.findByBenefitTitle(benefitList.get(i));
            for (CardBenefits byBenefitTitle : byBenefitTitles) {
                for (Card byCardType : byCardTypes) {
                    if (byBenefitTitle.getId() == byCardType.getId()) {
                        forCount.add(byCardType);
                    }
                }
            }
        }

        return forCount;
    }


}
