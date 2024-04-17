package project.local.controller.carddetail;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.local.dto.CardDetailDTO.CardDetailDTO;
import project.local.dto.CardDetailDTO.CardDetailRequestDTO;
import project.local.service.carddetail.CardDetailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class CardDetailController {

    private final CardDetailService cardDetailService;
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/carddetail")
    public List<CardDetailDTO> processCardDetail(@RequestBody CardDetailRequestDTO[] cardDetailRequestDTOS) {

        ArrayList<CardDetailDTO> list = new ArrayList<>();
        for (CardDetailRequestDTO x : cardDetailRequestDTOS) list.add(cardDetailService.findById(x.getCardId()));

        return list;

    }
}
