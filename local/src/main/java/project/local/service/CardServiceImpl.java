package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.entity.cardInfo.CardBenefits;
import project.local.entity.cardInfo.Card;
import project.local.repository.mypage.BenefitSummaryRepository;
import project.local.repository.mypage.CardInfoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl {

    private final CardInfoRepository cardInfoRepository;
    private final BenefitSummaryRepository benefitSummaryRepository;
//    List<CardInfo> forCount = new ArrayList<>();

    public List<Card> countA() {
        return cardInfoRepository.findAll();
    }

    public List<Card> countB(String cardType) {
        List<Card> byCardTypes = cardInfoRepository.findByCardType(cardType);

        return byCardTypes;
    }

    public List<Card> countC(String cardType, List<String> benefitList) {
        List<Card> byCardTypes = cardInfoRepository.findByCardType(cardType);
        List<Card> forCount = new ArrayList<>();
        for (int i = 0; i < benefitList.size(); i++) {
            List<CardBenefits> byBenefitTitles = benefitSummaryRepository.findByBenefitTitle(benefitList.get(i));
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
