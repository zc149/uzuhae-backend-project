package project.local.controller.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.local.dto.mypage.*;
import project.local.service.MyDataServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/woorieodi/mypage/{userId}")
@RequiredArgsConstructor
public class MypageController {

    private final MyDataServiceImpl myPageService;
    LocalDate now = LocalDate.now();

    @GetMapping
    public MypageDTO forMypage(@PathVariable("userId") Long id) throws Exception {
        Long userId = myPageService.findUser(id);

        // id를 header에 넣어서 api 요청 -> 내 카드 리스트 반환
        List<CardsDTO> cardsDTOS = myPageService.requestCards(userId);

        // id를 header에 넣어서 api 요청 -> 내 년, 월 별 청구내역 반환
        List<BillsDTO> billsDTOS = myPageService.requestBills(userId);

        //현재시간을 기반으로 전월에 대한 데이터 뽑기 위한 준비작업
        TimeAndTotalAmountDTO dto = myPageService.getTimeAndTotalAmount(billsDTOS, now);

        // id를 header, 년월을 파라미터로 api 요청 -> 특정 달의 청구 상세내역 반환
        List<BillsDetailsDTO> billsDetailsDTOS = myPageService.requestBillsDetails(userId, dto.getMonth());

        List<String> myCardImages = myPageService.findMyCardLists(cardsDTOS);

        SpentAmountDTO spentAmount = myPageService.findSpentAmount(billsDetailsDTOS);

        myPageService.findMySubscription();

        List<RecommendedSubDTO> recommendedSubDTOS = myPageService.recommendSub(spentAmount, cardsDTOS);

        System.out.println("recommendedSubDTOS = " + recommendedSubDTOS);


        return MypageDTO.builder()
                .timeAndTotalAmountDTO(dto)
                .spentAmountDTO(spentAmount)
                .images(myCardImages)
                .recommendedSubDTO(recommendedSubDTOS)
                .mySubscriptionDTO(myPageService.findMySubscription())
                .build();

    }

}




