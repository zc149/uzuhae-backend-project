package project.local.controller.card;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.local.entity.cardInfo.Card;
import project.local.service.CardServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/woorieodi/card")
@RequiredArgsConstructor
public class CardController {

    private final CardServiceImpl cardService;

    @GetMapping
    public int findCards(
            @RequestParam(value = "type", required = false, defaultValue = "") String cardType,
            @RequestParam(value = "benefits", required = false, defaultValue = "") String benefits
    ) {
        // benefits 파라미터를 콤마로 구분된 리스트로 변환
        List<String> benefitList = Arrays.asList(benefits.split(","));
//        List<String> benefitList = benefits.isEmpty() ? Collections.emptyList() : Arrays.asList(benefits.split(","));

        List<Card> forCount = new ArrayList<>();
        if (cardType.isEmpty() && benefits.isEmpty()) {
            forCount = cardService.countA();

        } else if (!cardType.isEmpty() && benefits.isEmpty()) {
            forCount = cardService.countB(cardType);

        } else if (!cardType.isEmpty() && !benefits.isEmpty()) {
            forCount = cardService.countC(cardType, benefitList);
        }

        return forCount.size();
    }
}
