package project.local.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.dto.mypage.*;
import project.local.entity.cardInfo.Card;
import project.local.entity.cardInfo.SubscriptionBenefits;
import project.local.entity.userInfo.User;
import project.local.repository.CardRepository;
import project.local.repository.SubscriptionRepository;
import project.local.repository.UserRepository;
import project.local.service.inter.MyPageService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MyDataServiceImpl implements MyPageService {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final SubscriptionRepository subscriptionRepository;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 회원 찾고 그 회원의 보유 카드 찾기
    public Long findUser(Long id) {
        User userInfo = userRepository.findById(id).orElse(null);
        Long userId = userInfo.getId();

        return userId;
    }

    public List<CardsDTO> requestCards(Long id) throws Exception {
        String userId = String.valueOf(id);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://192.168.0.65:8081/v2/card/cards"))
                .header("userId", userId) // 헤더에 userId 추가
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // JSON 응답을 CardsDTO 리스트로 변환
        List<CardsDTO> cards = objectMapper.readValue(response.body(), new TypeReference<List<CardsDTO>>() {
        });

        return cards;
    }

    public List<BillsDTO> requestBills(Long id) throws Exception {
        String userId = String.valueOf(id);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://192.168.0.65:8081/v2/card/bills"))
                .header("userId", userId) // 헤더에 userId 추가
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // JSON 응답을 BillsDetailDTO 리스트로 변환
        List<BillsDTO> bills = objectMapper.readValue(response.body(), new TypeReference<List<BillsDTO>>() {
        });

        return bills;
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

    public List<BillsDetailsDTO> requestBillsDetails(Long id, int month) throws Exception {
        String userId = String.valueOf(id);

        // month 파라미터를 URL 쿼리 파라미터로 추가
        String url = String.format("http://192.168.0.65:8081/v2/card/bills/detail?month=%d", month);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("userId", userId) // 헤더에 userId 추가
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // JSON 응답을 BillsDetailDTO 리스트로 변환
        List<BillsDetailsDTO> billsDetails = objectMapper.readValue(response.body(), new TypeReference<List<BillsDetailsDTO>>() {
        });

        return billsDetails;
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

    public List<String> findMyCardLists(List<CardsDTO> cards) {

        List<String> images = new ArrayList<>();
        for (CardsDTO card : cards) {
            Card byId = cardRepository.findById(card.getCardId()).orElse(null);
            images.add(byId.getCardImage());
        }
        return images;
    }

    public List<MySubscriptionDTO> findMySubscription() {
        return null;
    }

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
