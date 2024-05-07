package project.local.controller.map;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.local.dto.local.LocalCardBenefitsDTO;
import project.local.dto.loginAndSingUp.CustomUserDetails;
import project.local.dto.mydata.CardsDTO;
import project.local.dto.map.StoreDTO;
import project.local.service.MapServiceImpl;
import project.local.service.MyDataServiceImpl;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/universe/map")
public class MapBenefitsController {

    private final MapServiceImpl mapService;
    private final MyDataServiceImpl myDataService;

    @GetMapping
    public ResponseEntity<?> getCardBenefitsByCategory(@RequestParam String category, HttpSession session) throws IOException, InterruptedException {

        CustomUserDetails sessionUser = (CustomUserDetails) session.getAttribute("USER");

        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자 정보가 세션에 없습니다.");
        }

        Long userId = Long.valueOf(sessionUser.getUsername());
        List<CardsDTO> cardsDTOS = myDataService.requestCards(userId);
        String categoryByCode = mapService.getCategoryByCode(category);
        List<LocalCardBenefitsDTO> localCardBenefitsDTOs = mapService.findCardByCategory(categoryByCode, cardsDTOS);

        return ResponseEntity.ok(localCardBenefitsDTOs);
    }


    @GetMapping("/store")
    public List<StoreDTO> findAllStore() {
        List<StoreDTO> storeDTOS = mapService.findAllStore();
        return storeDTOS;
    }
}