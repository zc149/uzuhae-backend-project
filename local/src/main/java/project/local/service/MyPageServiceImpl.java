package project.local.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.dto.CardsDTO;
import project.local.dto.ChargeDetailListDTO;
import project.local.dto.MypageDTO;
import project.local.entity.cardInfo.Card;
import project.local.entity.userInfo.User;
import project.local.repository.mypage.CardRepository;
import project.local.repository.mypage.UserRepository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    List<ChargeDetailListDTO> billsDetails;
    List<CardsDTO> cardInfos;

    // 회원 찾고 그 회원의 보유 카드 찾기
    public String findUser(int id) {
        User userInfo = userRepository.findById(id).orElse(null);
        String userId = String.valueOf(userInfo.getId());

        return userId;
    }

    public List<ChargeDetailListDTO> reqeustCharges(String userId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://192.168.0.65:8080/v2/card/bills/detail"))
                .header("userId", userId) // 헤더에 userId 추가
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // JSON 응답을 BillsDetailDTO 리스트로 변환
        billsDetails = objectMapper.readValue(response.body(), new TypeReference<List<ChargeDetailListDTO>>() {
        });

        return billsDetails;
    }


    public List<CardsDTO> reqeustCards(String userId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://192.168.0.65:8080/v2/card/cards"))
                .header("userId", userId) // 헤더에 userId 추가
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // JSON 응답을 BillsDetailDTO 리스트로 변환
        cardInfos = objectMapper.readValue(response.body(), new TypeReference<List<CardsDTO>>() {
        });

        return cardInfos;
    }

    public MypageDTO responseMypage(List<ChargeDetailListDTO> billsDetails, List<CardsDTO> cardInfos) {
        int totalAmount = 0;
        int restaurant = 0;
        int cafe = 0;
        int gasStation = 0;
        int clothes = 0;
        int etc = 0;
        List<String> images = new ArrayList<>();

        for (ChargeDetailListDTO billsDetail : billsDetails) {
            // 업종에 따라서 분류하고 해당 업종이면 +
            if (billsDetail.getClassiBusinessBig().equals("음식점")) {
                restaurant += Integer.parseInt(billsDetail.getUsePay());
            } else if (billsDetail.getUsePay().equals("카페")) {
                cafe += Integer.parseInt(billsDetail.getUsePay());
            } else if (billsDetail.getClassiBusinessBig().equals("주유소")) {
                gasStation += Integer.parseInt(billsDetail.getUsePay());
            } else {
                etc += Integer.parseInt(billsDetail.getUsePay());
            }
        }
        totalAmount = restaurant + cafe + gasStation + etc;

        for (CardsDTO cardInfo : cardInfos) {
            Card localCardInfo = cardRepository.findById(cardInfo.getId()).orElse(null);
            images.add(localCardInfo.getCardImage());
        }

        MypageDTO mypageDTO = new MypageDTO(totalAmount, restaurant, cafe, gasStation, etc, images);

        return mypageDTO;
    }
}
