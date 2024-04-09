package project.local.controller.card;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.local.dto.BillsDTO;
import project.local.dto.CardDetailsDTO;
import project.local.dto.SearchDTO;
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
            forCount = cardService.countAll();

        } else if (!cardType.isEmpty() && benefits.isEmpty()) {
            forCount = cardService.countByTypes(cardType);

        } else if (!cardType.isEmpty() && !benefits.isEmpty()) {
            forCount = cardService.countByTypesAndBenefits(cardType, benefitList);
        }

        return forCount.size();
    }

    @PostMapping
    public List<CardDetailsDTO> cardDetails(@RequestBody SearchDTO searchDTO) {
        return cardService.findCardDetails(searchDTO);
    }
}
