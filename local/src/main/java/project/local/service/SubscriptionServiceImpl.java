package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.dto.mypage.CardsDTO;
import project.local.dto.mypage.RecommendedSubDTO;
import project.local.dto.mypage.SpentAmountDTO;
import project.local.entity.cardInfo.SubscriptionBenefits;
import project.local.repository.CardRepository;
import project.local.repository.SubscriptionRepository;
import project.local.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final SubscriptionRepository subscriptionRepository;

    // 내 카드 리스트에서 id가져와서 으데 카드산지 뽑아서 구독 서비스 중 해당 카드사만 ㄱㄱ
    public List<RecommendedSubDTO> recommendSub(SpentAmountDTO spentAmountDTO, List<CardsDTO> cards) {
        String maxCategory = spentAmountDTO.getMaxCategory();
        List<SubscriptionBenefits> subscriptionLists = subscriptionRepository.findByCategory(maxCategory);
        List<RecommendedSubDTO> recommendedSubDTOs = new ArrayList<>();

        for (SubscriptionBenefits subscriptionList : subscriptionLists) {
            for (CardsDTO card : cards) {
                if (card.getCardBrand().equals(subscriptionList.getCardCompany().getId())) {
                    recommendedSubDTOs.add(RecommendedSubDTO.builder()
                            .category(subscriptionList.getCategory())
                            .title(subscriptionList.getTitle())
                            .summary(subscriptionList.getSummary())
                            .fee(subscriptionList.getFee())
//                            .cardCompanyImage(subscriptionList.getImage())
//                            .companyImage(subscriptionList.getCardCompany().getImage())
                            .build());
                } else continue;
            }
        }
        return recommendedSubDTOs;
    }
}
