package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.dto.mypage.*;
import project.local.entity.cardInfo.Card;
import project.local.entity.userInfo.User;
import project.local.repository.CardRepository;
import project.local.repository.SubscriptionRepository;
import project.local.repository.UserRepository;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final SubscriptionRepository subscriptionRepository;

    // 회원 찾고 그 회원의 보유 카드 찾기
    public Long findUser(Long id) {
        User userInfo = userRepository.findById(id).orElse(null);
        Long userId = userInfo.getId();

        return userId;
    }

    public List<String> findMyCardLists(List<CardsDTO> cards) {

        List<String> images = new ArrayList<>();
        for (CardsDTO card : cards) {
            Card byId = cardRepository.findById(card.getCardId()).orElse(null);
            images.add(byId.getCardImage());
        }
        return images;
    }

    public TimeAndTotalAmountDTO getTimeAndTotalAmount(List<BillsDTO> bills, LocalDate time) {
        String year = String.valueOf(time.getYear());
        int month = time.getMonthValue() - 1;
        String realMonth = null;

        if (month == 0) {
            realMonth = String.valueOf(12);
        } else if (month / 10 == 0) {
            realMonth = "0" + month;
        }

        String str = year + realMonth;
        TimeAndTotalAmountDTO timesAndtotalAmount = null;

        for (BillsDTO bill : bills) {
            if (String.valueOf(bill.getChargeMonth()).equals(str)) {
                timesAndtotalAmount = new TimeAndTotalAmountDTO(bill.getChargeMonth(), bill.getChargeAmt());
            }
        }
        return timesAndtotalAmount;
    }

    public SpentAmountDTO findSpentAmount(List<BillsDetailsDTO> billsDetails) {
        Map<String, Integer> categories = new HashMap<>();
        Set<String> specifiedCategories = Set.of("음식점", "카페", "주유소", "쇼핑", "편의점", "대형마트", "영화관");

        for (BillsDetailsDTO billsDetail : billsDetails) {
            String category = billsDetail.getMerchantType();
            int paidAmount = billsDetail.getPaidAmt();

            // 각 업종별로 지출 금액을 합산
            categories.merge(category, paidAmount, Integer::sum);
        }
        int etcSum = 0;

        // 가장 큰 지출을 찾기
        String maxCategory = null;
        int maxValue = 0;

        for (Map.Entry<String, Integer> entry : categories.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxCategory = entry.getKey();
            }
            if (!specifiedCategories.contains(entry.getKey())) {
                etcSum += entry.getValue();
            }
        }

        return SpentAmountDTO.builder()
                .restaurant(categories.getOrDefault("음식점", 0))
                .cafe(categories.getOrDefault("카페", 0))
                .gasStation(categories.getOrDefault("주유", 0))
                .shopping(categories.getOrDefault("쇼핑", 0))
                .convenienceStore(categories.getOrDefault("편의점", 0))
                .supermarket(categories.getOrDefault("대형마트", 0))
                .movie(categories.getOrDefault("영화관", 0))
                .etc(etcSum)
                .maxCategory(maxCategory)
                .build();
    }

}
