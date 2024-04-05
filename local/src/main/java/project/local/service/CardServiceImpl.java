package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.entity.BenefitSummary;
import project.local.entity.CardInfo;
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

    public List<CardInfo> countA() {
        return cardInfoRepository.findAll();
    }

    public List<CardInfo> countB(String cardType) {
        List<CardInfo> byCardTypes = cardInfoRepository.findByCardType(cardType);

        return byCardTypes;
    }

    public List<CardInfo> countC(String cardType, List<String> benefitList) {
        List<CardInfo> byCardTypes = cardInfoRepository.findByCardType(cardType);
        List<CardInfo> forCount = new ArrayList<>();
        for (int i = 0; i < benefitList.size(); i++) {
            List<BenefitSummary> byBenefitTitles = benefitSummaryRepository.findByBenefitTitle(benefitList.get(i));
            for (BenefitSummary byBenefitTitle : byBenefitTitles) {
                for (CardInfo byCardType : byCardTypes) {
                    if (byBenefitTitle.getId() == byCardType.getId()) {
                        forCount.add(byCardType);
                    }
                }
            }
        }

        return forCount;
    }


}
