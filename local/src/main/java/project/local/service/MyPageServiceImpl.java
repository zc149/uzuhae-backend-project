package project.local.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import project.local.dto.*;
import project.local.entity.cardInfo.Card;
import project.local.entity.userInfo.User;
import project.local.repository.CardRepository;
import project.local.repository.UserRepository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;

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
                .uri(URI.create("http://192.168.0.65:8080/v2/card/cards"))
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
                .uri(URI.create("http://192.168.0.65:8080/v2/card/bills"))
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
        String url = String.format("http://192.168.0.65:8080/v2/card/bills/detail?month=%d", month);

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

    public MypageDTO responseMypage(List<CardsDTO> cards, int totalAmount, List<BillsDetailsDTO> billsDetails) {
        int restaurant = 0;
        int cafe = 0;
        int gasStation = 0;
        int shopping = 0;
        int convenienceStore = 0;
        int supermarket = 0;
        int movie = 0;
        int etc = 0;
        List<String> images = new ArrayList<>();

        for (CardsDTO card : cards) {
            Card byId = cardRepository.findById(card.getCardId()).orElse(null);
            images.add(byId.getCardImage());
        }

        for (BillsDetailsDTO billsDetail : billsDetails) {
            // 업종에 다라서 분류하고 해당 업종이면 +
            if (billsDetail.getMerchantType().equals("음식점")) {
                restaurant += billsDetail.getPaidAmt();

            } else if (billsDetail.getMerchantType().equals("대형마트")) {
                supermarket += billsDetail.getPaidAmt();

            } else if (billsDetail.getMerchantType().equals("편의점")) {
                convenienceStore += billsDetail.getPaidAmt();

            } else if (billsDetail.getMerchantType().equals("주유소")) {
                gasStation += billsDetail.getPaidAmt();

            } else if (billsDetail.getMerchantType().equals("카페")) {
                cafe += billsDetail.getPaidAmt();

            } else if (billsDetail.getMerchantType().equals("영화")) {
                movie += billsDetail.getPaidAmt();

            } else if (billsDetail.getMerchantType().equals("쇼핑")) {
                shopping += billsDetail.getPaidAmt();

            } else {
                etc += billsDetail.getPaidAmt();
            }

        }
        return MypageDTO.builder()
                .totalAmount(totalAmount)
                .restaurant(restaurant)
                .cafe(cafe)
                .gasStation(gasStation)
                .shopping(shopping)
                .convenienceStore(convenienceStore)
                .supermarket(supermarket)
                .movie(movie)
                .etc(etc)
                .images(images)
                .build();
    }
}
