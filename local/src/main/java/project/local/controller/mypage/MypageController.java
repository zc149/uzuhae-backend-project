package project.local.controller.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.local.dto.CardsDTO;
import project.local.dto.ChargeDetailListDTO;
import project.local.dto.MypageDTO;
import project.local.service.MyPageServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/woorieodi/mypage/{userId}")
@RequiredArgsConstructor
public class MypageController {

    private final MyPageServiceImpl myPageService;

    @GetMapping
    public MypageDTO showSpent(@PathVariable("userId") int id) throws Exception {
        String userId = myPageService.findUser(id);

        // 이제 user객체를 가지고? id만 헤더에 넣어서? HttpClient로 Mydata api에 요청!
        List<ChargeDetailListDTO> billsDetailDTOs = myPageService.reqeustCharges(userId);
        List<CardsDTO> cardInfoListDTOs = myPageService.reqeustCards(userId);

        MypageDTO mypageDTO = myPageService.responseMypage(billsDetailDTOs, cardInfoListDTOs);

        return mypageDTO;
    }

}




