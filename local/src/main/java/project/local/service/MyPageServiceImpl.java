package project.local.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.dto.CardInfoListDTO;
import project.local.dto.ChargeDetailListDTO;
import project.local.dto.MypageDTO;
import project.local.entity.CardInfo;
import project.local.entity.UserInfo;
import project.local.repository.mypage.CardInfoRepository;
import project.local.repository.mypage.UserInfoRepository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl {

    private final UserInfoRepository userInfoRepository;
    private final CardInfoRepository cardInfoRepository;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    List<ChargeDetailListDTO> billsDetails;
    List<CardInfoListDTO> cardInfos;

    // 회원 찾고 그 회원의 보유 카드 찾기
    public String findUser(int id) {
//        Optional<UserInfo> byId = userInfoRepository.findById(id);
        UserInfo userInfo = userInfoRepository.findById(id).orElse(null);
//        List<UserCard> byUserInfoId = userCardRepository.findByUserInfo_Id(userInfo.getId());
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


    public List<CardInfoListDTO> reqeustCards(String userId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://192.168.0.65:8080/v2/card/cards"))
                .header("userId", userId) // 헤더에 userId 추가
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // JSON 응답을 BillsDetailDTO 리스트로 변환
        cardInfos = objectMapper.readValue(response.body(), new TypeReference<List<CardInfoListDTO>>() {
        });

        return cardInfos;
    }

    public MypageDTO responseMypage(List<ChargeDetailListDTO> billsDetails, List<CardInfoListDTO> cardInfos) {
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

        for (CardInfoListDTO cardInfo : cardInfos) {
            CardInfo localCardInfo = cardInfoRepository.findById(cardInfo.getId()).orElse(null);
            images.add(localCardInfo.getImageURL());
        }

        MypageDTO mypageDTO = new MypageDTO(totalAmount, restaurant, cafe, gasStation, etc, images);

        return mypageDTO;
    }
}
