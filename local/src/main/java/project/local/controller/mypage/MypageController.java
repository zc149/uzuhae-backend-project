package project.local.controller.mypage;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import project.local.dto.local.LocalCardDTO;
import project.local.dto.loginAndSingUp.CustomUserDetails;
import project.local.dto.loginAndSingUp.UserDTO;
import project.local.dto.mydata.BillsDTO;
import project.local.dto.mydata.BillsDetailsDTO;
import project.local.dto.mydata.CardsDTO;
import project.local.dto.mypage.MypageDTO;
import project.local.dto.mypage.SpentAmountDTO;
import project.local.dto.mypage.TimeAndTotalAmountDTO;
import project.local.entity.userInfo.AnnualDiscount;
import project.local.entity.userInfo.User;
import project.local.service.AnnualDiscountService;
import project.local.service.MyDataServiceImpl;
import project.local.service.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/universe/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MyDataServiceImpl myDataService;
    private final UserServiceImpl userService;
    private final AnnualDiscountService annualDiscountService;
    LocalDate now = LocalDate.now();

    @GetMapping
    public ResponseEntity<?> getMypageData(HttpSession session) throws IOException, InterruptedException {
        // 세션에서 사용자 정보를 가져옵니다.
        CustomUserDetails sessionUser = (CustomUserDetails) session.getAttribute("USER");

        // 세션에서 사용자 ID를 추출합니다.
        Long userId = Long.valueOf(sessionUser.getUsername());

        List<CardsDTO> cards = myDataService.requestCards(userId);
        List<BillsDTO> bills = myDataService.requestBills(userId);
        TimeAndTotalAmountDTO timeAndTotalAmountDTO = userService.getTimeAndTotalAmount(bills, LocalDate.now());
        List<BillsDetailsDTO> billsDetails = myDataService.requestBillsDetails(userId, timeAndTotalAmountDTO.getMonth());
        List<LocalCardDTO> myCards = userService.findMyCardLists(cards);
        SpentAmountDTO spentAmount = userService.findSpentAmount(billsDetails);
        String categoryCodeFromValue = userService.getCategoryCodeFromValue(spentAmount.getMaxCategoryValue());
        AnnualDiscount annualDiscount = annualDiscountService.findById(userId);
        User user = userService.findById(userId);

        MypageDTO myPageDTO = MypageDTO.builder()
                .timeAndTotalAmountDTO(timeAndTotalAmountDTO)
                .spentAmountDTO(spentAmount)
                .myCards(myCards)
                .maxCategoryCode(categoryCodeFromValue)
                .annualDiscount(annualDiscount)
                .user(user)
                .build();

        return ResponseEntity.ok(myPageDTO);
    }

    @GetMapping("/update")
    public ResponseEntity<UserDTO> findForUpdate(HttpSession session) {

        CustomUserDetails sessionUser = (CustomUserDetails) session.getAttribute("USER");
        Long userId = Long.valueOf(sessionUser.getUsername());

        UserDTO userDTO = userService.findForUpdate(userId);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
    }
}